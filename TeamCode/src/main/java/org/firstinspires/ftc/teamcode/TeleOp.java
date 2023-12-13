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

        //we are using arm encoders right now
        robot.arm.armEncodersOn = true;

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

            //_______________________________________________
            //             MECH CONTROLLER
            //_______________________________________________


            if (robot.arm.armEncodersOn) {

                //arm manual controls
                if (gamepad2.dpad_up)
                    robot.arm.liftJoint.changeTargetPosition(200);
                if(gamepad2.dpad_down)
                    robot.arm.liftJoint.changeTargetPosition(-200);
                if (gamepad2.dpad_right)
                    robot.arm.clawJoint.changeTargetPosition(50);
                if (gamepad2.dpad_left)
                    robot.arm.clawJoint.changeTargetPosition(-50);

                //arm levels
                if (gamepad2.x)
                    robot.arm.moveArmToPosition(FullArm.ArmPosition.PICKINGUP);
                if (gamepad2.a)
                    robot.arm.moveArmToPosition(FullArm.ArmPosition.RESET);
                if (gamepad2.b)
                    robot.arm.moveArmToPosition(FullArm.ArmPosition.PLACINGLOW);
                if (gamepad2.y)
                    robot.arm.moveArmToPosition(FullArm.ArmPosition.TRAVEL);

                robot.arm.liftJoint.armLoop();
                robot.arm.clawJoint.armLoop();

            } else {
                //arm manual controls
                if (gamepad2.dpad_up)
                    robot.arm.liftJoint.usePower(true);
                if(gamepad2.dpad_down)
                    robot.arm.liftJoint.usePower(true);
                if (gamepad2.dpad_right)
                    robot.arm.clawJoint.usePower(false);
                if (gamepad2.dpad_left)
                    robot.arm.clawJoint.usePower(false);
            }

            //claw controls
            if (gamepad2.right_bumper)
                robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
            if (gamepad2.left_bumper)
                robot.arm.claw.moveClaw(Claw.ClawPos.CLOSE);
            if(!gamepad2.right_bumper && !gamepad2.left_bumper)
                robot.arm.claw.moveClaw(Claw.ClawPos.STOP);

            //_______________________________________________
            //             PRINT STATEMENTS
            //_______________________________________________

            telemetry.addData("ARM MODE1:", robot.arm.armEncodersOn? "using encoders" : "not using encoders");
            telemetry.addLine("\n");

            telemetry.addLine("----------------WHEELS-------------------------");


            //joystick inputs
            telemetry.addData("x: ", x);
            telemetry.addData("y: ", y);
            telemetry.addData("t: ", t);

            //wheels powers
            robot.printWheelPowers();

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

            telemetry.addData("lift limit: ", robot.liftLimitValue());
            telemetry.addData("claw limit: ", robot.clawLimitValue());

            telemetry.update();
        }
    }
}