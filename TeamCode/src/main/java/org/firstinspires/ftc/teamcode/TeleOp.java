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
            float y = -gamepad1.right_stick_y; //inputs from joystick are opposite
            float t = gamepad1.left_stick_x;

            robot.driving.joystickDrive(x, y, t);

            //make wheels go faster
            if (gamepad1.dpad_up)
                robot.driving.adjustSpeed(0.05f);

            //make wheels speed slower
            if(gamepad1.dpad_down)
                robot.driving.adjustSpeed(-0.05f);

            //reset arm encoders
            if (gamepad1.x)
                robot.arm.resetEncoders();

            //_______________________________________________
            //             MECH CONTROLLER
            //_______________________________________________

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
            if (gamepad2.right_bumper)
                robot.arm.claw.controlClaw(Claw.ClawPos.OPEN);
            if (gamepad2.left_bumper)
                robot.arm.claw.controlClaw(Claw.ClawPos.CLOSE);

            //_______________________________________________
            //             PRINT STATEMENTS
            //_______________________________________________

            //joystick inputs
            //telemetry.addData("x: ", x);
            //telemetry.addData("y: ", y);
            //telemetry.addData("t: ", t);

            //wheels powers
            //robot.printWheelPowers();

            telemetry.addLine("----------------ARM-------------------------");

            //arm current position
            telemetry.addData("lift joint: ", robot.arm.liftJoint.getCurrentPosition());
            telemetry.addData("claw joint: ", robot.arm.clawJoint.getCurrentPosition());

            //arm directions
            telemetry.addData("lift joint: ", robot.arm.liftJoint.getDirection());
            telemetry.addData("claw joint: ", robot.arm.clawJoint.getDirection());

            telemetry.addData("target lift joint: ", robot.arm.liftJoint.targetPosition);
            telemetry.addData("target claw joint: ", robot.arm.clawJoint.targetPosition);

            telemetry.addLine("----------------CLAW-------------------------");

            telemetry.addData("right servo: ", robot.arm.claw.getPower("right"));
            telemetry.addData("left servo: ", robot.arm.claw.getPower("left"));

            robot.arm.liftJoint.armLoop();
            robot.arm.clawJoint.armLoop();

            telemetry.update();
        }
    }
}