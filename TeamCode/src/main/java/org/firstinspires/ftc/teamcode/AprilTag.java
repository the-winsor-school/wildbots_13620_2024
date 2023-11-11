package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;
public class AprilTag {
    Robot robot;
    LinearOpMode opMode;
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;
    private static final double FEET_PER_METER = 3.28084;

    // units in pixels
    // might need to recalibrate for webcam resolution
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;
    // UNITS ARE METERS
    double tagsize = 0.166;
    //private static final int ID_TAG_OF_INTEREST = 1; // tag ID - from 36h11 family
    AprilTagDetection tagOfInterest = null;

    boolean tagFound = false;

    public AprilTag(LinearOpMode opMode) {
        robot = new Robot(opMode);
        HardwareMap map = opMode.hardwareMap;
        Telemetry telemetry = opMode.telemetry;
        //setting pipeline
        int cameraMonitorViewId = map.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", map.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(map.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addData("error", errorCode);
            }
        });

        opMode.telemetry.setMsTransmissionInterval(50);
    }

    public void tagToTelemetry(AprilTagDetection detection) {
        Orientation rotation = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
        opMode.telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        opMode.telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x * FEET_PER_METER));
        opMode.telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y * FEET_PER_METER));
        opMode.telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z * FEET_PER_METER));
        opMode.telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", rotation.firstAngle));
        opMode.telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", rotation.secondAngle));
        opMode.telemetry.addLine(String.format("Rotation Roll: %.2f degrees", rotation.thirdAngle));
    }

    public void seeTag() {
        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
        if (currentDetections.size() != 0) {
            findTag();
            if (tagFound) {
                opMode.telemetry.addLine("tag in sight!\n\nLocation:");
                tagToTelemetry(tagOfInterest);
            } else {
                opMode.telemetry.addLine("can't find tag :(");
                if (tagOfInterest == null) {
                    opMode.telemetry.addLine("we never knew where the tag was :(");
                } else {
                    opMode.telemetry.addLine("\ntag last seen at:");
                    tagToTelemetry(tagOfInterest);
                }
            }

        } else //if something is not detected
        {
            opMode.telemetry.addLine("can't find tag :(");
            if (tagOfInterest == null) {
                opMode.telemetry.addLine("we never knew where the tag was :(");
            } else {
                opMode.telemetry.addLine("\ntag last seen at:");
                tagToTelemetry(tagOfInterest);
            }
        }
        opMode.telemetry.update();
        opMode.sleep(20);
        if (tagOfInterest != null) {
            opMode.telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            opMode.telemetry.update();
        } else {
            opMode.telemetry.addLine("No tag snapshot available, we never knew where the tag was :(");
            opMode.telemetry.update();
        }

    }
    public void findTag() {
        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
        for (AprilTagDetection tag : currentDetections) {
            tagOfInterest = tag;
            tagFound = true;
            break;
            }
        }
    }


