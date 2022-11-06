package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class SubsystemV4B extends SubsystemBase {
    public Servo fourBarServoLeft;
    public Servo fourBarServoRight;
    private double fourBarRetractedPos = 0.18;
    private double fourBarHighPos = 0.33;
    private double fourBarHorizontalPos = 0.65;
    private double fourBarDownPos = 0.85;

    public boolean fourBarHighCheck = false;
    public boolean fourBarRetractedCheck = false;
    public boolean fourBarHorizontalCheck = false;
    public boolean fourBarDownCheck = false;
    public boolean fourBarMoving = false;

    public SubsystemV4B (Servo fourBarServoLeft, Servo fourBarServoRight) {
        this.fourBarServoLeft = fourBarServoLeft;
        this.fourBarServoRight = fourBarServoRight;
    }

    public void fourBarRetracted() {
        fourBarServoRight.setPosition(fourBarRetractedPos);
        fourBarServoLeft.setPosition(fourBarRetractedPos);
        fourBarRetractedCheck = true;
        fourBarHighCheck = false;
        fourBarHorizontalCheck = false;
        fourBarDownCheck = false;

    }

    public void fourBarHigh() {
        fourBarServoRight.setPosition(fourBarHighPos);
        fourBarServoLeft.setPosition(fourBarHighPos);
        fourBarRetractedCheck = false;
        fourBarHighCheck = true;
        fourBarHorizontalCheck = false;
        fourBarDownCheck = false;
    }

    public void fourBarHorizontal() {
        fourBarServoRight.setPosition(fourBarHorizontalPos);
        fourBarServoLeft.setPosition(fourBarHorizontalPos);
        fourBarRetractedCheck = false;
        fourBarHighCheck = false;
        fourBarHorizontalCheck = true;
        fourBarDownCheck = false;
    }

    public void fourBarDown() {
        fourBarServoRight.setPosition(fourBarDownPos);
        fourBarServoLeft.setPosition(fourBarDownPos);
        fourBarRetractedCheck = false;
        fourBarHighCheck = false;
        fourBarHorizontalCheck = false;
        fourBarDownCheck = true;
    }


}
