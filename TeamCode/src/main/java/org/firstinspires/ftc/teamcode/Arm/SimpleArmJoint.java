package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class SimpleArmJoint {
    private DcMotor motor;
    private double powerUsed;

    SimpleArmJoint (DcMotor motor, double powerUsed) {
        this.motor = motor;
        this.powerUsed = powerUsed;
    }

    public void moveArmJoint(DcMotorSimple.Direction direction) {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (direction == DcMotorSimple.Direction.FORWARD)
            motor.setPower(powerUsed);
        else if (direction == DcMotorSimple.Direction.REVERSE)
            motor.setPower(-powerUsed);
    }

    public void setBrake (DcMotor.ZeroPowerBehavior mode) {
        motor.setZeroPowerBehavior(mode);
    }

    public void stop() {
        motor.setPower(0);
    }
}
