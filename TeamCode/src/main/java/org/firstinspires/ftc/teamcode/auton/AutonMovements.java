package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.*;

public class AutonMovements {

    Robot robot;
    LinearOpMode opMode;
    Telemetry telemetry;

    public AutonMovements(LinearOpMode opMode, Robot robot) {
        this.robot = robot;
        this.opMode = opMode;
        this.telemetry = opMode.telemetry;
    }

    public void ClosePark() {

        while (!robot.checkTape()) {
            opMode.telemetry.addData("tape","not found");
            robot.printWheelPowers();
            telemetry.update();
            robot.driving.vertical(0.50f);
            opMode.sleep(20);
        }

        telemetry.addData("tape", "found");
        telemetry.update();
        robot.driving.stop();
    }

}
