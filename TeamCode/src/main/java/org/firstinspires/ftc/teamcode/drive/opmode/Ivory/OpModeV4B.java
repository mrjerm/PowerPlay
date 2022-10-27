package org.firstinspires.ftc.teamcode.drive.opmode.Ivory;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemV4B2;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "V4B OpMode")
public class OpModeV4B extends CommandOpMode {

    public static Servo leftServo;
    public static Servo rightServo;
    private SubsystemV4B2 subsystemV4B2;
    private GamepadEx driverOp;

    @Override
    public void initialize() {
        leftServo = hardwareMap.get(Servo.class, "Left V4B Servo");
        rightServo = hardwareMap.get(Servo.class, "Right V4B Servo");

        driverOp = new GamepadEx(gamepad1);

        subsystemV4B2 = new SubsystemV4B2(leftServo, rightServo);

        driverOp.getGamepadButton(GamepadKeys.Button.X).whenPressed(() -> subsystemV4B2.LIFT_IN());
        driverOp.getGamepadButton(GamepadKeys.Button.Y).whenPressed(() -> subsystemV4B2.LIFT_UP());
        driverOp.getGamepadButton(GamepadKeys.Button.B).whenPressed(() -> subsystemV4B2.LIFT_OUT());
        driverOp.getGamepadButton(GamepadKeys.Button.A).whenPressed(() -> subsystemV4B2.LIFT_DOWN());
    }

}
