package org.firstinspires.ftc.teamcode.drive.opmode.Jerm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class motorTest extends OpMode {
    public DcMotorEx motorDR4B1;
    public DcMotorEx motorDR4B2;
    @Override
    public void init() {
        motorDR4B1 = hardwareMap.get(DcMotorEx.class, "Motor DR4B 1");
        motorDR4B2 = hardwareMap.get(DcMotorEx.class, "Motor DR4B 2");
        motorDR4B1.setDirection(DcMotorEx.Direction.REVERSE);
        motorDR4B2.setDirection(DcMotorEx.Direction.REVERSE);
        motorDR4B1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorDR4B2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorDR4B1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorDR4B2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        motorDR4B1.setPower(-gamepad1.left_stick_y);
        motorDR4B2.setPower(-gamepad1.right_stick_y);
    }
}
