package build.data;

import build.driver.Collisions;
import build.display.Draw;

public class SquareShape extends BaseShape {

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
        Draw.drawSquare(this);
    }
}

