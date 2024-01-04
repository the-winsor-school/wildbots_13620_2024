
package org.firstinspires.ftc.teamcode;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Rect;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;


@Autonomous(name="open cv EASY")
public class EasyOpenCV extends LinearOpMode {
    LinearOpMode opMode;
    OpenCvCamera webcam;
    OpenCV.SignalPipeline pipeline;
    Robot robot;

    @Override
    public void runOpMode() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new OpenCV.SignalPipeline();
        webcam.setPipeline(pipeline);
        robot = new Robot(this);


        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            public void onOpened()
            {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT); //size of whole camera frame
            }
            public void onError(int errorCode)
            {

            }
        });
        webcam.resumeViewport();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
/*
        while (opModeIsActive()) {
            telemetry.addData("Type", pipeline.getType());
            telemetry.addData("Average", pipeline.getAverage());
            telemetry.update();
            sleep(50);
        }

 */

        if (opModeIsActive()) {
            telemetry.addData("debug", "start");
            telemetry.update();
            sleep(3000);
            OpenCV.SignalPipeline.TYPE zone = pipeline.getType();
            int[] averages = pipeline.getAverage();
            telemetry.addData("Type", zone);
            telemetry.addData("Average Red", averages[0]);
            telemetry.addData("Average Green", averages[1]);
            telemetry.addData("Average Blue", averages[2]);
            sleep(3000);
            telemetry.update();

            if (zone == OpenCV.SignalPipeline.TYPE.ZONE1){
                //signalPark(1,Location.RedTop);
                //simpleDriving.drive(0,-1,0);
                telemetry.addLine("Zone 1"); //moves forward one square mat
                sleep (250);
            }
            else if (zone == OpenCV.SignalPipeline.TYPE.ZONE2){
                telemetry.addLine("Zone 2");//moves forward one square mat
                sleep (250);
            }
            else if (zone == OpenCV.SignalPipeline.TYPE.ZONE3){
                //signalPark(2,Location.RedTop);
                //simpleDriving.drive(0,-1,0);
                telemetry.addLine("Zone 3"); //moves forward one square mat
                sleep (250);
            }
            /*autonLibrary.strafingSigmoid(1,0,0,2100, this, drivingLibrary); //strafes to the left for 2100 ms
            sleep (2100);*/

            telemetry.addData("debug", "strafing complete");
            sleep(10000000);

        }
    }
}