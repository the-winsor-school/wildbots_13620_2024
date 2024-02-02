
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.driving.*;
import org.firstinspires.ftc.teamcode.auton.*;


@Autonomous(name="test")
public class test extends LinearOpMode {
    Robot robot;
    AllAutonMovements auton;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();

        while (opModeIsActive()) {
                telemetry.addData("front", robot.getFrontDistance());
                telemetry.addData("right", robot.getRightDistance());
                telemetry.addData("left", robot.getLeftDistance());
                telemetry.update();

        }

    }
}

