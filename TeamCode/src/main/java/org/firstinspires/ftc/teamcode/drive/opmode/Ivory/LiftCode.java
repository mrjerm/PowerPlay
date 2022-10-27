package org.firstinspires.ftc.teamcode.drive.opmode.Ivory;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class LiftCode extends LinearOpMode {

    final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    final double     TURN_SPEED              = 0.5;

    DcMotor liftMotor;

    public void thing1() {



        liftMotor = hardwareMap.get(DcMotor.class, "Motor thing");

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status","Resetting Encoders");
        telemetry.update();

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    private void encoderDrive(double TURN_SPEED, int i, int i1, double i2, double i3){


        boolean button1 = gamepad1.x;
        boolean button2 = gamepad1.a;
        boolean button3 = gamepad1.y;
        boolean button4 = gamepad1.b;

        int position1 = liftMotor.getCurrentPosition() + (int) (i * COUNTS_PER_INCH);

        int position2 = liftMotor.getCurrentPosition() + (int) (i1 * COUNTS_PER_INCH);

        int position3 = liftMotor.getCurrentPosition() + (int) (i2 * COUNTS_PER_INCH);

        int position4 = liftMotor.getCurrentPosition() + (int) (i3 * COUNTS_PER_INCH);


        if (button1) {
            liftMotor.setTargetPosition(position1);

        }

        if (button2) {

            liftMotor.setTargetPosition(position2);

        }

        if (button3) {

            liftMotor.setTargetPosition(position3);
        }

        if (button4) {
            liftMotor.setTargetPosition(position4);
        }
    }
    @Override
    public void runOpMode() {

        thing1();

        encoderDrive(TURN_SPEED, 10, 20, 30, 40);

        ElapsedTime runtime = new ElapsedTime();



    }





    }
