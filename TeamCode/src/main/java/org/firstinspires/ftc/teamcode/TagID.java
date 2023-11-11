package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import java.util.ArrayList;

public class TagID {
    Robot robot;
    LinearOpMode opMode;
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    AprilTag aprilTag;

    boolean tagFound = false;

    AprilTagDetection tagID;

    public void seeTag() {
        aprilTag = new AprilTag(opMode);
        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
        if (currentDetections.size() != 0) {
            findTag(tagID);
            if (tagFound) {
                opMode.telemetry.addLine("tag in sight!\n\nLocation:");
                aprilTag.tagToTelemetry(tagID);
            } else {
                opMode.telemetry.addLine("can't find tag :(");
                if (tagID == null) {
                    opMode.telemetry.addLine("we never knew where the tag was :(");
                    } else {
                        opMode.telemetry.addLine("\ntag last seen at:");
                        aprilTag.tagToTelemetry(tagID);
                    }
                }
            } else //if something is not detected
            {
                opMode.telemetry.addLine("can't find tag :(");
                if (tagID == null) {
                    opMode.telemetry.addLine("we never knew where the tag was :(");
                } else {
                    opMode.telemetry.addLine("\ntag last seen at:");
                    aprilTag.tagToTelemetry(tagID);
                }
            }
            opMode.telemetry.update();
            opMode.sleep(20);
            if (tagID != null) {
                opMode.telemetry.addLine("Tag snapshot:\n");
                aprilTag.tagToTelemetry(tagID);
                opMode.telemetry.update();
            } else {
                opMode.telemetry.addLine("No tag snapshot available, we never knew where the tag was :(");
                opMode.telemetry.update();
            }

        }
        public void findTag(AprilTagDetection tag) {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
            for (AprilTagDetection detectedTag : currentDetections) {
                tagID = tag;
                tagFound = true;
                break;
            }
        }
    }
