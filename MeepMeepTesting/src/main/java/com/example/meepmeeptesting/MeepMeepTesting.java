package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600, 240);

        RoadRunnerBotEntity Red2Low = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 52.48291908330528, Math.toRadians(273.36816), Math.toRadians(273.36816), 10)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(38, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(38.5, -21, Math.toRadians(88)))
                                .lineToLinearHeading(new Pose2d(37, -12, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(60, -14, Math.toRadians(84)))
                                .lineToLinearHeading(new Pose2d(51.5, -14, Math.toRadians(86)))
                                .lineToLinearHeading(new Pose2d(60, -14, Math.toRadians(84)))
                                .lineToLinearHeading(new Pose2d(51.5, -14, Math.toRadians(86)))
                                .build()
                );
        RoadRunnerBotEntity Red1Low = new DefaultBotBuilder(meepMeep)
                .setConstraints(45, 52.48291908330528, Math.toRadians(273.36816), Math.toRadians(273.36816), 10)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-38, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-38.5, -21, Math.toRadians(92)))
                                .lineToLinearHeading(new Pose2d(-37, -12, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-60, -14, Math.toRadians(96)))
                                .lineToLinearHeading(new Pose2d(-51.5, -14, Math.toRadians(94)))
                                .lineToLinearHeading(new Pose2d(-60, -14, Math.toRadians(96)))
                                .lineToLinearHeading(new Pose2d(-51.5, -14, Math.toRadians(94)))
                                .build()
                );
        RoadRunnerBotEntity Blue2Low = new DefaultBotBuilder(meepMeep)
                .setConstraints(45, 52.48291908330528, Math.toRadians(273.36816), Math.toRadians(273.36816), 10)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-38, 62, Math.toRadians(-90)))
                                .lineToLinearHeading(new Pose2d(-38.5, 21, Math.toRadians(-92)))
                                .lineToLinearHeading(new Pose2d(-37, 12, Math.toRadians(-90)))
                                .lineToLinearHeading(new Pose2d(-60, 14, Math.toRadians(-96)))
                                .lineToLinearHeading(new Pose2d(-51.5, 14, Math.toRadians(-94)))
                                .lineToLinearHeading(new Pose2d(-60, 14, Math.toRadians(-96)))
                                .lineToLinearHeading(new Pose2d(-51.5, 14, Math.toRadians(-94)))
                                .build()
                );
        RoadRunnerBotEntity Blue1Low = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 52.48291908330528, Math.toRadians(273.36816), Math.toRadians(273.36816), 10)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(38, 62, Math.toRadians(-90)))
                                .lineToLinearHeading(new Pose2d(38.5, 21, Math.toRadians(-88)))
                                .lineToLinearHeading(new Pose2d(37, 12, Math.toRadians(-90)))
                                .lineToLinearHeading(new Pose2d(60, 14, Math.toRadians(-84)))
                                .lineToLinearHeading(new Pose2d(51.5, 14, Math.toRadians(-86)))
                                .lineToLinearHeading(new Pose2d(60, 14, Math.toRadians(-84)))
                                .lineToLinearHeading(new Pose2d(51.5, 14, Math.toRadians(-86)))
                                .build()
                );



        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(Red2Low)
                .addEntity(Red1Low)
                .addEntity(Blue2Low)
                .addEntity(Blue1Low)
                .start();
    }
}