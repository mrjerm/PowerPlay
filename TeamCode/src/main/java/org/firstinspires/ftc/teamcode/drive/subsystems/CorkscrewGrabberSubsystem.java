package org.firstinspires.ftc.teamcode.drive.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class CorkscrewGrabberSubsystem extends SubsystemBase {
    static Servo corkGrabber;


    public void init() {
        corkGrabber = hardwareMap.get(Servo.class, "Servo Corkscrew Grabber");

    }
    public static void corkGrabbing(){
        if (gamepad1.a) {
            corkGrabber.setPosition(0.34);

        }
        if(gamepad1.b){
            corkGrabber.setPosition(0.1);

        }
        telemetry.addData("Position", corkGrabber.getPosition());
        telemetry.update();

    }

}

