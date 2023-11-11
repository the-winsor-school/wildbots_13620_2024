package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.driving.IDriving;

@Autonomous(name="april tag auton")
public class AprilTagAutonTest extends LinearOpMode {
    Robot robot;
    LinearOpMode opMode;
    AprilTag aprilTag;

    TagID tagID;

    @Override
    public void runOpMode() {
        aprilTag = new AprilTag(opMode);
        tagID = new TagID();
        aprilTag.tagID = null;
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            int TagID = tagID.findTag();
            if (TagID == 1) {
                robot.driving.horizontal(1);
                // auton stuff for 1
            } else if (TagID == 2) {
                robot.driving.horizontal(1);
            } else if (TagID == 3) {
                robot.driving.horizontal(1);
            } else if (TagID == 4) {
                robot.driving.horizontal(1);
            } else if (TagID == 5) {
                robot.driving.horizontal(1);
            } else if (TagID == 6) {
                robot.driving.horizontal(1);
            }
        }
    }
}