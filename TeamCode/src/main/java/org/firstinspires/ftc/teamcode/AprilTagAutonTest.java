package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.AprilTagProcessing;

@Autonomous(name="april tag auton")
@Disabled
public class AprilTagAutonTest extends LinearOpMode {
    Robot robot;
    LinearOpMode opMode;
    AprilTagProcessing aprilTagProcessing;
    private volatile Zone zone = Zone.ZONE2; //default value

    @Override
    public void runOpMode() {
        aprilTagProcessing = new AprilTagProcessing(opMode);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            if (zone == Zone.ZONE1) {
                aprilTagProcessing.goToTag(4);
                // auton stuff for 1
            } else if (zone == Zone.ZONE2) {
                robot.driving.horizontal(1);
            } else if (zone == Zone.ZONE3) {
                robot.driving.horizontal(1);
            }
        }

        public Zone getZone () {
            return zone;
        }

        public enum Zone {
            ZONE1, //left
            ZONE2, //center
            ZONE3, //right
        }
    }
}

