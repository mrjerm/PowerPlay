package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Servo;

public class SubsystemTurret extends SubsystemBase{
    public Servo turretServo;
    private int northPos = 0;
    private int northEastPos = 0;
    private int northWestPos = 0;
    private int westPos = 0;
    private int eastPos = 0;
    private int southPos = 0;
    private int southEastPos = 0;
    private int southWestPos = 0;
    public boolean turretMoving = false;
    public int i = 0;
    double distanceArray[] = new double[]{northPos, northEastPos, eastPos, southEastPos, southPos,southWestPos, westPos, northWestPos};

    public SubsystemTurret(Servo turretServo) {
        this.turretServo = turretServo;
    }

    public void moveTurretPositive() {
        turretMoving = true;
        if (i>=0 || i<=7) {
            i++;
        } else {
            i=i;
        }
        turretServo.setPosition(distanceArray[i]);
    }

    public void moveTurretNegative() {
        turretMoving = true;
        if (i>=0 || i<=7) {
            i--;
        } else {
            i=i;
        }
        turretServo.setPosition(distanceArray[i]);
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
