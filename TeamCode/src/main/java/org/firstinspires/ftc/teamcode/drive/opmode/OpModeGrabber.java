package org.firstinspires.ftc.teamcode.drive.opmode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.commands.grabber.CommandCloseGrabber;
import org.firstinspires.ftc.teamcode.drive.commands.grabber.CommandOpenGrabber;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemGrabber;

@TeleOp(name="Handcuff Grabber (L)")
public class OpModeGrabber extends CommandOpMode {

    @Override
    public void initialize() {
        SubsystemGrabber subsystem = new SubsystemGrabber(hardwareMap, "Servo Grabber");
        GamepadEx driverOp = new GamepadEx(gamepad1);

        GamepadButton lBumper = new GamepadButton(driverOp, GamepadKeys.Button.LEFT_BUMPER);
        GamepadButton rBumper = new GamepadButton(driverOp, GamepadKeys.Button.RIGHT_BUMPER);

        lBumper.whenPressed(new CommandOpenGrabber(subsystem));
        rBumper.whenPressed(new CommandCloseGrabber(subsystem));

        register(subsystem);
    }


}
