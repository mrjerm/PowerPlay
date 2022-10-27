package org.firstinspires.ftc.teamcode.drive.opmode.Lincoln;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Shailen Claw (Lincoln)")
public class ShailenClaw extends LinearOpMode {
    private Servo servoClaw;

    // Define the minimum and maximum motor position
    private final double minPos = -1;
    private final double maxPos = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        // Set the servo variable equal to the claw servo
        servoClaw = hardwareMap.get(Servo.class, "Servo Claw");

        // Home the servo to position 0
        servoClaw.setPosition(0);

        waitForStart();

        while(opModeIsActive()) {
            // Clamp the gamepad X and set the servo position to it
            int stickClamp = (int) Math.max(minPos, Math.min(maxPos, gamepad1.right_stick_x));

            servoClaw.setPosition(stickClamp);

            // Make servo position visible on phone screen in order to determine best values for later use
            telemetry.addData("Servo Claw Position:", servoClaw.getPosition());
            telemetry.update();
        }
    }
}
