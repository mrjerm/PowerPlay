//
//package org.firstinspires.ftc.teamcode.drive.opmode.Autonomous;
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//
//import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
//
//
//@Config
//@Autonomous(group = "drive")
//public class StrafeTest extends LinearOpMode {
//    public static double DISTANCE = 60;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//telemetry= new MultipleTelemetry(telemetry,FtcDashboard.getInstance().getTelemetry());
//SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap) ;
//    TrajectoryBuilder trajectory = drive.trajectoryBuilder(new Pose2d());
//
//    waitForStart();
//
//    Pose2d poseEstimate = drive.getPoseEstimate();
//        telemetry.addData("finalX", ((Pose2d) poseEstimate).getX());
//        telemetry.addData("finalY", ((Pose2d) poseEstimate).getY());
//        telemetry.addData("finalHeading", ((Pose2d) poseEstimate.getHeading()));
//        telemetry.update();
//    }}