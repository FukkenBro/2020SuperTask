package build.driver;

import build.data.BaseShape;

public abstract class Move implements ShapeMovement {


    public static void moveUp(BaseShape shape) {
        shape.y -= BaseShape.step;
    }


    public static void moveDown(BaseShape shape) {
        shape.y += BaseShape.step;
    }


    public static void moveLeft(BaseShape shape) {
        shape.x -= BaseShape.step;
    }

    public static void moveRight(BaseShape shape) {
        shape.x += BaseShape.step;
    }

}
