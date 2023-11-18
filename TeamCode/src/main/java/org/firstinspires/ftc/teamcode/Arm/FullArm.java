package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class FullArm {

    public ArmJoint liftJoint;
    public ArmJoint clawJoint;

    public Claw claw;


    public FullArm(DcMotor liftMotor, DcMotor clawMotor, CRServo rightServo, CRServo leftServo) {
        liftJoint = new ArmJoint(liftMotor,  0.8f, 50);
        clawJoint = new ArmJoint(clawMotor, 0.2f, 5);

        claw = new Claw(rightServo, leftServo);
    }

    public void resetEncoders() {
        liftJoint.resetEncoders();
        clawJoint.resetEncoders();
    }

    public void moveToLevel(ArmLevel level) {
        switch (level) {
            case RESET: //init position
                liftJoint.setTargetPosition(0);
                clawJoint.setTargetPosition(80);
                break;

            case PLACINGLOW: //placing on board
                liftJoint.setTargetPosition(1340);
                clawJoint.setTargetPosition(-182);
                break;

            case PICKINGUP: //picking up
                int liftJointRotations = 900;
                liftJoint.moveJointSync(liftJointRotations + 500);
                clawJoint.moveJointSync(-530);
                liftJoint.moveJointSync(liftJointRotations);
                claw.controlClaw(Claw.ClawPos.OPEN);
                break;

            case PLACINGHIGH:
                liftJoint.setTargetPosition(1605);
                clawJoint.setTargetPosition(-190);
                liftJoint.scaleMotorPower(2);
                break;
        }
    }
    public enum ArmLevel {
        RESET,
        PLACINGLOW,
        PICKINGUP,

        PLACINGHIGH,
    }
}
