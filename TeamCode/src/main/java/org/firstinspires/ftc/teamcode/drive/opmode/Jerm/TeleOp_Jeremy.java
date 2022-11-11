package org.firstinspires.ftc.teamcode.drive.opmode.Jerm;

import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.*;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOp_Jeremy extends OpMode {
/*TODO: REMOVE 45 DEGREE TURRET POSITIONS*/
    /*TODO: GRABBER CLOSE DURING INIT AND RETRACT*/
/*TODO: DR4B BRAKING TOO WEAK, CHANGE TOLERANCE FROM 5 TO 10 TICKS, DR4B JITTERING*/
    /*TODO: V4B AUTOLIFT WHEN TURNING TURRET, SEPARATE STATE MACHINE*/
    /*TODO: DRIVING TEST TO MAKE SURE ROBOT DOES NOT TIP OVER*/

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
    public boolean upPrevious = false;
    public boolean downPrevious = false;

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
        HIGH;
        public DR4BState next(){
            switch (this){
                case REST: return LOW;
                case LOW: return MID;
                case MID: return HIGH;
                case HIGH: return HIGH;
                default: return REST;
            }
        }
        public DR4BState previous(){
            switch (this){
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
        HIGH,
        MID,
        LOW,
        GROUND,
        FLOOR,
        TURRETCLEARANCE,
        HORIZONTAL;

    }

    V4BState v4BState = V4BState.RETRACTED;

    public enum RobotState{
        PICKING_UP,
        GROUND_JUNCTION,
        LOW_JUNCTION,
        MEDIUM_JUNCTION,
        HIGH_JUNCTION,
        RETRACT;
        public RobotState next(){
            switch (this){
                case RETRACT: return PICKING_UP;
                case PICKING_UP: return GROUND_JUNCTION;
                case GROUND_JUNCTION: return LOW_JUNCTION;
                case LOW_JUNCTION: return MEDIUM_JUNCTION;
                case MEDIUM_JUNCTION: return HIGH_JUNCTION;
                case HIGH_JUNCTION: return HIGH_JUNCTION;
                default: return RETRACT;
            }
        }
        public RobotState previous(){
            switch (this){
                case HIGH_JUNCTION: return MEDIUM_JUNCTION;
                case MEDIUM_JUNCTION: return LOW_JUNCTION;
                case LOW_JUNCTION: return GROUND_JUNCTION;
                case GROUND_JUNCTION: return PICKING_UP;
                case PICKING_UP: return RETRACT;
                case RETRACT: return RETRACT;
                default: return RETRACT;
            }
        }
    }

    RobotState robotState = RobotState.RETRACT;

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

        servoV4BL.setPosition(V4B_RETRACTED);
        servoV4BR.setPosition(V4B_RETRACTED);
    }

    @Override
    public void loop() {
        turtle(gamepad1.y, gamepad1.a);
        drive();
        spinny(gamepad2.left_bumper, gamepad2.right_bumper);
        grippers(gamepad2.left_trigger > 0.3, gamepad2.right_trigger > 0.3);
        setRobotState(gamepad2.dpad_up, gamepad2.dpad_down);
        liftControl();
        v4bControl();
/*        lift(gamepad2.dpad_up, gamepad2.dpad_down);
        stick(gamepad2.y, gamepad2.a);*/
    }

    public void setRobotState(boolean up, boolean down){
        boolean upCurrent = up;
        if (upCurrent && !upPrevious){
            robotState = robotState.next();
        }
        upPrevious = upCurrent;

        boolean downCurrent = down;
        if (downCurrent && !downPrevious){
            robotState = robotState.previous();
        }
        downPrevious = downCurrent;
    }

    public void liftControl(){
        switch (robotState){
            case PICKING_UP:
                dr4BState = DR4BState.REST;
                break;
            case GROUND_JUNCTION:
                dr4BState = DR4BState.REST;
                break;
            case LOW_JUNCTION:
                dr4BState = DR4BState.LOW;
                break;
            case MEDIUM_JUNCTION:
                dr4BState = DR4BState.MID;
                break;
            case HIGH_JUNCTION:
                dr4BState = DR4BState.HIGH;
                break;
            case RETRACT:
                dr4BState = DR4BState.REST;
                break;
            default:
                dr4BState = DR4BState.REST;
        }

        switch (dr4BState){
            case REST:
                setLiftPosition(DR4B_GROUNDFLOORTURRETCLEARANCE);
                break;
            case LOW:
                setLiftPosition(DR4B_LOWJUNCTION);
                break;
            case MID:
                setLiftPosition(DR4B_MIDHIGHJUNCTION);
                break;
            case HIGH:
                setLiftPosition(DR4B_MIDHIGHJUNCTION);
                break;
            default:
                setLiftPosition(DR4B_GROUNDFLOORTURRETCLEARANCE);
        }
    }

    public void v4bControl(){
        switch (robotState){
            case PICKING_UP:
                v4BState = V4BState.FLOOR;
                break;
            case GROUND_JUNCTION:
                v4BState = V4BState.GROUND;
                break;
            case LOW_JUNCTION:
                v4BState = V4BState.LOW;
                break;
            case MEDIUM_JUNCTION:
                v4BState = V4BState.MID;
                break;
            case HIGH_JUNCTION:
                v4BState = V4BState.HIGH;
                break;
            case RETRACT:
                v4BState = V4BState.RETRACTED;
                break;
            default:
                v4BState = V4BState.RETRACTED;
        }

        switch (v4BState){
            case RETRACTED:
                setV4B(V4B_RETRACTED);
                servoGrabber.setPosition(grabberClose);
                break;
            case HIGH:
                setV4B(V4B_HIGHJUNCTION);
                break;
            case MID:
                setV4B(V4B_LOWMIDFLOOR);
                break;
            case LOW:
                setV4B(V4B_LOWMIDFLOOR);
                break;
            case GROUND:
                setV4B(V4B_GROUNDJUNCTION);
                break;
            case FLOOR:
                setV4B(V4B_LOWMIDFLOOR);
                break;
            case TURRETCLEARANCE:
                setV4B(V4B_TURRETCLEARANCE);
                break;
            case HORIZONTAL:
                setV4B(V4B_HORIZONTAL);
                break;
            default:
                setV4B(V4B_RETRACTED);
        }
    }

    public void turtle(boolean fast, boolean slow){
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

    public void spinny(boolean left, boolean right){
        if (v4BState != V4BState.GROUND && v4BState != V4BState.FLOOR) {
            boolean turretLeftCurrent = left;
            if (turretLeftCurrent && !turretLeftPrevious) {
                turretState = turretState.previous();
            }
            turretLeftPrevious = turretLeftCurrent;

            boolean turretRightCurrent = right;
            if (turretRightCurrent && !turretRightPrevious) {
                turretState = turretState.next();
            }
            turretRightPrevious = turretRightCurrent;

            switch (turretState) {
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



    public void setLiftPosition(int position){
        motorDR4B.setTargetPosition(position);
        if (Math.abs(motorDR4B.getCurrentPosition() - motorDR4B.getTargetPosition()) < 5){
            motorDR4B.setPower(0);
        } else {
            motorDR4B.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorDR4B.setPower(1);
        }
    }

    public void setV4B(double position){
        servoV4BL.setPosition(position);
        servoV4BR.setPosition(position);
    }
}