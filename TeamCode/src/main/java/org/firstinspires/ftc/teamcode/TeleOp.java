package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        //we are using arm encoders right now
        robot.arm.usingSmartElbow = true;
        robot.arm.usingSmartWrist = true;

        robot.arm.wrist.setTargetVolts(robot.arm.wrist.getCurrentVolts());

        boolean usingWristPosition = false;

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


            if (robot.arm.usingSmartElbow) {

                //smart elbow manual controls
                if (gamepad2.dpad_up)
                    robot.arm.elbow.changeTargetPosition(200);
                else if (gamepad2.dpad_down)
                    robot.arm.elbow.changeTargetPosition(-200);

                //smart elbow loop
                robot.arm.elbow.moveTowardsTargetPosition();

            } else {

                //elbow manual controls
                if (gamepad2.dpad_up)
                    robot.arm.simpleElbow.setMotorSate(MotorState.FORWARD);
                if(gamepad2.dpad_down)
                    robot.arm.simpleElbow.setMotorSate(MotorState.REVERSE);

                //elbow braking
                if (!gamepad2.dpad_down && !gamepad2.dpad_up)
                    robot.arm.simpleElbow.setMotorSate(MotorState.STOP);
            }

            if (robot.arm.usingSmartWrist) {
                //smart manual wrist controls
                if (gamepad2.dpad_right) {
                    usingWristPosition = false;
                    robot.arm.wrist.setPower(MotorState.FORWARD);
                }
                else if (gamepad2.dpad_left) {
                    usingWristPosition = false;
                    robot.arm.wrist.setPower(MotorState.REVERSE);
                }
                else
                    robot.arm.wrist.stop();

                //wrist loop
                 if (usingWristPosition) {
                     robot.arm.wrist.moveTowardsTargetPosition();
                 }
            } else {

                //maunal wrist contorls
                if (gamepad2.dpad_right)
                    robot.arm.simpleWrist.setMotorSate(MotorState.FORWARD);
                if (gamepad2.dpad_left)
                    robot.arm.simpleWrist.setMotorSate(MotorState.REVERSE);

                //braking
                if (!gamepad2.dpad_right&& !gamepad2.dpad_left)
                    robot.arm.simpleWrist.setMotorSate(MotorState.STOP);
            }

            //arm levels (loops only run if the variable are true)
            if (gamepad2.x) {
                usingWristPosition = true;
                robot.arm.moveArmToPosition(FullArm.ArmPosition.PICKING_UP);
            }
            if (gamepad2.a) {
                usingWristPosition = true;
                robot.arm.moveArmToPosition(FullArm.ArmPosition.RESET);
            }
            if (gamepad2.b) {
                usingWristPosition = true;
                robot.arm.moveArmToPosition(FullArm.ArmPosition.PLACING);
            }
            if (gamepad2.y) {
                usingWristPosition = true;
                robot.arm.moveArmToPosition(FullArm.ArmPosition.TRAVELING);
            }

            //claw controls
            if (gamepad2.right_bumper)
                robot.arm.claw.moveClaw(Claw.ClawPos.CLOSE);
            if (gamepad2.left_bumper)
                robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
            if(!gamepad2.right_bumper && !gamepad2.left_bumper)
                robot.arm.claw.moveClaw(Claw.ClawPos.STOP);

            //chaning smart vs dumb arm controls
            if (gamepad2.left_trigger > 0.75) {
                robot.arm.usingSmartWrist = !robot.arm.usingSmartWrist;
            }
            if (gamepad2.right_trigger > 0.75) {
                robot.arm.usingSmartElbow = !robot.arm.usingSmartElbow;
            }

            //reset encoders
            if (gamepad2.right_stick_button) {
                robot.arm.elbow.resetEncoder();
            }

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

           telemetry.addData("ELBOW SMART CONTROL", robot.arm.usingSmartElbow);
            telemetry.addData("WRIST SMART CONTROL", robot.arm.usingSmartWrist);

            //arm current position
            telemetry.addData("CURRENT ELBOW POSITION: ", robot.arm.elbow.getCurrentPosition());
            telemetry.addData("wrist: ", robot.arm.wrist.getCurrentVolts());

            //arm directions
            telemetry.addData("elbow: ", robot.arm.elbow.getDirection());
            telemetry.addData("wrist: ", robot.arm.wrist.getDirection());

            telemetry.addData("target lift joint: ", robot.arm.elbow.getTargetPosition());
            telemetry.addData("target claw joint: ", robot.arm.wrist.getTargetVolts());

            telemetry.addData("elbow limit: ", robot.liftLimitValue());
            telemetry.addData("wrist limit: ", robot.clawLimitValue());
            
            telemetry.addLine("----------------CLAW-------------------------");

            telemetry.addData("right servo: ", robot.arm.claw.getPower("right"));
            telemetry.addData("left servo: ", robot.arm.claw.getPower("left"));

            telemetry.update();
        }
    }
}