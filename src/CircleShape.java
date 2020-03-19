import javafx.scene.paint.Color;
import sun.awt.SunHints;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CircleShape extends BaseShape {

    private int red;
    private int green;
    private int blue;

    public CircleShape(int r, int g, int b) {
        super();
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public CircleShape(int x, int y, int red, int green, int blue) {
        super(x, y, ShapeType.CIRCLE);
       
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void draw() {
        if (!selected) {
            super.gc.setFill(Color.rgb(red, green, blue));
            super.gc.setStroke(Color.rgb(red, green, blue));
            super.gc.setLineWidth(super.lineWidth);
            super.gc.strokeOval(super.x, super.y, 2 * super.r, 2 * super.r);
            super.gc.fillOval(super.x, super.y, 2 * super.r, 2 * super.r);
            return;

        } else {
            super.gc.setFill(Color.rgb(255, 255, 255));
            super.gc.setStroke(Color.rgb(red, green, blue));
            super.gc.setLineWidth(super.lineWidth);
            super.gc.strokeOval(super.x, super.y, 2 * super.r, 2 * super.r);
            super.gc.fillOval(super.x, super.y, 2 * super.r, 2 * super.r);
        }
    }

    public double[] getCircleCenter() {
        double[] result = new double[2];
        int ox = x + r;
        result[0] = ox;
        int oy = y + r;
        result[1] = oy;
        return result;
    }

    public double circleCentersDstnc(CircleShape shape) {
        double[] thisCenter = getCircleCenter();
        System.out.println("this center " + thisCenter.toString() + " r = " + r);
        double[] shapeCenter = shape.getCircleCenter();
        System.out.println("shape center " + thisCenter.toString() + " r = " + shape.r);
        double deltaX = thisCenter[0] - shapeCenter[0];
        double deltaY = thisCenter[1] - shapeCenter[1];
        return Math.abs(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
    }

    @Override
    public boolean pointCollision(double x, double y) {
        double[] center = getCircleCenter();
        double tmp = Math.pow((x - center[0]), 2) + Math.pow((y - center[1]), 2);
        if (tmp <= Math.pow(r, 2)) {
            return true;
        }
        return false;
    }

    
//    public boolean shapeCollision() {
//        int bufferZone = 0;
//        for (int i = 0; i < shapes.size(); i++) {
////            if (getDistance(shapes.get(i)) != 0 && getDistance(shapes.get(i)) <= r * 2 + lineWidth) {
//            if (shapes.get(i).type == ShapeType.CIRCLE) {
//                if (circleCentersDstnc((CircleShape) shapes.get(i)) <= r + shapes.get(i).r + lineWidth + bufferZone && !(circleCentersDstnc((CircleShape) shapes.get(i)) == 0 && shapes.get(i).equals(this))) {
//                    System.out.println("Collision with shape #" + i);
//                    return true;
//                }
//            } else if (shapes.get(i).type == ShapeType.TRIANGLE) {
//                boolean touchFlag = false;
//                TriangleShape shape = (TriangleShape) shapes.get(i);
//                ArrayList<Integer[]> lines = shape.getLines();
//
//                double[] centroid = shape.getCentroid();
//                double tcx = centroid[0];
//                double tcy = centroid[1];
//                System.out.println("{" + tcx + ":" + tcy + "}");
//                double[] circleCenter = getCircleCenter();
//                double ccx = circleCenter[0];
//                double ccy = circleCenter[1];
//
//                double centerDistance = Math.sqrt(Math.pow((tcx - ccx), 2) + Math.pow((tcy - ccy), 2));
//                System.out.println(centerDistance);
//                System.out.println(3*r);
//
////                static void checkCollision(int a, int b, int c,
////                int x, int y, int radius)
////                {
//                for (int j = 0; j < lines.size(); j++) {
//                    int a = lines.get(j)[0];
//                    int b = lines.get(j)[1];
//                    int c = lines.get(j)[2];
//
//                    // Finding the distance of line from center.
//                    System.out.println("eq  " + a + "x + " + b + "y = " + c);
//                    double dist = (Math.abs(a * ccx + b * ccy + c)) / Math.sqrt(a * a + b * b);
//
//                    // Checking if the distance is less than,
//                    // greater than or equal to radius.
////                     && centerDistance <= r
//                    if (r == dist ) {
//                        System.out.println("Touch");
////                        touchFlag = true;
//                    } else if (r > dist ) {
//                        System.out.println("Intersect");
////                        touchFlag = true;
//                    } else {
//                        System.out.println("Outside");
//                    }
//                }
//                if (touchFlag) {
//                    return true;
//                }
//                return false;
////
////                boolean collision = true;
////                double[] distances = new double[3];
////
////                double deltaX1 = ((TriangleShape) shapes.get(i)).x1 - this.getCircleCenter()[0];
////                double deltaY1 = ((TriangleShape) shapes.get(i)).y1 - this.getCircleCenter()[1];
////                distances[0] = Math.abs(Math.sqrt(Math.pow(deltaX1, 2) + Math.pow(deltaY1, 2)));
////                System.out.println("Distance 1" + distances[0]);
////
////                double deltaX2 = ((TriangleShape) shapes.get(i)).x2 - this.getCircleCenter()[0];
////                double deltaY2 = ((TriangleShape) shapes.get(i)).y2 - this.getCircleCenter()[1];
////                distances[1] = Math.abs(Math.sqrt(Math.pow(deltaX2, 2) + Math.pow(deltaY2, 2)));
////                System.out.println("Distance 2" + distances[1]);
////
////                double deltaX3 = ((TriangleShape) shapes.get(i)).x3 - this.getCircleCenter()[0];
////                double deltaY3 = ((TriangleShape) shapes.get(i)).y3 - this.getCircleCenter()[1];
////                distances[2] = Math.abs(Math.sqrt(Math.pow(deltaX3, 2) + Math.pow(deltaY3, 2)));
////                System.out.println("Distance 3" + distances[2]);
////
////                for (int j = 0; j < distances.length; j++) {
////                    if (distances[i] >= r) {
////                        collision = false;
////                    }
////                }
////                if (collision == false) {
////                    System.out.println("Collision with shape #" + i);
////                    return true;
////                }
//            }
//        }
//        return false;
//    }


}
