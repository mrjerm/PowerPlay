package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class SubsystemDR4B extends SubsystemBase {
    public Motor liftMotor;
    private int highJunctionPos = 0;
    private int midJunctionPos = 0;
    private int lowJunctionPos = 0;
    private int groundJunctionPos = 0;
    private int floorPos = 0;
    private int homePos = 0;
    private boolean fineTune = false;
    private boolean isMoving = false;
    int DR4Barray[] = new int[]{floorPos, homePos, groundJunctionPos, lowJunctionPos, midJunctionPos, highJunctionPos};
    public int j = 0;

    public SubsystemDR4B(Motor liftMotor) {
        this.liftMotor = liftMotor;
    }

    public void moveLiftUp() {
        //run mode
        isMoving = true;
        liftMotor.setRunMode(Motor.RunMode.PositionControl);

        // set the target position
        if (j>=0 || j<=5) {
            j++;
        } else {
            j=j;
        }
        liftMotor.setTargetPosition(DR4Barray[j]); // an integer representing desired tick count

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
        if (j>=1 || j<=6) {
            j--;
        } else {
            j=j;
        }
        liftMotor.setTargetPosition(DR4Barray[j]); // an integer representing desired tick count

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
