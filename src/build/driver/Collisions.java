package build.driver;

import build.data.BaseShape;
import build.data.ShapeType;

public class Collisions implements ShapeCollisions {

    public static boolean pointCollision(BaseShape shape, double x, double y) {
        if (shape.type == ShapeType.TRIANGLE) {
            return TriangleCollisions.pointCollision(shape, x, y);
        }
        if (shape.type == ShapeType.SQUARE) {
            return SquareCollisions.pointCollision(shape, x, y);
        }
        if (shape.type == ShapeType.CIRCLE) {
            return CircleCollisions.pointCollision(shape, x, y);
        }
        return false;
    }

}
