package org.firstinspires.ftc.teamcode.drive.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemIntakeDeroak;

public class CommandIntake extends CommandBase {
    SubsystemIntakeDeroak subsystemIntakeDeroak;

    public CommandIntake (SubsystemIntakeDeroak subsystemIntakeDeroak) {
        this.subsystemIntakeDeroak = subsystemIntakeDeroak;
        addRequirements(subsystemIntakeDeroak);
    }
}
