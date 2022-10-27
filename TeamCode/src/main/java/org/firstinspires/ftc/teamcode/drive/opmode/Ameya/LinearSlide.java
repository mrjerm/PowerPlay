package org.firstinspires.ftc.teamcode.drive.opmode.Ameya;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Motor Control", group = "Movement")
public class LinearSlide extends LinearOpMode {

    DcMotor linearSlideMotor;

    //override
    @Override
    public void runOpMode() {
        //map hardware
        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlideMotor");

        linearSlideMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        //linearSlideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double linearSlideMotorPwr; //power for linearSlideMotor

        waitForStart();

        while (opModeIsActive()) {

            linearSlideMotorPwr = 0.25;

            linearSlideMotorPwr = gamepad1.left_stick_y;

            linearSlideMotorPwr = Range.clip(linearSlideMotorPwr, -1, 1);
            linearSlideMotor.setPower(linearSlideMotorPwr);
        }
    }
}