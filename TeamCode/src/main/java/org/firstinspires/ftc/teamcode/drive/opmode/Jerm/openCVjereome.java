package org.firstinspires.ftc.teamcode.drive.opmode.Jerm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@TeleOp
public class openCVjereome extends LinearOpMode {
    OpenCvWebcam webcam;

    //CONStANT
    final int C_x0 = 135;
    final int C_x1 = 185;
    final int C_y0 = 0;
    final int C_y1 = 200;

    @Override
    public void runOpMode() {

        //Camera initialization
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        //Loading pipeline
        openCVjereome.RingPipeline visionPipeline = new openCVjereome.RingPipeline();
        webcam.setPipeline(visionPipeline);

        webcam.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                /*
                 * Tell the webcam to start streaming images to us! Note that you must make sure
                 * the resolution you specify is supported by the camera. If it is not, an exception
                 * will be thrown.
                 *
                 * Keep in mind that the SDK's UVC driver (what OpenCvWebcam uses under the hood) only
                 * supports streaming from the webcam in the uncompressed YUV image format. This means
                 * that the maximum resolution you can stream at and still get up to 30FPS is 480p (640x480).
                 * Streaming at e.g. 720p will limit you to up to 10FPS and so on and so forth.
                 *
                 * Also, we specify the rotation that the webcam is used in. This is so that the image
                 * from the camera sensor can be rotated such that it is always displayed with the image upright.
                 * For a front facing camera, rotation is defined assuming the user is looking at the screen.
                 * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
                 * away from the user.
                 */
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });

        waitForStart();

        while (opModeIsActive()) {
            //Get data from pipeline
            //Output to telemetry
            telemetry.addData("x1y1:", visionPipeline.REGION1);
            telemetry.update();
        }

    }

    //Pipeline class
    class RingPipeline extends OpenCvPipeline {
        //Working Mat variables
        Mat YCrCb = new Mat(); //Store the whole YCrCb channel
        Mat Cb = new Mat(); //Store the Cb Channel (part from YCrCb)
        Mat tholdMat = new Mat(); //Store the threshold

        //Drawing variables
        Scalar GRAY = new Scalar(220, 220, 220); //RGB values for gray
        Scalar GREEN = new Scalar(0, 255, 0); //RGB values for green

        //Variables that will store the results of our pipeline
        public int REGION1;

        //Space which we will analyze data
        public Point REGION1_TOPLEFT = new Point(C_x0, C_y0);
        public Point REGION1_BOTTOMRIGHT = new Point(C_x1, C_y1);



        @Override
        public Mat processFrame(Mat input) {

            //Img processing
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_BGR2YCrCb);
            Core.extractChannel(YCrCb, Cb, 2);
            Imgproc.threshold(Cb, tholdMat, 150, 255, Imgproc.THRESH_BINARY_INV);

            //Drawing Points
            int x1y1X = (int) ((REGION1_TOPLEFT.x + REGION1_BOTTOMRIGHT.x) / 2);
            int x1y1Y = (int) ((REGION1_TOPLEFT.y + REGION1_BOTTOMRIGHT.y) / 2);


            double[] x1y1PV = tholdMat.get(x1y1Y, x1y1X);

            REGION1 = (int) x1y1PV[0];

            //REGION1
            Imgproc.rectangle(
                    input,
                    REGION1_TOPLEFT,
                    REGION1_BOTTOMRIGHT,
                    GRAY,
                    2
            );

            //REGION1 point
            Imgproc.circle(
                    input,
                    new Point(x1y1X, x1y1Y),
                    100,
                    GRAY,
                    2
            );




            // Change colors if the pipeline detected something
            if (REGION1 == 0) {
                Imgproc.rectangle(
                        input,
                        REGION1_TOPLEFT,
                        REGION1_BOTTOMRIGHT,
                        GREEN,
                        2
                );
                Imgproc.circle(
                        input,
                        new Point(x1y1X, x1y1Y),
                        10,
                        GREEN,
                        2
                );

            }

            return input;

        }
    }
}
