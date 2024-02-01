package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.driving.*;

@Autonomous(name="test auto")
public class TestingAuton extends LinearOpMode {
    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();

        while (opModeIsActive()) {

            robot.arm.wrist.updateCurrentVolts();
            telemetry.addData("WRIST", robot.arm.wrist.getCurrentVolts());
            telemetry.update();

        }

    }
}
