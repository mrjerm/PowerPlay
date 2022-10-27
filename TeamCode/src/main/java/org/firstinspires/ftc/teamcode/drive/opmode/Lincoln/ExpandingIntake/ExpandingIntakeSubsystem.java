package org.firstinspires.ftc.teamcode.drive.opmode.Lincoln.ExpandingIntake;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class ExpandingIntakeSubsystem extends SubsystemBase {

    private Servo intakeServo;

    public ExpandingIntakeSubsystem(Servo servoIntake) {
        intakeServo = servoIntake;
    }

    public void turn() {
        intakeServo.setPosition(intakeServo.getPosition() + 0.25);
    }
}
