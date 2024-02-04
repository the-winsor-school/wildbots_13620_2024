
package org.firstinspires.ftc.teamcode.AutonOpModes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AutonLibrary.*;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name="test distance sensors")
public class DistanceSensors extends LinearOpMode {
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

