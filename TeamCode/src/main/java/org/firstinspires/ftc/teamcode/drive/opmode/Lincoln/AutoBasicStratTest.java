package org.firstinspires.ftc.teamcode.drive.opmode.Lincoln;

import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.*;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.Jerm.TeleOp_Jeremy;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class AutoBasicStratTest extends LinearOpMode {

    Servo turret = hardwareMap.get(Servo.class, "Servo Turret");

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        // Pose for left blue alliance start
        Pose2d startPose = new Pose2d(39.75, 13.25, Math.toRadians(-90));

        drive.setPoseEstimate(startPose);

        TrajectorySequence trejSeq = drive.trajectorySequenceBuilder(startPose)
                .forward(50)
                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> turret.setPosition(west))
                .build();



        waitForStart();
    }
}
