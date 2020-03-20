package build.driver;

import build.data.*;

import java.util.ArrayList;

public abstract class MainLayer implements Layout {

    public static ArrayList<BaseShape> selectedShapes = new ArrayList<>();
    public static ArrayList<BaseShape> allShapes = new ArrayList<>();

    public static void add(ShapeType type, int x, int y, int red, int green, int blue) {
        switch (type) {
            case CIRCLE:
                BaseShape circle = new CircleShape(x, y, red, green, blue);
                allShapes.add(circle);
                break;
            case TRIANGLE:
                BaseShape triangle = new TriangleShape(x, y, red, green, blue);
                allShapes.add(triangle);
                break;
            case SQUARE:
                BaseShape square = new SquareShape(x, y, red, green, blue);
                allShapes.add(square);
                break;
        }
    }

    public static void clearSelections() {
        for (int i = 0; i < selectedShapes.size(); i++) {
            selectedShapes.get(i).selected = !selectedShapes.get(i).selected;
        }
        selectedShapes.clear();
    }

    public static void selectShape(BaseShape shape) {
        shape.selected = true;
        selectedShapes.add(shape);
    }

    public static void deselectShape(BaseShape shape) {
        shape.selected = false;
        selectedShapes.remove(shape);
    }

    public static void deleteShape(BaseShape shape) {
        allShapes.remove(shape);
    }

}
