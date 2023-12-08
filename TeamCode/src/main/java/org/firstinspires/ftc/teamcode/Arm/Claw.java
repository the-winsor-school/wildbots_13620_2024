package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    public Servo right;
    public Servo left;

    public Claw(Servo rightServo, Servo leftServo) {
        right = rightServo;
        left = leftServo;
    }

    /**
     * moves both claw servos
     * @param pos enum for the claw position
     */
    public void controlClaw(ClawPos pos) {
        switch (pos) {
            //TODO test for close values
            case OPEN:
                right.setPosition(-1);
                left.setPosition(1);
                break;

            case CLOSE:
                right.setPosition(1);
                left.setPosition(-1);
                break;

            case STOP:
                right.setPosition(0.5);
                left.setPosition(0.5);
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
            return right.getPosition();
        return left.getPosition();
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


