package org.firstinspires.ftc.teamcode.Arm;

import android.text.method.Touch;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WristJoint {

    private DcMotorEx motor;
    private AnalogInput anglePotentiometer;
    private double powerUsed;
    private double targetVolts;
    private double currentVolts;
    private double potentiometerTolerance;

    ElapsedTime time = new ElapsedTime();

    private Boolean usingPotentiometer = false;


    WristJoint (DcMotor motor, AnalogInput anglePotentiometer, double powerUsed) {
        //this. refers to the varaible of the class and without refers to the parameter being passes into the constructor (its a java convension)

        this.motor = (DcMotorEx) motor;
        this.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.anglePotentiometer = anglePotentiometer;
        this.powerUsed = powerUsed;
        setPotentiometerTolerance(0.2);

        targetVolts = getCurrentVolts();

        time.startTime();
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

    public void setPower(MotorState state) {
        if (state == MotorState.FORWARD)
            motor.setPower(powerUsed * 0.75);
        else if (state == MotorState.REVERSE)
            motor.setPower(-powerUsed * 0.75);
    }

    public void moveTowardsTargetPosition() {
        if (time.milliseconds() > 20) {
            updateCurrentVolts();
            time.reset();
        }

        if (currentVolts - targetVolts > potentiometerTolerance) { //current is too high
            motor.setPower(-powerUsed);
        }
        else if (currentVolts - targetVolts < -potentiometerTolerance) { //current is too low
            motor.setPower(powerUsed);
        }
        else {
            motor.setPower(0);
        }
    }

    public void setTargetPosition(WRIST_POSITION wristPosition) {
        if (wristPosition == WRIST_POSITION.INITIALIZATION) {
            setTargetVolts(0.4);
        }
        else if (wristPosition == WRIST_POSITION.PULLED_BACK) {
            setTargetVolts(0.2);
        }
        else if (wristPosition == WRIST_POSITION.EXTENDED_OUT) {
            //TODO find more accurate values (do averages wth drivers)
            setTargetVolts(1.0);
        }
    }

    public double getPower() {
        return motor.getPower();
    }

    public MotorState getDirection() {
        if (currentVolts - targetVolts > potentiometerTolerance) //current is too high
            return MotorState.REVERSE;
        else if (currentVolts - targetVolts > -potentiometerTolerance) //current is too low
            return MotorState.FORWARD;
        else
            return MotorState.STOP;
    }

    public enum WRIST_POSITION {
        INITIALIZATION,
        PULLED_BACK,
        EXTENDED_OUT,
    }

}
