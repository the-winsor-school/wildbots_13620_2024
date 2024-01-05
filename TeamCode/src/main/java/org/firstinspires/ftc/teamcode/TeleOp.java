package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;
import org.firstinspires.ftc.teamcode.Arm.SimpleArmJoint;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        //we are using arm encoders right now
        robot.arm.armEncodersOn = true;

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
                    robot.arm.elbow.changeTargetPosition(200);
                else if (gamepad2.dpad_down)
                    robot.arm.elbow.changeTargetPosition(-200);
                if (gamepad2.dpad_right)
                    robot.arm.simpleWrist.moveArmJoint(DcMotorSimple.Direction.FORWARD);
                else if (gamepad2.dpad_left)
                    robot.arm.simpleWrist.moveArmJoint(DcMotorSimple.Direction.REVERSE);
                else
                    robot.arm.simpleWrist.stop();

                //arm levels
                if (gamepad2.x)
                    robot.arm.moveArmToPosition(FullArm.ArmPosition.PICKING_UP);
                if (gamepad2.a)
                    robot.arm.moveArmToPosition(FullArm.ArmPosition.RESET);
                if (gamepad2.b)
                    robot.arm.moveArmToPosition(FullArm.ArmPosition.PLACING);
                if (gamepad2.y)
                    robot.arm.moveArmToPosition(FullArm.ArmPosition.TRAVELING);

            } else {

                //arm manual controls
                if (gamepad2.dpad_up)
                    robot.arm.simpleElbow.moveArmJoint(DcMotorSimple.Direction.FORWARD);
                if(gamepad2.dpad_down)
                    robot.arm.simpleElbow.moveArmJoint(DcMotorSimple.Direction.REVERSE);
                if (gamepad2.dpad_right)
                    robot.arm.simpleWrist.moveArmJoint(DcMotorSimple.Direction.FORWARD);
                if (gamepad2.dpad_left)
                    robot.arm.simpleWrist.moveArmJoint(DcMotorSimple.Direction.REVERSE);

                //braking
                if (!gamepad2.dpad_down && !gamepad2.dpad_up)
                    robot.arm.simpleElbow.stop();
                if (!gamepad2.dpad_right&& !gamepad2.dpad_left)
                    robot.arm.simpleWrist.stop();
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

            robot.arm.elbow.moveTowardsTargetPosition();
            //robot.arm.wrist.moveTowardsTargetPosition();

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

            //arm current position
            telemetry.addData("elbow: ", robot.arm.elbow.getCurrentPosition());
            telemetry.addData("wrist: ", robot.arm.wrist.getCurrentVolts());

            //arm directions
            telemetry.addData("elbow: ", robot.arm.elbow.getDirection());
            telemetry.addData("wrist: ", robot.arm.wrist.getDirection());

            telemetry.addData("target lift joint: ", robot.arm.elbow.getTargetPosition());
            telemetry.addData("target claw joint: ", robot.arm.wrist.getTargetVolts());

            telemetry.addData("elbow limit: ", robot.liftLimitValue());
            telemetry.addData("wrist limit: ", robot.clawLimitValue());

            telemetry.addData("wrist limit: ", robot.arm.wrist.getPower());

            telemetry.addLine("----------------CLAW-------------------------");

            telemetry.addData("right servo: ", robot.arm.claw.getPower("right"));
            telemetry.addData("left servo: ", robot.arm.claw.getPower("left"));



            telemetry.update();
        }
    }
}