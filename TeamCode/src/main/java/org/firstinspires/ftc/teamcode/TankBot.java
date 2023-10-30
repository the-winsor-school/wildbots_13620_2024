package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.TankDrive;
import org.firstinspires.ftc.teamcode.manipulators.SpinnySpinner;

public class TankBot
{
    private IDriving driveTrain;
    private SpinnySpinner spinner;

    public TankBot(LinearOpMode opMode)
    {
        HardwareMap map = opMode.hardwareMap;

        DcMotor left = map.tryGet(DcMotor.class, "left");
        DcMotor right = map.tryGet(DcMotor.class, "right");
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        driveTrain = new TankDrive(left, right);

        DcMotor spinny = map.tryGet(DcMotor.class, "spinny");
        spinner = new SpinnySpinner(spinny);
    }

    public void TeleopDrive(float x, float y)
    {
        if(Math.abs(x) < 0.05)
        {
            driveTrain.vertical(y);
            return;
        }

        float sign = y == 0 ? 1 : y/Math.abs(y);
        
        float throttle = (float)(Math.sqrt(x*x + y*y)*sign);
        float leftPower, rightPower;

        if(x < 0)
        {
            leftPower = throttle * (-1-2*x);
            rightPower = throttle;
        }
        else
        {
            leftPower = throttle;
            rightPower = throttle * (1 - 2 * x);
        }

        driveTrain.joystickDrive(leftPower, rightPower,0);
    }

    public void analogSpin(float power)
    {
        spinner.spin(power);
    }
    public void spinForward()
    {
        spinner.spin(1);
    }

    public void spinBackward()
    {
        spinner.spin(-1);
    }
}
