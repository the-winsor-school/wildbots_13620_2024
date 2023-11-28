package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.AprilTag.ATP;
import org.firstinspires.ftc.teamcode.driving.IDriving;
import org.firstinspires.ftc.teamcode.driving.StrafeDrive;
import org.firstinspires.ftc.teamcode.AprilTag.ATP.TelemetryVector;

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
        TelemetryVector tagsFound = atp.getVectorToTag(tag);
        double x = tagsFound.getX();
        double z = tagsFound.getZ();
        double yaw = tagsFound.getYaw();
        if (tagsFound != null) {
            while (Math.abs(yaw) > 15) {
                driving.turn(0.25f);
                if (yaw < 0) {
                    driving.turn(-0.25f);
                }
            } while (x < 0.3) {
                driving.horizontal(0.25f);
            } while (z > 3) {
                driving.vertical(0.25f);
            }
        } else {
            opMode.telemetry.addLine("can't go to any tags because we can't see them");
        }
    }
}
