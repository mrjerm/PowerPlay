package org.firstinspires.ftc.teamcode.drive.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;

public class LiftCodeSubsystem extends SubsystemBase {
    public DcMotor liftMotor;


    public class encoderCode extends SubsystemBase {

        public void thing() {
            liftMotor = hardwareMap.get(DcMotor.class, "Lift Motor");

            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            telemetry.addData("Position", liftMotor.getCurrentPosition());
            telemetry.update();


        }

        double targetPosOne = 1;
        double targetPosTwo = 2;
        double targetPosThree = 3;
        double targetPosFour = 4;

        boolean button1 = gamepad1.a;
        boolean button2 = gamepad1.b;
        boolean button3 = gamepad1.x;
        boolean button4 = gamepad1.y;

        public void lifting() {
            if (button1) {
                liftMotor.setTargetPosition((int) targetPosOne);
                liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            } else if (button2) {
                liftMotor.setTargetPosition((int) targetPosTwo);
                liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            } else if (button3) {
                liftMotor.setTargetPosition((int) targetPosThree);
                liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            } else if (button4) {
                liftMotor.setTargetPosition((int) targetPosFour);
                liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
    }


    }
}




