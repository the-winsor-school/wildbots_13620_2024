package org.firstinspires.ftc.teamcode.Arm;

import android.text.method.Touch;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class WristJoint {

    private DcMotorEx motor;
    private AnalogInput anglePotentiometer;
    private double powerUsed;
    private double targetVolts;
    private double currentVolts;
    private double potentiometerTolerance;

    private Boolean usingPotentiometer = false;

    WristJoint (DcMotor motor, AnalogInput anglePotentiometer, double powerUsed) {
        //this. refers to the varaible of the class and without refers to the parameter being passes into the constructor (its a java convension)

        this.motor = (DcMotorEx) motor;
        this.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.anglePotentiometer = anglePotentiometer;
        this.powerUsed = powerUsed;

        targetVolts = getCurrentVolts();

        this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setPotentiometerTolerance (double tolerance) {
        potentiometerTolerance = tolerance;
    }

    public void updateCurrentVolts () {
        currentVolts = anglePotentiometer.getVoltage();
    }

    public void changeTargetVolts(double volts) {
        targetVolts = getCurrentVolts() + volts;
    }

    public void resetTargetVolts() {
        targetVolts = getCurrentVolts();
    }

    public double getCurrentVolts () {
        return currentVolts;
    }

    public void setTargetVolts (double volts) {
        targetVolts = volts;
    }

    public double getTargetVolts() {
        return targetVolts;
    }

    public void stop () {
        motor.setPower(0);
    }

    public void setPower(DcMotorSimple.Direction direction) {
        if (direction == DcMotorSimple.Direction.FORWARD)
            motor.setPower(powerUsed);
        else if (direction == DcMotorSimple.Direction.REVERSE)
            motor.setPower(-powerUsed);
    }

    public void moveTowardsTargetPosition() {
        updateCurrentVolts();
        if (currentVolts - targetVolts > potentiometerTolerance) { //current is too high
            motor.setPower(-powerUsed);
        }
        else if (currentVolts - targetVolts > -potentiometerTolerance) { //current is too low
            motor.setPower(powerUsed);
        }
        else {
            motor.setPower(0);
        }
    }

    public void setTargetPosition(WRIST_POSITION wristPosition) {
        if (wristPosition == WRIST_POSITION.INITIALIZATION) {
            setPotentiometerTolerance(0.15);
            setTargetVolts(1.37);
        }
        else if (wristPosition == WRIST_POSITION.PULLED_BACK) {
            setPotentiometerTolerance(0.7);
            setTargetVolts(1.24);
        }
        else if (wristPosition == WRIST_POSITION.EXTENDED_OUT) {
            //TODO find more accurate values (do averages wth drivers)
            setPotentiometerTolerance(0.5);
            setTargetVolts(2.25);
        }
    }

    public double getPower() {
        return motor.getPower();
    }

    public String getDirection() {
        if (currentVolts - targetVolts > potentiometerTolerance) //current is too high
            return "REVERSE";
        else if (currentVolts - targetVolts > -potentiometerTolerance) //current is too low
            return "FORWARD";
        else
            return "NOT MOVING";
    }

    public enum WRIST_POSITION {
        INITIALIZATION,
        PULLED_BACK,
        EXTENDED_OUT,
    }

}
