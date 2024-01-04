package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.auton.AllAutonMovements;

@Autonomous(name = "Red Close Placing", group = "place")
public class RedClosePlacing extends LinearOpMode {
    Robot robot;
    AllAutonMovements autonMovements;
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AllAutonMovements(this, robot);

        waitForStart();

        if (opModeIsActive()) {
            autonMovements.PlacingAuton(AllAutonMovements.FieldPosition.CLOSE_RED);
        }
    }
}