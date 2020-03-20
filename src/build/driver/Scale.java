package build.driver;

import build.data.BaseShape;

public abstract class Scale implements ShapeScaling {

    public static void scaleUp(BaseShape shape) {
        shape.r += BaseShape.scaleStep;
        shape.x -= BaseShape.scaleStep;
        shape.y -= BaseShape.scaleStep;
    }

    public static void scaleDown(BaseShape shape) {
        if (shape.r <= 2 * BaseShape.lineWidth) {
            return;
        }
        shape.x += BaseShape.scaleStep;
        shape.y += BaseShape.scaleStep;
        shape.r -= BaseShape.scaleStep;
    }
}
