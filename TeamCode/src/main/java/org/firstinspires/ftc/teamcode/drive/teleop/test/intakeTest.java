package org.firstinspires.ftc.teamcode.drive.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Disabled

public class intakeTest extends LinearOpMode {

    public Servo intake1;
    public Servo intake2;

    @Override
    public void runOpMode() {
        intake1 = hardwareMap.get(Servo.class, "intake1");
        intake2 = hardwareMap.get(Servo.class, "intake2");

        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("intake1position", intake1.getPosition());
            telemetry.addData("intake2position", intake2.getPosition());
            telemetry.update();
        }
    }

}