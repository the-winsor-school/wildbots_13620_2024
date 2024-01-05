/*
package org.firstinspires.ftc.teamcode.driving;

import com.qualcomm.robotcore.hardware.DcMotor;

public class GridDrive implements IDriving {

    private DcMotor rf;
    private DcMotor rb;
    private DcMotor lf;
    private DcMotor lb;

    public GridDrive (DcMotor rf, DcMotor rb, DcMotor lf, DcMotor lb) {
        this.rf = rf;
        this.rb = rb;
        this.lf = lf;
        this.lb =lb;

    }

    public void joystickDrive (float x, float y, float t){
        if (Math.abs(x) > 0.2f || Math.abs(y) > 0.2f) {
            if (Math.abs(y) - Math.abs(x) > 0.5) { //going forward or back
                float lf = (y + t);
                float rf = (y - t);
                float lb = (y + t) ;
                float rb = (y - t) ;
                setEachPower(lf, rf, lb, rb);

            } else if (Math.abs(x) - Math.abs(y) > 0.5) { //we moving sideways
                float lf = (x + t);
                float rf = (-x - t) ;
                float lb = (-x + t);
                float rb = (x - t) ;
                setEachPower(lf, rf, lb, rb);
            }
        }
        else if (Math.abs(t) > 0.2f)
            turn(t);
        else {
            stop();
        }
        //does not let you move in both x and y
        //only moves in which ever magnitude is greater
        //no diagonals only simple driving here
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
        rb.setPower(rbp);
        lf.setPower(lfp);
        lb.setPower(lbp);
    }
    public void adjustSpeed(float x) {
        int t=5;
    }

}
*/
