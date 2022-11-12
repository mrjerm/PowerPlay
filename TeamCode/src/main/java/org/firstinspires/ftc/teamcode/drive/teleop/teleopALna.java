package org.firstinspires.ftc.teamcode.drive.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.ArrayList;
import java.util.Arrays;


@TeleOp
public class teleopALna extends OpMode {

    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;

    private DcMotorEx liftMotor;

    private Servo turretServo;
    private Servo fourBarServoLeft;
    private Servo fourBarServoRight;
    private Servo intakeServo;

    private double fourBarRetractedPos = 0.18;
    private double fourBarHighPos = 0.33;
    private double fourBarDiagonalPos = 0.5;
    private double fourBarHorizontalPos = 0.65;
    private double fourBarDownPos = 0.85;

    public boolean fourBarHighCheck = false;
    public boolean fourBarRetractedCheck = false;
    public boolean fourBarDiagonalCheck = false;
    public boolean fourBarHorizontalCheck = false;
    public boolean fourBarDownCheck = false;
    public boolean fourBarMoving = false;

    public int v4BIncrement = 0;
    public static ArrayList<Double> Finalv4bArray = new ArrayList<Double>(Arrays.<Double>asList(0.18, 0.33, 0.5, 0.65, 0.85));

    private int highJunctionPos = 144;
    private int midJunctionPos = 47;
    private int lowJunctionPos = 8;
    private int restPos = 0;
    private boolean fineTune = false;
    private boolean isMoving = false;
    public static ArrayList<Integer> FinalDR4Barray = new ArrayList<Integer>(Arrays.<Integer>asList(0, 8, 47, 144));
    public int liftIncrement = 0;

    private double northPos = 0.0;
    private double northEastPos = 0.12;
    private double eastPos = 0.23;
    private double southEastPos = 0.36;
    private double southPos = 0.49;
    private double southWestPos = 0.62;
    private double westPos = 0.75;
    private double northWestPos = 0.87;
    private double fullrotation = 0.98;

    public boolean turretMoving = false;

    public int increment = 0;
    public static ArrayList<Double> TurretDistanceArray = new ArrayList<Double>(Arrays.<Double>asList(0.0, 0.12, 0.23, 0.36, 0.49, 0.62, 0.75, 0.87, 0.98));

    public double intakeOpen = 0.2;
    public double intakeClose = 0.38;


    @Override
    public void init() {
        //declare motors
        frontLeft = hardwareMap.get(DcMotorEx.class, "Motor FL");
        frontRight = hardwareMap.get(DcMotorEx.class, "Motor FR");
        backLeft = hardwareMap.get(DcMotorEx.class, "Motor BL");
        backRight = hardwareMap.get(DcMotorEx.class, "Motor BR");
        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);

        liftMotor = hardwareMap.get(DcMotorEx.class, "Motor DR4B");

        //set zero power behavior
        frontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        liftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        // Reset encoders

        //invert motors
        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //declare servos
        turretServo = hardwareMap.get(Servo.class, "Servo Turret");
        intakeServo = hardwareMap.get(Servo.class, "Servo Intake");
        fourBarServoLeft = hardwareMap.get(Servo.class, "Servo V4BL");
        fourBarServoRight = hardwareMap.get(Servo.class, "Servo V4BR");
        fourBarServoLeft.setDirection(Servo.Direction.REVERSE);

        fourBarServoLeft.setPosition(fourBarRetractedPos);
        fourBarServoRight.setPosition(fourBarRetractedPos);
    }

    @Override
    public void loop() {
        liftUp(gamepad1.dpad_up);
        liftDown(gamepad1.dpad_down);
        turretPositive(gamepad1.dpad_right);
        turretNegative(gamepad1.dpad_left);
        movev4bPositive(gamepad2.dpad_right);
        movev4BNegative(gamepad2.dpad_left);
        intakeOpen(gamepad1.left_bumper);
        intakeClose(gamepad1.right_bumper);
    }

    public void driveCode() {
        float x1 = gamepad1.left_stick_x;
        float y1 = -gamepad1.left_stick_y;
        float x2 = gamepad1.right_stick_x;

        double fl = x1 + y1 + x2;
        double bl = -x1 + y1 + x2;
        double fr = -x1 + y1 - x2;
        double br = x1 + y1 - x2;

        frontLeft.setPower(fl);
        frontRight.setPower(bl);
        backLeft.setPower(fr);
        backRight.setPower(br);
    }
    
    public void liftUp(boolean up) {
        isMoving = true;
        if (liftIncrement >=0 && liftIncrement <=2) {
            liftIncrement++;
        } else {
            liftIncrement = liftIncrement;
        }
        liftMotor.setTargetPosition(FinalDR4Barray.get(liftIncrement)); // an integer representing desired tick count

        if (Math.abs(liftMotor.getCurrentPosition() - liftMotor.getTargetPosition()) < 5){
            liftMotor.setPower(0);
        } else {
            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftMotor.setPower(1);
        }
    }
    
    public void liftDown(boolean down) {
        isMoving = true;

        // set the target position
        if (liftIncrement >=0 && liftIncrement <=2) {
            liftIncrement++;
        } else {
            liftIncrement = liftIncrement;
        }
        liftMotor.setTargetPosition(FinalDR4Barray.get(liftIncrement)); // an integer representing desired tick count

        if (Math.abs(liftMotor.getCurrentPosition() - liftMotor.getTargetPosition()) < 5){
            liftMotor.setPower(0);
        } else {
            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftMotor.setPower(1);
        }
    }
    public void liftRest () {
        isMoving = true;
        liftMotor.setTargetPosition(restPos);
        if (Math.abs(liftMotor.getCurrentPosition() - liftMotor.getTargetPosition()) < 5){
            liftMotor.setPower(0);
        } else {
            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftMotor.setPower(1);
        }
    }

    public void liftLow () {
        isMoving = true;
        liftMotor.setTargetPosition(lowJunctionPos);
        if (Math.abs(liftMotor.getCurrentPosition() - liftMotor.getTargetPosition()) < 5){
            liftMotor.setPower(0);
        } else {
            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftMotor.setPower(1);
        }
    }

    public void liftMid () {
        isMoving = true;
        liftMotor.setTargetPosition(midJunctionPos);
        if (Math.abs(liftMotor.getCurrentPosition() - liftMotor.getTargetPosition()) < 5){
            liftMotor.setPower(0);
        } else {
            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftMotor.setPower(1);
        }
    }

    public void liftHigh () {
        isMoving = true;
        liftMotor.setTargetPosition(highJunctionPos);
        if (Math.abs(liftMotor.getCurrentPosition() - liftMotor.getTargetPosition()) < 5){
            liftMotor.setPower(0);
        } else {
            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftMotor.setPower(1);
        }
    }
    
    public void turretPositive(boolean moveturretpositive) {
        turretMoving = true;
        if (fourBarDownCheck == true) {
            fourBarHigh();
            if (increment >=0 && increment <=6) {
                increment++;
            } else {
                increment = increment;
            }
            turretServo.setPosition(TurretDistanceArray.get(increment));
        } else {
            if (increment >= 0 && increment <= 6) {
                increment++;
            } else {
                increment = increment;
            }
            turretServo.setPosition(TurretDistanceArray.get(increment));
        }
    }

    public void turretNegative(boolean moveturretnegative) {
        turretMoving = true;
        if (fourBarDownCheck == true) {
            fourBarHigh();
            if (increment >=1 && increment <=7) {
                increment--;
            } else {
                increment = increment;
            }
            turretServo.setPosition(TurretDistanceArray.get(increment));
        } else {
            if (increment >= 1 && increment <= 7) {
                increment--;
            } else {
                increment = increment;
            }
            turretServo.setPosition(TurretDistanceArray.get(increment));
        }
    }

    public void turretSetNorth() {
        turretMoving = true;
        turretServo.setPosition(northPos);
    }

    public void turretSetNorthEast () {
        turretMoving = true;
        turretServo.setPosition(northEastPos);
    }

    public void turretSetEast () {
        turretMoving = true;
        turretServo.setPosition(eastPos);
    }

    public void turretSetSouthEast () {
        turretMoving = true;
        turretServo.setPosition(southEastPos);
    }

    public void turretSetSouth () {
        turretMoving = true;
        turretServo.setPosition(southPos);
    }

    public void turretSetSouthWest () {
        turretMoving = true;
        turretServo.setPosition(southWestPos);
    }

    public void turretSetWest () {
        turretMoving = true;
        turretServo.setPosition(westPos);
    }

    public void turretSetNorthWest () {
        turretMoving = true;
        turretServo.setPosition(northWestPos);
    }
    
    public void intakeOpen(boolean openintake) {
        intakeServo.setPosition(intakeOpen);
    }

    public void intakeClose(boolean closeintake) {
        intakeServo.setPosition(intakeClose);
    }

    public void movev4bPositive (boolean v4bup) {
        if (v4BIncrement >= 0 && v4BIncrement <= 3) {
            v4BIncrement++;
        } else {
            v4BIncrement = v4BIncrement;
        }
        fourBarServoLeft.setPosition(Finalv4bArray.get(v4BIncrement));
        fourBarServoRight.setPosition(Finalv4bArray.get(v4BIncrement));
    }

    public void movev4BNegative (boolean v4bdown) {
        if (v4BIncrement >= 1 && v4BIncrement <= 4) {
            v4BIncrement--;
        } else {
            v4BIncrement = v4BIncrement;
        }
        fourBarServoLeft.setPosition(Finalv4bArray.get(v4BIncrement));
        fourBarServoRight.setPosition(Finalv4bArray.get(v4BIncrement));
    }

    public void fourBarRetracted() {
        fourBarServoRight.setPosition(fourBarRetractedPos);
        fourBarServoLeft.setPosition(fourBarRetractedPos);
        fourBarRetractedCheck = true;
        fourBarHighCheck = false;
        fourBarDiagonalCheck = false;
        fourBarHorizontalCheck = false;
        fourBarDownCheck = false;

    }

    public void fourBarHigh() {
        fourBarServoRight.setPosition(fourBarHighPos);
        fourBarServoLeft.setPosition(fourBarHighPos);
        fourBarRetractedCheck = false;
        fourBarHighCheck = true;
        fourBarDiagonalCheck = false;
        fourBarHorizontalCheck = false;
        fourBarDownCheck = false;
    }

    public void fourBarDiagonal() {
        fourBarServoRight.setPosition(fourBarDiagonalPos);
        fourBarServoLeft.setPosition(fourBarDiagonalPos);
        fourBarRetractedCheck = false;
        fourBarHighCheck = false;
        fourBarDiagonalCheck = true;
        fourBarHorizontalCheck = false;
        fourBarDownCheck = false;
    }

    public void fourBarHorizontal() {
        fourBarServoRight.setPosition(fourBarHorizontalPos);
        fourBarServoLeft.setPosition(fourBarHorizontalPos);
        fourBarRetractedCheck = false;
        fourBarHighCheck = false;
        fourBarDiagonalCheck = false;
        fourBarHorizontalCheck = true;
        fourBarDownCheck = false;
    }

    public void fourBarDown() {
        fourBarServoRight.setPosition(fourBarDownPos);
        fourBarServoLeft.setPosition(fourBarDownPos);
        fourBarRetractedCheck = false;
        fourBarHighCheck = false;
        fourBarDiagonalCheck = false;
        fourBarHorizontalCheck = false;
        fourBarDownCheck = true;
    }

    public void highJunction(boolean junctionhigh) {
        if (junctionhigh == true) {
            liftHigh();
            fourBarDiagonal();
        }
    }

    public void midJunction (boolean junctionmid) {
        if (junctionmid == true) {
            liftMid();
            fourBarHorizontal();
        }
    }

    public void lowJunction (boolean junctionlow) {
        if (junctionlow == true) {
            liftLow();
            fourBarHorizontal();
        }
    }

    public void groundJunction (boolean junctionGround) {
        if (junctionGround == true) {
            liftLow();
            fourBarDown();
        }
    }

    public void score (boolean scoring) {
        if (scoring == true) {
            intakeServo.setPosition(intakeOpen);

        }
    }
}
