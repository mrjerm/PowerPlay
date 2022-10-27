package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;

public class SubsystemFlicker extends SubsystemBase{

    public final Servo flicker;


    //FIND VALUES 0 for now
    public double flickerinactive = 0;
    public double flickeractive = 0;

    public boolean isFlickerActivated = false;

    public SubsystemFlicker(Servo flicker) {
        this.flicker = flicker;
    }

    public void activateFlicker() {
        flicker.setPosition(flickeractive);
        isFlickerActivated = true;
    }

    public void inactivateFlicker() {
        flicker.setPosition(flickerinactive);
        isFlickerActivated = false;
    }
}
