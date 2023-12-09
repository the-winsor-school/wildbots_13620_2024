package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="april tag auton")
public class AprilTagAutonTest extends LinearOpMode {
    Robot robot;
    //AprilTagDetectionPipeline aprilTagDetectionPipeline;

    //ATP atp;

    //Location location;

    @Override
    public void runOpMode() {
        //atp = new ATP(this);
        robot = new Robot(this);
        //atp = new ATP(this);
        //aprilTagDetectionPipeline = new AprilTagDetectionPipeline(robot.atp.tagsize, robot.atp.fx, robot.atp.fy, robot.atp.cx, robot.atp.cy);
        //location = new Location();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        if (opModeIsActive()) {
            //Location.TYPE zone = location.getType();
            robot.goToTag(1);

            /*
            averageBlue = (int) Core.mean(region1_Cb).val[0]; // red average values
            averageGreen = (int) Core.mean(region1_Cg).val[0]; // blue average values
            averageRed = (int) Core.mean(region1_Cr).val[0]; // green average values

            Imgproc.rectangle(input, topLeft, bottomRight, BLUE, 2);

            if (averageBlue < averageRed && averageGreen < averageRed) {
                type = TYPE.ZONE1;
            }
            else if (averageBlue < averageGreen && averageRed < averageGreen) {
                type = TYPE.ZONE2;
            }
            else if (averageGreen < averageBlue && averageRed < averageBlue) {
                type = TYPE.ZONE3;
            }
            else {
                type = null;
            }

            return input;
             */

            /*
            if (zone == Location.TYPE.ZONE1) {
                robot.goToTag(4);
            } else if (zone == Location.TYPE.ZONE2) {
                robot.goToTag(5);
            } else if (zone == Location.TYPE.ZONE3) {
                robot.goToTag(6);
            }

             */
        }
    }

    /*
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

     */
}

