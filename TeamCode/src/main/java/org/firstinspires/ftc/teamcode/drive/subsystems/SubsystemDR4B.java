package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.ArrayList;
import java.util.Arrays;

public class SubsystemDR4B extends SubsystemBase {
    public DcMotorEx liftMotor;
    private int highJunctionPos = 144;
    private int midJunctionPos = 47;
    private int lowJunctionPos = 8;
    private int restPos = 0;
    private boolean fineTune = false;
    private boolean isMoving = false;
    public static ArrayList<Integer> DR4Barray = new ArrayList<Integer>(Arrays.<Integer>asList(0, 8, 47, 144));
    public int liftIncrement = 0;

    public SubsystemDR4B(DcMotorEx liftMotor) {
        this.liftMotor = liftMotor;
    }

    public void moveLiftUp() {
        //run mode
        isMoving = true;
        liftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        // set the target position
        if (liftIncrement >=0 && liftIncrement <=2) {
            liftIncrement++;
        } else {
            liftIncrement = liftIncrement;
        }
        liftMotor.setTargetPosition(DR4Barray.get(liftIncrement)); // an integer representing desired tick count

        liftMotor.set(0);

        //tolerance
        liftMotor.setPositionTolerance(13.6);   // allowed maximum error

        // perform the control loop
        while (!liftMotor.atTargetPosition()) {
            liftMotor.set(1);
        }

        //stop motor
        liftMotor.stopMotor();
    }

    public void moveLiftDown() {
        //run mode
        isMoving = true;
        liftMotor.setRunMode(Motor.RunMode.PositionControl);

        // set the target position
        if (liftIncrement >=1 && liftIncrement <=3) {
            liftIncrement--;
        } else {
            liftIncrement = liftIncrement;
        }
        liftMotor.setTargetPosition(DR4Barray.get(liftIncrement)); // an integer representing desired tick count

        liftMotor.set(0);

        //tolerance
        liftMotor.setPositionTolerance(13.6);   // allowed maximum error

        // perform the control loop
        while (!liftMotor.atTargetPosition()) {
            liftMotor.set(1);
        }

        //stop motor
        liftMotor.stopMotor();
    }

    public void liftRest () {
        isMoving = true;
        liftMotor.setTargetPosition(restPos);
        while (!liftMotor.atTargetPosition()) {
            liftMotor.set(1);
        }
        liftMotor.stopMotor();
    }

    public void liftLow () {
        isMoving = true;
        liftMotor.setTargetPosition(lowJunctionPos);
        while (!liftMotor.atTargetPosition()) {
            liftMotor.set(1);
        }
        liftMotor.stopMotor();
    }

    public void liftMid () {
        isMoving = true;
        liftMotor.setTargetPosition(midJunctionPos);
        while (!liftMotor.atTargetPosition()) {
            liftMotor.set(1);
        }
        liftMotor.stopMotor();
    }

    public void liftHigh () {
        isMoving = true;
        liftMotor.setTargetPosition(highJunctionPos);
        while (!liftMotor.atTargetPosition()) {
            liftMotor.set(1);
        }
        liftMotor.stopMotor();
    }

//    public void liftHome() {
//        //run mode
//        isMoving = true;
//        liftMotor.setRunMode(Motor.RunMode.PositionControl);
//
//        // set the target position
//        liftMotor.setTargetPosition(homePos); // an integer representing desired tick count
//
//        liftMotor.set(0);
//
//        //tolerance
//        liftMotor.setPositionTolerance(13.6);   // allowed maximum error
//
//        // perform the control loop
//        while (!liftMotor.atTargetPosition()) {
//            liftMotor.set(1);
//        }
//
//        //stop motor
//        liftMotor.stopMotor();
//    }

//    public void liftTop() {
//        // set the run mode
//        isMoving = true;
//        liftMotor.setRunMode(Motor.RunMode.PositionControl);
//
//
//        // set the target position
//        liftMotor.setTargetPosition(highJunctionPos); // an integer representing desired tick count
//
//
//        liftMotor.set(0);
//
//
//        // set the tolerance
//        liftMotor.setPositionTolerance(13.6);   // allowed maximum error
//
//
//        // perform the control loop
//        while (!liftMotor.atTargetPosition()) {
//            liftMotor.set(1);
//
//        }
//        liftMotor.stopMotor();
//
//    }
//
//    public void liftMid() {
//        // set the run mode
//        isMoving = true;
//        liftMotor.setRunMode(Motor.RunMode.PositionControl);
//
//
//        // set and get the position coefficient
//        liftMotor.setPositionCoefficient(0.05);
//
//
//        double leftkP = liftMotor.getPositionCoefficient();
//
//
//        // set the target position
//        liftMotor.setTargetPosition(midJunctionPos); // an integer representing desired tick count
//
//
//        liftMotor.set(0);
//
//
//        // set the tolerance
//        liftMotor.setPositionTolerance(13.6);   // allowed maximum error
//
//
//        // perform the control loop
//        while (!liftMotor.atTargetPosition()) {
//            liftMotor.set(1);
//
//        }
//        liftMotor.stopMotor();
//
//    }
//    public void liftLow() {
//        // set the run mode
//        isMoving = true;
//        liftMotor.setRunMode(Motor.RunMode.PositionControl);
//
//
//        // set and get the position coefficient
//        liftMotor.setPositionCoefficient(0.05);
//
//
//        double leftkP = liftMotor.getPositionCoefficient();
//
//
//        // set the target position
//        liftMotor.setTargetPosition(lowJunctionPos); // an integer representing desired tick count
//
//
//        liftMotor.set(0);
//
//
//        // set the tolerance
//        liftMotor.setPositionTolerance(13.6);   // allowed maximum error
//
//
//        // perform the control loop
//        while (!liftMotor.atTargetPosition()) {
//            liftMotor.set(1);
//
//        }
//        liftMotor.stopMotor();
//
//    }


}
