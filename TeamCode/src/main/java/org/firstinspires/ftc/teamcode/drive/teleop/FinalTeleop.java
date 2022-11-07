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
import org.firstinspires.ftc.teamcode.drive.commands.CommandTurret;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemDrive;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemDR4B;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemIntakeDeroak;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemV4B;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemTurret;

@TeleOp
public class FinalTeleop extends CommandOpMode{

    // Declare Motors and Servos
    private Motor frontLeft;
    private Motor frontRight;
    private Motor backLeft;
    private Motor backRight;

    private Motor liftMotor;

    private Servo turretServo;
    private Servo fourBarServoLeft;
    private Servo fourBarServoRight;
    private Servo deroakServo;


    // Declare subsystems and commands
    private SubsystemDrive subsystemDrive;
    private CommandDrive commandDrive;

    private SubsystemDR4B subsystemDR4B;

    private SubsystemV4B subsystemV4B;

    private SubsystemTurret subsystemTurret;
//    private CommandTurret commandTurret;

    private SubsystemIntakeDeroak subsystemIntakeDeroak;

    //instant commands
    private InstantCommand instantCommandTurretPositive;
    private InstantCommand instantCommandTurretNegative;

    private InstantCommand instantCommandDeroakOpen;
    private InstantCommand instantCommandDeroakClose;

    private InstantCommand instantCommandDR4BUp;
    private InstantCommand instantCommandDR4BDown;

    private InstantCommand instantCommandV4BPositive;
    private InstantCommand instantCommandV4BNegative;

    private InstantCommand InstantCommandSwitchHighOrMid;
    private InstantCommand InstantCommandSwitchLowOrGround;



    //threads
    public Thread HighOrMid;
    public Thread LowOrGround;
    public Thread highJunction;
    public Thread midJunction;
    public Thread lowJunction;
    public Thread groundJunction;
    public Thread scoringThread;


    //declare gamepads
    GamepadEx driver1;
    GamepadEx driver2;

    //switch
    boolean HighOrMidBoolean = false;
    boolean LowOrGroundBoolean = false;

    @Override
    public void initialize() {

        //declare motors
        frontLeft = new Motor(hardwareMap, "Motor FL");
        frontRight = new Motor(hardwareMap, "Motor FR");
        backLeft = new Motor(hardwareMap, "Motor BL");
        backRight = new Motor(hardwareMap, "Motor BR");

        liftMotor = new Motor(hardwareMap, "Motor DR4B");

        //set zero power behavior
        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        liftMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        // Reset encoders

        //invert motors
        liftMotor.setInverted(true);

        //declare servos
        turretServo = hardwareMap.get(Servo.class, "Servo Turret");

        fourBarServoLeft = hardwareMap.get(Servo.class, "Servo V4BL");
        fourBarServoRight = hardwareMap.get(Servo.class, "Servo V4BR");

        deroakServo = hardwareMap.get(Servo.class, "Servo Intake");

        //invert servos
        fourBarServoLeft.setDirection(Servo.Direction.REVERSE);

        //home servos
        deroakServo.setPosition(0.38);
        fourBarServoLeft.setPosition(0.18);
        fourBarServoRight.setPosition(0.18);

        // Assign gamepads to drivers
        driver1 = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);


        // Initialize subsystems and commands

        subsystemDrive = new SubsystemDrive(frontLeft, frontRight, backLeft, backRight);
        commandDrive = new CommandDrive(subsystemDrive, driver1::getLeftY, driver1::getLeftX, driver1::getRightX);

        subsystemDR4B = new SubsystemDR4B(liftMotor);

        subsystemTurret = new SubsystemTurret(turretServo);
//        commandTurret = new CommandTurret(subsystemTurret, driver2::getRightX, driver2::getRightY);

        subsystemV4B = new SubsystemV4B(fourBarServoLeft, fourBarServoRight);

        subsystemIntakeDeroak = new SubsystemIntakeDeroak(deroakServo);

        //threads

//        HighOrMid = new Thread(() -> {
//            if (!HighOrMidBoolean) {
//                subsystemV4B.fourBarMoving = true;
//                subsystemDR4B.liftHigh();
//                subsystemV4B.fourBarHigh();
//            } else {
//                subsystemV4B.fourBarMoving = true;
//                subsystemDR4B.liftMid();
//                subsystemV4B.fourBarHorizontal();
//            }
//        });
//
//        LowOrGround = new Thread(() -> {
//            if (!LowOrGroundBoolean) {
//                subsystemV4B.fourBarMoving = true;
//                subsystemDR4B.liftLow();
//                subsystemV4B.fourBarHorizontal();
//            } else {
//                subsystemV4B.fourBarMoving = true;
//                subsystemDR4B.liftRest();
//                subsystemV4B.fourBarHorizontal();
//            }
//        });

        highJunction = new Thread(() -> {
            subsystemV4B.fourBarMoving = true;
            subsystemDR4B.liftHigh();
            subsystemV4B.fourBarDiagonal();
        });

        midJunction = new Thread(() -> {
            subsystemV4B.fourBarMoving = true;
            subsystemDR4B.liftMid();
            subsystemV4B.fourBarHorizontal();
        });

        lowJunction = new Thread(() -> {
            subsystemV4B.fourBarMoving = true;
            subsystemDR4B.liftLow();
            subsystemV4B.fourBarHorizontal();
        });

        groundJunction = new Thread(() -> {
            subsystemV4B.fourBarMoving = true;
            subsystemDR4B.liftRest();
            subsystemV4B.fourBarHorizontal();
        });

        scoringThread = new Thread(() -> {
            subsystemIntakeDeroak.openIntake();
            sleep(200);

            subsystemIntakeDeroak.closeIntake();
            subsystemV4B.fourBarHigh();
            subsystemDR4B.liftRest();
        });



        //instant commands
        instantCommandTurretPositive = new InstantCommand(() -> {
            subsystemTurret.moveTurretPositive();
        }, subsystemTurret);

        instantCommandTurretNegative = new InstantCommand(() -> {
            subsystemTurret.moveTurretNegative();
        }, subsystemTurret);

        instantCommandDeroakOpen = new InstantCommand(() -> {
            subsystemIntakeDeroak.openIntake();
        }, subsystemIntakeDeroak);

        instantCommandDeroakClose = new InstantCommand(() -> {
            subsystemIntakeDeroak.closeIntake();
        }, subsystemIntakeDeroak);

        instantCommandDR4BUp = new InstantCommand(() -> {
            subsystemDR4B.moveLiftUp();
        }, subsystemDR4B);

        instantCommandDR4BDown = new InstantCommand(() -> {
            subsystemDR4B.moveLiftDown();
        }, subsystemDR4B);

        instantCommandV4BPositive = new InstantCommand(() -> {
            subsystemV4B.v4BPositive();
        }, subsystemV4B);

        instantCommandV4BNegative = new InstantCommand(() -> {
            subsystemV4B.v4BNegative();
        }, subsystemV4B);

        InstantCommandSwitchHighOrMid = new InstantCommand(() -> {
            if (!HighOrMidBoolean)
                HighOrMidBoolean = true;
            else
                HighOrMidBoolean = false;
        });

        InstantCommandSwitchLowOrGround = new InstantCommand(() -> {
            if (!LowOrGroundBoolean)
                LowOrGroundBoolean = true;
            else
                LowOrGroundBoolean = false;
        });



        //buttons

//        Button HighOrMidButton = new GamepadButton(driver1, GamepadKeys.Button.DPAD_UP).whenPressed(() -> HighOrMid.start());
//        Button LowOrGroundButton = new GamepadButton(driver1, GamepadKeys.Button.DPAD_DOWN).whenPressed(() -> LowOrGround.start());
//        Button switchHighOrMid = new GamepadButton(driver1, GamepadKeys.Button.LEFT_BUMPER).whenPressed(InstantCommandSwitchHighOrMid);
//        Button switchLowOrGround = new GamepadButton(driver1, GamepadKeys.Button.RIGHT_BUMPER).whenPressed(InstantCommandSwitchLowOrGround);


        Button highJunctionPosition = new GamepadButton(driver1, GamepadKeys.Button.DPAD_UP).whenPressed(() -> highJunction.start());
        Button midJunctionPosition = new GamepadButton(driver1, GamepadKeys.Button.DPAD_LEFT).whenPressed(() -> midJunction.start());
        Button lowJunctionPosition = new GamepadButton(driver1, GamepadKeys.Button.DPAD_RIGHT).whenPressed(() -> lowJunction.start());
        Button groundJunctionPosition = new GamepadButton(driver1, GamepadKeys.Button.DPAD_DOWN).whenPressed(() -> groundJunction.start());
        Button scoringThreadButton = new GamepadButton(driver1, GamepadKeys.Button.RIGHT_BUMPER).whenPressed(() -> scoringThread.start());

        Button moveTurretPositive = new GamepadButton(driver2, GamepadKeys.Button.DPAD_RIGHT).whenPressed(instantCommandTurretPositive);
        Button moveTurretNegative = new GamepadButton(driver2, GamepadKeys.Button.DPAD_LEFT).whenPressed(instantCommandTurretNegative);
        Button openIntake = new GamepadButton(driver2, GamepadKeys.Button.LEFT_BUMPER).whenPressed(instantCommandDeroakOpen);
        Button closeIntake = new GamepadButton(driver2, GamepadKeys.Button.RIGHT_BUMPER).whenPressed(instantCommandDeroakClose);
        Button DR4BUp = new GamepadButton(driver2, GamepadKeys.Button.DPAD_UP).whenPressed(instantCommandDR4BUp);
        Button DR4BDown = new GamepadButton(driver2, GamepadKeys.Button.DPAD_DOWN).whenPressed(instantCommandDR4BDown);
        Button V4BPositive = new GamepadButton(driver2, GamepadKeys.Button.Y).whenPressed(instantCommandV4BPositive);
        Button V4BNegative = new GamepadButton(driver2, GamepadKeys.Button.A).whenPressed(instantCommandV4BNegative);

        //register
        register(subsystemDrive, subsystemDR4B, subsystemTurret, subsystemV4B, subsystemIntakeDeroak);
        subsystemDrive.setDefaultCommand(commandDrive);
//        subsystemTurret.setDefaultCommand(commandTurret);


    }

}
