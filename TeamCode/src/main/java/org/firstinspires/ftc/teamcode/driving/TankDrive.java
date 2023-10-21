package org.firstinspires.ftc.teamcode.driving;

import com.qualcomm.robotcore.hardware.DcMotor;

public class TankDrive implements IDriving
{
    private DcMotor left;
    private DcMotor right;

    public TankDrive(DcMotor lf, DcMotor rt)
    {
        left = lf;
        right = rt;
    }

    @Override
    public void vertical(float power) {

    }

    @Override
    public void horizontal(float power) {

    }

    @Override
    public void turn(float t) {

    }

    @Override
    public void stop() {

    }

    @Override
    public void joystickDrive(float x, float y, float t)
    {
        left.setPower(x);
        right.setPower(y);
    }
}
