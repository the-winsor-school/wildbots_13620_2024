package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.auton.AutonMovements;

@Autonomous(name = "Close Park (red or blue)", group = "park")
public class ClosePark extends LinearOpMode {

    Robot robot;
    AutonMovements autonMovements;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AutonMovements(this, robot);

        waitForStart();

        if (opModeIsActive())  {
            autonMovements.ClosePark();
        }
    }
}
