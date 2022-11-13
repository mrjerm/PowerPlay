package org.firstinspires.ftc.teamcode.drive.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Disabled

public class DeroakClawTest extends LinearOpMode {

    public Servo intake1;

    @Override
    public void runOpMode() {
        intake1 = hardwareMap.get(Servo.class, "intake1");

        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("intake1position", intake1.getPosition());
            telemetry.update();
        }
    }

}
