package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SubsystemDrive extends SubsystemBase {

    private final MecanumDrive m_drive;

    public SubsystemDrive(Motor frontLeft, Motor frontRight, Motor backLeft, Motor backRight) {
        m_drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    public void drive(double strafe, double forward, double rotation) {
        m_drive.driveRobotCentric(-strafe, -forward, -rotation);
    }
}