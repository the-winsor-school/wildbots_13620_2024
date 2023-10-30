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
            tank.TeleopDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y);

            if(gamepad1.left_bumper)
                tank.spinForward();
            else if(gamepad1.right_bumper)
                tank.spinBackward();
            else if(gamepad1.left_trigger > 0.1)
                tank.analogSpin(-gamepad1.left_trigger);
            else
                tank.analogSpin(gamepad1.right_trigger);
        }
    }
}
