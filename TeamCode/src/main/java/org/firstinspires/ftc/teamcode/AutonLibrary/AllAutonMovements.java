package org.firstinspires.ftc.teamcode.AutonLibrary;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.*;
import org.firstinspires.ftc.teamcode.Arm.Claw;

public class AllAutonMovements {

    Robot robot;
    LinearOpMode opMode;
    Telemetry telemetry;

    public AllAutonMovements(LinearOpMode opMode, Robot robot) {
        this.robot = robot;
        this.opMode = opMode;
        this.telemetry = opMode.telemetry;
    }

    public void FarPark(FieldPosition pos) {
        FarPark farPark = new FarPark(opMode, robot);
        farPark.driveFarPark(pos);
    }

    public void PlacingAuton (FieldPosition fieldPosition) {
        PlacingAuton placingAuton = new PlacingAuton(opMode, robot);
        placingAuton.drivePlacingAuton(fieldPosition);
    }

    public void PlacePixel(PixelLocation pixel) {
        if (pixel == PixelLocation.FRONT) {
            robot.driving.turn(0.5);
            opMode.sleep(5000);
            robot.driving.stop();
            robot.driving.vertical(0.25);
            opMode.sleep(100);
            robot.driving.stop();
            opMode.sleep(500);
            robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
            opMode.sleep(500);
            robot.arm.claw.moveClaw(Claw.ClawPos.STOP);

        }
        else if (pixel == PixelLocation.LEFT) {
            robot.driving.horizontal(0.5);
            opMode.sleep(200);
            robot.driving.turn(0.5);
            opMode.sleep(2300);
            robot.driving.vertical(0.25);
            opMode.sleep(100);
            opMode.sleep(500);
            robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
            opMode.sleep(500);
            robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
        }
        else if (pixel == PixelLocation.RIGHT) {

            robot.driving.turn(-0.5);
            opMode.sleep(2300);
            robot.driving.vertical(0.25);
            opMode.sleep(100);
            robot.driving.stop();
            opMode.sleep(500);
            robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
            opMode.sleep(500);
            robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
        }

    }

    public void clawPlacePixel() {
        opMode.sleep(500);
        robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
        opMode.sleep(1000);
        robot.arm.claw.moveClaw(Claw.ClawPos.STOP);
    }

    public void customPlacePixel(double turn, int sleep) {
        robot.driving.turn(turn);
        opMode.sleep(sleep);
        robot.driving.stop();
        opMode.sleep(500);
        robot.arm.claw.moveClaw(Claw.ClawPos.OPEN);
        opMode.sleep(1000);
    }

    public void moveToLeftDistance(double distance, double tolerance, double power) {

        while (Math.abs(robot.getLeftDistance() - distance) > tolerance) {
            if (robot.getLeftDistance() < distance) {
                telemetry.addData("left: ", robot.getLeftDistance());
                telemetry.update();
                robot.driving.horizontal(power);
                opMode.sleep(10);
            }
            else if (robot.getLeftDistance() > distance) {
                telemetry.addData("left: ", robot.getLeftDistance());
                telemetry.update();
                robot.driving.horizontal(-power);
                opMode.sleep(10);
            }
        }
        robot.driving.stop();
    }

    public void moveToRightDistance(double distance, double tolerance, double power) {

        while (Math.abs(robot.getRightDistance() - distance) > tolerance) {
            if (robot.getRightDistance() < distance) {
                telemetry.addData("right: ", robot.getRightDistance());
                telemetry.update();
                robot.driving.horizontal(-power);
                opMode.sleep(10);
            }
            else if (robot.getRightDistance() > distance) {
                telemetry.addData("right: ", robot.getRightDistance());
                telemetry.update();
                robot.driving.horizontal(power);
                opMode.sleep(10);
            }
        }
        robot.driving.stop();
    }

    public void moveToBackDistance(double distance, double tolerance, double power) {

        while (Math.abs(robot.getFrontDistance() - distance) > tolerance) {
            if (robot.getFrontDistance() < distance) {
                telemetry.addData("front: ", robot.getFrontDistance());
                telemetry.update();
                robot.driving.horizontal(power);
                opMode.sleep(10);
            }
            else if (robot.getFrontDistance() > distance) {
                telemetry.addData("front: ", robot.getFrontDistance());
                telemetry.update();
                robot.driving.horizontal(-power);
                opMode.sleep(10);
            }
        }
        robot.driving.stop();
    }

    /**
     * gives you all the field positions
     * (CLOSE means close side to stage and FAR is far from stage)
     */
    public enum FieldPosition {
        FAR_RED, //far side from stage
        CLOSE_RED, //close side to stage
        FAR_BLUE,
        CLOSE_BLUE,
    }

    public enum PixelLocation {
        LEFT,
        RIGHT,
        FRONT,
    }

}
