package org.firstinspires.ftc.teamcode.drive.opmode.Jerm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TurretTest extends LinearOpMode {
    DcMotorEx motorTurretLeft;
    DcMotorEx motorTurretRight;
    Servo servoTurret;

    @Override
    public void runOpMode() throws InterruptedException {
        boolean previous1 = false;
        boolean previous2 = false;
        motorTurretLeft = hardwareMap.get(DcMotorEx.class, "Motor Turret Left");
        motorTurretRight = hardwareMap.get(DcMotorEx.class, "Motor Turret Right");
        servoTurret = hardwareMap.get(Servo.class, "Servo Turret");

        waitForStart();
        if (opModeIsActive()) {
            servoTurret.setPosition(0.5);
        }
        while (opModeIsActive()){
            if (gamepad1.y){
                motorTurretLeft.setPower(1);
                motorTurretRight.setPower(1);
            }
            if (gamepad1.a){
                motorTurretLeft.setPower(0);
                motorTurretRight.setPower(0);
            }
            if (gamepad1.x){
                motorTurretLeft.setPower(-1);
                motorTurretRight.setPower(-1);
            }
            boolean current1 = gamepad1.dpad_up;
            if (current1 && !previous1){
                servoTurret.setPosition(servoTurret.getPosition() + 0.05);
            }
            previous1 = current1;

            boolean current2 = gamepad1.dpad_down;
            if (current2 && !previous2){
                servoTurret.setPosition(servoTurret.getPosition() - 0.05);
            }
            previous2 = current2;
            telemetry.addData("servo pos", servoTurret.getPosition());
            telemetry.update();
        }
    }
}
