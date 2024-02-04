
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AutonLibrary.AllAutonMovements;

@Autonomous(name="test turn")
public class Test extends LinearOpMode {
    Robot robot;
    AllAutonMovements auton;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();

        if (opModeIsActive()) {
                robot.driving.turn(-0.5);
                sleep(2300);
                robot.driving.stop();
        }

    }
}

