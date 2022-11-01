package org.firstinspires.ftc.teamcode.drive.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class TurretTest extends LinearOpMode {
    public Servo turretServo;

    @Override
    public void runOpMode() {
        turretServo = hardwareMap.get(Servo.class, "turretServo");

        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("intake1position", turretServo.getPosition());
            telemetry.update();
        }
    }
}
