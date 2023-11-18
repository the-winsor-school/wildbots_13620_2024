package org.firstinspires.ftc.teamcode.driving;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.driving.IDriving;

public class StrafeDrive implements IDriving {

    private DcMotor rf;
    private DcMotor rb;
    private DcMotor lf;
    private DcMotor lb;

    private float speed = 0.5f;

    public StrafeDrive(DcMotor rf, DcMotor rb, DcMotor lf, DcMotor lb) {
        this.rf = rf;
        this.rb = rb;
        this.lf = lf;
        this.lb =lb;

        //just the way it was built this year
        this.rf.setDirection(DcMotorSimple.Direction.REVERSE);
        this.lf.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rb.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void joystickDrive (float X, float Y, float T) {
        //threshold for values (bc our controllers are old and bad)
        //these are condensed if statements
        float x = (Math.abs(X) < 0.2f) ? 0 : X;
        float y = (Math.abs(Y) < 0.2f) ? 0 : Y;
        float t = (Math.abs(T) < 0.2f) ? 0 : T;

        //explanation in drive and slack
        rf.setPower((y - x - t) * speed);
        rb.setPower((y + x - t) * speed);
        lf.setPower((y + x + t) * speed);
        lb.setPower((y - x + t) * speed);
    }

    public void turn (float t) {
        setEachPower(t, t, -t, -t);
    }

    public void horizontal (float power) { //right positive
        setEachPower(-power, power, power, -power); // -rf, +rb, lf, -lb)
    }

    public void vertical (float power) { //forward positive
        setEachPower(power,  power, power, power); //one side negative -rf, -rb
    }

    public void stop () {
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void setEachPower (float rfp, float rbp, float lfp, float lbp) {
        rf.setPower(rfp * speed);
        rb.setPower(rbp * speed);
        lf.setPower(lfp * speed);
        lb.setPower(lbp * speed);
    }

}
