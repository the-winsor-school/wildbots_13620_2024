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

    LinearOpMode opMode;

    public FullArm(DcMotor liftMotor, DcMotor clawMotor, Servo rightServo, Servo leftServo, LinearOpMode opMode) {
        liftJoint = new ArmJoint(liftMotor, 0, armPower, tolerance);
        clawJoint = new ArmJoint(clawMotor, 0, armPower, tolerance);

        claw = new Claw(rightServo, leftServo);
        this.opMode = opMode;

    }

    public void moveToLevel(ArmLevel level) {
        switch (level) {
            case RESET: //init position
                liftJoint.moveArm(100);
                clawJoint.moveArm(100);
                break;

            case PLACE: //placing on board
                liftJoint.moveArm(1300);
                clawJoint.moveArm(800);
                opMode.telemetry.addData("lift joint", liftJoint.getCurrentPosition());
                break;
            case PICKINGUP: //picking up
                liftJoint.moveArm(100);
                clawJoint.moveArm(100);
                claw.clawControls(Claw.ClawPos.OPEN);
                break;
        }
    }
    public void moveToLevel(int i) {
        if (i == 1)  {
            liftJoint.moveArm(-800);
            clawJoint.moveArm(500);
            opMode.telemetry.addData("trying", "to place");
        }
    }
    public enum ArmLevel {
        RESET,
        PLACE,
        PICKINGUP,
    }


}
