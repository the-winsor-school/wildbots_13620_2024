package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    Servo right;
    Servo left;

    public Claw(Servo rightServo, Servo leftServo) {
        right = rightServo;
        left = leftServo;
    }

    public void controlClaw(ClawPos pos) {
        switch (pos) {
            case OPEN:
                right.setPosition(-1);
                left.setPosition(1);
                break;

            case STOP:
                right.setPosition(0);
                left.setPosition(0);
                break;

            case CLOSE:
                right.setPosition(1);
                left.setPosition(-1);
                break;
        }
    }

    public enum ClawPos {
        OPEN,
        CLOSE,
        STOP
    }
}


