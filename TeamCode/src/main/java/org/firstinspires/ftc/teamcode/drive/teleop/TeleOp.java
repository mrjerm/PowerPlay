package org.firstinspires.ftc.teamcode.drive.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.drive.commands.CommandDrive;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemDrive;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemDR4B;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemV4B;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemTurret;

public class TeleOp extends CommandOpMode{

    // Declare Motors and Servos
    private Motor frontLeft;
    private Motor frontRight;
    private Motor backLeft;
    private Motor backRight;

    private Motor liftMotor;

    private Servo turretServo;
    private Servo fourBarServoLeft;
    private Servo fourBarServoRight;


    // Declare subsystems and commands
    private SubsystemDrive subsystemDrive;
    private CommandDrive commandDrive;

    private SubsystemDR4B subsystemDR4B;

    private SubsystemV4B subsystemV4B;

    private SubsystemTurret subsystemTurret;

    //instant commands

    //threads

    //declare gamepads
    GamepadEx driver1;
    GamepadEx driver2;

    @Override
    public void initialize() {
        frontLeft = new Motor(hardwareMap, "Motor FL");
        frontRight = new Motor(hardwareMap, "Motor FR");
        backLeft = new Motor(hardwareMap, "Motor BL");
        backRight = new Motor(hardwareMap, "Motor BR");

        liftMotor = new Motor(hardwareMap, "liftMotor");

        //set zero power behavior
        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        // Reset encoders

        turretServo = hardwareMap.get(Servo.class, "turretServo");
        fourBarServoLeft = hardwareMap.get(Servo.class, "fourBarServoLeft");
        fourBarServoRight = hardwareMap.get(Servo.class, "fourBarServoRight");

        //invert servos

        //home servos

        // Assign gamepads to drivers
        driver1 = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);


        // Initialize subsystems and commands

        subsystemDrive = new SubsystemDrive(frontLeft, frontRight, backLeft, backRight);
        commandDrive = new CommandDrive(subsystemDrive, driver1::getLeftY, driver1::getLeftX, driver1::getRightX);

        subsystemDR4B = new SubsystemDR4B(liftMotor);

        subsystemTurret = new SubsystemTurret(turretServo);

        subsystemV4B = new SubsystemV4B(fourBarServoLeft, fourBarServoRight);

        //threads

        //instant commands

        //buttons


        //register
        register(subsystemDrive, subsystemDR4B, subsystemTurret, subsystemV4B);
        subsystemDrive.setDefaultCommand(commandDrive);


    }

}
