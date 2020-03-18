import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Random;

public class BaseShape implements Shape {

    private final Random RANDOM = new Random();
    protected static GraphicsContext gc = Main.gc;
    protected static ArrayList<BaseShape> shapes = Main.shapes;
    protected int x;
    protected int y;
    protected int r = 50;
    protected final int lineWidth = 10;
    protected final static int step = 5;
    protected boolean selected;

    public BaseShape() {
        x = RANDOM.nextInt(Main.BOARD_WIDTH) - 2 * r;
        y = RANDOM.nextInt(Main.BOARD_HEIGHT) - 2 * r;
    }

    public BaseShape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {

    }

    public void moveUp() {
        y -= step;
        if (shapeCollision()) {
            y += step;
        }
    }

    public void moveDown() {
        y += step;
        if (shapeCollision()) {
            y -= step;
        }
    }

    public void moveLeft() {
        x -= step;
        if (shapeCollision()) {
            x += step;
        }
    }

    public void moveRight() {
        x += step;
        if (shapeCollision()) {
            x -= step;
        }
    }

    public boolean collision() {
        return false;
    }

    public double getDistance(BaseShape shape) {
        double[] thisCenter = getCenter();
        double[] shapeCenter = shape.getCenter();
        double deltaX = thisCenter[0] - shapeCenter[0];
        double deltaY = thisCenter[1] - shapeCenter[1];
        return Math.abs(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
    }

    public boolean shapeCollision() {
        for (int i = 0; i < shapes.size(); i++) {
            if (getDistance(shapes.get(i)) <= r * 2 + lineWidth) {
                if (getDistance(shapes.get(i)) == 0 && shapes.get(i).equals(this)) {
                    return false;
                }
                System.out.println("Collision with shape #" + i);
                return true;
            }
        }
        return false;
    }

    public double[] getCenter() {
        double[] result = new double[2];
        int ox = x + r;
        result[0] = ox;
        int oy = y + r;
        result[1] = oy;
        return result;
    }

    public boolean pointCollision(double x, double y) {
        double[] center = getCenter();
        double tmp = Math.pow((x - center[0]), 2) + Math.pow((y - center[1]), 2);
        if (tmp <= Math.pow(r, 2)) {
            return true;
        }
        return false;
    }

    public void scaleUp() {
        r += 5;
    }

    public void scaleDown() {
        if (r <= 5) {
            return;
        }
        r -= 5;
    }

}
