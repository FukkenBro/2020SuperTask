package build.driver;

import build.data.BaseShape;

public class CircleCollisions implements ShapeCollisions {


    public static boolean pointCollision(BaseShape circle, double cursorX, double cursorY) {
        double[] center = getCircleCenter(circle.x, circle.y, circle.r);
        double tmp = Math.pow((cursorX - center[0]), 2) + Math.pow((cursorY - center[1]), 2);
        if (tmp <= Math.pow(circle.r, 2)) {
            return true;
        }
        return false;
    }

    private static double[] getCircleCenter(int x, int y, int r) {
        double[] result = new double[2];
        int ox = x + r;
        result[0] = ox;
        int oy = y + r;
        result[1] = oy;
        return result;
    }

}



