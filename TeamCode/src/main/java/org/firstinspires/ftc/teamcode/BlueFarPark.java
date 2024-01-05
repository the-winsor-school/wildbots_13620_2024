package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.auton.AllAutonMovements;

@Autonomous(name = "Blue Far Park", group = "park")
public class BlueFarPark extends LinearOpMode {

    Robot robot;
    AllAutonMovements autonMovements;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AllAutonMovements(this, robot);

        robot.arm.claw.moveClaw(Claw.ClawPos.CLOSE);

        waitForStart();

        if (opModeIsActive()) {
            autonMovements.FarPark(AllAutonMovements.FieldPosition.FAR_BLUE);
        }
    }
}
