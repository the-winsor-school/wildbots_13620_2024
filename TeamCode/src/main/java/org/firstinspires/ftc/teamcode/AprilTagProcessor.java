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

public class AprilTagProcessor {
    Robot robot;
    LinearOpMode opMode;
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;
    private static final double FEET_PER_METER = 3.28084;

    // units in pixels
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;
    // UNITS ARE METERS
    double tagsize = 0.166;

    private static final int ID_TAG_OF_INTEREST = 7; // tag ID - from 36h11 family
    AprilTagDetection tagOfInterest = null;
    boolean tagFound = false;
    ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

    public AprilTagProcessor(LinearOpMode opMode) {
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
        opMode.telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", rotation.firstAngle));
        opMode.telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", rotation.secondAngle));
        opMode.telemetry.addLine(String.format("Rotation Roll: %.2f degrees", rotation.thirdAngle));
    }

    /***
     * This function is for identifying a set AprilTag (like the one on the team object).
     * If the tag detected matches the tag we are looking for (ID_TAG_OF_INTEREST), the tag has been "found".
     * If something is being detected, but the tag we are looking for has not been detected, the tag is not found. The robot will keep looking.
     * If nothing is being detected, the tag is null.
     */
    public void identifyTeamObject() {
        if (currentDetections.size() != 0) {
            for (AprilTagDetection tag : currentDetections) {
                if (tag.id == ID_TAG_OF_INTEREST) {
                    tagOfInterest = tag;
                    tagFound = true;
                    break;
                }
            }
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
        boolean found = false;
        if (currentDetections.size() != 0) {
            opMode.telemetry.addLine("there are tags here!");
            found = true;
            return found;
        } else {
            opMode.telemetry.addLine("there are no tags here :(");
            return found;
        }
    }

    /***
     * This function returns an array of all tags that have been detected in the nearby area.
     * If no tags have been found, the function returns null.
     * @return
     */
    public ArrayList<AprilTagDetection> getCurrentDetections() {
        if (currentDetections.size() != 0) {
            return currentDetections;
        } else {
            opMode.telemetry.addLine("there are no tags here :(");
            return null;
        }
    }

    public java.lang.Integer getTagID() {
        AprilTagDetection detection = new AprilTagDetection();
        getCurrentDetections();
        try {
            return detection.id;
        } catch (Exception e){
            opMode.telemetry.addLine("there are no tags here :(");
            return null;
        }
    }

    public TelemetryVector findTagPosition() {
        AprilTagDetection detection = new AprilTagDetection();
        Orientation rotation = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
        double xPos = detection.pose.x * FEET_PER_METER;
        double yPos = detection.pose.y * FEET_PER_METER;
        double zPos = detection.pose.z * FEET_PER_METER;
        double yaw = rotation.firstAngle;
        double pitch = rotation.secondAngle;
        double roll = rotation.thirdAngle;
        return new TelemetryVector (xPos, yPos, zPos, roll, yaw, pitch);
    }

   class TelemetryVector
    {
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
        public double getX() {return x;}
        public double getY() {return y;}
        public double getZ() {return z;}
        public double getRoll() {return roll;}
        public double getPitch() {return pitch;}
        public double getYaw() {return yaw;}

    }
}





