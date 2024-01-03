
package org.firstinspires.ftc.teamcode;
import org.opencv.core.Core;
import org.opencv.core.Rect;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

import org.openftc.easyopencv.OpenCvPipeline;


public class OpenCV {

    public static class SignalPipeline extends OpenCvPipeline {
        private static final Scalar BLUE = new Scalar(0, 0, 255);
        private static final Scalar RED = new Scalar(255, 0, 0);
        private static final Scalar GREEN = new Scalar(0, 255, 0);

        //CHANGE THRESHOLD BASED ON SLEEVE COLORS
        private static final int THRESHOLD1 = 125; // yellow < 107 < white
        private static final int THRESHOLD2 = 140; // white < 140 < purple
        private static final int RED1 = 20; // yellow < 107 < white
        private static final int GREEN1 = 20; // white < 140 < purple

        //CHANGE DIMENSIONS BASED ON POSITION FROM START OF AUTON
        Point topLeftZone1 = new Point(0, 0);
        Point bottomRightZone1 = new Point(266, 448);
        Point topLeftZone2 = new Point(266,0);
        Point bottomRightZone2 = new Point(533,448);
        Point topLeftZone3 = new Point(533,0);
        Point bottomRightZone3 = new Point(800,448);

        // regions 1-3 are for blue
        // regions 4-6 are for red
        Mat region1;
        Mat region2;
        Mat region3;
        Mat region4;
        Mat region5;
        Mat region6;
        Mat Cb = new Mat();
        Mat Cr = new Mat();

        // zone 1 = left
        // zone 2 = center
        // zone 3 = right
        private volatile int redZone1;
        private volatile int redZone2;
        private volatile int redZone3;
        private volatile int blueZone1;
        private volatile int blueZone2;
        private volatile int blueZone3;
        private volatile TYPE type = TYPE.ZONE2; //default value

        private void inputToCb(Mat input) {
            Core.extractChannel(input, Cb, 2);
            Core.extractChannel(input, Cr, 0);
        }

        @Override
        public void init(Mat input) {
            inputToCb(input);
            //dimensions for blue
            region1 = Cb.submat(new Rect(topLeftZone1, bottomRightZone1));
            region2 = Cb.submat(new Rect(topLeftZone2, bottomRightZone2));
            region3 = Cb.submat(new Rect(topLeftZone3, bottomRightZone3));
            //dimensions for red
            region4 = Cr.submat(new Rect(topLeftZone1, bottomRightZone1));
            region5 = Cr.submat(new Rect(topLeftZone2, bottomRightZone2));
            region6 = Cr.submat(new Rect(topLeftZone3, bottomRightZone3));
        }

        @Override
        public Mat processFrame(Mat input) {
            inputToCb(input);
            //average values for blue in each zone
            blueZone1 = (int) Core.mean(region1).val[0];
            blueZone2 = (int) Core.mean(region2).val[0];
            blueZone3 = (int) Core.mean(region3).val[0];
            //average values for red in each zone
            redZone1 = (int) Core.mean(region4).val[0];
            redZone2 = (int) Core.mean(region5).val[0];
            redZone3 = (int) Core.mean(region6).val[0];

            //Imgproc.rectangle(input, topLeft, bottomRight, BLUE, 2);

            /*

            Imgproc.rectangle(
                input,
		        new Point(0,0),
		        new Point(266,448),
		        new Scalar(0,255,0), 5
            );

            Imgproc.rectangle(
		        input,
		        new Point(266,0),
		        new Point (533,448),
		        new Scalar(0,255,0), 5
            );

            Imgproc.rectangle(
		        input,
		        new Point(533,0),
		        new Point (800,448),
		        new Scalar(0,255,0), 5
            );

             */

            if (blueZone2 < blueZone1 && blueZone3 < blueZone1) {
                type = TYPE.ZONE1;
            }
            else if (blueZone1 < blueZone2 && blueZone3 < blueZone2) {
                type = TYPE.ZONE2;
            }
            else if (blueZone1 < blueZone3 && blueZone2 < blueZone3) {
                type = TYPE.ZONE3;
            }
            else if (redZone2 < redZone1 && redZone3 < redZone1) {
                type = TYPE.ZONE4;
            }
            else if (redZone1 < redZone2 && redZone3 < redZone2) {
                type = TYPE.ZONE5;
            }
            else if (redZone1 < redZone3 && redZone2 < redZone3) {
                type = TYPE.ZONE6;
            }
            else {
                type = null;
            }

            return input;

        }

        public TYPE getType() {
            return type;
        }

        public int[] getBlueValues () {
            int[] blueValues = {blueZone1, blueZone2, blueZone3};
            return blueValues;
        }

        public int[] getRedValues () {
            int[] redValues = {redZone1, redZone2, redZone3};
            return redValues;
        }


        public enum TYPE {
            ZONE1, ZONE2, ZONE3, ZONE4, ZONE5, ZONE6
            //#1-3 = blue left/right/center
            //#4-6 = red left/right/center
            //corresponds to apriltag IDs
        }
    }

}
