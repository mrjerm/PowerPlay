package org.firstinspires.ftc.teamcode.drive.opmode.ALAN;

import com.arcrobotics.ftclib.command.CommandBase;

public class CommandTurret extends CommandBase {
    private final SubsystemTurret subsystemTurret;

    public CommandTurret(SubsystemTurret subsystemTurret) {
        this.subsystemTurret = subsystemTurret;
        addRequirements(subsystemTurret);
    }
}
