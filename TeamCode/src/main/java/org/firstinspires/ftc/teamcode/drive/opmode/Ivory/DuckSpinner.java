package org.firstinspires.ftc.teamcode.drive.opmode.Ivory;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous (name = "DuckSpinner", group="LinearOpmode")
public class DuckSpinner extends LinearOpMode{

    CRServo servoL;
    CRServo servoR;
    double servoPosition= 0.0;
    private long milliseconds;

    @Override
    public void runOpMode() throws InterruptedException {
    telemetry.addData("Status", "Initialized");
    telemetry.update();

    servoL = hardwareMap.crservo.get("Servo Duck Spinner Left");
    servoR = hardwareMap.crservo.get("Servo Duck Spinner Right");
    waitForStart();

    servoL.setPower(1);
    sleep(5000);
    servoL.setPower(0);
    servoR.setPower(1);
    sleep(5000);
    servoR.setPower(0);


    }
}
