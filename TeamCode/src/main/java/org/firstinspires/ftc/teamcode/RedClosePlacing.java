package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.FullArm;
import org.firstinspires.ftc.teamcode.auton.AutonMovements;

@Autonomous(name = "Red Close Placing", group = "place")
public class RedClosePlacing extends LinearOpMode {
    Robot robot;
    AutonMovements autonMovements;
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AutonMovements(this, robot);

        waitForStart();

        if (opModeIsActive()) {
            autonMovements.PlacingAuton(AutonMovements.FieldPosition.CLOSE_RED);
        }
    }
}