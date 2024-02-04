
package org.firstinspires.ftc.teamcode.AutonOpModes.Pixel;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.AutonLibrary.AllAutonMovements;
import org.firstinspires.ftc.teamcode.Robot;
@Disabled
@Autonomous(name = "Blue Far Pixel", group = "pixel")
public class BlueFarPixel extends LinearOpMode {

    Robot robot;
    AllAutonMovements auton;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        auton = new AllAutonMovements(this, robot);
        robot.arm.claw.moveClaw(Claw.ClawPos.CLOSE);

        waitForStart();

        if (opModeIsActive()) {
            auton.moveToLeftDistance(68, 0.5, 0.5);

            telemetry.addData("moving", "forward");
            telemetry.update();
            robot.driving.vertical(-0.25);
            sleep(4000);

            auton.moveToLeftDistance(68, 0.5, 0.5);

            while (!robot.seePixel() && opModeIsActive())
            {
                telemetry.addData("in", "oop");
                telemetry.update();
                robot.driving.vertical(-0.25);
            }
            robot.driving.stop();

            if (robot.frontPixel()) {
                auton.PlacePixel(robot.whatPixel());
            }
            else if (robot.rightPixel()) {
                auton.PlacePixel(robot.whatPixel());
            }
            else if (robot.leftPixel()) {
                auton.PlacePixel(robot.whatPixel());
            }
        }
    }
}

