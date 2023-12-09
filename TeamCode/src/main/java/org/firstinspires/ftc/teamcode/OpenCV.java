
package org.firstinspires.ftc.teamcode;
import org.opencv.core.Core;
import org.opencv.core.Rect;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

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

        //CHANGE DIMENSIONS BASED ON POSITOIN FROM START OF AUTON
        Point topLeftZone1 = new Point(0, 0);
        Point bottomRightZone1 = new Point(266, 448);
        Point topLeftZone2 = new Point(266,0);
        Point bottomRightZone2 = new Point(533,448);
        Point topLeftZone3 = new Point(533,0);
        Point bottomRightZone3 = new Point(800,448);

        Mat region1_Cb;
        Mat region1_Cg;
        Mat region1_Cr;
        Mat region2_Cb;
        Mat region2_Cg;
        Mat region2_Cr;
        Mat region3_Cb;
        Mat region3_Cg;
        Mat region3_Cr;
        //Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        Mat Cg = new Mat();
        Mat Cr = new Mat();

        private volatile int averageRedZone1;
        private volatile int averageRedZone2;
        private volatile int averageRedZone3;
        //private volatile int averageBlue;
        //private volatile int averageGreen;
        private volatile TYPE type = TYPE.ZONE2; //default value

        private void inputToCb(Mat input) {
            ///Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(input, Cb, 2);
            Core.extractChannel(input, Cg, 1);
            Core.extractChannel(input, Cr, 0);
        }

        @Override
        public void init(Mat input) {
            inputToCb(input);

            //region1_Cb = Cb.submat(new Rect(topLeft, bottomRight)); //setting region dimensions
            //region1_Cg = Cg.submat(new Rect(topLeft, bottomRight)); //setting region dimensions
            region1_Cr = Cr.submat(new Rect(topLeftZone1, bottomRightZone1)); //setting region dimensions
            region2_Cr = Cr.submat(new Rect(topLeftZone2, bottomRightZone2));
            region3_Cr = Cr.submat(new Rect(topLeftZone3, bottomRightZone3));

        }

        @Override
        public Mat processFrame(Mat input) {
            inputToCb(input);

            //averageBlue = (int) Core.mean(region1_Cb).val[0]; // red average values
            //averageGreen = (int) Core.mean(region1_Cg).val[0]; // blue average values
            averageRedZone1 = (int) Core.mean(region1_Cr).val[0]; // green average values
            averageRedZone2 = (int) Core.mean(region2_Cr).val[0];
            averageRedZone3 = (int) Core.mean(region3_Cr).val[0];

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
            
            if (averageBlue < averageRed && averageGreen < averageRed) {
                type = TYPE.ZONE1;
            }
            else if (averageBlue < averageGreen && averageRed < averageGreen) {
                type = TYPE.ZONE2;
            }
            else if (averageGreen < averageBlue && averageRed < averageBlue) {
                type = TYPE.ZONE3;
            }
            else {
                type = null;
            }

            return input;

        }

        public TYPE getType() {
            return type;
        }

        public int[] getAverage () {
            int[] averages = {averageRed, averageGreen, averageBlue};
            return averages;
        }

        public enum TYPE {
            ZONE1, ZONE2, ZONE3, ZONE4, ZONE5, ZONE6
            //#1-3 = blue left/right/center
            //#4-6 = red left/right/center
            //corresponds to apriltag IDs
        }
    }

}
