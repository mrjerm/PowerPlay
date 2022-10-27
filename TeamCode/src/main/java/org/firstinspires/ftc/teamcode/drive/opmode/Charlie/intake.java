package org.firstinspires.ftc.teamcode.drive.opmode.Charlie;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
@TeleOp
public class intake <loop> extends OpMode {
   private double speed;

    private Servo IntakeServo;
    private int servoButton;


    @Override
    public void init() {
       IntakeServo = hardwareMap.get(Servo.class,"IntakeServo");
       IntakeServo.setDirection(Servo.Direction.REVERSE);

        double speed;

        Servo IntakeServo;
    }



    @Override
    public void loop() {
       if (servoButton == 1) {
            IntakeServo.setPosition(0.25);
        } else {
            IntakeServo.setPosition(0);

        }

  boolean servoButton = gamepad1.a;
        }}

