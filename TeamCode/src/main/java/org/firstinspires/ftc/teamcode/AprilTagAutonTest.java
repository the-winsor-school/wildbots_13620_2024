/*
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
            //getting telemetry values for each color in each zone

            OpenCV.SignalPipeline.TYPE zone = robot.atp.openCVPipeline.getType();
            int[] blueValues = robot.atp.openCVPipeline.getBlueValues();
            int[] redValues = robot.atp.openCVPipeline.getRedValues();
            telemetry.addData("Signal: ", zone);
            telemetry.addData("Blue values: ", blueValues);
            telemetry.addData("Red values: ", redValues);
            /*
            telemetry.addData("Blue Zone 1: ", blueValues[0]);
            telemetry.addData("Blue Zone 2: ", blueValues[1]);
            telemetry.addData("Blue Zone 3: ", blueValues[2]);

            telemetry.addData("Red Zone 1: ", redValues[0]);
            telemetry.addData("Red Zone 2: ", redValues[1]);
            telemetry.addData("Red Zone 3: ", redValues[2]);

             */
/*
            sleep(3000);
            telemetry.update();

            //each zone corresponds to a tag
            if (zone == OpenCV.SignalPipeline.TYPE.ZONE1) {
                telemetry.addLine("Zone 1, Tag 1");
                sleep(250);
                //robot.goToTag(1);
            } else if (zone == OpenCV.SignalPipeline.TYPE.ZONE2) {
                telemetry.addLine("Zone 2, Tag 2");
                sleep(250);
                //robot.goToTag(2);
            } else if (zone == OpenCV.SignalPipeline.TYPE.ZONE3) {
                telemetry.addLine("Zone 3, Tag 3");
                sleep(250);
                //robot.goToTag(3);
            } else if (zone == OpenCV.SignalPipeline.TYPE.ZONE4) {
                telemetry.addLine("Zone 4, Tag 4");
                sleep(250);
                //robot.goToTag(4);
            } else if (zone == OpenCV.SignalPipeline.TYPE.ZONE5) {
                telemetry.addLine("Zone 5, Tag 5");
                sleep(250);
               // robot.goToTag(5);
            } else if (zone == OpenCV.SignalPipeline.TYPE.ZONE6) {
                telemetry.addLine("Zone 6, Tag 6");
                sleep(250);
                //robot.goToTag(6);

            }
        }
    }
}


 */

