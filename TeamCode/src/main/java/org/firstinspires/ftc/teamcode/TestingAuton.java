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
                telemetry.addLine("backing up");
                telemetry.update();
                robot.driving.vertical(-0.50f);//backing up to the tape
                sleep(2650);
                robot.driving.vertical(0f);
                telemetry.addLine("stopping");
                telemetry.update();

                while (!robot.frontFound && !robot.leftFound && !robot.rightFound)
                {
                    robot.driving.turn(0.5f);
                    sleep(100);
                    robot.driving.turn(0f);

                    robot.checkAllDistances();

                    robot.driving.turn(0.5f);
                    sleep(100);
                    robot.driving.turn(0f);

                    robot.checkAllDistances();

                    robot.driving.turn(-0.5f);
                    sleep(300);
                    robot.driving.turn(0f);

                    robot.checkAllDistances();

                    robot.driving.turn(-0.5f);
                    sleep(100);
                    robot.driving.turn(0f);

                    robot.checkAllDistances();

                    //FAIL SAFE

                    telemetry.addLine("FAILED");
                    telemetry.update();

                    pixelPlaced=true;
                }


                if (robot.frontFound == true)
                {
                    telemetry.addLine("front found");
                    telemetry.update();
                    sleep(2000);
                    robot.driving.vertical(-0.50f);//backing up until it's lined up to place the pixel
                    sleep(1500);
                    robot.driving.vertical(0f);

                    /*
                    COMMENTED THIS OUT BC THE CLAW IS BROKEN RN
                    robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
                    sleep(500);
                    robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
                     */

                    robot.driving.vertical(0.50f); //go back
                    sleep(1500);
                    robot.driving.vertical(0f);
                    pixelPlaced = true;
                }

                if (robot.rightFound==true)
                {
                    telemetry.addLine("right found");
                    telemetry.update();
                    robot.driving.turn(0.50f);
                    sleep(500); //ESTIMATE!! should be a little bit more than a quarter of a turn
                    robot.driving.vertical(0.50f);
                    sleep(300); //inch forward a little

                    /*
                    COMMENTED THIS OUT BC THE CLAW IS BROKEN RN
                    robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
                    sleep(500);
                    robot.arm.claw.moveClaw(Claw.ClawPos.STOP);*/

                    robot.driving.vertical(-0.50f);
                    sleep(300); //go back
                    robot.driving.vertical(0f);
                    robot.driving.turn(-0.50f);
                    sleep(500); //turn back
                    pixelPlaced = true;
                }

                if (robot.leftFound==true)
                {
                    telemetry.addLine("left found");
                    telemetry.update();
                    robot.driving.turn(-0.50f);
                    sleep(500); //ESTIMATE!! should be a little bit more than a quarter of a turn
                    robot.driving.vertical(0.50f);
                    sleep(300); //inch forward a little

                    /* COMMENTING CLAW PART OUT
                    robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
                    sleep(500);
                    robot.arm.claw.moveClaw(Claw.ClawPos.STOP);*/

                    robot.driving.vertical(-0.50f);
                    sleep(300); //go back
                    robot.driving.turn(0.50f);
                    sleep(500); //turn back
                    pixelPlaced = true;
                }


            }

           //ONCE PIXEL HAS BEEN PLACED, WE PARK!

            telemetry.addLine("PIXEL IS PLACED");
            telemetry.update();

            robot.driving.vertical(0.50f);
            sleep(1000);
            robot.driving.vertical(0f);
            robot.driving.turn(0.50f);
            sleep(1000);
            robot.driving.turn(0f);

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
