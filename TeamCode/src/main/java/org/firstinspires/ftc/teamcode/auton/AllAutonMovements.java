package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.*;
import org.firstinspires.ftc.teamcode.Arm.Claw;
import org.firstinspires.ftc.teamcode.Arm.FullArm;

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

    public void checkDistanceMovement() {
        while (!robot.objectFound()) {
            opMode.telemetry.addData("front", robot.frontDistanceValue());
            opMode.telemetry.addData("right", robot.rightDistanceValue());
            opMode.telemetry.addData("left", robot.leftDistanceValue());
            opMode.telemetry.update();
            robot.driving.vertical(-0.5);
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

}
