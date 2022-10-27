package org.firstinspires.ftc.teamcode.drive.opmode.ALAN;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Servo;

public class SubsystemTurret extends SubsystemBase {
    private Motor turretLeft;
    private Motor turretRight;
    private Servo turretServo;

    public SubsystemTurret(Motor turretLeft, Motor turretRight, Servo turretServo) {
        this.turretLeft = turretLeft;
        this.turretRight = turretRight;
        this.turretServo = turretServo;

    }

    public void runTurret() {
        turretLeft.set(0.3);
        turretRight.set(0.3);
    }

    public void stopTurret() {
        turretLeft.set(0);
        turretRight.set(0);
    }

    public void moveTurretServo(double position) {
        turretServo.setPosition(turretServo.getPosition() + position);
    }

}
