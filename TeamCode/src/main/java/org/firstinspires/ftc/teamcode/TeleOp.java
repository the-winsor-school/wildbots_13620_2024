package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.CombinedArm;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        //we are using arm encoders right now
        robot.arm.armEncodersOn = true;

        if (robot.arm.armEncodersOn) {
            robot.arm.resetEncoders();
        } else {
            robot.arm.runWithoutEncoders();
        }

        waitForStart();

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
                    robot.arm.elbow.setTargetPosition(robot.arm.elbow.getCurrentPosition() + 200);
                if(gamepad2.dpad_down)
                    robot.arm.elbow.setTargetPosition(robot.arm.elbow.getCurrentPosition() - 200);
                if (gamepad2.dpad_right)
                    robot.arm.wrist.setTargetPosition(robot.arm.wrist.getCurrentPosition() + 50);
                if (gamepad2.dpad_left)
                    robot.arm.wrist.setTargetPosition(robot.arm.wrist.getCurrentPosition() - 50);

                //arm levels
                if (gamepad2.x)
                    robot.arm.moveArmToPosition(CombinedArm.ArmPosition.PICKING_UP);
                if (gamepad2.a)
                    robot.arm.moveArmToPosition(CombinedArm.ArmPosition.RESET);
                if (gamepad2.b)
                    robot.arm.moveArmToPosition(CombinedArm.ArmPosition.PLACING);
                if (gamepad2.y)
                    robot.arm.moveArmToPosition(CombinedArm.ArmPosition.TRAVELING);

            } else {

                //arm manual controls
                if (gamepad2.dpad_up)
                    robot.arm.elbow.setPower(robot.arm.elbowPower);
                if(gamepad2.dpad_down)
                    robot.arm.elbow.setPower(-robot.arm.elbowPower);
                if (gamepad2.dpad_right)
                    robot.arm.wrist.setPower(robot.arm.wristPower);
                if (gamepad2.dpad_left)
                    robot.arm.wrist.setPower(-robot.arm.wristPower);

                //braking
                if (!gamepad2.dpad_down && !gamepad2.dpad_up) {
                    robot.arm.elbow.setPower(0);
                    robot.arm.elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                }
                if (!gamepad2.dpad_right&& !gamepad2.dpad_left) {
                    robot.arm.wrist.setPower(0);
                    robot.arm.wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                }
            }

            //claw controls
            if (gamepad2.right_bumper)
                robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
            if (gamepad2.left_bumper)
                robot.arm.claw.moveClaw(Claw.ClawPos.CLOSE);
            if(!gamepad2.right_bumper && !gamepad2.left_bumper)
                robot.arm.claw.moveClaw(Claw.ClawPos.STOP);

            //_______________________________________________
            //             ARM LOOPS
            //_______________________________________________

            robot.arm.runToPosition();
            robot.arm.updateZeroWithLimits();

            //_______________________________________________
            //             PRINT STATEMENTS
            //_______________________________________________



            telemetry.addLine("----------------WHEELS-------------------------");
            telemetry.addData("WHEELS SPEED:", robot.driving.getSpeed());
/*

            //joystick inputs
            telemetry.addData("x: ", x);
            telemetry.addData("y: ", y);
            telemetry.addData("t: ", t);

            //wheels powers
            robot.printWheelPowers();
*/

           telemetry.addLine("----------------ARM-------------------------");

           telemetry.addData("ARM MODE:", robot.arm.armEncodersOn? "using encoders" : "not using encoders");
           telemetry.addLine("\n");

            //arm current position
            telemetry.addData("elbow: ", robot.arm.elbow.getCurrentPosition());
            telemetry.addData("wrist: ", robot.arm.wrist.getCurrentPosition());

            //arm directions
            telemetry.addData("elbow: ", robot.arm.elbow.getDirection());
            telemetry.addData("wrist: ", robot.arm.wrist.getDirection());

            telemetry.addData("target lift joint: ", robot.arm.elbow.getTargetPosition());
            telemetry.addData("target claw joint: ", robot.arm.wrist.getTargetPosition());

            telemetry.addLine("----------------CLAW-------------------------");

            telemetry.addData("right servo: ", robot.arm.claw.getPower("right"));
            telemetry.addData("left servo: ", robot.arm.claw.getPower("left"));

            telemetry.addData("lift limit: ", robot.liftLimitValue());
            telemetry.addData("claw limit: ", robot.clawLimitValue());

            telemetry.update();
        }
    }
}