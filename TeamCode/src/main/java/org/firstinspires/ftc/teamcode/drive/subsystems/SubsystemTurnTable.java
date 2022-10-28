package org.firstinspires.ftc.teamcode.drive.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class SubsystemTurnTable extends SubsystemBase{
    public Motor turnTableMotor;
    private int northPos = 0;
    private int northEastPos = 0;
    private int northWestPos = 0;
    private int westPos = 0;
    private int eastPos = 0;
    private int southPos = 0;
    private int southEastPos = 0;
    private int southWestPos = 0;
    public boolean turnTableMoving = false;

    public SubsystemTurnTable(Motor turnTableMotor) {
        this.turnTableMotor = turnTableMotor;
    }

    public void northPos() {
        turnTableMoving = true;
        turnTableMotor.setRunMode(Motor.RunMode.PositionControl);

        turnTableMotor.setTargetDistance(northPos);

        turnTableMotor.set(0);

        turnTableMotor.setPositionTolerance(13.6);

        while(!turnTableMotor.atTargetPosition()) {
            turnTableMotor.set(1);
        }

        turnTableMotor.stopMotor();
    }



}
