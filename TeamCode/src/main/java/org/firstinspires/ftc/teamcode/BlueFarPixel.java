
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.FullArm;
import org.firstinspires.ftc.teamcode.auton.AllAutonMovements;

@Autonomous(name = "Blue Far Pixel", group = "pixel")
public class BlueFarPixel extends LinearOpMode {

    Robot robot;
    AllAutonMovements auton;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        auton = new AllAutonMovements(this, robot);

        waitForStart();

        if (opModeIsActive()) {
            while (robot.getLeftDistance() < 70 && opModeIsActive()) {
                telemetry.addData("left", robot.getLeftDistance());
                telemetry.update();
                robot.driving.horizontal(0.5);
                sleep(10);
            }

            telemetry.addData("moving", "forwadrd");
            telemetry.update();
            robot.driving.vertical(-0.25);
            sleep(4000);

            while (robot.getLeftDistance() < 69 && opModeIsActive()) {
                telemetry.addData("left", robot.getLeftDistance());
                telemetry.update();
                robot.driving.horizontal(0.5);
                sleep(10);
            }

            while (!robot.seePixel() && opModeIsActive())
            {
                telemetry.addData("in", "oop");
                telemetry.update();
                robot.driving.vertical(-0.25);
            }
            robot.driving.stop();

            if (robot.frontPixel()) {
                auton.PlacePixel(AllAutonMovements.PixelLocation.FRONT);
            }
            else if (robot.leftPixel()) {
                auton.PlacePixel(AllAutonMovements.PixelLocation.LEFT);
            }
            else if (robot.rightPixel()) {
                auton.PlacePixel(AllAutonMovements.PixelLocation.RIGHT);
            }
        }
    }
}

