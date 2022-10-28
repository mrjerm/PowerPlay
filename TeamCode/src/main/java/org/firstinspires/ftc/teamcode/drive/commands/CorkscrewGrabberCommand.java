package org.firstinspires.ftc.teamcode.drive.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.drive.subsystems.other.CorkscrewGrabberSubsystem;

@TeleOp (name = "CorkscrewGrabberCommand")
public class CorkscrewGrabberCommand extends CommandBase {

    public void initialize(CorkscrewGrabberSubsystem corkGrabbing){

        addRequirements(corkGrabbing);
    }

    public void execute(){
        CorkscrewGrabberSubsystem.corkGrabbing();
    }

}

