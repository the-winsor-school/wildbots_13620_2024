package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class ArmJoint {

    DcMotor motor;
    DcMotor encoder;

    int gearRatio;
    double powerUsed;
    int armTolerance;

    public int targetPosition;

    public ArmJoint(DcMotor motor, DcMotor encoder,
                    int gearRatio, double powerUsed,
                    int armTolerance) {
        this.motor = motor;
        this.encoder = encoder;
        this.gearRatio = gearRatio;
        this.powerUsed = powerUsed;
        this.armTolerance = armTolerance;
    }

    public void armLoop() {
        if (getCurrentPosition() - targetPosition > armTolerance) //go down
            motor.setPower(-powerUsed);
        else if (getCurrentPosition() - targetPosition < -armTolerance) //go up
            motor.setPower(powerUsed);
        else //not moving
            brake();
    }

    public void resetEncoders() {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void changeTargetPosition(int rotations) {
        targetPosition = (getCurrentPosition() + rotations);
    }

    public String getDirection() {
        if (getCurrentPosition() - targetPosition > armTolerance)
            return "going down";
        else if (getCurrentPosition() - targetPosition < -armTolerance)
            return "going up";
        else
            return "not moving";
    }

    public int getCurrentPosition() { return encoder.getCurrentPosition(); }

    public int getTargetPosition() { return targetPosition; }

    public void setTargetPosition(int position) { targetPosition = position; }

    public void brake() { motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); }

    public void setMotorPower(double power) { motor.setPower(power); }

    public void changeMotorPower(int power) { powerUsed += power; }

    public void usePowerOnly(double power) {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(power);
    }

}
