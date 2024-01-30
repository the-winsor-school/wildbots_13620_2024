package org.firstinspires.ftc.teamcode.driving;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Odometry {

    DcMotor leftEncoder;
    DcMotor rightEncoder;
    DcMotor frontEncoder;

    IDriving driving;

    double deadwheelDiameter = 3.5; //in cm
    double deadwheelCircumfrance = 3.5 * Math.PI;
    double tickPerRev = 8192;

    double tolerance = 0.1;

    public Odometry(DcMotor leftEncoder, DcMotor rightEncoder, DcMotor frontEncoder, IDriving driving) {
        this.frontEncoder = frontEncoder;
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;

        this.driving = driving;
    }

    public double xValue() {
        return frontEncoder.getCurrentPosition();
    }

    public double yValue() {
        return (leftEncoder.getCurrentPosition() + rightEncoder.getCurrentPosition()) / 2;
    }

    public double feedbackLoop(double targetPos, double currentPos) { // returns direction to move in
        if (Math.abs(currentPos - targetPos) > tolerance) { //current is too high - go up
            return 1;
        }
        if (Math.abs(targetPos - currentPos) > tolerance) { //target is too high - go down
            return -1;
        }
        return 0;
    }

    public void verticalMovement(double targetTicks, double power) {
        while (feedbackLoop(targetTicks, yValue()) != 0) {
            driving.vertical(feedbackLoop(targetTicks, yValue()) * power);
        }
    }

    public void horizontalMovement(double targetTicks, double power) {
        while (feedbackLoop(targetTicks, xValue()) != 0) {
            driving.horizontal(feedbackLoop(targetTicks, yValue()) * power);
        }
    }

    /**
     * drive either forwards or backwards to certain distance (cm) at a ceratin power
     * @param distance in cm
     * @param power of all motors (only positive)
     */
    //TODO make power only positive with different variable type
    public void verticalDistance(double distance, double power) {
        double rotations = distance / deadwheelCircumfrance;
        double ticks = rotations * tickPerRev;
        verticalMovement(ticks, Math.abs(power));
    }
    /**
     * strafe left or right to certain distance (cm) at a ceratin power
     * @param distance in cm
     * @param power of all motors (only positive)
     */
    public void horizontalDistance(double distance, double power) {
        double rotations = distance / deadwheelCircumfrance;
        double ticks = rotations * tickPerRev;
        horizontalMovement(ticks, Math.abs(power));
    }
}
