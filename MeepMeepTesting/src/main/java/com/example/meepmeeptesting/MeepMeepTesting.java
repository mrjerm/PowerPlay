package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import org.jetbrains.annotations.NotNull;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600, 240);

        TrajectoryVelocityConstraint slowVel = new TrajectoryVelocityConstraint() {
            @Override
            public double get(double v, @NotNull Pose2d pose2d, @NotNull Pose2d pose2d1, @NotNull Pose2d pose2d2) {
                return 5;
            }
        };

        TrajectoryAccelerationConstraint defaultAccel = new TrajectoryAccelerationConstraint() {
            @Override
            public double get(double v, @NotNull Pose2d pose2d, @NotNull Pose2d pose2d1, @NotNull Pose2d pose2d2) {
                return 52.48291908330528;
            }
        };

        RoadRunnerBotEntity speed = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 52.48291908330528, Math.toRadians(273.36816), Math.toRadians(273.36816), 11.58)
                .setDimensions(14.1, 14.1)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(35.6,-71.2, Math.toRadians(90))).setTangent(Math.toRadians(90))
                                //preload
                                .splineToSplineHeading(new Pose2d(36,-42.4, Math.toRadians(92)), Math.toRadians(92))

                                //curve to starter stack
                                .splineToSplineHeading(new Pose2d(36,-25.6, Math.toRadians(86)), Math.toRadians(86))
                                .splineToSplineHeading(new Pose2d(38.8,-16.8, Math.toRadians(30)), Math.toRadians(30))
                                .splineToSplineHeading(new Pose2d(45.6,-12, Math.toRadians(0)), Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(56,-12.8, Math.toRadians(0)), Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(58,-12.8, Math.toRadians(0)), Math.toRadians(0), slowVel, defaultAccel)

                                //score 1st of stack on high junction
                                .splineToSplineHeading(new Pose2d(56,-12.8, Math.toRadians(0)), Math.toRadians(-180), slowVel, defaultAccel)
                                .splineToSplineHeading(new Pose2d(42,-12.8, Math.toRadians(0)), Math.toRadians(-180))
                                .splineToSplineHeading(new Pose2d(34,-12.8, Math.toRadians(0)), Math.toRadians(177))
                                .splineToSplineHeading(new Pose2d(23.6,-12.8, Math.toRadians(0)), Math.toRadians(-180))

                                //back to starter stack
                                .splineToSplineHeading(new Pose2d(30.8,-12.8, Math.toRadians(0)), Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(40,-12.8, Math.toRadians(2)), Math.toRadians(2))
                                .splineToSplineHeading(new Pose2d(51.6,-12.8, Math.toRadians(-2)), Math.toRadians(-2))
                                .splineToSplineHeading(new Pose2d(56,-12.8, Math.toRadians(0)), Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(58,-12.8, Math.toRadians(0)), Math.toRadians(0), slowVel, defaultAccel)

                                //2nd of stack
                                .splineToSplineHeading(new Pose2d(56,-12.8, Math.toRadians(0)), Math.toRadians(-180), slowVel, defaultAccel)
                                .splineToSplineHeading(new Pose2d(42,-12.8, Math.toRadians(0)), Math.toRadians(-180))
                                .splineToSplineHeading(new Pose2d(34,-12.8, Math.toRadians(0)), Math.toRadians(177))
                                .splineToSplineHeading(new Pose2d(23.6,-12.8, Math.toRadians(0)), Math.toRadians(-180))

                                //back to starter stack
                                .splineToSplineHeading(new Pose2d(30.8,-12.8, Math.toRadians(0)), Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(40,-12.8, Math.toRadians(2)), Math.toRadians(2))
                                .splineToSplineHeading(new Pose2d(51.6,-12.8, Math.toRadians(-2)), Math.toRadians(-2))
                                .splineToSplineHeading(new Pose2d(56,-12.8, Math.toRadians(0)), Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(58,-12.8, Math.toRadians(0)), Math.toRadians(0), slowVel, defaultAccel)

                                //3rd of stack
                                .splineToSplineHeading(new Pose2d(56,-12.8, Math.toRadians(0)), Math.toRadians(-180), slowVel, defaultAccel)
                                .splineToSplineHeading(new Pose2d(42,-12.8, Math.toRadians(0)), Math.toRadians(-180))
                                .splineToSplineHeading(new Pose2d(34,-12.8, Math.toRadians(0)), Math.toRadians(177))
                                .splineToSplineHeading(new Pose2d(23.6,-12.8, Math.toRadians(0)), Math.toRadians(-180))

                                //back to starter stack
                                .splineToSplineHeading(new Pose2d(30.8,-12.8, Math.toRadians(0)), Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(40,-12.8, Math.toRadians(2)), Math.toRadians(2))
                                .splineToSplineHeading(new Pose2d(51.6,-12.8, Math.toRadians(-2)), Math.toRadians(-2))
                                .splineToSplineHeading(new Pose2d(56,-12.8, Math.toRadians(0)), Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(58,-12.8, Math.toRadians(0)), Math.toRadians(0), slowVel, defaultAccel)

                                //4th of stack
                                .splineToSplineHeading(new Pose2d(56,-12.8, Math.toRadians(0)), Math.toRadians(-180), slowVel, defaultAccel)
                                .splineToSplineHeading(new Pose2d(42,-12.8, Math.toRadians(0)), Math.toRadians(-180))
                                .splineToSplineHeading(new Pose2d(34,-12.8, Math.toRadians(0)), Math.toRadians(177))
                                .splineToSplineHeading(new Pose2d(23.6,-12.8, Math.toRadians(0)), Math.toRadians(-180))

                                //back to starter stack
                                .splineToSplineHeading(new Pose2d(30.8,-12.8, Math.toRadians(0)), Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(40,-12.8, Math.toRadians(2)), Math.toRadians(2))
                                .splineToSplineHeading(new Pose2d(51.6,-12.8, Math.toRadians(-2)), Math.toRadians(-2))
                                .splineToSplineHeading(new Pose2d(56,-12.8, Math.toRadians(0)), Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(58,-12.8, Math.toRadians(0)), Math.toRadians(0), slowVel, defaultAccel)

                                //5th of stack
                                .splineToSplineHeading(new Pose2d(56,-12.8, Math.toRadians(0)), Math.toRadians(-180), slowVel, defaultAccel)
                                .splineToSplineHeading(new Pose2d(42,-12.8, Math.toRadians(0)), Math.toRadians(-180))
                                .splineToSplineHeading(new Pose2d(34,-12.8, Math.toRadians(0)), Math.toRadians(177))
                                .splineToSplineHeading(new Pose2d(23.6,-12.8, Math.toRadians(0)), Math.toRadians(-180))

                                //park

                .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(speed)
                .start();
    }
}