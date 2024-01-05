package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;
import org.firstinspires.ftc.teamcode.auton.AllAutonMovements;

@Autonomous(name = "Blue Close Placing", group = "place")
public class BlueClosePlacing extends LinearOpMode {
    Robot robot;
    AllAutonMovements autonMovements;
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AllAutonMovements(this, robot);

        waitForStart();

        if (opModeIsActive()) {
            robot.driving.horizontal(0.50f);
            sleep(4200);//have to test time

            robot.driving.vertical(0.50f);
            sleep(1000);//have to test time

            robot.arm.moveToPositionSync(FullArm.ArmPosition.PLACING);
            telemetry.addLine("Arm Moved");
            telemetry.update();

            robot.driving.vertical(-0.25f);
            sleep(1000);//have to test time
            robot.driving.stop();

            robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
            sleep(500);
            robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
        }
    }
}