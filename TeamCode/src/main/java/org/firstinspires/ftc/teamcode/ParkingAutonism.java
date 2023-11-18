package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name="parking")
public class ParkingAutonism extends LinearOpMode{
    Robot robot;
    public ColorSensor color;
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

    waitForStart();

        if (opModeIsActive()) {

            //robot.driving.vertical(0.5f);
            //sleep(1000);

            while (!robot.checkTape()) {
                telemetry.addData("tape","not found");
                robot.printWheelPowers();
                telemetry.update();
                robot.driving.vertical(0.50f);
                sleep(20);
            }

            telemetry.addData("tape", "found");
            telemetry.update();
            robot.driving.stop();
        }
    }
}
