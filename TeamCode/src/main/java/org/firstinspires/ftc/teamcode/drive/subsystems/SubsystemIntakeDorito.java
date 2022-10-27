package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;


public class SubsystemIntakeDorito extends SubsystemBase {

    public final CRServo dorito;

    public double doritoRun = 0;
    public double doritoStop = 0;
    public double doritoOuttake = 0;


    public SubsystemIntakeDorito(CRServo dorito) {
        this.dorito = dorito;
    }

    public void runIntake() {
        dorito.setPower(doritoRun);
    }

    public void reverseIntake() {
        dorito.setPower(doritoOuttake);
    }

    public void stopIntake() {
        dorito.setPower(doritoStop);
    }
}

