package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.checkerframework.checker.units.qual.C;

public class FullArm {

    public ElbowJoint elbow;
    public WristJoint wrist;
    public Claw claw;

    public SimpleArmJoint simpleElbow;
    public SimpleArmJoint simpleWrist;

    public Boolean usingSmartElbow;
    public Boolean usingSmartWrist;

    /**
     * will change the code used in the teleOp for the arm
     * if false arm will only have manual controls on power
     * if true arm will have move to position and manual controls that adjust encoder values
     */
    public Boolean armEncodersOn;

    public FullArm(DcMotor elbowMotor, TouchSensor elbowLimit, DcMotor wristMotor, TouchSensor wristLimit, AnalogInput wristPotentiometer, CRServo rightServo, CRServo leftServo) {
        elbow = new ElbowJoint(elbowMotor, elbowLimit, 0.8, 50);
        wrist = new WristJoint(wristMotor, wristPotentiometer, 0.3);
        elbow.resetEncoder();
        wrist.resetTargetVolts();

        simpleElbow = new SimpleArmJoint(elbowMotor, 0.8);
        simpleWrist = new SimpleArmJoint(wristMotor, 0.3);

        simpleWrist.setBrake(DcMotor.ZeroPowerBehavior.FLOAT);
        simpleElbow.setBrake(DcMotor.ZeroPowerBehavior.BRAKE);

        claw = new Claw(rightServo, leftServo);
    }

    public void armLoop() {
        elbow.moveTowardsTargetPosition();
        wrist.moveTowardsTargetPosition();
    }

    /**
     * moves both of the arm joints to set positons for different arm positions
     */
    public void moveArmToPosition(ArmPosition pos) {
        if (pos == ArmPosition.RESET) {
            elbow.setTargetPosition(0);
            wrist.setTargetPosition(WristJoint.WRIST_POSITION.INITIALIZATION);
        }

        else if (pos == ArmPosition.PICKING_UP) {
            elbow.setTargetPosition(150);
            wrist.setTargetPosition(WristJoint.WRIST_POSITION.INITIALIZATION);
        }

        else if (pos == ArmPosition.PLACING) {
            elbow.setTargetPosition(2650);
            wrist.setTargetPosition(WristJoint.WRIST_POSITION.EXTENDED_OUT);
        }

        else if (pos == ArmPosition.TRAVELING) {
            elbow.setTargetPosition(290);
            wrist.setTargetPosition(WristJoint.WRIST_POSITION.PULLED_BACK);
        }
    }

    public void moveToPositionSync(ArmPosition pos) {
        moveArmToPosition(pos);
        while ((elbow.getDirection() != MotorState.STOP) && (wrist.getDirection() != MotorState.STOP)) {
            elbow.moveTowardsTargetPosition();
            wrist.moveTowardsTargetPosition();
        }
    }

    public enum ArmPosition {
        RESET,
        PICKING_UP,
        PLACING,
        TRAVELING,
    }
}
