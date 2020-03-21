package build.data;

import java.util.ArrayList;

public class Structure implements ShapesStructure {

    public ArrayList<BaseShape> selectedShapes = new ArrayList<>();
    public ArrayList<BaseShape> allShapes = new ArrayList<>();

    public Structure() {
    }

    @Override
    public void add(ShapeType type, int x, int y, int red, int green, int blue) {
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

    @Override
    public void clearSelections() {
        for (BaseShape selectedShape : selectedShapes) {
            selectedShape.selected = !selectedShape.selected;
        }
        selectedShapes.clear();
    }

    @Override
    public void selectShape(BaseShape shape) {
        shape.selected = true;
        selectedShapes.add(shape);
    }

    @Override
    public void deselectShape(BaseShape shape) {
        shape.selected = false;
        selectedShapes.remove(shape);
    }

    @Override
    public void deleteShape(BaseShape shape) {
        allShapes.remove(shape);
    }

    @Override
    public Structure getState() {
        return this;
    }

    @Override
    public String toString() {
        return "Structure{" +
                "selectedShapes=" + selectedShapes +
                ", allShapes=" + allShapes +
                '}';
    }
}
