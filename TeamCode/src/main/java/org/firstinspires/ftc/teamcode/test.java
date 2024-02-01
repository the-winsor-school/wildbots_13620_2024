package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.driving.*;

@Autonomous(name="test")
public class test extends LinearOpMode {
    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        boolean pixelPlaced = false;

        waitForStart();

        while (opModeIsActive()) {

            while (opModeIsActive()) {
                robot.checkAllDistances();
                telemetry.addData("front", robot.frontFound);
                telemetry.addData("left", robot.leftFound);
                telemetry.addData("right", robot.rightFound);
                telemetry.update();
            }

        }

    }
}
