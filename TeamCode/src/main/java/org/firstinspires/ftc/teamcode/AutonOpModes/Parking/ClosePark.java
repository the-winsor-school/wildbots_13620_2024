package org.firstinspires.ftc.teamcode.AutonOpModes.Parking;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;
import org.firstinspires.ftc.teamcode.Arm.MotorState;
import org.firstinspires.ftc.teamcode.AutonLibrary.AllAutonMovements;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name = "Close Park (red or blue)", group = "park")
public class ClosePark extends LinearOpMode {

    Robot robot;
    AllAutonMovements autonMovements;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        autonMovements = new AllAutonMovements(this, robot);

        robot.arm.wrist.setPower(MotorState.REVERSE);

        waitForStart();

        if (opModeIsActive())  {
            robot.arm.moveToPositionSync(FullArm.ArmPosition.TRAVELING);

            while (!robot.checkTape()) {
                telemetry.addData("tape: ","not found");
                robot.printWheelPowers();
                telemetry.update();
                robot.driving.vertical(0.50f);
                sleep(20);
            }

            telemetry.addData("tape: ", "found");
            telemetry.update();
            robot.driving.stop();

            robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
            sleep(1000);
            robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
        }
    }
}
