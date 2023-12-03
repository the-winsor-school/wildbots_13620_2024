package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class FullArm {

    public ArmJoint liftJoint;
    public ArmJoint clawJoint;

    public Claw claw;


    public FullArm(DcMotor liftMotor, DcMotor clawMotor, Servo rightServo, Servo leftServo) {
        liftJoint = new ArmJoint(liftMotor,  0.8f, 50);
        clawJoint = new ArmJoint(clawMotor, 0.2f, 5);

        claw = new Claw(rightServo, leftServo);
    }

    /**
     * resets encoder to 0 for both arm joints
     */
    public void resetEncoders() {
        liftJoint.resetEncoders();
        clawJoint.resetEncoders();
    }

    @Deprecated
    //TODO fix these values (after building fixes arm)
    /**
     * moves both of the arm joints to set positons for different arm positions
     */
    public void moveArmToPosition(ArmPosition pos) {
        switch (pos) {
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
                break;
        }
    }
    public enum ArmPosition {
        RESET,
        PLACINGLOW,
        PICKINGUP,
        PLACINGHIGH,
    }
}
