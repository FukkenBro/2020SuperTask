package build.data;

import build.display.Paint;
import build.driver.TriangleCollisions;

public class TriangleShape extends BaseShape {

    public TriangleShape() {
    }

    public TriangleShape(int x, int y, int red, int green, int blue) {
        super(ShapeType.TRIANGLE, x, y, red, green, blue);
    }

    public TriangleShape(boolean b, ShapeType type, int x, int y, int r, int red, int green, int blue) {
        super(b, type, x, y, r, red, green, blue);
    }

    @Override
    public boolean pointCollision(double x, double y) {
        return TriangleCollisions.pointCollision(this, x, y);
    }

    @Override
    public void draw() {
        Paint.drawTriangle(this);
    }
}
