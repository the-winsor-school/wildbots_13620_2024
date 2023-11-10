package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.driving.IDriving;

@Autonomous(name="parking")
public class parkingauton extends LinearOpMode{
    Robot robot;
    public ColorSensor color;
    color.red;
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

    waitForStart();

        if (opModeIsActive()) {
            while (!robot.checkTape() && opModeIsActive()) {
                telemetry.addData("no","Tape");
                telemetry.addData("red", color.red());
                telemetry.update();
                robot.driving.vertical(0.5f);
            }
                robot.driving.stop();
        }
    }
}
