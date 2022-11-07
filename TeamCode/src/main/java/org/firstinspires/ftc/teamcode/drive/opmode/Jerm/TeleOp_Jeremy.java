package org.firstinspires.ftc.teamcode.drive.opmode.Jerm;

import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.*;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOp_Jeremy extends OpMode {


    public DcMotorEx motorFL, motorBL, motorFR, motorBR;
    public DcMotorEx motorDR4B;

    public Servo servoTurret;
    public Servo servoGrabber;
    public Servo servoV4BL, servoV4BR;

//    public CRServo servoIntake;
//    public Servo servoV4B;



    public boolean turretLeftPrevious = false;
    public boolean turretRightPrevious = false;
    public boolean moveUpPrevious = false;
    public boolean moveDownPrevious = false;
    public boolean extendPrevious = false;
    public boolean retractPrevious = false;

    public enum TurretState{
        SOUTH1,
        SOUTHWEST,
        WEST,
        NORTHWEST,
        NORTH,
        NORTHEAST,
        EAST,
        SOUTHEAST,
        SOUTH2;
        public TurretState next(){
            switch (this){
                case NORTH: return NORTHEAST;
                case NORTHEAST: return EAST;
                case EAST: return SOUTHEAST;
                case SOUTHEAST: return SOUTH2;
                case SOUTH2: return SOUTH2;
                case SOUTH1: return SOUTHWEST;
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
                case SOUTHWEST: return SOUTH1;
                case SOUTH1: return SOUTH1;
                case SOUTH2: return SOUTHEAST;
                case SOUTHEAST: return EAST;
                case EAST: return NORTHEAST;
                case NORTHEAST: return NORTH;
                case NORTH: return NORTHWEST;
                default: return NORTH;
            }
        }
    }
    TurretState turretState = TurretState.NORTH;

    public enum DR4BState{
        REST,
        LOW,
        MID,
        HIGH,
        MAX;
        public DR4BState next(){
            switch (this){
                case REST: return LOW;
                case LOW: return MID;
                case MID: return HIGH;
                case HIGH: return MAX;
                case MAX: return MAX;
                default: return REST;
            }
        }
        public DR4BState previous(){
            switch (this){
                case MAX: return HIGH;
                case HIGH: return MID;
                case MID: return LOW;
                case LOW: return REST;
                case REST: return REST;
                default: return REST;
            }
        }
    }

    DR4BState dr4BState = DR4BState.REST;

    public enum V4BState{
        RETRACTED,
        PEAK,
        DIAGONAL,
        EXTENDED,
        DOWN;
        public V4BState next(){
            switch (this){
                case RETRACTED: return PEAK;
                case PEAK: return DIAGONAL;
                case DIAGONAL: return EXTENDED;
                case EXTENDED: return DOWN;
                case DOWN: return DOWN;
                default: return RETRACTED;
            }
        }
        public V4BState previous(){
            switch (this){
                case DOWN: return EXTENDED;
                case EXTENDED: return DIAGONAL;
                case DIAGONAL: return PEAK;
                case PEAK: return RETRACTED;
                case RETRACTED: return RETRACTED;
                default: return RETRACTED;
            }
        }
    }

    V4BState v4BState = V4BState.RETRACTED;

    @Override
    public void init() {
        motorFL = hardwareMap.get(DcMotorEx.class, "Motor FL");
        motorBL = hardwareMap.get(DcMotorEx.class, "Motor BL");
        motorFR = hardwareMap.get(DcMotorEx.class, "Motor FR");
        motorBR = hardwareMap.get(DcMotorEx.class, "Motor BR");
        motorFL.setDirection(DcMotorEx.Direction.REVERSE);
        motorBL.setDirection(DcMotorEx.Direction.REVERSE);

        motorDR4B = hardwareMap.get(DcMotorEx.class, "Motor DR4B");
        motorDR4B.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motorDR4B.setDirection(DcMotorEx.Direction.REVERSE);
        motorDR4B.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        servoTurret = hardwareMap.get(Servo.class, "Servo Turret");
        servoGrabber = hardwareMap.get(Servo.class, "Servo Intake");
        servoV4BL = hardwareMap.get(Servo.class, "Servo V4BL");
        servoV4BR = hardwareMap.get(Servo.class, "Servo V4BR");
        servoV4BL.setDirection(Servo.Direction.REVERSE);

        servoV4BL.setPosition(0.18);
        servoV4BR.setPosition(0.18);
    }

    @Override
    public void loop() {
        turtle(gamepad1.y, gamepad1.a);
        swerve();
        spinny(gamepad2.left_bumper, gamepad2.right_bumper);
        grippers(gamepad2.left_trigger > 0.3, gamepad2.right_trigger > 0.3);
        thrust(gamepad2.dpad_up, gamepad2.dpad_down);
        stick(gamepad2.y, gamepad2.a);
    }

    public void turtle(boolean fast, boolean slow){
        if (fast){
            speedLimit = max;
        }
        else if (slow){
            speedLimit = min;
        }
    }

    public void swerve() {
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

    public void spinny(boolean left, boolean right){
        boolean turretLeftCurrent = left;
        if (turretLeftCurrent && !turretLeftPrevious){
            turretState = turretState.previous();
        }
        turretLeftPrevious = turretLeftCurrent;

        boolean turretRightCurrent = right;
        if (turretRightCurrent && !turretRightPrevious){
            turretState = turretState.next();
        }
        turretRightPrevious = turretRightCurrent;

        switch (turretState){
            case SOUTH1:
                servoTurret.setPosition(south1);
                break;
            case SOUTH2:
                servoTurret.setPosition(south2);
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
        telemetry.addData("turret state", turretState);
        telemetry.update();
    }

    public void grippers(boolean open, boolean close){
        if (open){
            servoGrabber.setPosition(grabberOpen);
        }
        if (close){
            servoGrabber.setPosition(grabberClose);
        }
    }

    public void thrust(boolean up, boolean down){
        boolean moveUpCurrent = up;
        if (moveUpCurrent && !moveUpPrevious){
            dr4BState = dr4BState.next();
        }
        moveUpPrevious = moveUpCurrent;

        boolean moveDownCurrent = down;
        if (moveDownCurrent && !moveDownPrevious){
            dr4BState = dr4BState.previous();
        }
        moveDownPrevious = moveDownCurrent;

        switch (dr4BState){
            case REST:
                setLiftPosition(DR4B_REST);
                break;
            case LOW:
                setLiftPosition(DR4B_LOW);
                break;
            case MID:
                setLiftPosition(DR4B_MID);
                break;
            case HIGH:
                setLiftPosition(DR4B_HIGH);
                break;
            case MAX:
                setLiftPosition(DR4B_MAX);
                break;
            default:
                telemetry.addData("lift is broken", "☹️☹️☹️☹️☹️☹️☹️");
                telemetry.update();
        }
        telemetry.addData("dr4b", dr4BState);
        telemetry.update();
    }

    public void setLiftPosition(int position){
        motorDR4B.setTargetPosition(position);
        motorDR4B.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorDR4B.setPower(1);
    }

    public void stick(boolean extend, boolean retract){
        boolean extendCurrent = extend;
        if (extendCurrent && !extendPrevious){
            v4BState = v4BState.next();
        }
        extendPrevious = extendCurrent;

        boolean retractCurrent = retract;
        if (retractCurrent && !retractPrevious){
            v4BState = v4BState.previous();
        }
        retractPrevious = retractCurrent;

        switch (v4BState){
            case RETRACTED:
                setV4B(V4B_RETRACTED);
                break;
            case PEAK:
                setV4B(V4B_PEAK);
                break;
            case DIAGONAL:
                setV4B(V4B_DIAGONAL);
                break;
            case EXTENDED:
                setV4B(V4B_EXTENDED);
                break;
            case DOWN:
                setV4B(V4B_DOWN);
                break;
            default:
                telemetry.addData("v4b is broken", "😮😮😮😮😮");
                telemetry.update();
        }
    }

    public void setV4B(double position){
        servoV4BL.setPosition(position);
        servoV4BR.setPosition(position);
    }
}
