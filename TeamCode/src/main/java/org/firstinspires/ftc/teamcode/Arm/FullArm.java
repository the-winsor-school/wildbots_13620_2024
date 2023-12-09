package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class FullArm {

    public ArmJoint liftJoint;
    public ArmJoint clawJoint;

    public Claw claw;

    /**
     * will change the code used in the teleOp for the arm
     * if false arm will only have manual controls on power
     * if true arm will have move to position and manual controls that adjust encoder values
     */
    public Boolean armEncodersOn;

    public FullArm(DcMotor liftMotor, DcMotor clawMotor, Servo rightServo, Servo leftServo) {
        liftJoint = new ArmJoint(liftMotor,  0.8f, 50);
        clawJoint = new ArmJoint(clawMotor, 0.1f, 10);

        claw = new Claw(rightServo, leftServo);
    }

    /**
     * resets encoder to 0 for both arm joints
     */
    public void resetEncoders() {
        liftJoint.resetEncoders();
        clawJoint.resetEncoders();
    }

    //TODO fix these values
    /**
     * moves both of the arm joints to set positons for different arm positions
     */
    public void moveArmToPosition(ArmPosition pos) {
        switch (pos) {
            case RESET: //init position
                liftJoint.setTargetPosition(0);
                clawJoint.setTargetPosition(0);
                break;

            case PLACINGLOW: //placing on board
                liftJoint.setTargetPosition(0);
                clawJoint.setTargetPosition(0);
                break;

            case PICKINGUP: //picking up
                liftJoint.setTargetPosition(0);
                clawJoint.setTargetPosition(0);
                break;

            case PLACINGHIGH:
                liftJoint.setTargetPosition(0);
                clawJoint.setTargetPosition(0);
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
