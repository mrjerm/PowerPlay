package org.firstinspires.ftc.teamcode.drive;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstantsPP {

    //DR4B values
    public static int DR4B_GROUNDFLOORTURRETCLEARANCE = 0;
    public static int DR4B_LOWJUNCTION = 185;
    public static int DR4B_MIDHIGHJUNCTION = 365;
    public static double DR4B_LOWPOWER = 0.15;

    //V4B values
    public static double V4B_RETRACTED = 0.13;
    public static double V4B_VERTICAL = 0.4;
    public static double V4B_HIGHJUNCTION = 0.5;
    public static double V4B_HORIZONTAL = 0.64;
    public static double V4B_TURRETCLEARANCE = 0.72;
    public static double V4B_GROUNDJUNCTION = 0.72;
    public static double V4B_FLOOR = 0.86;
    public static double V4B_LOWMID = 0.8;
    public static double V4B_SCALELEFT = 1.01162791;

    //cone 1 is top cone, cone 5 is bottom cone
    public static double V4B_CONE1 = 0.67;
    public static double V4B_CONE2 = 0.69;
    public static double V4B_CONE3 = 0.75;
    public static double V4B_CONE4 = 0.78;
    public static double V4B_CONE5 = 0.84;

    public static List<Double> starterStack = Arrays.asList(V4B_CONE1, V4B_CONE2, V4B_CONE3, V4B_CONE4, V4B_CONE5);

    //drivetrain min/max speeds
    public static double min = 0.5;
    public static double max = 1;
    public static double speedLimit = min;
    public static double accel = 0.6;

    //grabber positions
    public static double grabberClose = 0.37;
    public static double grabberOpen = 0.20;

    //turret positions
    public static double south1 = 0.01 , southwest = 0.12, west = 0.23, northwest = 0.36, north = 0.48, northeast = 0.62, east = 0.74, southeast = 0.87, south2 = 0.98;
}
