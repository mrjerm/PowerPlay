package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class SubsystemTurnTable extends SubsystemBase{
    public Motor turnTableMotor;
    private int forwardPos = 0;
    private int leftPos = 0;
    private int rightPos = 0;
    private int backPos = 0;
    public boolean turnTableMoving = false;

    public SubsystemTurnTable(Motor turnTableMotor) {
        this.turnTableMotor = turnTableMotor;
    }

    public void forwardPos() {
        turnTableMoving = true;
        turnTableMotor.setRunMode(Motor.RunMode.PositionControl);

        turnTableMotor.setTargetDistance(forwardPos);

        turnTableMotor.set(0);

        turnTableMotor.setPositionTolerance(13.6);

        while(!turnTableMotor.atTargetPosition()) {
            turnTableMotor.set(1);
        }

        turnTableMotor.stopMotor();
    }



}
