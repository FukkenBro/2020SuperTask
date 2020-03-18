import javafx.scene.paint.Color;

public class CircleShape extends BaseShape {

    private int red;
    private int green;
    private int blue;


    public CircleShape(int r, int g, int b) {
        super();
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public CircleShape(int x, int y, int red, int green, int blue) {
        super(x, y);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void draw() {
        if (!selected) {
            super.gc.setFill(Color.rgb(red, green, blue));
            super.gc.setStroke(Color.rgb(red, green, blue));
            super.gc.setLineWidth(super.lineWidth);
            super.gc.strokeOval(super.x, super.y, 2 * super.r, 2 * super.r);
            super.gc.fillOval(super.x, super.y, 2 * super.r, 2 * super.r);
            return;

        } else {
            super.gc.setFill(Color.rgb(255, 255, 255));
            super.gc.setStroke(Color.rgb(red, green, blue));
            super.gc.setLineWidth(super.lineWidth);
            super.gc.strokeOval(super.x, super.y, 2 * super.r, 2 * super.r);
            super.gc.fillOval(super.x, super.y, 2 * super.r, 2 * super.r);
        }
    }




}
