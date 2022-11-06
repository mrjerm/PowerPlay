package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.Arrays;

public class SubsystemTurret extends SubsystemBase{
    public Servo turretServo;
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
    public static ArrayList<Double> distanceArray = new ArrayList<Double>(Arrays.<Double>asList(0.0, 0.12, 0.23, 0.36, 0.49, 0.62, 0.75, 0.87, 0.98));

    public double turretRotation = 0;

    public SubsystemTurret(Servo turretServo) {
        this.turretServo = turretServo;
    }

    public void moveTurretPositive() {
        turretMoving = true;
        if (increment >=0 && increment <=6) {
            increment++;
        } else {
            increment = increment;
        }
        turretServo.setPosition(distanceArray.get(increment));
    }

    public void moveTurretNegative() {
        turretMoving = true;
        if (increment >=1 && increment <=7) {
            increment--;
        } else {
            increment = increment;
        }
        turretServo.setPosition(distanceArray.get(increment));
    }

    public void turretNorth() {
        turretMoving = true;
        turretServo.setPosition(northPos);
    }

    public void turretNorthEast () {
        turretMoving = true;
        turretServo.setPosition(northEastPos);
    }

    public void turretEast () {
        turretMoving = true;
        turretServo.setPosition(eastPos);
    }

    public void turretSouthEast () {
        turretMoving = true;
        turretServo.setPosition(southEastPos);
    }

    public void turretSouth () {
        turretMoving = true;
        turretServo.setPosition(southPos);
    }

    public void turretSouthWest () {
        turretMoving = true;
        turretServo.setPosition(southWestPos);
    }

    public void turretWest () {
        turretMoving = true;
        turretServo.setPosition(westPos);
    }

    public void turretNorthWest () {
        turretMoving = true;
        turretServo.setPosition(northWestPos);
    }


//    public void moveTurret(double turretx, double turrety) {
//        turretMoving = true;
//        if (turretx == 0 && turrety == 1) {
//            turretRotation = (90/360) * fullrotation;
//        } else if (turretx == 0 && turrety == -1) {
//            turretRotation = (270/360) * fullrotation;
//        } else if (turretx == 1 && turrety == 0) {
//            turretRotation = (0/360) * fullrotation;
//        } else if (turretx == -1 && turrety == 0) {
//            turretRotation = (180/360) * fullrotation;
//        } else if (turretRotation>=0 && turretRotation<=fullrotation) {
//            turretRotation = (((Math.atan2(turrety, turretx)) / 360) * fullrotation);
//        }
//        turretServo.setPosition(turretRotation);
//    }




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
