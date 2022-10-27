package org.firstinspires.ftc.teamcode.drive.commands;


import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.subsystems.LiftCodeSubsystem;

@TeleOp(name = "LiftCodeSub")
public class LiftCodeCommand extends CommandBase {


    private LiftCodeSubsystem.encoderCode liftcodeSubsystem;

    public void initialize(LiftCodeSubsystem liftcodesubsystem) {
        addRequirements(liftcodesubsystem);
    }

    public void execute() {
        liftcodeSubsystem.thing();
        liftcodeSubsystem.lifting();

    }

}











