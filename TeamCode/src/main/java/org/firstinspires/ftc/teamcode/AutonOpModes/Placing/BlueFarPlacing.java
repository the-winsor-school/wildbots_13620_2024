package org.firstinspires.ftc.teamcode.AutonOpModes.Placing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;
import org.firstinspires.ftc.teamcode.AutonLibrary.AllAutonMovements;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name = "Blue Far Placing", group = "place")
public class BlueFarPlacing extends LinearOpMode {
    Robot robot;
    AllAutonMovements autonMovements;
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AllAutonMovements(this, robot);
        ElapsedTime timer = new ElapsedTime();

        robot.arm.claw.moveClaw(Claw.ClawPos.CLOSE);
        robot.stopAirPlaneLauncher();


        waitForStart();

        if (opModeIsActive()) {

            //reverse
            robot.driving.vertical(-0.5f);
            sleep(500);

            //first horizontal
            while (robot.getLeftDistance() < 128) {
                robot.driving.horizontal(0.50f);
                sleep(20);
            }

            //forward
            robot.driving.vertical(0.5f);
            sleep(7000);

            while (robot.getLeftDistance() > 75) {
                robot.driving.horizontal(-0.50f);
            }

            robot.arm.moveArmToPosition(FullArm.ArmPosition.PLACING);

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