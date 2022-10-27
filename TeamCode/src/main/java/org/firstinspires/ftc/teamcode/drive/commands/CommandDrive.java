package org.firstinspires.ftc.teamcode.drive.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemDrive;
import java.util.function.DoubleSupplier;

public class CommandDrive extends CommandBase{

    private final SubsystemDrive subsystemDrive;
    private final DoubleSupplier forward;
    private final DoubleSupplier strafe;
    private final DoubleSupplier rotation;

    public CommandDrive(SubsystemDrive subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier rotation) {
        subsystemDrive = subsystem;
        this.strafe = strafe;
        this.forward = forward;
        this.rotation = rotation;
        addRequirements(subsystemDrive);
    }

    @Override
    public void execute() {
        subsystemDrive.drive(strafe.getAsDouble(), forward.getAsDouble(), rotation.getAsDouble());
    }

}
