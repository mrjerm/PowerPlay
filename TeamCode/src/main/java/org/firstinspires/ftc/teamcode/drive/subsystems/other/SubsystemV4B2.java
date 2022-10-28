package org.firstinspires.ftc.teamcode.drive.subsystems.other;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

public class SubsystemV4B2 extends SubsystemBase {

    public static Servo leftServo;
    public static Servo rightServo;

    public SubsystemV4B2(Servo leftServo, Servo rightServo) {
    }

    public void init(){
        leftServo = hardwareMap.get(Servo.class, "V4B Left Servo");
        rightServo = hardwareMap.get(Servo.class, "V4B Right Servo");
    }


    public void loop(){
        telemetry.addData("Left Servo", leftServo.getPosition());
        telemetry.addData("Right Servo", rightServo.getPosition());
        telemetry.update();
    }


    public static void LIFT_IN(){
        leftServo.setPosition(0);
        rightServo.setPosition(0);

    }
    public static void LIFT_UP(){
        leftServo.setPosition(0.25);
        rightServo.setPosition(0.25);


    }
    public static void LIFT_OUT(){
        leftServo.setPosition(0.5);
        rightServo.setPosition(0.5);

    }
    public static void LIFT_DOWN(){
        leftServo.setPosition(0.75);
        rightServo.setPosition(0.75);

    }
}
