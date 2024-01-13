package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.driving.*;

@Autonomous(name="test auto")
public class TestingAuton extends LinearOpMode {
    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        boolean pixelPlaced = false;

        waitForStart();

        while (opModeIsActive()) {

            while (pixelPlaced == false) {
                robot.driving.vertical(-0.50f);//backing up to the tape
                sleep(2100); //just an estimate - test time

                if (robot.frontDistanceValue() < 5) //5 is just the estimate - test & change
                {
                    robot.driving.vertical(-0.50f);//backing up until it's lined up to place the pixel
                    sleep(1500); //just an estimate - test time
                    robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
                    sleep(500);
                    robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
                    robot.driving.vertical(0.50f); //go back
                    sleep(1500);
                    pixelPlaced = true;
                }

                if (robot.rightDistanceValue() < 5)//5 is just an estimate
                {
                    robot.driving.turn(0.50f);
                    sleep(500); //ESTIMATE!! should be a little bit more than a quarter of a turn
                    robot.driving.vertical(0.50f);
                    sleep(300); //inch forward a little
                    robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
                    sleep(500);
                    robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
                    robot.driving.vertical(-0.50f);
                    sleep(300); //go back
                    robot.driving.turn(-0.50f);
                    sleep(500); //turn back
                    pixelPlaced = true;
                }

                if (robot.leftDistanceValue() < 5)//just an estimate
                {
                    robot.driving.turn(-0.50f);
                    sleep(500); //ESTIMATE!! should be a little bit more than a quarter of a turn
                    robot.driving.vertical(0.50f);
                    sleep(300); //inch forward a little
                    robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
                    sleep(500);
                    robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
                    robot.driving.vertical(-0.50f);
                    sleep(300); //go back
                    robot.driving.turn(0.50f);
                    sleep(500); //turn back
                    pixelPlaced = true;
                }

            }
            robot.driving.vertical(0.50f);
            sleep(1000);
            robot.driving.turn(0.50f);
            sleep(1000);

            while (!robot.checkTape())
            {
                telemetry.addData("tape: ","not found");
                robot.printWheelPowers();
                telemetry.update();
                robot.driving.vertical(0.50f);
                sleep(20);
            }



        }

    }
}
