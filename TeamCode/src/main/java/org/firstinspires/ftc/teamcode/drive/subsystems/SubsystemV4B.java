package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class SubsystemV4B extends SubsystemBase {
    public Servo fourBarServoLeft;
    public Servo fourBarServoRight;
    private double fourBarTopPos = 0;
    private double fourBarInPos = 0;
    private double fourBarDownPos = 0;
    private double fourBarPosition = 0;
    public boolean fourBarTopCheck = false;
    public boolean fourBarInCheck = false;
    public boolean fourBarDownCheck = false;
    public boolean fourBarMoving = false;

    public SubsystemV4B (Servo fourBarServoLeft, Servo fourBarServoRight) {
        this.fourBarServoLeft = fourBarServoLeft;
        this.fourBarServoRight = fourBarServoRight;
    }

    public void fourBarTop() {
        fourBarServoRight.setPosition(fourBarTopPos);
        fourBarServoLeft.setPosition(fourBarTopPos);
        fourBarTopCheck = true;
        fourBarInCheck = false;
        fourBarDownCheck = false;
    }

    public void fourBarIn() {
        fourBarServoRight.setPosition(fourBarInPos);
        fourBarServoLeft.setPosition(fourBarInPos);
        fourBarTopCheck = false;
        fourBarInCheck = true;
        fourBarDownCheck = false;
    }
    public void fourBarDown() {
        fourBarServoRight.setPosition(fourBarDownPos);
        fourBarServoLeft.setPosition(fourBarDownPos);
        fourBarTopCheck = false;
        fourBarInCheck = false;
        fourBarDownCheck = true;
    }

}
