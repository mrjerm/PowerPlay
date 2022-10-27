package org.firstinspires.ftc.teamcode.drive.opmode.Ameya;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "LinearSA")
public class LinearSA extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor linearActuator = hardwareMap.dcMotor.get("linearActuatorMotor");
        DcMotor linearSlide = hardwareMap.dcMotor.get("linearSlideMotor");

        linearActuator.setDirection(DcMotorSimple.Direction.FORWARD);
        linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double actuatorPwr;
            double slidePwr;

            actuatorPwr = gamepad1.left_stick_y;
            slidePwr = gamepad1.right_stick_y;

            slidePwr = Range.clip(slidePwr, -1, 1);
            actuatorPwr = Range.clip(actuatorPwr, -1, 1);
            linearSlide.setPower(slidePwr);
            linearActuator.setPower(actuatorPwr);
        }
    }
}
