package build.data;

import build.data.*;

import java.util.ArrayList;

public interface ShapesStructure {

    ArrayList<BaseShape> selectedShapes = new ArrayList<>();

    ArrayList<BaseShape> allShapes = new ArrayList<>();

    void add(ShapeType type, int x, int y, int red, int green, int blue);

    void clearSelections();

    void selectShape(BaseShape shape);

    void deselectShape(BaseShape shape);

    void deleteShape(BaseShape shape);

    Structure getState();

}
