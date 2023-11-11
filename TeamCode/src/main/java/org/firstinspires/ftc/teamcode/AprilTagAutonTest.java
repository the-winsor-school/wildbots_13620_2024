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

    @Override
    public void runOpMode() {
        aprilTag = new AprilTag(opMode);
        aprilTag.seeTag();
        if (tag = 1) {
            robot.driving.horizontal(1);
            // auton stuff for 1
        } else {
            robot.driving.horizontal(1);
            //auton stuff
            // e.g.
            if (aprilTag.tagOfInterest.pose.x <= 20) {
                // do something
            } else if (aprilTag.tagOfInterest.pose.x >= 20 && aprilTag.tagOfInterest.pose.x <= 50) {
                // do something else
            } else if (aprilTag.tagOfInterest.pose.x >= 50) {
                // do something else
            }
        }
    }
}