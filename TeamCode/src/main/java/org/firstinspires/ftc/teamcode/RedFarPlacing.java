package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.auton.AllAutonMovements;

@Disabled
@Autonomous(name = "Red Far Placing", group = "place")
public class RedFarPlacing extends LinearOpMode {
    Robot robot;
    AllAutonMovements autonMovements;
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AllAutonMovements(this, robot);

        waitForStart();

        if (opModeIsActive()) {
            autonMovements.PlacingAuton(AllAutonMovements.FieldPosition.FAR_RED);
        }
    }
}