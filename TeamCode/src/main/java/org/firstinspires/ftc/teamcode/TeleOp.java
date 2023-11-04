package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Simple")
public class TeleOp extends LinearOpMode {

    Robot robot;
    FullArm arm;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();

        while (opModeIsActive()){

            float x = gamepad1.right_stick_x;
            float y = -gamepad1.right_stick_y; //inputs from joystick are opposite
            float t = gamepad1.left_stick_x;

            robot.driving.joystickDrive(x, y, t);

            //arm levels
            if (gamepad2.y)
                arm.moveToLevel(FullArm.ArmLevel.PICKINGUP);
            if(gamepad2.b)
                arm.moveToLevel(FullArm.ArmLevel.RESET);
            if(gamepad2.x)
                arm.moveToLevel(FullArm.ArmLevel.PLACE);

            //arm manual controls
            if (gamepad2.dpad_up)
                arm.liftJoint.changePosition(200);
            if(gamepad2.dpad_down)
                arm.liftJoint.changePosition(-200);
            if (gamepad2.dpad_left)
                arm.clawJoint.changePosition(200);
            if(gamepad2.dpad_right)
                arm.clawJoint.changePosition(-200);

            //claw controls
            if (gamepad2.left_stick_x > 0.75f)
                arm.claw.clawControls(Claw.ClawPos.OPEN);
            if (gamepad2.left_stick_x < 0.75f)
                arm.claw.clawControls(Claw.ClawPos.CLOSE);

            //telemetry
            telemetry.addData("x: ", x);
            telemetry.addData("y: ", y);
            telemetry.addData("t: ", t);

            telemetry.update();
        }
    }
}