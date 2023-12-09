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

public class ATP {
    //Robot robot;
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    OpenCV.SignalPipeline openCVPipeline;

    OpenCV opencv;
    Telemetry telemetry;

    static final double FEET_PER_METER = 3.28084;

    // units in pixels
    // might need to recalibrate for webcam resolution
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    public ATP(LinearOpMode opMode) {
        HardwareMap map = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        opencv = new OpenCV();
        //robot = new Robot(opMode);
        //setting pipeline
        int cameraMonitorViewId = map.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", map.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(map.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
        camera.setPipeline(aprilTagDetectionPipeline);
        openCVPipeline = new OpenCV.SignalPipeline();
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });
        camera.resumeViewport();
        opMode.telemetry.setMsTransmissionInterval(50);
    }

    /***
     * This function identifies and prints the position of a detected tag on the Driver Hub. Distance and angle are calculated.
     * @param detection If an AprilTag is detected, it is called a detection.
     */


    public void getTagTelemetryData (AprilTagDetection detection) {
        Orientation rotation = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x * FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y * FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z * FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", rotation.thirdAngle));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", rotation.secondAngle));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", rotation.firstAngle));
    }

    /***
     * This function creates a location vector for the tag we want to find. That way, we can use the location data to navigate to the correct position on the field.
     * @param id the universal ID of the tag we are looking for (1,2,3,4,5, or 6, depending on the zone)
     */


    public TelemetryVector getVectorToTag (int id) {
        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
        aprilTagDetectionPipeline.getDetectionsUpdate();
        telemetry.addData("detections", currentDetections);
        //telemetry.update();
        AprilTagDetection tagOfInterest = null;
        //boolean tagOfInterestFound = false;
        if (currentDetections.size() != 0) {
            for (AprilTagDetection tag : currentDetections) {
                if (tag.id == id) {
                    telemetry.addData("tag found! tag id: ", id);
                    telemetry.update();
                    tagOfInterest = tag;
                    //tagOfInterestFound = true;
                }
            }
            if (tagOfInterest == null) {
                telemetry.addLine("can't find tag :(");
                telemetry.update();
                return null;
            }

            telemetry.addLine("tag in sight!\n\nLocation:");
            getTagTelemetryData(tagOfInterest);
            telemetry.update();
            return findTagPosition(tagOfInterest);

        } else if (currentDetections.size() == 0) {
            telemetry.addLine("no tags here :(");
            telemetry.update();
            return null;
        } else {
            telemetry.addLine("current detections = 0 but not null");
            telemetry.update();
            return null;
        }
    }

    /***
     * This function creates a vector from the position of the AprilTag with x/y/z coordinates and roll/pitch/yaw angles.
     */


    public TelemetryVector findTagPosition (AprilTagDetection detection) {
        Orientation rotation = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
        double xPos = detection.pose.x * FEET_PER_METER;
        double yPos = detection.pose.y * FEET_PER_METER;
        double zPos = detection.pose.z * FEET_PER_METER;
        double roll = rotation.thirdAngle;
        double pitch = rotation.secondAngle;
        double yaw = rotation.firstAngle;
        return new TelemetryVector(xPos, yPos, zPos, roll, pitch, yaw);
    }

    public class TelemetryVector {
        double x;

        public double getX() {
            return x;
        }

        double y;

        public double getY() {
            return y;
        }
        double z;
        public double getZ() {
            return z;
        }
        double roll;
        public double getRoll() {
            return roll;
        }
        double pitch;
        public double getPitch() {
            return pitch;
        }
        double yaw;
        public double getYaw() {
            return yaw;
        }

        public TelemetryVector(double x, double y, double z, double roll, double pitch, double yaw) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.roll = roll;
            this.pitch = pitch;
            this.yaw = yaw;
        }
    }


}
