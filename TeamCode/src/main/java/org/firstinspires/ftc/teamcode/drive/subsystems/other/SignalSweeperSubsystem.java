package org.firstinspires.ftc.teamcode.drive.subsystems.other;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class SignalSweeperSubsystem extends SubsystemBase {
    public Servo sweeper;

    public void init() {
        sweeper = hardwareMap.get(Servo.class, "Sweeper Servo");

    }
        public void sweep () {

            if (gamepad1.x) {
                sweeper.setPosition(1);
                telemetry.addData("Position", sweeper.getPosition());
                telemetry.update();

        }
            else{
                sweeper.setPosition(0);

            }

    }
}