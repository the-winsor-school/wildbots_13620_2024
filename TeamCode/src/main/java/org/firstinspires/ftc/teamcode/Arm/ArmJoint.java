package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class ArmJoint {
    double powerUsed;
    DcMotor motor;
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

    public void armLoop() {
        if (getCurrentPosition() - targetPosition > armTolerance
                || getCurrentPosition() - targetPosition < -armTolerance) {
            moveJointRotations();
        }
        deltaRotations = targetPosition - getTargetPosition();
    }

    public void resetEncoders() {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void changeTargetPosition(int rotations) {
        targetPosition = (getCurrentPosition() + rotations);
    }

    public void moveJointRotations() {
        motor.setTargetPosition(targetPosition);
        motor.setPower(sigmoidMath(deltaRotations));
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public String getDirection() {
        if (getCurrentPosition() - targetPosition > armTolerance)
            return "going down";
        else if (getCurrentPosition() - targetPosition < -armTolerance)
            return "going up";
        else
            return "not moving";
    }

    public int getCurrentPosition() { return motor.getCurrentPosition(); }

    public int getTargetPosition() { return targetPosition; }

    public void setTargetPosition(int position) { targetPosition = position; }

    public void brake() { motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); }

    public void setMotorPower(double power) { motor.setPower(power); }

    public void changeMotorPower(int power) { powerUsed += power; }

    public void usePowerOnly(double power) {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(power);
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
