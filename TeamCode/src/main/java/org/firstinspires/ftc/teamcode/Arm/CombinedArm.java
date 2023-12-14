package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class CombinedArm {

    public DcMotorEx elbow;
    public DcMotorEx wrist;

    public float elbowPower;
    public float wristPower;

    private TouchSensor elbowLimit;
    private TouchSensor wristLimit;

    public Claw claw;

    /**
     * will change the code used in the teleOp for the arm
     * if false arm will only have manual controls on power
     * if true arm will have move to position and manual controls that adjust encoder values
     */
    public Boolean armEncodersOn;

    public CombinedArm(DcMotor elbowMotor, DcMotor wristMotor, TouchSensor elbowLimit, TouchSensor wristLimit, CRServo rightServo, CRServo leftServo) {
        elbow = (DcMotorEx) elbowMotor;
        elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elbowPower = 0.8f;
        elbow.setPower(elbowPower);
        elbow.setTargetPositionTolerance(50);
        elbow.setTargetPosition(0);
        this.elbowLimit = elbowLimit;
        elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        wrist = (DcMotorEx) wristMotor;
        wrist.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wristPower = 0.1f;
        wrist.setPower(wristPower);
        wrist.setTargetPositionTolerance(10);
        wrist.setTargetPosition(0);
        this.wristLimit = wristLimit;
        elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        claw = new Claw(rightServo, leftServo);
    }

    /**
     * resets encoder to 0 for both arm joints
     */
    public void resetEncoders() {
        elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wrist.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runWithoutEncoders() {
        elbow.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wrist.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void runToPosition() {
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wrist.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void updateZeroWithLimits() {
        if (elbowLimit.isPressed())
            elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if (wristLimit.isPressed())
            wrist.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * moves both of the arm joints to set positons for different arm positions
     */
    public void moveArmToPosition(ArmPosition pos) {
        switch (pos) {
            case RESET: //init position
                elbow.setTargetPosition(0);
                wrist.setTargetPosition(0);
                break;

            case PICKINGUP: //picking up
                elbow.setTargetPosition(150);
                wrist.setTargetPosition(25);
                break;

            case PLACINGLOW: //placing on board
                elbow.setTargetPosition(2650);
                wrist.setTargetPosition(90);
                break;

            case PLACINGHIGH:
                elbow.setTargetPosition(0);
                wrist.setTargetPosition(0);
                break;
        }
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public enum ArmPosition {
        RESET,
        PLACINGLOW,
        PICKINGUP,
        PLACINGHIGH,
    }
}
