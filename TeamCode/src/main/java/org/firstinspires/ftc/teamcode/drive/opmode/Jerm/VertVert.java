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
public class VertVert extends LinearOpMode {
    OpenCvWebcam webcam;

    //CONStANT
    final int x0 = 154;
    final int x1 = 166;
    final int y0 = 0;
    final int y1 = 12;
    final int y2 = 24;
    final int y3 = 36;
    final int y4 = 48;
    final int y5 = 60;
    final int y6 = 72;
    final int y7 = 84;
    final int y8 = 96;
    final int y9 = 108;
    final int y10 = 120;
    final int y11 = 132;
    final int y12= 144;
    final int y13 = 156;
    final int y14 = 168;
    final int y15 = 180;
    final int y16 = 192;
    final int y17 = 204;
    final int y18 = 216;
    final int y19 = 228;
    final int y20 = 240;


    @Override
    public void runOpMode() {

        //Camera initialization
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        //Loading pipeline
        RingPipeline visionPipeline = new RingPipeline();
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

        webcam.showFpsMeterOnViewport(false);

        waitForStart();

        while (opModeIsActive()) {
            //Get data from pipeline
            //Output to telemetry
        }

    }

    //Pipeline class

}
