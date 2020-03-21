package build.driver;

import build.Main;
import build.data.BaseShape;
import build.data.Structure;

public class MouseHandler {

    private static Structure state = Main.state;

    public static void clickSelection(double cursorX, double cursorY) {
        for (int i = state.allShapes.size() - 1; i >= 0; i--) {
            BaseShape shape = state.allShapes.get(i);
            if (shape.pointCollision(cursorX, cursorY)) {
                if (!shape.selected) {
                    state.selectShape(shape);
                    break;
                } else {
                    state.deselectShape(shape);
                    break;
                }
            }
        }
    }

}
