package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.CRServo;

public class Claw {
    public CRServo right;
    public CRServo left;

    public Claw(CRServo rightServo, CRServo leftServo) {
        right = rightServo;
        left = leftServo;
    }

    /**
     * moves both claw servos but does not stop it (moves async)
     * @param pos enum for the claw position
     */
    public void moveClaw(ClawPos pos) {
        switch (pos) {
            //TODO test for close values
            case CLOSE:
                right.setPower(-0.5);
                left.setPower(0.5);
                break;

            case OPEN:
                right.setPower(0.5);
                left.setPower(-0.5);
                break;

            case STOP:
                right.setPower(0);
                left.setPower(0);
                break;
        }
    }

    /**
     * for telemetry
     * @param side either "right" or "left"
     * @return double of the current power
     */
    public double getPower(String side) {
        if (side == "right")
            return right.getPower();
        return left.getPower();
    }

    /**
     * enum for the claw position
     */
    public enum ClawPos {
        OPEN,
        CLOSE,
        STOP
    }
}


