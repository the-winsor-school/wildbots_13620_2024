package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AprilTag.ATP;

@Autonomous(name="april tag auton")
@Disabled
public class AprilTagAutonTest extends LinearOpMode {
    Robot robot;
    LinearOpMode opMode;
    ATP atp;

    Location location;

    @Override
    public void runOpMode() {
        atp = new ATP(opMode);
        location = new Location();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        if (opModeIsActive()) {
            Location.TYPE zone = location.getType();
            if (zone == Location.TYPE.ZONE1) {
                robot.goToTag(4);
            } else if (zone == Location.TYPE.ZONE2) {
                robot.driving.horizontal(1);
            } else if (zone == Location.TYPE.ZONE3) {
                robot.driving.horizontal(1);
            }
        }
    }

    public static class Location {
        private volatile TYPE type = TYPE.ZONE2; //default value
        public TYPE getType() {
            return type;
        }

        public enum TYPE {
            ZONE1, //left
            ZONE2, //center
            ZONE3, //right
        }
    }
}

