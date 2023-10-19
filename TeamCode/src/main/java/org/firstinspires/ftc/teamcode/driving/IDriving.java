package org.firstinspires.ftc.teamcode.driving;

public interface IDriving {

    public void vertical (float power);

    public void horizontal (float power);

    public void turn (float t);

    public void stop ();

    public void joystickDrive (float x, float y, float t);

}
