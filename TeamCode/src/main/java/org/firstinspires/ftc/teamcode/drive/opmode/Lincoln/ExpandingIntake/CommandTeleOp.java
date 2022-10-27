package org.firstinspires.ftc.teamcode.drive.opmode.Lincoln.ExpandingIntake;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Servo;

public class CommandTeleOp extends CommandOpMode {

    private Servo intakeServo;
    private ExpandingIntakeSubsystem expandingIntakeSubsystem;

    private GamepadEx driverOp;

    @Override
    public void initialize() {
        intakeServo = hardwareMap.get(Servo.class, "Servo Expanding Intake");

        driverOp = new GamepadEx(gamepad1);

        expandingIntakeSubsystem = new ExpandingIntakeSubsystem(intakeServo);

        intakeServo.setPosition(0);

        driverOp.getGamepadButton(GamepadKeys.Button.A).whenPressed(() -> expandingIntakeSubsystem.turn());
    }
}
