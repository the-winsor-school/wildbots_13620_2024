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

public class TagID {
    Robot robot;
    LinearOpMode opMode;
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    AprilTag aprilTag;

    boolean tagFound = false;

    public void seeTag() {
        aprilTag = new AprilTag(opMode);
        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
        if (currentDetections.size() != 0) {
            findTag();
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
        public void findTag(int tagID) {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
            for (AprilTagDetection tag : currentDetections) {
                tag = tagID;
                tagFound = true;
                break;
            }
        }
    }
}
