package org.firstinspires.ftc.teamcode.drive.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.drive.subsystems.other.SignalSweeperSubsystem;

@TeleOp (name = "SignalSweeperCommand")
public class SignalSweeperCommand extends CommandBase {
    private SignalSweeperSubsystem SignalSweeperSubsystem = new SignalSweeperSubsystem();
    private SignalSweeperSubsystem sweeper = new SignalSweeperSubsystem();


    public void initialize(SignalSweeperSubsystem signalSweeper){
        addRequirements(signalSweeper);
    }

    public void execute(){
        sweeper.sweeper.setPosition(0);
        SignalSweeperSubsystem.sweep();
    }

}
