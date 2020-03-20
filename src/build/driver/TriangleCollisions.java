package build.driver;

import build.data.BaseShape;

public abstract class TriangleCollisions implements ShapeCollisions {

    public static boolean pointCollision(BaseShape triangle, double cursorX, double cursorY) {
        int x = triangle.x;
        int y = triangle.y;
        int r = triangle.r;

        int x1 = x + 20;
        int y1 = y + 2 * r + 5;

        int x2 = x + (2 * r + 5) / 2;
        int y2 = y + 5;

        int x3 = x + 2 * r + 5;
        int y3 = y + 2 * r + 5;

        double A = area(x1, y1, x2, y2, x3, y3);

        double A1 = area(cursorX, cursorY, x2, y2, x3, y3);

        double A2 = area(x1, y1, cursorX, cursorY, x3, y3);

        double A3 = area(x1, y1, x2, y2, cursorX, cursorY);

        return (A == A1 + A2 + A3);

    }

    static double area(double x1, double y1, double x2, double y2, double x3, double y3) {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) +
                x3 * (y1 - y2)) / 2.0);
    }
}
