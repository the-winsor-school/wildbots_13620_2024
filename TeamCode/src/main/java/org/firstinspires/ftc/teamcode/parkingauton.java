package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.driving.IDriving;

@Autonomous(name="parking")
public class parkingauton extends LinearOpMode{
    Robot robot;
    LinearOpMode opMode;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(opMode);

    waitForStart();

        if (opModeIsActive()) {
            while (!robot.checkTape() && opModeIsActive()) {
                robot.driving.vertical(1);
                sleep(300);
            }
            robot.driving.stop();
        }
    }
}
