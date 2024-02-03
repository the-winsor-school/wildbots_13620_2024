package org.firstinspires.ftc.teamcode.AutonLibrary;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;

public class FarPark
{
    Robot robot;
    LinearOpMode opMode;
    Telemetry telemetry;

    public FarPark(LinearOpMode opMode, Robot robot) {
        this.robot = robot;
        this.opMode = opMode;
        this.telemetry = opMode.telemetry;
    }

    /**
     * can hanlde simple parking from all positions on the field
     * @param fieldPosition where you start on the field (ex CLOSE_RED, FAR_BLUE)
     */
    public void driveFarPark(AllAutonMovements.FieldPosition fieldPosition) {
        /**
         * will either be -1 or 1 bases on blue or red starting positions
         * will be multiplyed by the horizontal vlues
         * if blue position = positive directions
         * if red position = negative direction
         */
        int horizontalDirection = 0; //will never be zero

        if (fieldPosition == AllAutonMovements.FieldPosition.FAR_BLUE)
            horizontalDirection = 1;
        else if (fieldPosition == AllAutonMovements.FieldPosition.FAR_RED)
            horizontalDirection = -1;

        //reverse
        robot.driving.vertical(-0.5f);
        opMode.sleep(500);

        //first horizontal
        robot.driving.horizontal(0.50f * horizontalDirection);
        opMode.sleep(7500);

        //forward
        robot.driving.vertical(0.5f);
        opMode.sleep(7000);

        //sideways
        robot.driving.horizontal(0.5f * -horizontalDirection);
        opMode.sleep(2000);

        while (!robot.checkTape()) {
            telemetry.addData("tape: ", "not found");
            telemetry.update();
            robot.driving.vertical(0.50f);
            opMode.sleep(20);
        }


        robot.driving.stop();
    }
}
