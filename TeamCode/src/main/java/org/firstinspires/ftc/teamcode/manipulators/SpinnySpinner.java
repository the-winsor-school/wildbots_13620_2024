package org.firstinspires.ftc.teamcode.manipulators;

import com.qualcomm.robotcore.hardware.DcMotor;

public class SpinnySpinner
{
    private DcMotor spinner;
    public SpinnySpinner(DcMotor spin)
    {
        spinner = spin;
    }

    public void spin(float power)
    {
        spinner.setPower(power);
    }

    public void stop()
    {
        spinner.setPower(0);
    }
}
