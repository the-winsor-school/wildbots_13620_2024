package org.firstinspires.ftc.teamcode.AutonOpModes.Placing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;
import org.firstinspires.ftc.teamcode.AutonLibrary.AllAutonMovements;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name = "Red Far Placing", group = "place")
public class RedFarPlacing extends LinearOpMode {
    Robot robot;
    AllAutonMovements autonMovements;
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AllAutonMovements(this, robot);
        ElapsedTime timer = new ElapsedTime();

        robot.stopAirPlaneLauncher();

        robot.arm.claw.moveClaw(Claw.ClawPos.CLOSE);

        waitForStart();

        if (opModeIsActive()) {

            //reverse
            robot.driving.vertical(-0.5f);
            sleep(800);

            while (robot.getRightDistance() < 128) {
                telemetry.addData("right", robot.getRightDistance());
                telemetry.update();
                robot.driving.horizontal(-0.50f);
                sleep(20);
            }

            //forward
            robot.driving.vertical(0.5f);
            sleep(8000);

            //sideways
            robot.driving.horizontal(0.5f);
            sleep(1500);

            //arm
            robot.arm.moveArmToPosition(FullArm.ArmPosition.PLACING);

            timer.reset();
            while (timer.milliseconds() < 1000) {
                robot.arm.wrist.moveTowardsTargetPosition();
                robot.arm.elbow.moveTowardsTargetPosition();
                sleep(20);
            }

            while (!robot.checkTape()) {
                robot.arm.wrist.moveTowardsTargetPosition();
                robot.arm.elbow.moveTowardsTargetPosition();
                robot.driving.vertical(0.5f);
                sleep(20);//have to test time
            }
            robot.driving.stop();

            robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
            sleep(500);
            robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
        }
    }
}