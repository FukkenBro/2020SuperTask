package build.driver;

import build.data.BaseShape;

public abstract class SquareCollisions implements ShapeCollisions {


    public static boolean pointCollision(BaseShape square, double cursorX, double cursorY) {

        double x1 = square.x;
        double y1 = square.y + 2 * square.r;


        double x2 = square.x + 2 * square.r;
        double y2 = square.y;

        return cursorX > x1 && cursorX < x2 && cursorY > y2 && cursorY < y1;

    }

}
