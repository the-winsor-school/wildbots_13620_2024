package org.firstinspires.ftc.teamcode.driving;

/**
 * this interface is basically the "format" for every driving class.
 * Each file that impletments IDriving has to fufill all of these functions but they can each do it in a different way
 * the univeral driving for this project is defined in the robot class
 * the robot class is the only place that specifics which class that implements Idriving is being used
 * this allows us to change what kind of driving we are using without have to change all of our functions
 */
public interface IDriving {

    /**
     * will move robot forward or backwards (on y axis)
     * @param power positive values go forward, negative values backwards, magnitude is speed
     */
    public void vertical (double power);

    /**
     * will move robot left or right (on x axis)
     * uses strafing (robot doesnt turn)
     * @param power positive values go right, negative values go left, magnitude is speed
     */
    public void horizontal (double power);

    /**
     * will turn robot without moving just a spin
     * @param t positive values go right, negative values go left, magnitude is speed of the turn
     */
    public void turn (double t);

    /**
     * stops the robot with brake stop
     */
    public void stop();

    /**
     * moves robot along the coordinates of x, y, and t (turn)
     * @param x x cordinate
     * @param y y cordinate
     * @param t turn value
     */
    public void joystickDrive (float x, float y, float t);

    /**
     * changes the speed used for the motors
     * @param x adds this value to the current speed;
     */
    public void adjustSpeed(double x);

    /**
     * retruns current speed
     */
    public double getSpeed();


}
