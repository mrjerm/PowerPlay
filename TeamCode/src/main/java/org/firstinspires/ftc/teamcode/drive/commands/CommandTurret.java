//package org.firstinspires.ftc.teamcode.drive.commands;
//
//import com.arcrobotics.ftclib.command.CommandBase;
//
//import org.firstinspires.ftc.teamcode.drive.subsystems.SubsystemTurret;
//
//import java.util.function.DoubleSupplier;
//
//public class CommandTurret extends CommandBase {
//
//    private final SubsystemTurret subsystemTurret;
//    private final DoubleSupplier turretx;
//    private final DoubleSupplier turrety;
//
//    public CommandTurret(SubsystemTurret turretSubsystem, DoubleSupplier turretx, DoubleSupplier turrety) {
//        subsystemTurret = turretSubsystem;
//        this.turretx = turretx;
//        this.turrety = turrety;
//        addRequirements(subsystemTurret);
//    }
//
//    @Override
//    public void execute() {
//        subsystemTurret.moveTurret(turretx.getAsDouble(), turrety.getAsDouble());
//    }
//}
