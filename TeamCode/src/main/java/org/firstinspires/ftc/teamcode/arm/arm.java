package org.firstinspires.ftc.teamcode.arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.*;

public class arm {

    public ArmJoint bottomJoint;
    public ArmJoint topJoint;

    public Claw claw;

    double armPower = 0.5;
    int tolerance = 100;

    public arm(Robot robot) {
        bottomJoint = new ArmJoint(robot.bottomMotor, 0);
        topJoint = new ArmJoint(robot.topMotor, 0);

        claw = new Claw(robot.rightServo, robot.leftServo);

    }

    public class ArmJoint {
        int gearRatio;
        double powerUsed;
        DcMotor motor;
        int armTolerance;


        public ArmJoint(DcMotor motor, int gearRatio) {
            this.motor = motor;
            this.gearRatio = gearRatio;
            powerUsed = armPower;
            armTolerance = tolerance;
        }

        public void resetEncoders() {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        public void moveArm(int targetPosition) { //targetPosition in rotation
            Thread moveArm = new MoveArm(targetPosition);
            moveArm.start();
        }

        public int getCurrentPosition() {
            return motor.getCurrentPosition();
        }

        public void setMotorPower(double power) {
            motor.setPower(power);
        }

        public void brake() {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        public void changePower(int power) {
            powerUsed += power;
        }

        public class MoveArm extends Thread {
            private int targetPosition;

            public MoveArm(int targetPosition) {
                this.targetPosition = targetPosition;
            }

            public void run() {
                while (Math.abs(getCurrentPosition() - targetPosition) > armTolerance) {//motor is high
                    motor.setTargetPosition(targetPosition);
                    motor.setPower(powerUsed);
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public class Claw {
        Servo right;
        Servo left;

        public Claw(Servo rightServo, Servo leftServo) {
            right = rightServo;
            left = leftServo;
        }

        public void clawControls(ClawPos pos) {
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
    }

    public enum ClawPos {
        OPEN,
        CLOSE,
        STOP
    }

}
