package org.firstinspires.ftc.teamcode.drive.opmode.Jerm;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

public class JeremyRoadRunnerTest extends LinearOpMode {
    Pose2d startPose = new Pose2d(0, 0, Math.toRadians(-90));

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        //vision
        //servo initialization

        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(startPose)
                .UNSTABLE_addDisplacementMarkerOffset(1, () -> {
                    //servo.setposition
                    //dr4b lift
                    //v4b out
                })
                .lineToLinearHeading(new Pose2d(10, 10, Math.toRadians(-45)))
                .build();

        TrajectorySequence traj2 = drive.trajectorySequenceBuilder(traj1.end())
                        .build();


        waitForStart();

        drive.followTrajectorySequence(traj1);
        drive.followTrajectorySequence(traj2);
    }
}
