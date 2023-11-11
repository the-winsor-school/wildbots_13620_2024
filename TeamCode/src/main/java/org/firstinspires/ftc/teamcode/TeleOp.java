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

        while (opModeIsActive()){

            float x = gamepad1.right_stick_x;
            float y = gamepad1.right_stick_y;
            float t = gamepad1.left_stick_x;

            robot.driving.joystickDrive(x, y, t);

            //arm levels
            if (gamepad2.y)
                robot.arm.moveToLevel(1);
            if(gamepad2.b)
                robot.arm.moveToLevel(FullArm.ArmLevel.RESET);
            if(gamepad2.a)
                robot.arm.moveToLevel(FullArm.ArmLevel.PLACE);

            //arm manual controls
            if (gamepad2.dpad_up) {
                robot.arm.liftJoint.setPower(1);
                sleep(300);
                robot.arm.liftJoint.setPower(0);
            }
            if(gamepad2.dpad_down)
                robot.arm.liftJoint.changePosition(-200);
            if (gamepad2.dpad_left)
                robot.arm.clawJoint.changePosition(200);
            if(gamepad2.dpad_right)
                robot.arm.clawJoint.changePosition(-200);

            //claw controls
            if (gamepad2.left_stick_x > 0.75f)
                robot.arm.claw.clawControls(Claw.ClawPos.OPEN);
            if (gamepad2.left_stick_x < 0.75f)
                robot.arm.claw.clawControls(Claw.ClawPos.CLOSE);

            //telemetry
            telemetry.addData("x: ", x);
            telemetry.addData("y: ", y);
            telemetry.addData("t: ", t);

            //robot.printWheels();
            robot.printArm();

            telemetry.update();
        }
    }
}