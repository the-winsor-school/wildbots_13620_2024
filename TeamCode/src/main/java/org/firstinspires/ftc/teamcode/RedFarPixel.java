
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.auton.AllAutonMovements;

@Autonomous(name = "Red Far Pixel", group = "pixel")
public class RedFarPixel extends LinearOpMode {

    Robot robot;
    AllAutonMovements auton;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        auton = new AllAutonMovements(this, robot);

        waitForStart();

        if (opModeIsActive()) {

            auton.moveUnderRightDistance(70, 0.5);

            telemetry.addData("moving", "forward");
            telemetry.update();
            robot.driving.vertical(-0.25);
            sleep(4000);

            auton.moveUnderRightDistance(69, 0.5);

            while (!robot.seePixel() && opModeIsActive())
            {
                telemetry.addData("in", "oop");
                telemetry.update();
                robot.driving.vertical(-0.25);
            }
            robot.driving.stop();

            auton.PlacePixel(robot.whatPixel());
        }
    }
}

