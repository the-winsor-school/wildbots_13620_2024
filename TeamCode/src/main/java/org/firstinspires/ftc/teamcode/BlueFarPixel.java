
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.auton.AllAutonMovements;

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
            auton.moveToLeftDistance(68, 0.5);

            telemetry.addData("moving", "forward");
            telemetry.update();
            robot.driving.vertical(-0.25);
            sleep(4000);

            auton.moveToLeftDistance(68, 0.5);

            while (!robot.seePixel() && opModeIsActive())
            {
                telemetry.addData("in", "oop");
                telemetry.update();
                robot.driving.vertical(-0.25);
            }
            robot.driving.stop();

            if (robot.frontPixel()) { //good
                robot.driving.turn(0.5);
                sleep(5000);
                robot.driving.stop();
                robot.driving.vertical(0.25);
                sleep(100);
                robot.driving.stop();
                sleep(500);
                robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
                sleep(1000);
            }

            else if (robot.leftPixel()) {
                robot.driving.turn(0.5);
                sleep(2000);
                robot.driving.stop();
                sleep(500);
                robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
                sleep(1000);
            }
            else if (robot.rightPixel()) {
                robot.driving.turn(-0.5);
                sleep(2000);
                robot.driving.stop();
                sleep(500);
                robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
                sleep(1000);
            }
        }
    }
}

