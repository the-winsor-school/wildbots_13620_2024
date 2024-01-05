package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;
import org.firstinspires.ftc.teamcode.Robot;

public class PlacingAuton {

    Robot robot;
    LinearOpMode opMode;
    Telemetry telemetry;

    public PlacingAuton(LinearOpMode opMode, Robot robot) {
        this.robot = robot;
        this.opMode = opMode;
        this.telemetry = opMode.telemetry;
    }

    public void drivePlacingAuton (AllAutonMovements.FieldPosition fieldPosition) {
        int horizontalDirection = 0; //will never be zero

        if (fieldPosition == AllAutonMovements.FieldPosition.FAR_BLUE || fieldPosition == AllAutonMovements.FieldPosition.CLOSE_BLUE)
            horizontalDirection = 1;
        else if (fieldPosition == AllAutonMovements.FieldPosition.FAR_RED || fieldPosition == AllAutonMovements.FieldPosition.CLOSE_RED)
            horizontalDirection = -1;

        if (fieldPosition == AllAutonMovements.FieldPosition.CLOSE_BLUE
                || fieldPosition == AllAutonMovements.FieldPosition.CLOSE_RED) {

            robot.driving.horizontal(0.50f * horizontalDirection);
            opMode.sleep(4200);//have to test time

            robot.driving.vertical(0.50f);
            opMode.sleep(1000);//have to test time

            robot.arm.moveToPositionSync(FullArm.ArmPosition.PLACING);

            robot.driving.vertical(0.25f);
            opMode.sleep(1000);//have to test time
            robot.driving.stop();

            robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
            opMode.sleep(500);
            robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
        }

        else if (fieldPosition == AllAutonMovements.FieldPosition.FAR_BLUE
                || fieldPosition == AllAutonMovements.FieldPosition.FAR_RED){

            robot.driving.vertical(-0.5f);
            opMode.sleep(500);
            robot.driving.stop();

            robot.driving.horizontal(0.50f * horizontalDirection);
            opMode.sleep(4000);
            robot.driving.stop();
            robot.driving.horizontal(0.50f * horizontalDirection);
            opMode.sleep(3500);
            robot.driving.stop();

            robot.driving.vertical(0.50f);
            opMode.sleep(2500);
            robot.driving.stop();

            robot.driving.horizontal(0.50f * horizontalDirection);
            opMode.sleep(2000);
            robot.driving.stop();

            robot.arm.moveToPositionSync(FullArm.ArmPosition.PLACING);

            robot.driving.vertical(0.25f);
            opMode.sleep(100);//have to test time
            robot.driving.stop();

            robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
            opMode.sleep(500);
            robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
        }
    }
}
