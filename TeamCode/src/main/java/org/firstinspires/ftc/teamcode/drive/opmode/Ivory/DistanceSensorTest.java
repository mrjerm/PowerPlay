package org.firstinspires.ftc.teamcode.drive.opmode.Ivory;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensorTest extends LinearOpMode {
    DistanceSensor bob;

    @Override
    public void runOpMode() throws InterruptedException {
        bob = hardwareMap.get(DistanceSensor.class, "distance sensor");
        waitForStart();
        while (!isStopRequested()){
            if (bob.getDistance(DistanceUnit.MM) <= 4000){
                telemetry.addData("within 4000 meters?", "yes");
                telemetry.update();
            }
            else{
                telemetry.addData("within 4000 meters?", "no");
                telemetry.update();
            }
        }
    }
}
