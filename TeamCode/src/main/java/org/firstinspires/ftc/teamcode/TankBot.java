package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.TankDrive;

public class TankBot
{
    private IDriving driveTrain;

    public TankBot(LinearOpMode opMode)
    {
        HardwareMap map = opMode.hardwareMap;

        DcMotor left = map.tryGet(DcMotor.class, "left");
        DcMotor right = map.tryGet(DcMotor.class, "right");

        driveTrain = new TankDrive(left, right);
    }

    /**
     * Drive the tank using the simple tank interface
     * @param leftY
     * @param rightY
     */
    public void TeleopDrive(float leftY, float rightY)
    {
        driveTrain.joystickDrive(leftY, rightY, 0);
    }
}
