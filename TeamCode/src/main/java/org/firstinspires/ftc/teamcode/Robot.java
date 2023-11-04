package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.driving.GridDrive;
import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.StrafeDrive;

public class Robot {

    //wheels
    //rf, rb, lf, lb
    public DcMotor rf;
    public DcMotor rb;
    public DcMotor lf;
    public DcMotor lb;
    public ColorSensor color;

    public IDriving driving;


    public Robot(LinearOpMode opMode) {
        HardwareMap map = opMode.hardwareMap;

        //wheels
        rf = map.tryGet(DcMotor.class, "rf");
        rb = map.tryGet(DcMotor.class, "rb");
        lf = map.tryGet(DcMotor.class, "lf");
        lb = map.tryGet(DcMotor.class, "lb");
        color = map.tryGet(ColorSensor.class, "Color");

        driving = new StrafeDrive(rf, rb, lf, lb);
    }

    public boolean checkRedTape() {
        if (color.red() > 1500)
            return true;
        return false;
    }

    public boolean checkBlueTape() {
        if (color.blue() > 1500)
            return true;
        return false;
    }

    public boolean checkTape() {
        if (checkBlueTape() || checkRedTape())
            return true;
        return false;
    }
}
