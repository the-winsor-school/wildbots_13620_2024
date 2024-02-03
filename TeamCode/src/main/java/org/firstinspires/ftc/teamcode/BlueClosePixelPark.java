
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.auton.AllAutonMovements;

@Disabled
@Autonomous(name = "Blue Close Pixel Park", group = "pixel and park")
public class BlueClosePixelPark extends LinearOpMode {

    Robot robot;
    AllAutonMovements auton;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        auton = new AllAutonMovements(this, robot);

        waitForStart();

        if (opModeIsActive()) {
            auton.moveUnderRightDistance(123, 0.5);

            telemetry.addData("moving", "forward");
            telemetry.update();
            robot.driving.vertical(-0.25);
            sleep(4000);

            auton.moveUnderRightDistance(123, 0.5);

            while (!robot.seePixel() && opModeIsActive())
            {
                telemetry.addData("in", "loop");
                telemetry.update();
                robot.driving.vertical(-0.25);
            }
            robot.driving.stop();

            AllAutonMovements.PixelLocation pixel = robot.whatPixel();

            auton.PlacePixel(pixel);

            //parking

            if (pixel == AllAutonMovements.PixelLocation.FRONT)  {
                auton.moveUnderFrontDistance(50, 0.5);

                while (!robot.checkTape()) {
                    robot.driving.horizontal(-0.5);
                }
            }
            else if (pixel == AllAutonMovements.PixelLocation.LEFT)  {
                auton.moveToLeftDistance(50, 0.5);
                while (!robot.checkTape()) {
                    robot.driving.vertical(0.5);
                }
            }
            else if (pixel == AllAutonMovements.PixelLocation.RIGHT) {
                auton.moveUnderRightDistance(50, 0.5);
                while (!robot.checkTape()) {
                    robot.driving.vertical(-0.5);
                }
            }

            robot.driving.stop();

        }
    }
}

