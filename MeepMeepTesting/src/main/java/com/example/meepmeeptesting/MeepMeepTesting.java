package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600, 240);

        RoadRunnerBotEntity Red2Park1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 13)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(35, -62, Math.toRadians(90)))
                                .lineToConstantHeading(new Vector2d(35, -34))
                                .lineToConstantHeading(new Vector2d(35, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(12, -35))
                                .build()
                );

        RoadRunnerBotEntity Red2Park2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 13)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(35, -62, Math.toRadians(90)))
                                .lineToConstantHeading(new Vector2d(35, -34))
                                .lineToConstantHeading(new Vector2d(35, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(12, -35))
                                .lineToConstantHeading(new Vector2d(35, -35))
                                .build()
                );

        RoadRunnerBotEntity Red2Park3 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 13)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(35, -62, Math.toRadians(90)))
                                .lineToConstantHeading(new Vector2d(35, -34))
                                .lineToConstantHeading(new Vector2d(35, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(55, -12))
                                .lineToConstantHeading(new Vector2d(12, -12))
                                .lineToConstantHeading(new Vector2d(12, -35))
                                .lineToConstantHeading(new Vector2d(58, -35))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(Red2Park1)
                .addEntity(Red2Park2)
                .addEntity(Red2Park3)
                .start();
    }
}