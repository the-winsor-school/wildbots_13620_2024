/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;
import org.firstinspires.ftc.teamcode.Arm.MotorState;
import org.firstinspires.ftc.teamcode.auton.AllAutonMovements;

@Autonomous(name = "Red Close Pixel", group = "pixel")
public class RedCloseDistance extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        robot.arm.moveArmToPosition(FullArm.ArmPosition.TRAVELING);

        waitForStart();

        if (opModeIsActive()) {

            while (!robot.frontFound || !robot.leftFound || !robot.rightFound) {
                robot.checkAllDistances();
                telemetry.addData("right", robot.rightFound);
                telemetry.addData("front", robot.frontFound);
                telemetry.addData("left", robot.leftFound);
                telemetry.update();

                robot.driving.vertical(-0.25);
                sleep(2500);
            }

            if (robot.leftFound) {
                robot.driving.turn(-0.5);
                sleep(5000);
            }
        }
    }
}
*/
