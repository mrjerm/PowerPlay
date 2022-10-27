package org.firstinspires.ftc.teamcode.drive;

import static org.firstinspires.ftc.teamcode.drive.Constants.clawClosePos;
import static org.firstinspires.ftc.teamcode.drive.Constants.odometerDownPos;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public abstract class AutoBuyScreen extends LinearOpMode{
    public static ArrayList<Double> regionValues = new ArrayList<Double>(Arrays.<Double>asList(103.0, 75.0, 63.0, 53.0, 45.0, 39.0, 34.5, 31.0, 28., 25., 23.0, 21.0, 19.5, 18.0, 16.5, 16.0, 15.0, 14.0, 13.25, 12.5));
    public static ArrayList<Double> detectedRegionsValues = new ArrayList<>();

    public DcMotorEx frontLeft;
    public DcMotorEx backLeft;
    public DcMotorEx frontRight;
    public DcMotorEx backRight;


    public DcMotorEx horizontalSlide;
    public DcMotorEx intake;

    public Servo DR4BServo;

    public CRServo duckSpinnerLeft;
    public CRServo duckSpinnerRight;

    public Servo clawServo;
    public Servo odometerYL;
    public Servo odometerYR;
    public Servo odometerX;

    public void initialize() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "Motor FL");
        backLeft = hardwareMap.get(DcMotorEx.class, "Motor BL");
        frontRight = hardwareMap.get(DcMotorEx.class, "Motor FR");
        backRight = hardwareMap.get(DcMotorEx.class, "Motor BR");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        horizontalSlide = hardwareMap.get(DcMotorEx.class, "Motor Horizontal Slide");
        horizontalSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        horizontalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake = hardwareMap.get(DcMotorEx.class, "Motor Intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        DR4BServo = hardwareMap.get(Servo.class, "Servo DR4B");
        DR4BServo.setDirection(Servo.Direction.FORWARD);

        duckSpinnerLeft = hardwareMap.get(CRServo.class, "Servo Duck Spinner Left");
        duckSpinnerRight = hardwareMap.get(CRServo.class, "Servo Duck Spinner Right");
        duckSpinnerLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        duckSpinnerRight.setDirection(DcMotorSimple.Direction.REVERSE);

        clawServo = hardwareMap.get(Servo.class, "Servo Claw");
        clawServo.setDirection(Servo.Direction.FORWARD);
        clawServo.setPosition(clawClosePos);

        odometerYL = hardwareMap.get(Servo.class, "Servo Odometer YL");
        odometerYL.setDirection(Servo.Direction.REVERSE);

        odometerYR = hardwareMap.get(Servo.class, "Servo Odometer YR");
        odometerYR.setDirection(Servo.Direction.FORWARD);

        odometerX = hardwareMap.get(Servo.class, "Servo Odometer X");
        odometerX.setDirection(Servo.Direction.REVERSE);

        odometerYL.setPosition(odometerDownPos);
        odometerYR.setPosition(odometerDownPos);
        odometerX.setPosition(odometerDownPos);

        telemetry.addData("Status", "Ready!");
        telemetry.update();

    }

    @Override
    public void runOpMode() throws InterruptedException {

    }

    public void driveTo (double yDis, double maxPower){

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        double fl = encoderInchesToTicks(yDis);
        double fr = encoderInchesToTicks(yDis);
        double bl = encoderInchesToTicks(yDis);
        double br = encoderInchesToTicks(yDis);

        frontLeft.setTargetPosition((int) fl);
        frontRight.setTargetPosition((int) fr);
        backLeft.setTargetPosition((int) bl);
        backRight.setTargetPosition((int) br);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(maxPower);
        frontRight.setPower(maxPower);
        backLeft.setPower(maxPower);
        backRight.setPower(maxPower);

        while (frontLeft.isBusy() || frontRight.isBusy()){
            if (detectedRegionsValues.size() == 1){
                telemetry.addData("banaana", detectedRegionsValues.get(0));
                telemetry.update();
            }
            else {
                telemetry.addData("accepted values", findAcceptedValues(detectedRegionsValues));
                telemetry.addData("drive values", distanceToDrive(findAcceptedValues(detectedRegionsValues)));
                telemetry.update();
            }
        }

        StopAll();

    }

    public void TurnForever (double power) {
        frontLeft.setPower (-power);
        frontRight.setPower (power);
        backLeft.setPower (-power);
        backRight.setPower (power);
    }

    public static boolean IsZero(int... args) {
        for (int arg : args) {
            if (arg == 0) {
                return true;
            }
        }
        return false;
    }

    public static void findRegionsDetected(int... args) {
        detectedRegionsValues.clear();
        for (int i = 0; i <= args.length - 1; i++) {
            if (args[i] == 0) {
                detectedRegionsValues.add(regionValues.get(i));
            }
        }
    }

    public static double getMedian(List<Double> data) {
        if (data.size() == 1) {
            return data.get(0);
        }
        int median = data.size() / 2;
        median = median > 0 && median % 2 == 0 ? median - 1 : median;
        return data.get(median);
    }

    public static List<Double> findAcceptedValues(List<Double> input) {
        System.out.println(input.size());
        List<Double> acceptedValues = new ArrayList<Double>();
        List<Double> data1 = new ArrayList<Double>();
        List<Double> data2 = new ArrayList<Double>();
        if (input.size() % 2 == 0) {
            data1 = input.subList(0, input.size() / 2);
            data2 = input.subList(input.size() / 2, input.size());
        } else {
            data1 = input.subList(0, input.size() / 2);
            data2 = input.subList(input.size() / 2 + 1, input.size());
        }
        double q1 = getMedian(data1);
        double q3 = getMedian(data2);
        double iqr = q3 - q1;
        double lowerFence = q1 + 0.05 * iqr;
        double upperFence = q3 - 0.05 * iqr;
        for (int i = 0; i < input.size(); i++) {
            if (!(input.get(i) < lowerFence || input.get(i) > upperFence)){
                acceptedValues.add(input.get(i));
            }
        }
        System.out.println("lower fence: " + lowerFence);
        System.out.println("upper fence: " + upperFence);
        System.out.println("iqr: " + iqr);
        System.out.println("q1: " + q1);
        System.out.println("q3: " + q3);

        return acceptedValues;

    }

    public static double distanceToDrive(List<Double> input) {
        double sum = 0;
        double avgdistance = 0;
        for (int i = 0; i<input.size(); i++) {
            sum += input.get(i);
        }
        avgdistance = sum / input.size();
        return avgdistance;
    }


    public void StopAll() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }



    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        frontLeft.setZeroPowerBehavior(zeroPowerBehavior);
        frontRight.setZeroPowerBehavior(zeroPowerBehavior);
        backLeft.setZeroPowerBehavior(zeroPowerBehavior);
        backRight.setZeroPowerBehavior(zeroPowerBehavior);
    }


}
