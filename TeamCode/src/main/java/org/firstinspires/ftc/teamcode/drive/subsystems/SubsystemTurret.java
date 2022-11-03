package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class SubsystemTurret extends SubsystemBase{
    public Servo turretServo;
    private double northPos = 0;
    private double northEastPos = 0;
    private double northWestPos = 0;
    private double westPos = 0;
    private double eastPos = 0;
    private double southPos = 0;
    private double southEastPos = 0;
    private double southWestPos = 0;
    private double fullrotation = 0;

    public boolean turretMoving = false;

    public int increment = 0;
    double distanceArray[] = new double[]{northPos, northEastPos, eastPos, southEastPos, southPos,southWestPos, westPos, northWestPos};

    public double turretRotation = 0;

    public SubsystemTurret(Servo turretServo) {
        this.turretServo = turretServo;
    }

    public void moveTurretPositive() {
        turretMoving = true;
        if (increment >=0 || increment <=6) {
            increment++;
        } else {
            increment = increment;
        }
        turretServo.setPosition(distanceArray[increment]);
    }

    public void moveTurretNegative() {
        turretMoving = true;
        if (increment >=1 || increment <=7) {
            increment--;
        } else {
            increment = increment;
        }
        turretServo.setPosition(distanceArray[increment]);
    }

    public void moveTurret(double turretx, double turrety) {
        turretMoving = true;
        if (turretx == 0 && turrety == 1) {
            turretRotation = (90/360) * fullrotation;
        } else if (turretx == 0 && turrety == -1) {
            turretRotation = (270/360) * fullrotation;
        } else if (turretx == 1 && turrety == 0) {
            turretRotation = (0/360) * fullrotation;
        } else if (turretx == -1 && turrety == 0) {
            turretRotation = (180/360) * fullrotation;
        } else if (turretRotation>=0 && turretRotation<=fullrotation) {
            turretRotation = (((Math.atan2(turrety, turretx)) / 360) * fullrotation);
        }
        turretServo.setPosition(turretRotation);
    }




//    public void setNorthPos() {
//        turretMoving = true;
//        turretServo.setPosition(northPos);
//    }
//    public void setNorthEastPos() {
//        turretMoving = true;
//        turretServo.setPosition(northEastPos);
//    }
//    public void setNorthWestPos() {
//        turretMoving = true;
//        turretServo.setPosition(northWestPos);
//    }
//    public void setEastPos() {
//        turretMoving = true;
//        turretServo.setPosition(northPos);
//    }
//    public void setWestPos() {
//        turretMoving = true;
//        turretServo.setPosition(northPos);
//    }
//    public void setSouthPos() {
//        turretMoving = true;
//        turretServo.setPosition(northPos);
//    }
//    public void setSouthWestPos() {
//        turretMoving = true;
//        turretServo.setPosition(northPos);
//    }
//    public void setSouthEastPos() {
//        turretMoving = true;
//        turretServo.setPosition(northPos);
//    }




}
