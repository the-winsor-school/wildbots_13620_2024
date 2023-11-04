/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
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

    //arm
    public DcMotor bottomMotor;
    public DcMotor topMotor;

    public Servo rightServo;
    public Servo leftServo;

    public IDriving driving;


    public Robot(LinearOpMode opMode) {
        HardwareMap map = opMode.hardwareMap;

        //wheels
        rf = map.tryGet(DcMotor.class, "rf");
        rb = map.tryGet(DcMotor.class, "rb");
        lf = map.tryGet(DcMotor.class, "lf");
        lb = map.tryGet(DcMotor.class, "lb");

        //arm
        bottomMotor = map.tryGet(DcMotor.class, "bottom arm joint");
        topMotor = map.tryGet(DcMotor.class, "top arm joint");

        rightServo = map.tryGet(Servo.class, "right servo");
        leftServo = map.tryGet(Servo.class, "left servo");

        driving = new StrafeDrive(rf, rb, lf, lb);
    }

}
*/
