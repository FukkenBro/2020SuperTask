package build.display;

import build.data.BaseShape;
import javafx.scene.paint.Color;

public class Draw {

    public static void drawCircle(BaseShape shape) {
        BaseShape.gc.setFill(Color.rgb(shape.red, shape.green, shape.blue));
        BaseShape.gc.setStroke(Color.rgb(shape.red, shape.green, shape.blue));
        BaseShape.gc.setLineWidth(BaseShape.lineWidth);
        BaseShape.gc.strokeOval(shape.x, shape.y, 2 * shape.r, 2 * shape.r);
        if (!shape.selected) {
            BaseShape.gc.fillOval(shape.x, shape.y, 2 * shape.r, 2 * shape.r);
        }
    }

    public static void drawSquare(BaseShape shape) {
        BaseShape.gc.setStroke(Color.rgb(shape.red, shape.green, shape.blue));
        BaseShape.gc.setLineWidth(BaseShape.lineWidth);
        BaseShape.gc.setFill(Color.rgb(shape.red, shape.green, shape.blue));
        if (!shape.selected) {
            BaseShape.gc.fillRect(shape.x, shape.y, 2 * shape.r, 2 * shape.r);
        } else {
            BaseShape.gc.strokeRect(shape.x + BaseShape.lineWidth / 2, shape.y + BaseShape.lineWidth / 2,
                    2 * shape.r - BaseShape.lineWidth, 2 * shape.r - BaseShape.lineWidth);

        }
    }

    public static void drawTriangle(BaseShape shape) {
        BaseShape.gc.setFill(Color.rgb(shape.red, shape.green, shape.blue));
        BaseShape.gc.setStroke(Color.rgb(shape.red, shape.green, shape.blue));
        BaseShape.gc.setLineWidth(BaseShape.lineWidth);
        if (!shape.selected) {
            int a = shape.x + 20;
            int b = shape.x + (2 * shape.r + 5) / 2;
            int c = shape.x + 2 * shape.r + 5;

            int d = shape.y + 2 * shape.r + 5;
            int e = shape.y + 5;
            int f = shape.y + 2 * shape.r + 5;
            BaseShape.gc.fillPolygon(new double[]{shape.x + 5, shape.x + (2 * shape.r + 5) / 2.0, shape.x + 2 * shape.r + 5},
                    new double[]{shape.y + 2 * shape.r + 5, shape.y + 5, shape.y + 2 * shape.r + 5}, 3);
        } else {
            double a = shape.x + BaseShape.lineWidth + (BaseShape.lineWidth / 3);
            double b = shape.x + (BaseShape.lineWidth / 3) + (2 * shape.r) / 2;
            double c = shape.x - (BaseShape.lineWidth / 3) + 2 * shape.r;

            double d = shape.y + 2 * shape.r;
            double e = shape.y + BaseShape.lineWidth + (BaseShape.lineWidth / 3 * 2);
            double f = shape.y + 2 * shape.r;
            BaseShape.gc.strokePolygon(new double[]{a, b, c}, new double[]{d, e, f}, 3);
        }
    }
}


