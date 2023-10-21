package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "SimpleTank")
public class TankTeleop extends LinearOpMode
{
    private TankBot tank;


    @Override
    public void runOpMode() throws InterruptedException {
        tank = new TankBot(this);
        waitForStart();

        while(opModeIsActive())
        {
            float leftY = -gamepad1.left_stick_y;
            float rightY = gamepad1.right_stick_y;
            tank.TeleopDrive(leftY, rightY);
        }
    }
}
