package org.firstinspires.ftc.teamcode.drive.opmode.Lincoln;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "Duck Spinner")
@Disabled
public class DuckSpinner extends OpMode {

    private CRServo duckSpinnerL;
    private CRServo duckSpinnerR;

    private double speed;

    @Override
    public void init() {
        duckSpinnerL = hardwareMap.get(CRServo.class, "Servo Duck Spinner Left");
        duckSpinnerR = hardwareMap.get(CRServo.class, "Servo Duck Spinner Right");
    }

    @Override
    public void loop() {
        speed = gamepad1.right_stick_y;

        duckSpinnerL.setPower(speed);
        duckSpinnerR.setPower(speed);
    }
}
