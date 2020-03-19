import javafx.scene.paint.Color;

public class SqareShape extends BaseShape {

    private int red;
    private int green;
    private int blue;


    public SqareShape(int r, int g, int b) {
        super();
        this.red = r;
        this.green = g;
        this.blue = b;
        super.r *= 2;
    }

    public SqareShape(int x, int y, int red, int green, int blue) {
        super(x, y,ShapeType.SQUARE);
        this.red = red;
        this.green = green;
        this.blue = blue;
        super.r *= 2;
    }

    public void draw() {
        if (!selected) {
            gc.setFill(Color.rgb(red, green, blue));
            gc.setFill(Color.rgb(red, green, blue));
            gc.setLineWidth(3);
            gc.fillRect(x, y, r, r);
        } else {
            gc.setFill(Color.rgb(red, green, blue));
            gc.setFill(Color.rgb(red, green, blue));
            gc.setLineWidth(3);
            gc.strokeRect(x, y, r, r);
        }
    }


}
