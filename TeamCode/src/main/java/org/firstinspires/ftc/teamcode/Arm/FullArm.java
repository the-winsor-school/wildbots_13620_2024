package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class FullArm {

    public ArmJoint liftJoint;
    public ArmJoint clawJoint;

    public Claw claw;

    double armPower = 0.5;
    int tolerance = 100;

    public FullArm(DcMotor liftMotor, DcMotor clawMotor,
                   DcMotor liftMotorEncoder, DcMotor clawMotorEncoder,
                   Servo rightServo, Servo leftServo) {
        liftJoint = new ArmJoint(liftMotor, liftMotorEncoder, 0, armPower, tolerance);
        clawJoint = new ArmJoint(clawMotor, clawMotorEncoder,  0, armPower, tolerance);

        claw = new Claw(rightServo, leftServo);
    }

    public void moveToLevel(ArmLevel level) {
        switch (level) {
            case RESET: //init position
                liftJoint.setTargetPosition(100);
                clawJoint.setTargetPosition(100);
                break;

            case PLACE: //placing on board
                liftJoint.setTargetPosition(1300);
                clawJoint.setTargetPosition(800);
                break;
            case PICKINGUP: //picking up
                liftJoint.setTargetPosition(1300);
                clawJoint.setTargetPosition(100);
                claw.controlClaw(Claw.ClawPos.OPEN);
                break;
        }
    }
    public enum ArmLevel {
        RESET,
        PLACE,
        PICKINGUP,
    }
}
