package build.data;

import build.display.Paint;
import build.driver.Collisions;

public class SquareShape extends BaseShape {

    public SquareShape() {
    }

    public SquareShape(int x, int y, int red, int green, int blue) {
        super(ShapeType.SQUARE, x, y, red, green, blue);
    }

    public SquareShape(boolean b, ShapeType type, int x, int y, int r, int red, int green, int blue) {
        super(b, type, x, y, r, red, green, blue);
    }

    @Override
    public boolean pointCollision(double x, double y) {
        return Collisions.pointCollision(this, x, y);
    }

    @Override
    public void draw() {
        Paint.drawSquare(this);
    }
}

