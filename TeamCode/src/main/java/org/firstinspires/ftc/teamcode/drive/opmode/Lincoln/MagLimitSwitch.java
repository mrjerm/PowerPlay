package org.firstinspires.ftc.teamcode.drive.opmode.Lincoln;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class MagLimitSwitch extends OpMode {
    TouchSensor limitSwitch;
    DcMotorEx turret;

    @Override
    public void init() {
        limitSwitch = hardwareMap.get(TouchSensor.class, "Magnetic Limit Switch");
        turret = hardwareMap.get(DcMotorEx.class, "Motor Turret");
    }

    @Override
    public void loop() {
        if (!limitSwitch.isPressed()) {
            if(gamepad1.dpad_left) {
                centerLeft();
            } else if (gamepad1.dpad_right) {
                centerRight();
            }
        }
    }

    public void centerLeft() {
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (!limitSwitch.isPressed()) {
            turret.setPower(-0.75);
        }

        turret.setPower(0);
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void centerRight() {
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (!limitSwitch.isPressed()) {
            turret.setPower(0.75);
        }

        turret.setPower(0);
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
