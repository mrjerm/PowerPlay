package org.firstinspires.ftc.teamcode.drive.opmode.Jerm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOp_Jeremy extends OpMode {
    public enum TurretState{
        NORTH,
        NORTHEAST,
        EAST,
        SOUTHEAST,
        SOUTH,
        SOUTHWEST,
        WEST,
        NORTHWEST;
        public TurretState next(){
            switch (this){
                case NORTH: return NORTHEAST;
                case NORTHEAST: return EAST;
                case EAST: return SOUTHEAST;
                case SOUTHEAST: return SOUTH;
                case SOUTH: return SOUTH;
                case SOUTHWEST: return WEST;
                case WEST: return NORTHWEST;
                case NORTHWEST: return NORTH;
                default: return NORTH;
            }
        }
        public TurretState previous(){
            switch (this){
                case NORTHWEST: return WEST;
                case WEST: return SOUTHWEST;
                case SOUTHWEST: return SOUTH;
                case SOUTH: return SOUTH;
                case SOUTHEAST: return EAST;
                case EAST: return NORTHEAST;
                case NORTHEAST: return NORTH;
                case NORTH: return NORTHWEST;
                default: return NORTH;
            }
        }
    }
    TurretState turretState = TurretState.NORTH;



    public DcMotorEx motorFL, motorBL, motorFR, motorBR;
    public DcMotorEx motorDR4B;

    public Servo servoTurret;
    public Servo servoGrabber;
    public Servo servoV4BL, servoV4BR;

//    public CRServo servoIntake;
//    public Servo servoV4B;

    public double min = 0.5;
    public double max = 1;
    public double speedLimit = min;

    public double grabberClose = 0.35;
    public double grabberOpen = 0.25;

    public double south1 = 0, southwest = 0.12, west = 0.23, northwest = 0.36, north = 0.5, northeast = 0.62, east = 0.75, southeast = 0.87, south2 = 1;

    public boolean turretLeftPrevious = false;
    public boolean turretLeftCurrent = false;
    public boolean turretRightPrevious = false;
    public boolean turretRightCurrent = false;

    @Override
    public void init() {
        motorFL = hardwareMap.get(DcMotorEx.class, "Motor FL");
        motorBL = hardwareMap.get(DcMotorEx.class, "Motor BL");
        motorFR = hardwareMap.get(DcMotorEx.class, "Motor FR");
        motorBR = hardwareMap.get(DcMotorEx.class, "Motor BR");
        motorDR4B = hardwareMap.get(DcMotorEx.class, "Motor DR4B");

        servoTurret = hardwareMap.get(Servo.class, "Servo Turret");
        servoGrabber = hardwareMap.get(Servo.class, "Servo Intake");
        servoV4BL = hardwareMap.get(Servo.class, "Servo V4BL");
        servoV4BR = hardwareMap.get(Servo.class, "Servo V4BR");

    }

    @Override
    public void loop() {
        setSpeedLimit(gamepad1.y, gamepad1.a);
        drive();
        turret(gamepad2.left_bumper, gamepad2.right_bumper);
        grabber(gamepad2.left_trigger > 0.3, gamepad2.right_trigger > 0.3);
    }

    public void setSpeedLimit(boolean fast, boolean slow){
        if (fast){
            speedLimit = max;
        }
        else if (slow){
            speedLimit = min;
        }
    }

    public void drive() {
        float x1 = gamepad1.left_stick_x;
        float y1 = -gamepad1.left_stick_y;
        float x2 = gamepad1.right_stick_x;

        double fl = x1 + y1 + x2;
        double bl = -x1 + y1 + x2;
        double fr = -x1 + y1 - x2;
        double br = x1 + y1 - x2;

        motorFL.setPower(fl * speedLimit);
        motorBL.setPower(bl * speedLimit);
        motorFR.setPower(fr * speedLimit);
        motorBR.setPower(br * speedLimit);
    }

    public void turret(boolean left, boolean right){
        turretLeftCurrent = left;
        if (turretLeftCurrent && !turretLeftPrevious){
            turretState.previous();
        }
        turretLeftPrevious = turretLeftCurrent;

        turretRightCurrent = right;
        if (turretRightCurrent && !turretRightPrevious){
            turretState.next();
        }
        turretRightPrevious = turretRightCurrent;

        switch (turretState){
            case SOUTH:
                if (servoTurret.getPosition() > 0.5){
                    servoTurret.setPosition(south1);
                }
                else {
                    servoTurret.setPosition(south2);
                }
                break;
            case SOUTHEAST:
                servoTurret.setPosition(southeast);
                break;
            case EAST:
                servoTurret.setPosition(east);
                break;
            case NORTHEAST:
                servoTurret.setPosition(northeast);
                break;
            case NORTH:
                servoTurret.setPosition(north);
                break;
            case NORTHWEST:
                servoTurret.setPosition(northwest);
                break;
            case WEST:
                servoTurret.setPosition(west);
                break;
            case SOUTHWEST:
                servoTurret.setPosition(southwest);
                break;
            default:
                telemetry.addData("turret status", "we messed up 💀");
                telemetry.update();
        }
    }

    public void grabber(boolean open, boolean close){
        if (open){
            servoGrabber.setPosition(grabberOpen);
        }
        if (close){
            servoGrabber.setPosition(grabberClose);
        }
    }
}
