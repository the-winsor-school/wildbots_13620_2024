package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.driving.GridDrive;
import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.StrafeDrive;
import com.qualcomm.robotcore.hardware.ColorSensor;


public class Robot {

    //wheels
    //rf, rb, lf, lb
    private DcMotor rf;
    private DcMotor rb;
    private DcMotor lf;
    private DcMotor lb;
    private ColorSensor color;
    public IDriving driving;

    private LinearOpMode opMode;



    public Robot(LinearOpMode opMode) {
        HardwareMap map = opMode.hardwareMap;
        this.opMode = opMode;
        //wheels
        rf = map.tryGet(DcMotor.class, "rf");
        rb = map.tryGet(DcMotor.class, "rb");
        lf = map.tryGet(DcMotor.class, "lf");
        lb = map.tryGet(DcMotor.class, "lb");

        driving = new StrafeDrive(rf, rb, lf, lb);
    }

    public boolean checkRedTape() {
        if (color.red()  > 500)
            return true;
        return false;
    }

    public boolean checkBlueTape() {
        if (color.blue() > 500)
            return true;
        return false;
    }

    public boolean checkTape() {
        if (checkBlueTape() || checkRedTape())
            return true;
        return false;
    }

    public void checkColorValues() {
        opMode.telemetry.addData("red",color.red());
        opMode.telemetry.addData("blue", color.blue());
        opMode.telemetry.update();
    }
}
