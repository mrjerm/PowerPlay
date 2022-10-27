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
//import org.firstinspires.ftc.teamcode.drive.commands.CommandTurret;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemDrive;
//import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemTurret;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemIntakeDeroak;


@TeleOp
public class Testing extends CommandOpMode {

    //declaring motors and servos
    private Motor frontLeft;
    private Motor frontRight;
    private Motor backLeft;
    private Motor backRight;
//    private Motor turretLeft;
//    private Motor turretRight;
//    private Servo turretServo;
    private Servo intake1;
    private Servo intake2;


    //declaring subsystems and commands
    private SubsystemDrive subsystemDrive;
    private CommandDrive commandDrive;

//    private SubsystemTurret subsystemTurret;
//    private CommandTurret commandTurret;

//    private SubsystemIntake subsystemIntake;
//    private CommandIntake commandIntake;

    private SubsystemIntakeDeroak subsystemIntakeDeroak;

    //threads
//    public Thread runTurretThread;
//    public Thread stopTurretThread;

    //instant commands
//    private InstantCommand instantCommandRunTurret;
//    private InstantCommand instantCommandMoveServoPositive;
//    private InstantCommand instantCommandMoveServoNegative;
//    private InstantCommand instantCommandOpenIntake;
//    private InstantCommand instantCommandCloseIntake;
    private InstantCommand instantCommandOpenDeroakIntake;
    private InstantCommand instantCommandCloseDeroakIntake;

    //Declaring gamepads
    GamepadEx driver1;
    GamepadEx driver2;

    @Override

    public void initialize() {

        //initialize motors
        frontLeft = new Motor(hardwareMap, "Motor FL");
        frontRight = new Motor(hardwareMap, "Motor FR");
        backLeft = new Motor(hardwareMap, "Motor BL");
        backRight = new Motor(hardwareMap, "Motor BR");
//        turretLeft = new Motor(hardwareMap, "Motor Turret Left");
//        turretRight = new Motor(hardwareMap, "Motor Turret Right");



        //set zero power behavior
        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        //initialize servos
//        turretServo = hardwareMap.get(Servo.class, "Servo Turret");
        intake1 = hardwareMap.get(Servo.class, "intake1");
        intake2 = hardwareMap.get(Servo.class, "intake2");

        //set servos
//        turretServo.setPosition(0.5);
        intake1.setPosition(0);
        intake2.setPosition(0);

        //Assign drivers to gamepads
        driver1 = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);

        //Initialize subsystems and commands
        subsystemDrive = new SubsystemDrive(frontLeft, frontRight, backLeft, backRight);
        commandDrive = new CommandDrive(subsystemDrive, driver1::getLeftY, driver1::getLeftX, driver1::getRightX);
//
//        subsystemTurret = new SubsystemTurret(turretLeft, turretRight, turretServo);
//        commandTurret = new CommandTurret(subsystemTurret);
//        subsystemIntake = new SubsystemIntake(intake1, intake2);
//        commandIntake = new CommandIntake(subsystemIntake);

        subsystemIntakeDeroak = new SubsystemIntakeDeroak(intake1, intake2);

        telemetry.addData("servo", intake1.getPosition());
        telemetry.addData("2servo", intake2.getPosition());
        //THREADS

        //INSTANT COMMANDS
//        instantCommandRunTurret = new InstantCommand(() -> {
//            if((turretLeft.get() != 0) & (turretRight.get() != 0))
//                subsystemTurret.stopTurret();
//            else
//                subsystemTurret.runTurret();
//        },subsystemTurret);
//
//        instantCommandMoveServoNegative = new InstantCommand(() -> {
//            subsystemTurret.moveTurretServo(-0.05);
//        },subsystemTurret);
//
//        instantCommandMoveServoPositive = new InstantCommand(() -> {
//            subsystemTurret.moveTurretServo(0.05);
//        },subsystemTurret);
//        instantCommandOpenIntake = new InstantCommand(() -> {
//            subsystemIntake.openIntake();
//        },subsystemIntake);
//        instantCommandCloseIntake = new InstantCommand(() -> {
//            subsystemIntake.closeIntake();
//        },subsystemIntake);

        instantCommandOpenDeroakIntake = new InstantCommand( () -> {
            subsystemIntakeDeroak.openIntake();
        }, subsystemIntakeDeroak);

        instantCommandCloseDeroakIntake = new InstantCommand(() -> {
            subsystemIntakeDeroak.closeIntake();
        }, subsystemIntakeDeroak);


        //Assign things to buttons
        //Button turretButton = new GamepadButton(driver1, GamepadKeys.Button.A).whenPressed(() -> runTurretThread.start());
        //Button turretButton2 = new GamepadButton(driver1, GamepadKeys.Button.X).whenPressed(() -> stopTurretThread.start());
//        Button turretButton = new GamepadButton(driver1, GamepadKeys.Button.A).whenPressed(instantCommandRunTurret);
//        Button moveTurretServoPositive = new GamepadButton(driver1, GamepadKeys.Button.DPAD_UP).whenPressed(instantCommandMoveServoPositive);
//        Button moveTurretServoNegative = new GamepadButton(driver1, GamepadKeys.Button.DPAD_DOWN).whenPressed(instantCommandMoveServoNegative);
//        Button openIntake = new GamepadButton(driver1, GamepadKeys.Button.A).whenPressed(instantCommandOpenIntake);
//        Button closeIntake = new GamepadButton(driver1, GamepadKeys.Button.DPAD_DOWN).whenPressed(instantCommandCloseIntake);
        Button openDeroakIntake = new GamepadButton(driver1, GamepadKeys.Button.A).whenPressed(instantCommandOpenDeroakIntake);
        Button closeDeroakIntake = new GamepadButton(driver1, GamepadKeys.Button.B).whenPressed(instantCommandCloseDeroakIntake);


        //Register things
        register(subsystemDrive, subsystemIntakeDeroak/*, subsystemTurret subsystemIntake*/);
        subsystemDrive.setDefaultCommand(commandDrive);

    }
}