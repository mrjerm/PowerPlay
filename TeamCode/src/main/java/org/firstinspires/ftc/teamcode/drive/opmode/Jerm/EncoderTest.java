package org.firstinspires.ftc.teamcode.drive.opmode.Jerm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class EncoderTest extends OpMode {

    public DcMotorEx motorDR4B;

    @Override
    public void init() {
        motorDR4B = hardwareMap.get(DcMotorEx.class, "Motor DR4B");
        motorDR4B.setDirection(DcMotorEx.Direction.REVERSE);
    }

    @Override
    public void loop() {
        if (gamepad1.a){
            motorDR4B.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        }
        telemetry.addData("position", motorDR4B.getCurrentPosition());
        telemetry.update();
    }
}
