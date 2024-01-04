
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
        //private static final Scalar RED = new Scalar(255, 0, 0);
        //private static final Scalar GREEN = new Scalar(0, 255, 0);

        //CHANGE THRESHOLD BASED ON SLEEVE COLORS
        /*
        private static final int THRESHOLD1 = 125; // yellow < 107 < white
        private static final int THRESHOLD2 = 140; // white < 140 < purple
        private static final int RED1 = 20; // yellow < 107 < white
        private static final int GREEN1 = 20; // white < 140 < purple

         */

        //CHANGE DIMENSIONS BASED ON POSITION FROM START OF AUTON
        Point topLeft = new Point(160, 100);
        Point bottomRight = new Point(185, 150);

        Mat region1_Cb;
        Mat region1_Cg;
        Mat region1_Cr;
        //Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        Mat Cg = new Mat();
        Mat Cr = new Mat();

        private volatile int averageRed;
        private volatile int averageBlue;
        private volatile int averageGreen;
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

            region1_Cb = Cb.submat(new Rect(topLeft, bottomRight)); //setting region dimensions
            region1_Cg = Cg.submat(new Rect(topLeft, bottomRight)); //setting region dimensions
            region1_Cr = Cr.submat(new Rect(topLeft, bottomRight)); //setting region dimensions

        }

        @Override
        public Mat processFrame(Mat input) {
            inputToCb(input);

            averageBlue = (int) Core.mean(region1_Cb).val[0]; // red average values
            averageGreen = (int) Core.mean(region1_Cg).val[0]; // blue average values
            averageRed = (int) Core.mean(region1_Cr).val[0]; // green average values

            Imgproc.rectangle(input, topLeft, bottomRight, BLUE, 2);

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
            ZONE1, ZONE2, ZONE3
        }
    }

}