package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.Arrays;

public class SubsystemV4B extends SubsystemBase {
    public Servo fourBarServoLeft;
    public Servo fourBarServoRight;
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
    public static ArrayList<Double> v4bArray = new ArrayList<Double>(Arrays.<Double>asList(0.18, 0.33, 0.5, 0.65, 0.85));

    public SubsystemV4B (Servo fourBarServoLeft, Servo fourBarServoRight) {
        this.fourBarServoLeft = fourBarServoLeft;
        this.fourBarServoRight = fourBarServoRight;
    }

    public void v4BPositive () {
        if (v4BIncrement >=0 && v4BIncrement<= 3) {
            v4BIncrement++;
        } else {
            v4BIncrement = v4BIncrement;
        }
        fourBarServoLeft.setPosition(v4bArray.get(v4BIncrement));
        fourBarServoRight.setPosition(v4bArray.get(v4BIncrement));
    }

    public void v4BNegative () {
        if (v4BIncrement >=1 && v4BIncrement<= 4) {
            v4BIncrement--;
        } else {
            v4BIncrement = v4BIncrement;
        }
        fourBarServoLeft.setPosition(v4bArray.get(v4BIncrement));
        fourBarServoRight.setPosition(v4bArray.get(v4BIncrement));
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


}
