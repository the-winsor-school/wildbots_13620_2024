package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();

        robot.arm.resetEncoders();

        while (opModeIsActive()){

            //_______________________________________________
            //             MAIN CONTROLLER
            //_______________________________________________

            float x = gamepad1.right_stick_x;
            float y = gamepad1.right_stick_y;
            float t = gamepad1.left_stick_x;

            robot.driving.joystickDrive(x, y, t);

            if (gamepad1.a)
                robot.arm.resetEncoders();

            //_______________________________________________
            //             MECH CONTROLLER
            //_______________________________________________

            //arm levels
            if (gamepad2.y)
                robot.arm.moveToLevel(FullArm.ArmLevel.PLACINGHIGH);
            if(gamepad2.b)
                robot.arm.moveToLevel(FullArm.ArmLevel.RESET);
            if(gamepad2.a)
                robot.arm.moveToLevel(FullArm.ArmLevel.PICKINGUP);
            if(gamepad2.x)
                robot.arm.moveToLevel(FullArm.ArmLevel.PLACINGLOW);

            //arm manual controls
            if (gamepad2.dpad_up)
                robot.arm.liftJoint.changeTargetPosition(200);
            if(gamepad2.dpad_down)
                robot.arm.liftJoint.changeTargetPosition(-200);
            if (gamepad2.dpad_left)
                robot.arm.clawJoint.changeTargetPosition(25);
            if(gamepad2.dpad_right)
                robot.arm.clawJoint.changeTargetPosition(-25);

            //claw controls
            if (gamepad2.left_stick_x > 0.75f)
                robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            else if (gamepad2.left_stick_x < 0.75f)
                robot.arm.claw.controlClaw(Claw.ClawPos.CLOSE);
            else
                robot.arm.claw.controlClaw(Claw.ClawPos.STOP);

            //update arm positions
            robot.arm.clawJoint.armLoop();

            //_______________________________________________
            //             PRINT STATEMENTS
            //_______________________________________________

            //joystick inputs
            //telemetry.addData("x: ", x);
            //telemetry.addData("y: ", y);
            //telemetry.addData("t: ", t);

            //wheels powers
            //robot.printWheelPowers();

            //arm current position
            telemetry.addData("lift joint: ", robot.arm.liftJoint.getCurrentPosition());
            telemetry.addData("claw joint: ", robot.arm.clawJoint.getCurrentPosition());

            //arm directions
            telemetry.addData("lift joint: ", robot.arm.liftJoint.getDirection());
            telemetry.addData("claw joint: ", robot.arm.clawJoint.getDirection());

            telemetry.addData("target lift joint", robot.arm.liftJoint.targetPosition);
            telemetry.addData("target claw joint", robot.arm.clawJoint.targetPosition);

            robot.arm.liftJoint.armLoop();
            robot.arm.clawJoint.armLoop();

            telemetry.update();
        }
    }
}