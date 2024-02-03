package org.firstinspires.ftc.teamcode.AutonOpModes.Parking;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.MotorState;
import org.firstinspires.ftc.teamcode.AutonLibrary.AllAutonMovements;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name = "Red Far Park", group = "park")
public class RedFarPark extends LinearOpMode {

    Robot robot;
    AllAutonMovements autonMovements;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AllAutonMovements(this, robot);

        robot.arm.claw.moveClaw(Claw.ClawPos.CLOSE);
        robot.arm.wrist.setPower(MotorState.REVERSE);


        waitForStart();

        if (opModeIsActive()) {
            //reverse
            robot.driving.vertical(-0.5f);
            sleep(800);

            //first horizontal
            robot.driving.horizontal(-0.50f);
            sleep(8000);

            //forward
            robot.driving.vertical(0.5f);
            sleep(8000);

            //sideways
            robot.driving.horizontal(0.5f);
            sleep(1500);

            while (!robot.checkTape()) {
                telemetry.addData("tape: ", "not found");
                telemetry.update();
                robot.driving.vertical(0.50f);
                sleep(20);
            }


            robot.driving.stop();
        }
    }
}
