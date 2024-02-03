
package org.firstinspires.ftc.teamcode.AutonOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.AutonLibrary.AllAutonMovements;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name = "Testing Pixel Turns", group = "test")
public class TestPixelTurns extends LinearOpMode {

    Robot robot;
    AllAutonMovements auton;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        auton = new AllAutonMovements(this, robot);
        robot.arm.claw.moveClaw(Claw.ClawPos.CLOSE);

        waitForStart();

        if (opModeIsActive()) {
            robot.driving.turn(0.5);
            sleep(2500);
            robot.driving.stop();

        }
    }
}

