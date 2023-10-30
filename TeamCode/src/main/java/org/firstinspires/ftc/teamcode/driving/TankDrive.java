package org.firstinspires.ftc.teamcode.driving;

import com.qualcomm.robotcore.hardware.DcMotor;

public class TankDrive implements IDriving
{
    private static final float _DEAD_ZONE = 0.05f;


    private DcMotor left;
    private DcMotor right;

    public TankDrive(DcMotor lf, DcMotor rt)
    {
        left = lf;
        right = rt;
    }

    private float validatePowerInput(float f)
    {
        if(Math.abs(f) < _DEAD_ZONE)
            f = 0f;

        if(f > 1f)
            f = 1f;

        if(f < -1f)
            f = -1f;

        return f;
    }

    @Override
    public void vertical(float power) {
        power = validatePowerInput(power);
        left.setPower(power);
        right.setPower(power);
    }

    @Override
    public void horizontal(float power) {
        power = validatePowerInput(power);
        left.setPower(power);
        right.setPower(power);
    }

    @Override
    public void turn(float t) {
        t = validatePowerInput(t);
        left.setPower(t);
        right.setPower(-t);
    }

    @Override
    public void stop() {
        left.setPower(0);
        right.setPower(0);
    }

    @Override
    public void joystickDrive(float x, float y, float t)
    {
        x = validatePowerInput(x);
        y = validatePowerInput(y);

        left.setPower(x);
        right.setPower(y);
    }
}
