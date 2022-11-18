package org.firstinspires.ftc.teamcode.drive.opmode.Jerm;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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


        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(startPose)
                .lineToConstantHeading(new Vector2d(35, -12)) //move to the (4, 3) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //score preload
                })
                .lineToConstantHeading(new Vector2d(55, -12)) //move to the starter stack
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //pick up cone 1 from starter stack
                })
                .lineToConstantHeading(new Vector2d(35, -12)) //move to the (4, 3) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //score cone 1
                })
                .lineToConstantHeading(new Vector2d(55, -12)) //move to the starter stack
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //pick up cone 2
                })
                .lineToConstantHeading(new Vector2d(35, -12)) //move to the (4, 3) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //score cone 2
                })
                .lineToConstantHeading(new Vector2d(55, -12)) //move to the starter stack
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //pick up cone 3
                })
                .lineToConstantHeading(new Vector2d(35, -12)) //move to the (4, 3) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //score cone 3
                })
                .lineToConstantHeading(new Vector2d(55, -12)) //move to the starter stack
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //pick up cone 4
                })
                .lineToConstantHeading(new Vector2d(35, -12)) //move to the (4, 3) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //score cone 4
                })
                .lineToConstantHeading(new Vector2d(55, -12)) //move to the starter stack
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //pick up cone 5
                })
                .lineToConstantHeading(new Vector2d(35, -12)) //move to the (4, 3) junction
                .UNSTABLE_addDisplacementMarkerOffset(0, () -> {
                    //score cone 5
                })
                .build();


        drive.followTrajectorySequence(traj1);
    }
}
