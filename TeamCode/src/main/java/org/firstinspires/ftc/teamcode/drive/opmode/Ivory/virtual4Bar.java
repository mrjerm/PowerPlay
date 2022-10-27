package org.firstinspires.ftc.teamcode.drive.opmode.Ivory;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import android.graphics.RenderNode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class virtual4Bar extends OpMode {

    private double speed;

    public Servo barServo;

    public void init() {
        barServo = hardwareMap.get(Servo.class, "Servo V4B");
        barServo.setDirection(Servo.Direction.REVERSE);
    }

    public void loop() {
      /*  if (gamepad1.a) {
            barServo.setPosition(1);
        } else {
            barServo.setPosition(0);

        }*/
            boolean servoButton = gamepad1.x;
            boolean servoButton2 = gamepad1.a;
            boolean servoButton3 = gamepad1.y;
            boolean oldServoButton = false;

            if(servoButton && !oldServoButton){
                    barServo.setPosition(1);
                telemetry.addData("Position", barServo.getPosition());
               telemetry.update();
                }

            if(servoButton2 && !oldServoButton) {
                barServo.setPosition(0.5);
                telemetry.addData("Position", barServo.getPosition());
                telemetry.update();
                  }
            if(servoButton3 && !oldServoButton){
                barServo.setPosition(0);
                telemetry.addData("Position", barServo.getPosition());
                telemetry.update();
            }


    }
}
