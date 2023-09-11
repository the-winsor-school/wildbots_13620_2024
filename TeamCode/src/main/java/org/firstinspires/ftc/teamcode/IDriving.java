package org.firstinspires.ftc.teamcode;

public interface IDriving {

    public void vertical (float power);

    public void horizontal (float power);

    public void turn (float t);

    public void stop ();

    public void joystickDrive (float x, float y, float t);

}
