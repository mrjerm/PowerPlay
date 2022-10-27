package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

public class DuckSpinnerSubsystem extends SubsystemBase {

    private CRServo duckSpinnerL, duckSpinnerR;

    public DuckSpinnerSubsystem(CRServo servoLeft, CRServo servoRight) {
        duckSpinnerL = servoLeft;
        duckSpinnerR = servoRight;
    }

/*    public void spin(double speed) {
        duckSpinnerL.setPower(speed);
        duckSpinnerR.setPower(speed);
    } */

    public void spin() {
        duckSpinnerL.setPower(0.3);
        duckSpinnerR.setPower(0.3);
    }

    public void stopSpin() {
        duckSpinnerL.setPower(0);
        duckSpinnerR.setPower(0);
    }
}
