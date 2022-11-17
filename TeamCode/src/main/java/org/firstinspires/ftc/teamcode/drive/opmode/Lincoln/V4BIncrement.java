package org.firstinspires.ftc.teamcode.drive.opmode.Lincoln;

import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.V4B_FLOOR;
import static org.firstinspires.ftc.teamcode.drive.ConstantsPP.V4B_RETRACTED;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class V4BIncrement extends OpMode {

    Servo servoV4BL, servoV4BR;

    Double increment = 0.1;
    Double V4BPos;

    boolean upLast, downLast;

    @Override
    public void init() {
        servoV4BL = hardwareMap.get(Servo.class, "Servo V4BL");
        servoV4BR = hardwareMap.get(Servo.class, "Servo V4BR");
        servoV4BL.setDirection(Servo.Direction.REVERSE);

        servoV4BL.setPosition(V4B_FLOOR);
        servoV4BR.setPosition(V4B_FLOOR);

        V4BPos = servoV4BL.getPosition();

        telemetry.addData("left pos", servoV4BL.getPosition());
        telemetry.addData("right pos", servoV4BR.getPosition());
    }

    @Override
    public void loop() {
        if (gamepad1.x) {
            servoV4BL.setPosition(V4B_RETRACTED);
            servoV4BR.setPosition(V4B_RETRACTED);
        }

        if (gamepad1.y && !upLast) {
            upLast = true;

            V4BPos += increment;

            servoV4BL.setPosition(V4BPos);
            servoV4BR.setPosition(V4BPos);
        } else if (gamepad1.a && !downLast) {
            downLast = true;

            V4BPos -= increment;

            servoV4BL.setPosition(V4BPos);
            servoV4BR.setPosition(V4BPos);
        }

        if (!gamepad1.y && upLast) {
            upLast = false;
        }

        if (!gamepad1.a && downLast) {
            downLast = false;
        }

        telemetry.update();
    }
}
