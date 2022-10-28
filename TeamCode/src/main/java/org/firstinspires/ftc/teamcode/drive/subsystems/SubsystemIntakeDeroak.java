package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;



public class SubsystemIntakeDeroak extends SubsystemBase {

    public final Servo intake1;

    public double intake1Open = 0;
    public double intake1Close = 0;
    public double intake2Open = 0;
    public double intake2Close = 0;

    public boolean isIntakeClosed = false;

    public SubsystemIntakeDeroak(Servo intake1) {
        this.intake1 = intake1;
    }

    public void openIntake() {
        intake1.setPosition(intake1Open);

        isIntakeClosed = false;
    }

    public void closeIntake() {
        intake1.setPosition(intake1Close);

        isIntakeClosed = true;
    }
}
