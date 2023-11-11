package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Arm.*;
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
    public DcMotor liftMotor;
    public DcMotor clawMotor;

    public Servo rightServo;
    public Servo leftServo;

    public IDriving driving;

    public FullArm arm;

    private LinearOpMode opMode;


    public Robot(LinearOpMode opMode) {
        HardwareMap map = opMode.hardwareMap;

        this.opMode = opMode;


        //wheels
        rf = map.tryGet(DcMotor.class, "rf");
        rb = map.tryGet(DcMotor.class, "rb");
        lf = map.tryGet(DcMotor.class, "lf");
        lb = map.tryGet(DcMotor.class, "lb");

        //arm
        liftMotor = map.tryGet(DcMotor.class, "bottom arm joint");
        clawMotor = map.tryGet(DcMotor.class, "top arm joint");

        rightServo = map.tryGet(Servo.class, "right servo");
        leftServo = map.tryGet(Servo.class, "left servo");

        driving = new StrafeDrive(rf, rb, lf, lb);
        arm = new FullArm(liftMotor, clawMotor, rightServo, leftServo, opMode);
    }

    public void printWheels() {
        opMode.telemetry.addData("rf: ", rf.getPower());
        opMode.telemetry.addData("lf: ", lf.getPower());
        opMode.telemetry.addData("rb: ", rb.getPower());
        opMode.telemetry.addData("lb: ", lb.getPower());

    }

    public void printArm() {
        opMode.telemetry.addData("lift motor", liftMotor.getCurrentPosition());
        opMode.telemetry.addData("claw motor", clawMotor.getCurrentPosition());
    }

}
