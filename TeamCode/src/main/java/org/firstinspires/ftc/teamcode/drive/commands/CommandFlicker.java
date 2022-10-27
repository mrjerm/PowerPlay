package org.firstinspires.ftc.teamcode.drive.commands;

import static android.os.SystemClock.sleep;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemFlicker;


public class CommandFlicker extends CommandBase{
    SubsystemFlicker subsystemFlicker;

    public CommandFlicker(SubsystemFlicker subsystemFlicker){
        this.subsystemFlicker = subsystemFlicker;
        addRequirements(subsystemFlicker);
    }
}
