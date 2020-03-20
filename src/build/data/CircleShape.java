package build.data;

import build.display.Draw;
import build.driver.Collisions;


public class CircleShape extends BaseShape {


    public CircleShape(int x, int y, int red, int green, int blue) {
        super(ShapeType.CIRCLE, x, y, red, green, blue);
    }

    public CircleShape(boolean b, ShapeType type, int x, int y, int r, int red, int green, int blue) {
        super(b, type, x, y, r, red, green, blue);
    }

    @Override
    public boolean pointCollision(double x, double y) {
        return Collisions.pointCollision(this, x, y);
    }

    @Override
    public void draw() {
        Draw.drawCircle(this);
    }

}

