package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.external.samples.RobotTeleopPOV_Linear;


public class ArmJoint {
    /**
     * default power used for this motor
     * set in initialization of object
     */
    double powerUsed;

    /**
     * actual DcMotor object
     */
    DcMotor motor;

    /**
     * tolerance used for this joint
     * (which is how accurate it will try to get to the exact target position)
     */
    int armTolerance;

    public int targetPosition;

    /**
     * difference between target rotations and current postiton at any given point in time
     * positive means current < target
     * negative means current > target
     */
    public int deltaRotations;

    public ArmJoint(DcMotor motor, double powerUsed, int armTolerance) {
        this.motor = motor;
        this.powerUsed = powerUsed;
        this.armTolerance = armTolerance;
    }

    /**
     * always should be running to move joint closer to target position
     */
    public void armLoop() {
        int current = getCurrentPosition();
        if (current - targetPosition > armTolerance
                || current - targetPosition < -armTolerance) {
            moveJointRotations();
        }
        else {
            brake();
        }
        deltaRotations = targetPosition - getTargetPosition();
    }

    /**
     * resets the encoders to 0
     * makes sure motor is in encoder mode
     */
    public void resetEncoders() {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        targetPosition = getCurrentPosition();
    }

    /**
     * adjust the target position bases on current position
     * @param rotations will be added to the current position
     */
    public void changeTargetPosition(int rotations) {
        targetPosition = getCurrentPosition() + rotations;
    }

    @Deprecated
    /**
     * takes the thread and stops rest of progam whiles its executing
     */
    public void moveJointSync(int rotations) {
        targetPosition = rotations;
        while (getCurrentPosition() - targetPosition > armTolerance
                || getCurrentPosition() - targetPosition < -armTolerance)
            moveJointRotations();
    }

    /**
     * actually moves joint to the correct rotations
     */
    public void moveJointRotations() {
        motor.setTargetPosition(targetPosition);
        motor.setPower(sigmoidMath(deltaRotations));
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * use for telemetry
     * @return string saying which way the joint is moving
     */
    public String getDirection() {
        if (getCurrentPosition() - targetPosition > armTolerance)
            return "going down";
        else if (getCurrentPosition() - targetPosition < -armTolerance)
            return "going up";
        else
            return "not moving";
    }

    /**
     *
     * @return the current position (integer)
     */
    public int getCurrentPosition() { return motor.getCurrentPosition(); }

    /**
     *
     * @return current target position (integer)
     */
    public int getTargetPosition() { return targetPosition; }

    /**
     *
     * @param position will be the new target position (in rotations)
     */
    public void setTargetPosition(int position) { targetPosition = position; }

    /**
     * stops the joint with brake stop
     */
    public void brake() { motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); }

    /**
     *
     * @param power this is the new powerUsed for the motor
     */
    public void setMotorPower(double power) { powerUsed = power; }

    /**
     * adjusts the motor power
     * @param power will be added to the current powerUsed
     */
    public void changeMotorPower(int power) { powerUsed += power; }

    /**
     * stop using encoders and just run the motor with the powerUsed of that motor
     * (will not stop automatically)
     * @param forwardMotion true = forward, flase = backwards
     */
    public void usePower(boolean forwardMotion) {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if (forwardMotion) {
            motor.setPower(powerUsed); //move forwards
        }
        else {
            motor.setPower(-powerUsed); //move backwards
        }
    }

    public void stop() {
        motor.setPower(0);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * calculates motor power at a given rotations value
     * in an effort to make the power less as the delta rotations gets lower
     * @param rotations number of rotations until the target position
     * @return the motor's power at the inputted number of rotations
     */
    public double sigmoidMath (int rotations){
        float slope = 8; //can be tested (higher for steeper slope, lower for shallower slope
        int denomDecel = (int)(1000*(Math.pow(Math.E, -(slope*(2*rotations - 200))/(2*200)))); //if <200 rotations until target position, then power begins to decelerate
        return 1000.0/(1000.0 + denomDecel);
    }
}
