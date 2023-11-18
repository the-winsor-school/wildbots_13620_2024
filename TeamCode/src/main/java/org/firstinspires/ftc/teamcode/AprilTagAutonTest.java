/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.TagID;

@Autonomous(name="april tag auton")
@Disabled
public class AprilTagAutonTest extends LinearOpMode {
    Robot robot;
    LinearOpMode opMode;
    AprilTagProcessor aprilTag;

    TagID tagID;

    @Override
    public void runOpMode() {
        aprilTag = new AprilTagProcessor(opMode);
        tagID = new TagID();
        aprilTag.tagID = null;
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            tagID.seeTag();
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

 */
