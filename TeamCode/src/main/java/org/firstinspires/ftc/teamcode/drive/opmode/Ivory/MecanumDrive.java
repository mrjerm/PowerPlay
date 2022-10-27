package org.firstinspires.ftc.teamcode.drive.opmode.Ivory;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MecanumDrive extends LinearOpMode {

    DcMotor MotorFL;
    DcMotor MotorFR;
    DcMotor MotorBL;
    DcMotor MotorBR;

    @Override
    public void runOpMode() throws InterruptedException {
        double vertical = 0.0;
        double horizontal = 0.0;
        double pivot = 0.0;

        //see bottom

        MotorFL = hardwareMap.get(DcMotor.class, "Motor FL");
        MotorFR = hardwareMap.get(DcMotor.class, "Motor FR");
        MotorBL = hardwareMap.get(DcMotor.class,"Motor BL");
        MotorBR = hardwareMap.get(DcMotor.class,"Motor BR");


        waitForStart();
        //@Override
        while (opModeIsActive()) {

            vertical = gamepad1.left_stick_y;
            horizontal = gamepad1.left_stick_x;
            pivot = gamepad1.right_stick_x;

            MotorFL.setPower(pivot + (vertical + horizontal));
            MotorFR.setPower(pivot + (vertical - horizontal));
            MotorBL.setPower(pivot + (-vertical + horizontal));
            MotorBR.setPower(pivot + (-vertical - horizontal));
        /*fix this D: */
        MotorFL.setDirection(DcMotor.Direction.REVERSE);
        MotorFR.setDirection(DcMotor.Direction.REVERSE);


        }


        //@Override
        }
    }
