package org.firstinspires.ftc.teamcode.drive.opmode.Jerm;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class RingPipeline extends OpenCvPipeline {

    //CONStANT
    final int x0 = 154;
    final int x1 = 166;
    final int y0 = 0;
    final int y1 = 12;
    final int y2 = 24;
    final int y3 = 36;
    final int y4 = 48;
    final int y5 = 60;
    final int y6 = 72;
    final int y7 = 84;
    final int y8 = 96;
    final int y9 = 108;
    final int y10 = 120;
    final int y11 = 132;
    final int y12 = 144;
    final int y13 = 156;
    final int y14 = 168;
    final int y15 = 180;
    final int y16 = 192;
    final int y17 = 204;
    final int y18 = 216;
    final int y19 = 228;
    final int y20 = 240;

    //Working Mat variables
    Mat YCrCb = new Mat(); //Store the whole YCrCb channel
    Mat Cb = new Mat(); //Store the Cb Channel (part from YCrCb)
    Mat tholdMat = new Mat(); //Store the threshold

    //Drawing variables
    Scalar GRAY = new Scalar(220, 220, 220); //RGB values for gray
    Scalar BATTERY_CHARGED_BLUE = new Scalar(155, 12, 12); //RGB values for green

    //Variables that will store the results of our pipeline
    public static int R1;
    public static int R2;
    public static int R3;
    public static int R4;
    public static int R5;
    public static int R6;
    public static int R7;
    public static int R8;
    public static int R9;
    public static int R10;
    public static int R11;
    public static int R12;
    public static int R13;
    public static int R14;
    public static int R15;
    public static int R16;
    public static int R17;
    public static int R18;
    public static int R19;
    public static int R20;

    //Space which we will analyze data
    public Point REGION1_TOPLEFT = new Point(x0, y0);
    public Point REGION1_BOTTOMRIGHT = new Point(x1, y1);
    public Point REGION2_TOPLEFT = new Point(x0, y1);
    public Point REGION2_BOTTOMRIGHT = new Point(x1, y2);
    public Point REGION3_TOPLEFT = new Point(x0, y2);
    public Point REGION3_BOTTOMRIGHT = new Point(x1, y3);
    public Point REGION4_TOPLEFT = new Point(x0, y3);
    public Point REGION4_BOTTOMRIGHT = new Point(x1, y4);
    public Point REGION5_TOPLEFT = new Point(x0, y4);
    public Point REGION5_BOTTOMRIGHT = new Point(x1, y5);
    public Point REGION6_TOPLEFT = new Point(x0, y5);
    public Point REGION6_BOTTOMRIGHT = new Point(x1, y6);
    public Point REGION7_TOPLEFT = new Point(x0, y6);
    public Point REGION7_BOTTOMRIGHT = new Point(x1, y7);
    public Point REGION8_TOPLEFT = new Point(x0, y7);
    public Point REGION8_BOTTOMRIGHT = new Point(x1, y8);
    public Point REGION9_TOPLEFT = new Point(x0, y8);
    public Point REGION9_BOTTOMRIGHT = new Point(x1, y9);
    public Point REGION10_TOPLEFT = new Point(x0, y9);
    public Point REGION10_BOTTOMRIGHT = new Point(x1, y10);
    public Point REGION11_TOPLEFT = new Point(x0, y10);
    public Point REGION11_BOTTOMRIGHT = new Point(x1, y11);
    public Point REGION12_TOPLEFT = new Point(x0, y11);
    public Point REGION12_BOTTOMRIGHT = new Point(x1, y12);
    public Point REGION13_TOPLEFT = new Point(x0, y12);
    public Point REGION13_BOTTOMRIGHT = new Point(x1, y13);
    public Point REGION14_TOPLEFT = new Point(x0, y13);
    public Point REGION14_BOTTOMRIGHT = new Point(x1, y14);
    public Point REGION15_TOPLEFT = new Point(x0, y14);
    public Point REGION15_BOTTOMRIGHT = new Point(x1, y15);
    public Point REGION16_TOPLEFT = new Point(x0, y15);
    public Point REGION16_BOTTOMRIGHT = new Point(x1, y16);
    public Point REGION17_TOPLEFT = new Point(x0, y16);
    public Point REGION17_BOTTOMRIGHT = new Point(x1, y17);
    public Point REGION18_TOPLEFT = new Point(x0, y17);
    public Point REGION18_BOTTOMRIGHT = new Point(x1, y18);
    public Point REGION19_TOPLEFT = new Point(x0, y18);
    public Point REGION19_BOTTOMRIGHT = new Point(x1, y19);
    public Point REGION20_TOPLEFT = new Point(x0, y19);
    public Point REGION20_BOTTOMRIGHT = new Point(x1, y20);


    @Override
    public Mat processFrame(Mat input) {

        //Img processing
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_BGR2YCrCb);
        Core.extractChannel(YCrCb, Cb, 2);
        Imgproc.threshold(Cb, tholdMat, 150, 255, Imgproc.THRESH_BINARY_INV);

        //Drawing Points
        int P1_X = (int) ((REGION1_TOPLEFT.x + REGION1_BOTTOMRIGHT.x) / 2);
        int P1_Y = (int) ((REGION1_TOPLEFT.y + REGION1_BOTTOMRIGHT.y) / 2);
        int P2_X = (int) ((REGION2_TOPLEFT.x + REGION2_BOTTOMRIGHT.x) / 2);
        int P2_Y = (int) ((REGION2_TOPLEFT.y + REGION2_BOTTOMRIGHT.y) / 2);
        int P3_X = (int) ((REGION3_TOPLEFT.x + REGION3_BOTTOMRIGHT.x) / 2);
        int P3_Y = (int) ((REGION3_TOPLEFT.y + REGION3_BOTTOMRIGHT.y) / 2);
        int P4_X = (int) ((REGION4_TOPLEFT.x + REGION4_BOTTOMRIGHT.x) / 2);
        int P4_Y = (int) ((REGION4_TOPLEFT.y + REGION4_BOTTOMRIGHT.y) / 2);
        int P5_X = (int) ((REGION5_TOPLEFT.x + REGION5_BOTTOMRIGHT.x) / 2);
        int P5_Y = (int) ((REGION5_TOPLEFT.y + REGION5_BOTTOMRIGHT.y) / 2);
        int P6_X = (int) ((REGION6_TOPLEFT.x + REGION6_BOTTOMRIGHT.x) / 2);
        int P6_Y = (int) ((REGION6_TOPLEFT.y + REGION6_BOTTOMRIGHT.y) / 2);
        int P7_X = (int) ((REGION7_TOPLEFT.x + REGION7_BOTTOMRIGHT.x) / 2);
        int P7_Y = (int) ((REGION7_TOPLEFT.y + REGION7_BOTTOMRIGHT.y) / 2);
        int P8_X = (int) ((REGION8_TOPLEFT.x + REGION8_BOTTOMRIGHT.x) / 2);
        int P8_Y = (int) ((REGION8_TOPLEFT.y + REGION8_BOTTOMRIGHT.y) / 2);
        int P9_X = (int) ((REGION9_TOPLEFT.x + REGION9_BOTTOMRIGHT.x) / 2);
        int P9_Y = (int) ((REGION9_TOPLEFT.y + REGION9_BOTTOMRIGHT.y) / 2);
        int P10_X = (int) ((REGION10_TOPLEFT.x + REGION10_BOTTOMRIGHT.x) / 2);
        int P10_Y = (int) ((REGION10_TOPLEFT.y + REGION10_BOTTOMRIGHT.y) / 2);
        int P11_X = (int) ((REGION11_TOPLEFT.x + REGION11_BOTTOMRIGHT.x) / 2);
        int P11_Y = (int) ((REGION11_TOPLEFT.y + REGION11_BOTTOMRIGHT.y) / 2);
        int P12_X = (int) ((REGION12_TOPLEFT.x + REGION12_BOTTOMRIGHT.x) / 2);
        int P12_Y = (int) ((REGION12_TOPLEFT.y + REGION12_BOTTOMRIGHT.y) / 2);
        int P13_X = (int) ((REGION13_TOPLEFT.x + REGION13_BOTTOMRIGHT.x) / 2);
        int P13_Y = (int) ((REGION13_TOPLEFT.y + REGION13_BOTTOMRIGHT.y) / 2);
        int P14_X = (int) ((REGION14_TOPLEFT.x + REGION14_BOTTOMRIGHT.x) / 2);
        int P14_Y = (int) ((REGION14_TOPLEFT.y + REGION14_BOTTOMRIGHT.y) / 2);
        int P15_X = (int) ((REGION15_TOPLEFT.x + REGION15_BOTTOMRIGHT.x) / 2);
        int P15_Y = (int) ((REGION15_TOPLEFT.y + REGION15_BOTTOMRIGHT.y) / 2);
        int P16_X = (int) ((REGION16_TOPLEFT.x + REGION16_BOTTOMRIGHT.x) / 2);
        int P16_Y = (int) ((REGION16_TOPLEFT.y + REGION16_BOTTOMRIGHT.y) / 2);
        int P17_X = (int) ((REGION17_TOPLEFT.x + REGION17_BOTTOMRIGHT.x) / 2);
        int P17_Y = (int) ((REGION17_TOPLEFT.y + REGION17_BOTTOMRIGHT.y) / 2);
        int P18_X = (int) ((REGION18_TOPLEFT.x + REGION18_BOTTOMRIGHT.x) / 2);
        int P18_Y = (int) ((REGION18_TOPLEFT.y + REGION18_BOTTOMRIGHT.y) / 2);
        int P19_X = (int) ((REGION19_TOPLEFT.x + REGION19_BOTTOMRIGHT.x) / 2);
        int P19_Y = (int) ((REGION19_TOPLEFT.y + REGION19_BOTTOMRIGHT.y) / 2);
        int P20_X = (int) ((REGION20_TOPLEFT.x + REGION20_BOTTOMRIGHT.x) / 2);
        int P20_Y = (int) ((REGION20_TOPLEFT.y + REGION20_BOTTOMRIGHT.y) / 2);


        double[] P1_PV = tholdMat.get(P1_Y, P1_X);
        double[] P2_PV = tholdMat.get(P2_Y, P2_X);
        double[] P3_PV = tholdMat.get(P3_Y, P3_X);
        double[] P4_PV = tholdMat.get(P4_Y, P4_X);
        double[] P5_PV = tholdMat.get(P5_Y, P5_X);
        double[] P6_PV = tholdMat.get(P6_Y, P6_X);
        double[] P7_PV = tholdMat.get(P7_Y, P7_X);
        double[] P8_PV = tholdMat.get(P8_Y, P8_X);
        double[] P9_PV = tholdMat.get(P9_Y, P9_X);
        double[] P10_PV = tholdMat.get(P10_Y, P10_X);
        double[] P11_PV = tholdMat.get(P11_Y, P11_X);
        double[] P12_PV = tholdMat.get(P12_Y, P12_X);
        double[] P13_PV = tholdMat.get(P13_Y, P13_X);
        double[] P14_PV = tholdMat.get(P14_Y, P14_X);
        double[] P15_PV = tholdMat.get(P15_Y, P15_X);
        double[] P16_PV = tholdMat.get(P16_Y, P16_X);
        double[] P17_PV = tholdMat.get(P17_Y, P17_X);
        double[] P18_PV = tholdMat.get(P18_Y, P18_X);
        double[] P19_PV = tholdMat.get(P19_Y, P19_X);
        double[] P20_PV = tholdMat.get(P20_Y, P20_X);

        R1  = (int) P1_PV [0];
        R2  = (int) P2_PV [0];
        R3  = (int) P3_PV [0];
        R4  = (int) P4_PV [0];
        R5 = (int) P5_PV [0];
        R6 = (int) P6_PV [0];
        R7 = (int) P7_PV [0];
        R8 = (int) P8_PV [0];
        R9 = (int) P9_PV [0];
        R10 = (int) P10_PV [0];
        R11 = (int) P11_PV [0];
        R12 = (int) P12_PV [0];
        R13 = (int) P13_PV [0];
        R14 = (int) P14_PV [0];
        R15 = (int) P15_PV [0];
        R16 = (int) P16_PV [0];
        R17 = (int) P17_PV [0];
        R18 = (int) P18_PV [0];
        R19 = (int) P19_PV [0];
        R20 = (int) P20_PV [0];

        //REGION1 point
        Imgproc.circle(input, new Point(P1_X, P1_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R1 == 0) {
            Imgproc.circle(input, new Point(P1_X, P1_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION2 point
        Imgproc.circle(input, new Point(P2_X, P2_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R2 == 0) {
            Imgproc.circle(input, new Point(P2_X, P2_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION3 point
        Imgproc.circle(input, new Point(P3_X, P3_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R3 == 0) {
            Imgproc.circle(input, new Point(P3_X, P3_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION4 point
        Imgproc.circle(input, new Point(P4_X, P4_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R4 == 0) {
            Imgproc.circle(input, new Point(P4_X, P4_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION5 point
        Imgproc.circle(input, new Point(P5_X, P5_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R5 == 0) {
            Imgproc.circle(input, new Point(P5_X, P5_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION6 point
        Imgproc.circle(input, new Point(P6_X, P6_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R6 == 0) {
            Imgproc.circle(input, new Point(P6_X, P6_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION7 point
        Imgproc.circle(input, new Point(P7_X, P7_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R7 == 0) {
            Imgproc.circle(input, new Point(P7_X, P7_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION8 point
        Imgproc.circle(input, new Point(P8_X, P8_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R8 == 0) {
            Imgproc.circle(input, new Point(P8_X, P8_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION9 point
        Imgproc.circle(input, new Point(P9_X, P9_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R9 == 0) {
            Imgproc.circle(input, new Point(P9_X, P9_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION10 point
        Imgproc.circle(input, new Point(P10_X, P10_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R10 == 0) {
            Imgproc.circle(input, new Point(P10_X, P10_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION11 point
        Imgproc.circle(input, new Point(P11_X, P11_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R11 == 0) {
            Imgproc.circle(input, new Point(P11_X, P11_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION12 point
        Imgproc.circle(input, new Point(P12_X, P12_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R12 == 0) {
            Imgproc.circle(input, new Point(P12_X, P12_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION13 point
        Imgproc.circle(input, new Point(P13_X, P13_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R13 == 0) {
            Imgproc.circle(input, new Point(P13_X, P13_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION14 point
        Imgproc.circle(input, new Point(P14_X, P14_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R14 == 0) {
            Imgproc.circle(input, new Point(P14_X, P14_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION15 point
        Imgproc.circle(input, new Point(P15_X, P15_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R15 == 0) {
            Imgproc.circle(input, new Point(P15_X, P15_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION16 point
        Imgproc.circle(input, new Point(P16_X, P16_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R16 == 0) {
            Imgproc.circle(input, new Point(P16_X, P16_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION17 point
        Imgproc.circle(input, new Point(P17_X, P17_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R17 == 0) {
            Imgproc.circle(input, new Point(P17_X, P17_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION18 point
        Imgproc.circle(input, new Point(P18_X, P18_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R18 == 0) {
            Imgproc.circle(input, new Point(P18_X, P18_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION19 point
        Imgproc.circle(input, new Point(P19_X, P19_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R19 == 0) {
            Imgproc.circle(input, new Point(P19_X, P19_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }
        //REGION20 point
        Imgproc.circle(input, new Point(P20_X, P20_Y), 7, GRAY, 2);
        // Change colors if the pipeline detected something
        if (R20 == 0) {
            Imgproc.circle(input, new Point(P20_X, P20_Y), 7, BATTERY_CHARGED_BLUE, 2);
        }

        return input;

    }
}
