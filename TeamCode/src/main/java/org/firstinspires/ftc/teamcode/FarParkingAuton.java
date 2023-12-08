package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
@Autonomous(name="FarParking")
public class FarParkingAuton extends LinearOpMode {
    Robot robot;
    public ColorSensor color;
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();

        if (opModeIsActive()) {

            robot.driving.horizontal(0.50f);
            robot.printWheelPowers();
            telemetry.update();
            sleep(7250);
            robot.driving.stop();

            while(!robot.checkTape()){
                telemetry.addData("tape","not found");
                telemetry.update();
                robot.driving.vertical(0.50f);
                sleep(20);
            }
            robot.driving.stop();

        }
    }
}
