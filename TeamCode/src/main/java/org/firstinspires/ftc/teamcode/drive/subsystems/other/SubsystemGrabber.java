package org.firstinspires.ftc.teamcode.drive.subsystems.other;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SubsystemGrabber extends SubsystemBase {

    public final Servo grabber;

    public SubsystemGrabber(final HardwareMap hMap, final String name) {
        grabber = hMap.get(Servo.class, name);
    }

    public void close() {
        grabber.setPosition(0.8);
    }

    public void open() {
        grabber.setPosition(0.6);
    }
}
