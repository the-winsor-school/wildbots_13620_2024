package org.firstinspires.ftc.teamcode.Arm;

import org.firstinspires.ftc.teamcode.*;

public class FullArm {

    public ArmJoint liftJoint;
    public ArmJoint clawJoint;

    public Claw claw;

    double armPower = 0.5;
    int tolerance = 100;

    public FullArm(Robot robot) {
        liftJoint = new ArmJoint(robot.bottomMotor, 0, armPower, tolerance);
        clawJoint = new ArmJoint(robot.topMotor, 0, armPower, tolerance);

        claw = new Claw(robot.rightServo, robot.leftServo);

    }

    public void moveToLevel(ArmLevel level) {
        switch (level) {
            case RESET: //init position
                liftJoint.moveArm(100);
                clawJoint.moveArm(100);
                break;

            case PLACE: //placing on board
                liftJoint.moveArm(100);
                clawJoint.moveArm(100);
                break;
            case PICKINGUP: //picking up
                liftJoint.moveArm(100);
                clawJoint.moveArm(100);
                claw.clawControls(Claw.ClawPos.OPEN);
                break;
        }
    }
    public enum ArmLevel {
        RESET,
        PLACE,
        PICKINGUP,
    }


}
