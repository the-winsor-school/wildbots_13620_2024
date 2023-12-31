package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Arm.*;
import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.StrafeDrive;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * In this file we:
 * initalize all the sensors, motors, and libraries
 * the motors and sensors go here so we are only initializing them in one place in the whole repo
 * (to avoid mistakes and conflicts)
 * the libraries like IDriving and arm etc are intialized here so they are also only in one place
 * the libaries also ahve access to everything within the robot class like the motors if they are intialized in this way
 * when you create a new opMode you should only initlaize the robot class (by passing the opMode (by writing "this" in the parentheses)
 * you cannot access any of the sensors or motors outside of this class (because encapsulation and saefty)
 * you can only control things by using the libraries and the functions within them that are public
 */
public class Robot {

    /**
     * itializtion of all sensors and motors
     */
    //wheels
    //rf, rb, lf, lb
    private DcMotor rf;
    private DcMotor rb;
    private DcMotor lf;
    private DcMotor lb;

    //elbow
    private DcMotor elbowMotor;
    private TouchSensor elbowLimit;

    //wrist
    private DcMotor wristMotor;
    private TouchSensor wristLimit;
    private AnalogInput wristPotentiometer;

    //claw
    private CRServo rightServo;
    private CRServo leftServo;

    //auton
    private ColorSensor color;

    /**
     * itialization of libraires
     */
    public IDriving driving;
    public FullArm arm;

    private LinearOpMode opMode;

    /**
     * @param opMode pass by writing: new Robot(this);
     */
    public Robot(LinearOpMode opMode) {
        HardwareMap map = opMode.hardwareMap;
        this.opMode = opMode;

        //wheels
        rf = map.tryGet(DcMotor.class, "rf");
        rb = map.tryGet(DcMotor.class, "rb");
        lf = map.tryGet(DcMotor.class, "lf");
        lb = map.tryGet(DcMotor.class, "lb");

        //elbow
        elbowMotor = map.tryGet(DcMotor.class, "elbow");
        elbowLimit = map.tryGet(TouchSensor.class, "elbowLimit");

        wristMotor = map.tryGet(DcMotor.class, "wrist");
        wristLimit = map.tryGet(TouchSensor.class, "wristLimit");
        wristPotentiometer = map.tryGet(AnalogInput.class, "wristAngle");

        rightServo = map.tryGet(CRServo.class, "right");
        leftServo = map.tryGet(CRServo.class, "left");

        //just because o the orienttion o the motor
        elbowMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        wristMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        color = map.tryGet(ColorSensor.class, "color");

        rf.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.REVERSE);
        lf.setDirection(DcMotor.Direction.REVERSE);
        lb.setDirection(DcMotor.Direction.FORWARD);

        driving = new StrafeDrive(rf, rb, lf, lb);

        arm = new FullArm(elbowMotor, elbowLimit, wristMotor, wristLimit, wristPotentiometer, rightServo, leftServo);
    }

    public void printWheelPowers() {
        opMode.telemetry.addData("rf: ", rf.getPower());
        opMode.telemetry.addData("lf: ", lf.getPower());
        opMode.telemetry.addData("rb: ", rb.getPower());
        opMode.telemetry.addData("lb: ", lb.getPower());

    }

    public boolean checkRedTape() {
        if (color.red()  > 350)
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

    public Boolean liftLimitValue() {
        return elbowLimit.isPressed();
    }
    public Boolean clawLimitValue() {
        return wristLimit.isPressed();
    }

}
