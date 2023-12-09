package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.StrafeDrive;
import org.firstinspires.ftc.teamcode.ATP.TelemetryVector;

public class Robot {

    //wheels
    //rf, rb, lf, lb
    private DcMotor rf;
    private DcMotor rb;
    private DcMotor lf;
    private DcMotor lb;

    public IDriving driving;
    public ATP atp;

    private LinearOpMode opMode;

    public Robot(LinearOpMode opMode) {
        HardwareMap map = opMode.hardwareMap;
        this.opMode = opMode;
        atp = new ATP(opMode);
        //wheels
        rf = map.tryGet(DcMotor.class, "rf");
        rb = map.tryGet(DcMotor.class, "rb");
        lf = map.tryGet(DcMotor.class, "lf");
        lb = map.tryGet(DcMotor.class, "lb");
        driving = new StrafeDrive(rf, rb, lf, lb);
    }


    public void goToTag(int tag) {
        driving.turn(-0.25f);
        opMode.sleep(400);
        TelemetryVector tagsFound = atp.getVectorToTag(tag);
        double x = tagsFound.getX();
        double z = tagsFound.getZ();
        double yaw = tagsFound.getYaw();
        while (tagsFound == null) {
            opMode.telemetry.addLine("no tags found, keep looking");
            driving.vertical(0.05f);
            opMode.sleep(200);
            return;
        } while (tagsFound != null) {
            if (Math.abs(yaw) > 15) {
                opMode.telemetry.addData("yaw", yaw);
                driving.turn(0.25f);
                opMode.sleep(200);
                if (yaw < 0) {
                    opMode.telemetry.addData("negative yaw", yaw);
                    driving.turn(-0.25f);
                    opMode.sleep(200);
                }
            } if (x < 0.3) {
                opMode.telemetry.addData("x", x);
                driving.horizontal(0.25f);
                opMode.sleep(200);
            } if (z > 3) {
                opMode.telemetry.addData("z", z);
                driving.vertical(0.25f);
                opMode.sleep(200);
            }
        }
    }

}
