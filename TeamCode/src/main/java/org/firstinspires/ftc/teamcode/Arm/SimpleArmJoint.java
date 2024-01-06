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

    public void setMotorSate(MotorState state) {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (state == MotorState.FORWARD)
            motor.setPower(powerUsed);
        if (state == MotorState.REVERSE)
            motor.setPower(-powerUsed);
        if (state == MotorState.STOP)
            motor.setPower(0);
    }

    public void setBrake (DcMotor.ZeroPowerBehavior mode) {
        motor.setZeroPowerBehavior(mode);
    }
}
