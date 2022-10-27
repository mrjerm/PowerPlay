package org.firstinspires.ftc.teamcode.drive.opmode.Charlie;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Charlie's Driving Test")
public class DrivingTest extends LinearOpMode {

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        DcMotor motorL = hardwareMap.get(DcMotor.class, "motorL");
        DcMotor motorR = hardwareMap.get(DcMotor.class, "motorR");
        DcMotor motorLS = hardwareMap.get(DcMotor.class, "motorLS");

        while (opModeIsActive()) {
            motorL.setPower(-gamepad1.left_stick_y);
            motorR.setPower(-gamepad1.right_stick_y);
            motorLS.setPower(-gamepad1.right_trigger);
            if (gamepad1.left_trigger == 1) {
                motorLS.setDirection(DcMotorSimple.Direction.REVERSE);
                motorLS.setPower(1+.3);
            }
        }
    }
}