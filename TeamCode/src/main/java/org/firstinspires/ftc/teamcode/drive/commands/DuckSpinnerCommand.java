package org.firstinspires.ftc.teamcode.drive.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.drive.subsystems.DuckSpinnerSubsystem;

public class DuckSpinnerCommand extends CommandBase {

    private DuckSpinnerSubsystem duckSpinnerSubsystem;

    public DuckSpinnerCommand(DuckSpinnerSubsystem duckSpinnerSubsystem) {
        this.duckSpinnerSubsystem = duckSpinnerSubsystem;

        addRequirements(duckSpinnerSubsystem);
    }

    @Override
    public void execute() {
        duckSpinnerSubsystem.spin();
    }
}
