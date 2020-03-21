package build.driver;

import build.Main;
import build.data.BaseShape;

public class MouseHandler {

    public static void clickSelection(double cursorX, double cursorY) {
        for (int i = Main.state.allShapes.size() - 1; i >= 0; i--) {
            BaseShape shape = Main.state.allShapes.get(i);
            if (shape.pointCollision(cursorX, cursorY)) {
                if (!shape.selected) {
                    Main.state.selectShape(shape);
                    break;
                } else {
                    Main.state.deselectShape(shape);
                    break;
                }
            }
        }
    }

}
