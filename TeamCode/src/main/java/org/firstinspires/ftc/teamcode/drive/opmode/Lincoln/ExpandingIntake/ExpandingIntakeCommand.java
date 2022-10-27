package org.firstinspires.ftc.teamcode.drive.opmode.Lincoln.ExpandingIntake;

import com.arcrobotics.ftclib.command.CommandBase;

public class ExpandingIntakeCommand extends CommandBase {

    private ExpandingIntakeSubsystem expandingIntakeSubsystem;

    public ExpandingIntakeCommand(ExpandingIntakeSubsystem subsystem) {
        this.expandingIntakeSubsystem = subsystem;

        addRequirements(expandingIntakeSubsystem);
    }

    @Override
    public void execute() {
        expandingIntakeSubsystem.turn();
    }
}
