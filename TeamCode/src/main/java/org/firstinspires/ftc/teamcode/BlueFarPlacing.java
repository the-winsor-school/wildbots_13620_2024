package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.auton.AutonMovements;

@Autonomous(name = "Blue Far Placing", group = "place")
public class BlueFarPlacing extends LinearOpMode {
    Robot robot;
    AutonMovements autonMovements;
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AutonMovements(this, robot);

        waitForStart();

        if (opModeIsActive()) {
            autonMovements.PlacingAuton(AutonMovements.FieldPosition.FAR_BLUE);
        }
    }
}