//package org.firstinspires.ftc.teamcode.drive.autonomous;
//
//import static org.firstinspires.ftc.teamcode.drive.Constants.clawClosePos;
//import static org.firstinspires.ftc.teamcode.drive.Constants.odometerDownPos;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.trajectory.Trajectory;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Servo;
//
//import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
//
//@Autonomous
//public class BlueSideRedTerminal extends LinearOpMode {
//
//    SampleMecanumDrive drive;
//    Trajectory trajectory1;
//    Trajectory trajectory2;
//    Trajectory trajectory3;
//    Trajectory trajectory4;
//    Trajectory trajectory5;
//    Trajectory trajectory6;
//
//    Pose2d startPose;
//
//
//    public DcMotorEx frontLeft;
//    public DcMotorEx backLeft;
//    public DcMotorEx frontRight;
//    public DcMotorEx backRight;
//
//
//    public DcMotorEx horizontalSlide;
//    public DcMotorEx intake;
//
//    public Servo DR4BServo;
//
//    public CRServo duckSpinnerLeft;
//    public CRServo duckSpinnerRight;
//
//    public Servo clawServo;
//    public Servo odometerYL;
//    public Servo odometerYR;
//    public Servo odometerX;
//
//    private BNO055IMU imu;
//
//    public void runOpMode() {
//        drive = new SampleMecanumDrive(hardwareMap);
//        frontLeft = hardwareMap.get(DcMotorEx.class, "Motor FL");
//        backLeft = hardwareMap.get(DcMotorEx.class, "Motor BL");
//        frontRight = hardwareMap.get(DcMotorEx.class, "Motor FR");
//        backRight = hardwareMap.get(DcMotorEx.class, "Motor BR");
//
//        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
//        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
//
//        imu = hardwareMap.get(BNO055IMU.class, "imu");
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        imu.initialize(parameters);
//
//        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        horizontalSlide = hardwareMap.get(DcMotorEx.class, "Motor Horizontal Slide");
//        horizontalSlide.setDirection(DcMotorSimple.Direction.FORWARD);
//        horizontalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        horizontalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        intake = hardwareMap.get(DcMotorEx.class, "Motor Intake");
//        intake.setDirection(DcMotorSimple.Direction.FORWARD);
//
//        DR4BServo = hardwareMap.get(Servo.class, "Servo DR4B");
//        DR4BServo.setDirection(Servo.Direction.FORWARD);
//
//        duckSpinnerLeft = hardwareMap.get(CRServo.class, "Servo Duck Spinner Left");
//        duckSpinnerRight = hardwareMap.get(CRServo.class, "Servo Duck Spinner Right");
//        duckSpinnerLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        duckSpinnerRight.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        clawServo = hardwareMap.get(Servo.class, "Servo Claw");
//        clawServo.setDirection(Servo.Direction.FORWARD);
//        clawServo.setPosition(clawClosePos);
//
//        odometerYL = hardwareMap.get(Servo.class, "Servo Odometer YL");
//        odometerYL.setDirection(Servo.Direction.REVERSE);
//
//        odometerYR = hardwareMap.get(Servo.class, "Servo Odometer YR");
//        odometerYR.setDirection(Servo.Direction.FORWARD);
//
//        odometerX = hardwareMap.get(Servo.class, "Servo Odometer X");
//        odometerX.setDirection(Servo.Direction.REVERSE);
//
//        odometerYL.setPosition(odometerDownPos);
//        odometerYR.setPosition(odometerDownPos);
//        odometerX.setPosition(odometerDownPos);
//
//        telemetry.addData("Status", "Ready!");
//        telemetry.update();
//
//        //CODE GOES HERE
//
//        startPose = new Pose2d(35.1,60.6,Math.toRadians(270));
//
//        trajectory1 = drive.trajectoryBuilder(startPose)
//                .strafeRight(22.4)
//                .build();
//
//        trajectory2 = drive.trajectoryBuilder(trajectory1.end())
//                .forward(49.0)
//                .build();
//
//        trajectory3 = drive.trajectoryBuilder(trajectory2.end())
//                .forward(48.8)
//                .build();
//
//        trajectory4 = drive.trajectoryBuilder(trajectory3.end())
//                .back(44.2)
//                .build();
//
//        trajectory5 = drive.trajectoryBuilder(trajectory4.end())
//                .forward(44.2)
//                .build();
//
//        trajectory6 = drive.trajectoryBuilder(trajectory5.end())
//                .back(44.2)
//                .build();
//
//
//
//        //START
//        while (!isStopRequested() && !isStarted()) {
//            //CAMERA CODE
//        }
//
//        if (!isStopRequested()) {
//            //CAMERA CODE IF STATEMENTS Case1 Case2 Case3
//        }
//
//        private void Case3(SampleMecanumDrive drive) {
//            drive.setPoseEstimate(startPose);
//            drive.followTrajectory(trajectory1);
//            drive.followTrajectory(trajectory2);
//            drive.turn(49.4);
//            //PUT CONE ON JUNCTION
//            drive.turn(41.4);
//            drive.followTrajectory(trajectory3);
//            //INTAKE CONE
//            drive.followTrajectory(trajectory4);
//            drive.turn(-57.9);
//            //PUT CONE ON JUNCTION
//            drive.turn(57.9);
//            drive.followTrajectory(trajectory5);
//            //INTAKE CONE
//            drive.followTrajectory(trajectory6);
//            drive.turn(-57.9);
//            //PUT CONE ON JUNCTION
//        }
//
//    }
//    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
//        frontLeft.setZeroPowerBehavior(zeroPowerBehavior);
//        frontRight.setZeroPowerBehavior(zeroPowerBehavior);
//        backLeft.setZeroPowerBehavior(zeroPowerBehavior);
//        backRight.setZeroPowerBehavior(zeroPowerBehavior);
//    }
//}
