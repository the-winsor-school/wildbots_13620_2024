package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


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

    public ArmJoint(DcMotor motor, double powerUsed, int armTolerance) {
        this.motor = motor;
        this.powerUsed = powerUsed;
        this.armTolerance = armTolerance;
    }

    /**
     * always should be running to move joint closer to target position
     */
    public void armLoop() {
        if (getCurrentPosition() - targetPosition > armTolerance
                || getCurrentPosition() - targetPosition < -armTolerance) {
            moveJointRotations();
        }
        else {
            brake();
        }
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
        targetPosition = (getCurrentPosition() + rotations);
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
        motor.setPower(powerUsed);
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
     * stop using encoders and just run the motor
     * (will not stop automatically)
     * @param power power used when motor is running
     */
    public void usePowerOnly(double power) {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(power);
    }

}
