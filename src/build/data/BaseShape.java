package build.data;

import build.Main;
import build.driver.Move;
import build.driver.Scale;
import javafx.scene.canvas.GraphicsContext;

public abstract class BaseShape implements Shape {

    public boolean selected;

    public static final int lineWidth = 10;
    public static final int scaleStep = 1;
    public static double step = 1;
    public static GraphicsContext gc = Main.gc;

    public ShapeType type;
    public int x;
    public int y;
    public int r = 50;
    public int red;
    public int green;
    public int blue;

    public BaseShape(ShapeType st, int x, int y, int red, int green, int blue) {
        this.type = st;
        this.x = x;
        this.y = y;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }


    public BaseShape cloneShape() {
        switch (this.type) {
            case CIRCLE:
                return new CircleShape(this.selected, this.type, x + 10, y + 10, r, red, green, blue);
            case SQUARE:
                return new SquareShape(this.selected, this.type, x + 10, y + 10, r, red, green, blue);
            case TRIANGLE:
                return new TriangleShape(this.selected, this.type, x + 10, y + 10, r, red, green, blue);
        }
        return null;
    }

    protected BaseShape(boolean selected, ShapeType type, int x, int y, int r, int red, int green, int blue) {
        this.selected = selected;
        this.type = type;
        this.x = x;
        this.y = y;
        this.r = r;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void draw() {
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                Move.moveUp(this);
                break;
            case DOWN:
                Move.moveDown(this);
                break;
            case LEFT:
                Move.moveLeft(this);
                break;
            case RIGHT:
                Move.moveRight(this);
                break;
        }
    }


    public boolean pointCollision(double x, double y) {
        return false;
    }


    public void scale(Direction direction) {
        switch (direction) {
            case UP:
                Scale.scaleUp(this);
                break;
            case DOWN:
                Scale.scaleDown(this);
                break;

        }
    }


}
