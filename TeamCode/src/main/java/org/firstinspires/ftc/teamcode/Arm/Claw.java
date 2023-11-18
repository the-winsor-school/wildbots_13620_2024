package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.CRServo;

public class Claw {
    CRServo right;
    CRServo left;

    public Claw(CRServo rightServo, CRServo leftServo) {
        right = rightServo;
        left = leftServo;
    }

    public void controlClaw(ClawPos pos) {
        switch (pos) {
            case OPEN:
                right.setPower(-1);
                left.setPower(1);
                break;

            case STOP:
                right.setPower(0.5);
                left.setPower(0.5);
                break;

            case CLOSE:
                right.setPower(1);
                left.setPower(-1);
                break;
        }
    }

    public double getPower(String side) {
        if (side == "right")
            return right.getPower();
        return left.getPower();
    }

    public enum ClawPos {
        OPEN,
        CLOSE,
        STOP
    }
}


