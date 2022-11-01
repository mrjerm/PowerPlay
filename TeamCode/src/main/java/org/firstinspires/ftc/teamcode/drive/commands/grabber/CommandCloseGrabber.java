package org.firstinspires.ftc.teamcode.drive.commands.grabber;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.drive.subsystems.other.SubsystemGrabber;

public class CommandCloseGrabber extends CommandBase {

    private final SubsystemGrabber m_subsystem;

    public CommandCloseGrabber(SubsystemGrabber subsystem) {
        m_subsystem = subsystem;

        addRequirements(m_subsystem);
    }

    @Override
    public void initialize() {
        m_subsystem.close();
    }
}
