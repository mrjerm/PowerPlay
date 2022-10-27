
package org.firstinspires.ftc.teamcode.drive.opmode.Charlie;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


    @TeleOp(name = "Shailen_claw", group = "Drive")
    public class Shailen_claw extends LinearOpMode {


        static final double INCREMENT   = 0.01;
        static final int    CYCLE_MS    =   50;
        static final double MAX_POS     =  1.0;
        static final double MIN_POS     =  0.0;


        Servo servo;
        double  position = (MAX_POS - MIN_POS) / 2;
        boolean rampUp = true;


        @Override
        public void runOpMode() {


            servo = hardwareMap.get(Servo.class, "left_hand");





            while(gamepad1.right_trigger == 1.00F)


                if (rampUp) {
                    position += INCREMENT ;
                    if (position >= MAX_POS ) {
                        position = MAX_POS;
                        rampUp = !rampUp ;
                    }
                }
                else {

                    position -= INCREMENT ;
                    if (position <= MIN_POS ) {
                        position = MIN_POS;
                        rampUp = !rampUp;
                    }
                }





                servo.setPosition(position);
                sleep(CYCLE_MS);
                idle();
            }


        }


