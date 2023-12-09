package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="april tag auton")
public class AprilTagAutonTest extends LinearOpMode {
    Robot robot;

    @Override
    public void runOpMode() {
        //atp = new ATP(this);
        robot = new Robot(this);
        //atp = new ATP(this);
        //aprilTagDetectionPipeline = new AprilTagDetectionPipeline(robot.atp.tagsize, robot.atp.fx, robot.atp.fy, robot.atp.cx, robot.atp.cy);
        waitForStart();

        if (opModeIsActive()) {
            telemetry.update();
            sleep(3000);
            OpenCV.SignalPipeline.TYPE zone = robot.atp.openCVPipeline.getType();
            int[] averages = robot.atp.openCVPipeline.getAverage();
            telemetry.addData("Signal: ", zone);
            telemetry.addData("Average Red: ", averages[0]);
            telemetry.addData("Average Green: ", averages[1]);
            telemetry.addData("Average Blue: ", averages[2]);
            sleep(3000);
            telemetry.update();
            //Location.TYPE zone = location.getType();
            robot.goToTag(1);

            if (zone == OpenCV.SignalPipeline.TYPE.ZONE1) {
                robot.goToTag(1);
            } else if (zone == OpenCV.SignalPipeline.TYPE.ZONE2) {
                robot.goToTag(2);
            } else if (zone == OpenCV.SignalPipeline.TYPE.ZONE3) {
                robot.goToTag(3);
            } else if (zone == OpenCV.SignalPipeline.TYPE.ZONE4) {
                robot.goToTag(4);
            } else if (zone == OpenCV.SignalPipeline.TYPE.ZONE5) {
                robot.goToTag(5);
            } else if (zone == OpenCV.SignalPipeline.TYPE.ZONE6) {
                robot.goToTag(6);

            }
        }
    }
}

