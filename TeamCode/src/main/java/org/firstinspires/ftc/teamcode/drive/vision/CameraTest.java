//package org.firstinspires.ftc.teamcode.drive.vision;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//@Disabled
//@Autonomous
//public class CameraTest extends LinearOpMode{
//    SignalUtil cameraUtil;
//    SignalDetection2.SignalPosition ImagePosition;
//
//    @Override
//    public void runOpMode () {
//        cameraUtil = new SignalUtil(hardwareMap, "Webcam", telemetry);
//        cameraUtil.init();
//
//        waitForStart();
//        while(opModeIsActive()) {
//            ImagePosition = cameraUtil.getSignalPosition();
//            telemetry.addData("LOCATION ", ImagePosition);
//        }
//    }
//}
