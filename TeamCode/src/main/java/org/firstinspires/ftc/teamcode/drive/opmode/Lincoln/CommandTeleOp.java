package org.firstinspires.ftc.teamcode.drive.opmode.Lincoln;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.drive.subsystems.other.DuckSpinnerSubsystem;

@TeleOp(name = "Command TeleOp")
public class CommandTeleOp extends CommandOpMode {

    private CRServo duckSpinnerL, duckSpinnerR;
    private DuckSpinnerSubsystem duckSpinnerSubsystem;

    private GamepadEx driverOp;

    @Override
    public void initialize() {
        duckSpinnerL = hardwareMap.get(CRServo.class, "Servo Duck Spinner Left");
        duckSpinnerR = hardwareMap.get(CRServo.class, "Servo Duck Spinner Right");

        driverOp = new GamepadEx(gamepad1);

        duckSpinnerSubsystem = new DuckSpinnerSubsystem(duckSpinnerL, duckSpinnerR);

        /*driverOp.getGamepadButton(GamepadKeys.Button.A)
                .whenHeld(new DuckSpinnerCommand(duckSpinnerSubsystem)); */
        driverOp.getGamepadButton(GamepadKeys.Button.A).whenPressed(() -> duckSpinnerSubsystem.spin());
        driverOp.getGamepadButton(GamepadKeys.Button.B).whenPressed(() -> duckSpinnerSubsystem.stopSpin());
    }
}
