package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="test auto")
public class SimpleAuton extends LinearOpMode {
    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        while (opModeIsActive()) {

            robot.driving.horizontal(1);
        }

    }
}
