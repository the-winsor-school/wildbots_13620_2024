package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.*;

public class AutonMovements {

    Robot robot;
    LinearOpMode opMode;
    Telemetry telemetry;

    public AutonMovements(LinearOpMode opMode, Robot robot) {
        this.robot = robot;
        this.opMode = opMode;
        this.telemetry = opMode.telemetry;
    }

    /**
     * can hanlde simple parking from all positions on the field
     * @param fieldPosition where you start on the field (ex CLOSE_RED, FAR_BLUE)
     */
    public void SimplePark(FieldPosition fieldPosition) {

        //if position is close use ClosePark movements and call that function
        if (fieldPosition == FieldPosition.CLOSE_BLUE
                || fieldPosition == FieldPosition.CLOSE_RED)
            ClosePark();

        else if (fieldPosition == FieldPosition.FAR_BLUE
                || fieldPosition == FieldPosition.FAR_RED){ //for far field positions

            /**
             * will either be -1 or 1 bases on blue or red starting positions
             * will be multiplyed by the horizontal vlues
             * if blue position = positive directions
             * if red position = negative direction
             */
            int horizontalDirection = 0; //will never be zero

            if (fieldPosition == FieldPosition.FAR_BLUE)
                horizontalDirection = 1;
            else if (fieldPosition == FieldPosition.FAR_RED)
                horizontalDirection = -1;

            robot.driving.horizontal(0.50f * horizontalDirection);
            robot.printWheelPowers();
            telemetry.update();
            opMode.sleep(7250);
            robot.driving.stop();

            while (!robot.checkTape()) {
                telemetry.addData("tape: ", "not found");
                telemetry.update();
                robot.driving.vertical(0.50f);
                opMode.sleep(20);
            }
            robot.driving.stop();
        }
    }

    /**
     * Just drives forward and stops when color sensor sees tape
     * works from both close starting field positions with same code
     */
    public void ClosePark() {

        while (!robot.checkTape()) {
            opMode.telemetry.addData("tape: ","not found");
            robot.printWheelPowers();
            telemetry.update();
            robot.driving.vertical(0.50f);
            opMode.sleep(20);
        }

        telemetry.addData("tape: ", "found");
        telemetry.update();
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
