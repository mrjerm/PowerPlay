package org.firstinspires.ftc.teamcode.drive.opmode.ALAN;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvTracker;
import org.openftc.easyopencv.OpenCvTrackerApiPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@TeleOp
public class trackeropencvalan extends LinearOpMode{
    OpenCvWebcam webcam;
    OpenCvTrackerApiPipeline trackerApiPipeline;
    UselessColorBoxDrawingTracker tracker1, tracker2, tracker3;


    @Override
    public void runOpMode() {

        //Camera initialization
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);


        trackerApiPipeline = new OpenCvTrackerApiPipeline();
        webcam.setPipeline(trackerApiPipeline);

        webcam.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
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

        tracker1 = new UselessColorBoxDrawingTracker(new Scalar(255, 0, 0));
        tracker2 = new UselessColorBoxDrawingTracker(new Scalar(0, 255, 0));
        tracker3 = new UselessColorBoxDrawingTracker(new Scalar(0, 0, 255));

        trackerApiPipeline.addTracker(tracker1);
        trackerApiPipeline.addTracker(tracker2);
        trackerApiPipeline.addTracker(tracker3);

        waitForStart();

        while (opModeIsActive()) {
            trackerApiPipeline.removeTracker(tracker1);
            sleep(100);
        }

    }

    class UselessColorBoxDrawingTracker extends OpenCvTracker {
        Scalar color;

        UselessColorBoxDrawingTracker(Scalar color)
        {
            this.color = color;
        }

        @Override
        public Mat processFrame(Mat input) {

            //Img processing
            Imgproc.rectangle(
                    input,
                    new Point(
                            input.cols()/4,
                            input.rows()/4),
                    new Point(
                            input.cols()*(3f/4f),
                            input.rows()*(3f/4f)),
                    color, 4);

            return input;

        }
    }

}
