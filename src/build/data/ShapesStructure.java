package build.data;

import build.data.*;

import java.util.ArrayList;

public interface ShapesStructure {

    ArrayList<BaseShape> selectedShapes = new ArrayList<>();

    ArrayList<BaseShape> allShapes = new ArrayList<>();

    static void add(ShapeType type, int x, int y, int red, int green, int blue) {
    }

    static void clearSelections() {
    }

    static void selectShape(BaseShape shape) {
    }

    static void deselectShape(BaseShape shape) {
    }
}
