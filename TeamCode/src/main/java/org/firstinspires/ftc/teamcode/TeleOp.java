package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.StrafeDrive;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Simple")
public class TeleOp extends LinearOpMode {

    Robot robot;
    IDriving driving;
    LinearOpMode opMode;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(opMode);
        driving = robot.driving;

        waitForStart();

        while (opModeIsActive()){

            float x = gamepad1.right_stick_x;
            float y = -gamepad1.right_stick_y; //inputs from joystick are opposite
            float t = gamepad1.left_stick_x;

            driving.joystickDrive(x, y, t);

            telemetry.update();
        }
    }
}