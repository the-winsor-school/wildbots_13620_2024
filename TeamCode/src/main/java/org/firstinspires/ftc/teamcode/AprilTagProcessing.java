package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;
import java.lang.Math;

public class AprilTagProcessing {
    Robot robot;
    LinearOpMode opMode;
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;
    private static final double FEET_PER_METER = 3.28084;

    // APRIL TAG CONSTANTS
    // units in pixels
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;
    // UNITS ARE METERS
    double tagsize = 0.166;
    AprilTagDetection tagOfInterest = null;
    boolean tagsFound = false;
    boolean tagOfInterestFound = false;
    ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

    public AprilTagProcessing(LinearOpMode opMode) {
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

        camera.resumeViewport();
        opMode.telemetry.setMsTransmissionInterval(50);
    }

    /***
     * This function identifies and prints the position of a detected tag.
     * @param detection If an AprilTag is detected, it is called a detection.
     */

    public void tagToTelemetry(AprilTagDetection detection) {
        Orientation rotation = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
        opMode.telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        opMode.telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x * FEET_PER_METER));
        opMode.telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y * FEET_PER_METER));
        opMode.telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z * FEET_PER_METER));
        opMode.telemetry.addLine(String.format("Rotation Roll: %.2f degrees", rotation.thirdAngle));
        opMode.telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", rotation.secondAngle));
        opMode.telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", rotation.firstAngle));
    }

    /***
     * This function is for identifying a set AprilTag (like the one on the team object).
     * If the tag detected matches the tag we are looking for (ID_TAG_OF_INTEREST), the tag has been "found".
     * If something is being detected, but the tag we are looking for has not been detected, the tag is not found. The robot will keep looking.
     * If nothing is being detected, the tag is null.
     */

    public void identifyTag(int id) {
        if (currentDetections.size() != 0) {
            for (AprilTagDetection tag : currentDetections) {
                if (tag.id == id) {
                    tagOfInterest = tag;
                    tagOfInterestFound = true;
                    break;
                }
            }
            if (tagOfInterestFound) {
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
        } else {
            opMode.telemetry.addLine("can't find tag :(");
            if (tagOfInterest == null) {
                opMode.telemetry.addLine("we never knew where the tag was :(");
            } else {
                opMode.telemetry.addLine("\ntag last seen at:");
                tagToTelemetry(tagOfInterest);
            }

        }
        opMode.telemetry.update();
    }

    /***
     * This function asks the robot to identify whether it can see any AprilTags in the area.
     * While similar to identifyTeamObject(), this function is applicable to settings with multiple AprilTags, not just one.
     * @return
     */

    public boolean anyTags() {
        if (currentDetections.size() != 0) {
            opMode.telemetry.addLine("there are tags here!");
            tagsFound = true;
            return tagsFound;
        } else {
            opMode.telemetry.addLine("there are no tags here :(");
            return tagsFound;
        }
    }

    public TelemetryVector findTagPosition() {
        AprilTagDetection detection = new AprilTagDetection();
        Orientation rotation = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
        double xPos = detection.pose.x * FEET_PER_METER;
        double yPos = detection.pose.y * FEET_PER_METER;
        double zPos = detection.pose.z * FEET_PER_METER;
        double roll = rotation.thirdAngle;
        double pitch = rotation.secondAngle;
        double yaw = rotation.firstAngle;
        return new TelemetryVector(xPos, yPos, zPos, roll, pitch, yaw);

    }

    class TelemetryVector {
        double x;
        double y;
        double z;
        double roll;
        double pitch;
        double yaw;

        public TelemetryVector(double x, double y, double z, double roll, double pitch, double yaw) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.roll = roll;
            this.pitch = pitch;
            this.yaw = yaw;
        }

        public void goToTag(int tag) {
            if (tagsFound) {
                anyTags();
                identifyTag(4);
                if (tagOfInterestFound) {
                    findTagPosition();
                    while (Math.abs(yaw) > 15) {
                        robot.driving.turn(0.25f);
                        if (yaw < 0) {
                            robot.driving.turn(-0.25f);
                        }
                    } while (x < 0.3) {
                        robot.driving.horizontal(0.25f);
                    } while (z > 3) {
                        robot.driving.vertical(0.25f);
                    }
                }
            }
        }
    }
}