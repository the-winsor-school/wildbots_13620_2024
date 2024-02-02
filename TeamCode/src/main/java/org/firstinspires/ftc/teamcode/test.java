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
        auton =  new AllAutonMovements(this, robot);

        boolean pixelPlaced = false;
        while(!opModeIsActive()) {
            robot.checkAllDistances();
            telemetry.addData("front distance", robot.frontDistanceValue());
            telemetry.update();
        }

        waitForStart();

        while (opModeIsActive()) {

            while (opModeIsActive()) {
                auton.checkDistanceMovement();
            }

        }

    }
}
