package org.firstinspires.ftc.teamcode.drive.opmode.Jerm;

import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.DR4B_MIDHIGHJUNCTION;
import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.V4B_HIGHJUNCTION;
import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.V4B_RETRACTED;
import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.V4B_SCALELEFT;
import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.grabberClose;
import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.grabberOpen;
import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.north;
import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.northeast;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.vision.AprilTagDetectionPipeline;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous
public class AutonomousRed2 extends LinearOpMode {

    public DcMotorEx motorDR4B;

    public Servo servoTurret;
    public Servo servoGrabber;
    public Servo servoV4BL, servoV4BR;

    Pose2d startPose = new Pose2d(35, -62, Math.toRadians(90));

    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    // Tag ID 1,2,3 from the 36h11 family
    int LEFT = 1;
    int MIDDLE = 2;
    int RIGHT = 3;

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode() throws InterruptedException {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        //vision
        //servo initialization

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */

        motorDR4B = hardwareMap.get(DcMotorEx.class, "Motor DR4B");
        motorDR4B.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motorDR4B.setDirection(DcMotorEx.Direction.REVERSE);
        motorDR4B.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        servoTurret = hardwareMap.get(Servo.class, "Servo Turret");
        servoGrabber = hardwareMap.get(Servo.class, "Servo Intake");
        servoGrabber.setPosition(grabberClose);
        servoV4BL = hardwareMap.get(Servo.class, "Servo V4BL");
        servoV4BR = hardwareMap.get(Servo.class, "Servo V4BR");
        servoV4BL.setDirection(Servo.Direction.REVERSE);

        servoV4BL.setPosition(V4B_RETRACTED);
        servoV4BR.setPosition(V4B_RETRACTED);
        servoTurret.setPosition(north);

        while (!isStarted() && !isStopRequested()){
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
                    if(tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                }

            }

            telemetry.update();
            sleep(20);
        }

        if(tagOfInterest != null)
        {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        }
        else
        {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        if(tagOfInterest == null || tagOfInterest.id == LEFT){
            //trajectory 1
        }else if(tagOfInterest.id == MIDDLE){
            //trajectory 2
        }else{
            //trajectory 3 (assume right)
        }

        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(startPose)
                //prep to drop
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    servoTurret.setPosition(northeast);
                    setHigh();
                })
                .lineToConstantHeading(new Vector2d(35, -34)) //move to the (4, 2) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    openGrabber();
                })
                .UNSTABLE_addTemporalMarkerOffset()
                .lineToConstantHeading(new Vector2d(35, -34)) //move to the starter stack
                .lineToConstantHeading(new Vector2d(55, -12)) //move to the starter stack
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //pick up cone 1 from starter stack
                })
                .lineToConstantHeading(new Vector2d(12, -12)) //move to the (4, 3) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //score cone 1
                })
                .lineToConstantHeading(new Vector2d(55, -12)) //move to the starter stack
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //pick up cone 2
                })
                .lineToConstantHeading(new Vector2d(12, -12)) //move to the (4, 3) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //score cone 2
                })
                .lineToConstantHeading(new Vector2d(55, -12)) //move to the starter stack
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //pick up cone 3
                })
                .lineToConstantHeading(new Vector2d(12, -12)) //move to the (4, 3) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //score cone 3
                })
                .lineToConstantHeading(new Vector2d(55, -12)) //move to the starter stack
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //pick up cone 4
                })
                .lineToConstantHeading(new Vector2d(12, -12)) //move to the (4, 3) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //score cone 4
                })
                .lineToConstantHeading(new Vector2d(55, -12)) //move to the starter stack
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //pick up cone 5
                })
                .lineToConstantHeading(new Vector2d(12, -12)) //move to the (4, 3) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //score cone 5
                })
                .build();


        drive.followTrajectorySequence(traj1);
    }

    void openGrabber() {
        servoGrabber.setPosition(grabberOpen);
    }

    void closeGrabber() {
        servoGrabber.setPosition(grabberClose);
    }

    void setLift(int position) {
        motorDR4B.setTargetPosition(position);
        motorDR4B.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorDR4B.setPower(1);
    }

    public void setV4B(double position) {
        servoV4BL.setPosition(position * V4B_SCALELEFT);
        servoV4BR.setPosition(position);
    }

    public void setHigh(){
        setLift(DR4B_MIDHIGHJUNCTION);
        setV4B(V4B_HIGHJUNCTION);
    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }
}
