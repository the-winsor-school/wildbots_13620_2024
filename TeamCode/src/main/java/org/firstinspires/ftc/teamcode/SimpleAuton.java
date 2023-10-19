package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.driving.*;

@Autonomous(name="test auto")
public class SimpleAuton extends LinearOpMode {
    Robot robot;
    LinearOpMode opMode;
    IDriving driving;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(opMode);
        driving = robot.driving;

        while (opModeIsActive()) {

            driving.horizontal(1);

        }

    }
}
