package org.firstinspires.ftc.teamcode.driving;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.driving.IDriving;

public class StrafeDrive implements IDriving {

    private DcMotor rf;
    private DcMotor rb;
    private DcMotor lf;
    private DcMotor lb;

    public StrafeDrive(DcMotor rf, DcMotor rb, DcMotor lf, DcMotor lb) {
        rf = rf;
        rb = rb;
        lf = lf;
        lb =lb;

    }

    public void joystickDrive (float x, float y, float t){
        rf.setPower(y - x - t);
        rb.setPower(y + x - t);
        lf.setPower(y + x + t);
        lb.setPower(y - x + t);
    }

    public void turn (float t) {
        setEachPower(t, t, -t, -t);
    }

    public void horizontal (float power) { //right positive
        setEachPower(-power, power, power, -power); // -rf, +rb, lf, -lb)
    }

    public void vertical (float power) { //forward positive
        setEachPower(-power, -power, power, power); //one side negative -rf, -rb
    }

    public void stop () {
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void setEachPower (float rfp, float rbp, float lfp, float lbp) {
        rf.setPower(rfp);
        lf.setPower(rbp);
        lf.setPower(lfp);
        lb.setPower(lbp);
    }

}
