package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class SubsystemDR4B extends SubsystemBase {
    public Motor leftLiftMotor;
    public Motor rightLiftMotor;
    private int highJunctionPos = 0;
    private int midJunctionPos = 0;
    private int lowJunctionPos = 0;
    private int groundJunctionPos = 0;
    private int floorPos = 0;
    private int homePos = 0;
    private boolean fineTune = false;
    private boolean isMoving = false;

    public SubsystemDR4B(Motor leftLiftMotor, Motor rightLiftMotor) {
        this.leftLiftMotor = leftLiftMotor;
        this.rightLiftMotor = rightLiftMotor;
    }

    public void liftHome() {
        //run mode
        isMoving = true;
        leftLiftMotor.setRunMode(Motor.RunMode.PositionControl);
        rightLiftMotor.setRunMode(Motor.RunMode.PositionControl);

        // set the target position
        leftLiftMotor.setTargetPosition(homePos); // an integer representing desired tick count
        rightLiftMotor.setTargetPosition(homePos);

        leftLiftMotor.set(0);
        rightLiftMotor.set(0);

        //tolerance
        leftLiftMotor.setPositionTolerance(13.6);   // allowed maximum error
        rightLiftMotor.setPositionTolerance(13.6);

        // perform the control loop
        while (!leftLiftMotor.atTargetPosition()) {
            leftLiftMotor.set(1);
            rightLiftMotor.set(1);
        }

        //stop motor
        leftLiftMotor.stopMotor();
        rightLiftMotor.stopMotor();
    }

    public void liftTop() {
        // set the run mode
        isMoving = true;
        leftLiftMotor.setRunMode(Motor.RunMode.PositionControl);
        rightLiftMotor.setRunMode(Motor.RunMode.PositionControl);

        // set the target position
        leftLiftMotor.setTargetPosition(highJunctionPos); // an integer representing desired tick count
        rightLiftMotor.setTargetPosition(highJunctionPos);

        leftLiftMotor.set(0);
        rightLiftMotor.set(0);

        // set the tolerance
        leftLiftMotor.setPositionTolerance(13.6);   // allowed maximum error
        rightLiftMotor.setPositionTolerance(13.6);

        // perform the control loop
        while (!leftLiftMotor.atTargetPosition()) {
            leftLiftMotor.set(1);
            rightLiftMotor.set(1);
        }
        leftLiftMotor.stopMotor();
        rightLiftMotor.stopMotor();// stop the motor
    }

    public void liftMid() {
        // set the run mode
        isMoving = true;
        leftLiftMotor.setRunMode(Motor.RunMode.PositionControl);
        rightLiftMotor.setRunMode(Motor.RunMode.PositionControl);

        // set and get the position coefficient
        leftLiftMotor.setPositionCoefficient(0.05);
        rightLiftMotor.setPositionCoefficient(0.05);

        double leftkP = leftLiftMotor.getPositionCoefficient();
        double rightkP = rightLiftMotor.getPositionCoefficient();

        // set the target position
        leftLiftMotor.setTargetPosition(midJunctionPos); // an integer representing desired tick count
        rightLiftMotor.setTargetPosition(midJunctionPos);

        leftLiftMotor.set(0);
        rightLiftMotor.set(0);

        // set the tolerance
        leftLiftMotor.setPositionTolerance(13.6);   // allowed maximum error
        rightLiftMotor.setPositionTolerance(13.6);

        // perform the control loop
        while (!leftLiftMotor.atTargetPosition()) {
            leftLiftMotor.set(1);
            rightLiftMotor.set(1);
        }
        leftLiftMotor.stopMotor();
        rightLiftMotor.stopMotor();// stop the motor
    }
    public void liftLow() {
        // set the run mode
        isMoving = true;
        leftLiftMotor.setRunMode(Motor.RunMode.PositionControl);
        rightLiftMotor.setRunMode(Motor.RunMode.PositionControl);

        // set and get the position coefficient
        leftLiftMotor.setPositionCoefficient(0.05);
        rightLiftMotor.setPositionCoefficient(0.05);

        double leftkP = leftLiftMotor.getPositionCoefficient();
        double rightkP = rightLiftMotor.getPositionCoefficient();

        // set the target position
        leftLiftMotor.setTargetPosition(lowJunctionPos); // an integer representing desired tick count
        rightLiftMotor.setTargetPosition(lowJunctionPos);

        leftLiftMotor.set(0);
        rightLiftMotor.set(0);

        // set the tolerance
        leftLiftMotor.setPositionTolerance(13.6);   // allowed maximum error
        rightLiftMotor.setPositionTolerance(13.6);

        // perform the control loop
        while (!leftLiftMotor.atTargetPosition()) {
            leftLiftMotor.set(1);
            rightLiftMotor.set(1);
        }
        leftLiftMotor.stopMotor();
        rightLiftMotor.stopMotor();// stop the motor
    }


}
