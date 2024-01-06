package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class ElbowJoint {

    private DcMotorEx motor;
    private TouchSensor zeroPositionLimit;
    private double powerUsed;

    /**
     * true for wrist, false for elbow
     * only here so both motor can have the same object
     */

    private Boolean usingPotentiometer = false;

    ElbowJoint(DcMotor motor, TouchSensor zeroPositionLimit, double powerUsed, int tolerance) {
        //this. refers to the varaible of the class and without refers to the parameter being passes into the constructor (its a java convension)

        this.motor = (DcMotorEx) motor;
        this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.powerUsed = powerUsed;
        this.motor.setPower(powerUsed);

        this.motor.setTargetPositionTolerance(tolerance);
        this.motor.setTargetPosition(0);
        this.zeroPositionLimit = zeroPositionLimit;
        this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void moveTowardsTargetPosition () {
        runToPosition();
        updateZeroWithLimit();
    }

    public int getTargetPosition() {
        return motor.getTargetPosition();
    }

    public void resetEncoder() {

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(motor.getCurrentPosition());
    }

    public void runWithoutEncoder() {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void runToPosition() {
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void updateZeroWithLimit () {
        if (zeroPositionLimit.isPressed())
            resetEncoder();
    }

    public int getCurrentPosition () {
        return motor.getCurrentPosition();
    }

    public void setTargetPosition(int position) {
        motor.setTargetPosition(position);
    }

    public void changeTargetPosition(int position) {
        motor.setTargetPosition(getCurrentPosition() + position);
    }

    public MotorState getDirection() {
        if (motor.getPower() > 0)
            return MotorState.FORWARD;
        else if (motor.getPower() < 0)
            return MotorState.REVERSE;
        return MotorState.STOP;
    }
}
