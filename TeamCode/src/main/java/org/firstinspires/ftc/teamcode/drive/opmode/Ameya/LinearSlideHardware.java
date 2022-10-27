package org.firstinspires.ftc.teamcode.drive.opmode.Ameya;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class LinearSlideHardware {
    //Create motors
    public DcMotor linearSlideMotor;

    HardwareMap hardwareMap = null;
    public ElapsedTime runtime = new ElapsedTime();

    public LinearSlideHardware(HardwareMap hwMap)  {
        initialize(hwMap);
    }

    private void initialize(HardwareMap hwMap) {
        hardwareMap = hwMap;
        //Connect motors
        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlideMotor");

    }
}

