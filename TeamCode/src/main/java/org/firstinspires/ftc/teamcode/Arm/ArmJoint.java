package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class ArmJoint {
    double powerUsed;
    DcMotor motor;
    int armTolerance;

    public int targetPosition;

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
        else {
            brake();
        }
    }

    public void resetEncoders() {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        targetPosition = getCurrentPosition();
    }

    public void changeTargetPosition(int rotations) {
        targetPosition = (getCurrentPosition() + rotations);
    }

    public void moveJointSync(int rotations) {
        targetPosition = rotations;
        while (getCurrentPosition() - targetPosition > armTolerance
                || getCurrentPosition() - targetPosition < -armTolerance)
            moveJointRotations();
    }

    public void moveJointRotations() {
        motor.setTargetPosition(targetPosition);
        motor.setPower(powerUsed);
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

    public void setMotorPower(double power) { powerUsed = power; }

    public void scaleMotorPower(double power) {
        powerUsed = powerUsed * power;
    }

    public void changeMotorPower(int power) { powerUsed += power; }

    public void usePowerOnly(double power) {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(power);
    }

}
