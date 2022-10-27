package org.firstinspires.ftc.teamcode.drive.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemV4B2;

//import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemV4B;

public class CommandV4B extends CommandBase {
  /*  private SubsystemV4B subsystemV4B;

    private void initialize(SubsystemV4B subsystemV4B) {
        this.subsystemV4B = subsystemV4B;
        addRequirements(subsystemV4B);
    }

    public void execute() {
        SubsystemV4B.V4BMovement();

    }
 */
  private SubsystemV4B2 subsystemV4B2;

    private void initialize(SubsystemV4B2 subsystemV4B2) {
        this.subsystemV4B2 = subsystemV4B2;
        addRequirements(subsystemV4B2);
    }

    public void execute() {
        SubsystemV4B2.LIFT_IN();
        SubsystemV4B2.LIFT_UP();
        SubsystemV4B2.LIFT_OUT();
        SubsystemV4B2.LIFT_DOWN();

    }

}