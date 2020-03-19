import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Random;

public class BaseShape implements Shape {

    private final Random RANDOM = new Random();

    public ShapeType type;

    public static double step = 1;
    protected static final int scaleStep = 1;
    protected static final int lineWidth = 10;
    protected static GraphicsContext gc = Main.gc;
    protected static ArrayList<BaseShape> shapes = Main.shapes;

    protected int x;
    protected int y;
    protected int r = 50;

    protected boolean selected;

    public BaseShape() {
        x = RANDOM.nextInt(Main.BOARD_WIDTH) - 2 * r;
        y = RANDOM.nextInt(Main.BOARD_HEIGHT) - 2 * r;
    }

    public BaseShape(int x, int y, ShapeType st) {
        this.type = st;
        this.x = x;
        this.y = y;
    }

    public void draw() {

    }

    public void moveUp() {
        y -= step;
    }

    public void moveDown() {
        y += step;
    }

    public void moveLeft() {
        x -= step;
    }

    public void moveRight() {
        x += step;
    }

    public boolean collision() {
        return false;
    }


    public boolean shapeCollision() {
        return false;
    }

    public boolean pointCollision(double x, double y) {
//        double[] center = getCenter();
//        double tmp = Math.pow((x - center[0]), 2) + Math.pow((y - center[1]), 2);
//        if (tmp <= Math.pow(r, 2)) {
//            return true;
//        }
        return false;
    }

    public void scaleUp() {
        r += scaleStep;
        x -= scaleStep;
        y -= scaleStep;
    }

    public void scaleDown() {
        if (r <= 2*lineWidth) {
            return;
        }
        x += scaleStep;
        y += scaleStep;
        r -= scaleStep;
    }

}
