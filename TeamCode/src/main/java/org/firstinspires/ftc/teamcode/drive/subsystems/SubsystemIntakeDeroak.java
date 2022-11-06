package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;



public class SubsystemIntakeDeroak extends SubsystemBase {

    public final Servo intakeDeroak;

    public double intakeDeroakOpen = 0.2;
    public double intakeDeroakClose = 0.38;

    public boolean isIntakeClosed = false;

    public SubsystemIntakeDeroak(Servo intakeDeroak) {
        this.intakeDeroak = intakeDeroak;
    }

    public void openIntake() {
        intakeDeroak.setPosition(intakeDeroakOpen);

        isIntakeClosed = false;
    }

    public void closeIntake() {
        intakeDeroak.setPosition(intakeDeroakClose);

        isIntakeClosed = true;
    }
}
