package build.driver;

import build.Main;
import build.data.BaseShape;
import build.data.Direction;
import build.data.Structure;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static build.data.ShapeType.*;

public class KeyHandler {

    private static Structure state = Main.state;
    private static boolean hold = Main.hold;

    private static double defaultSpeed = 1;
    private static double fastMov = 1;

    public static void redo() {
        state = SaveAndLoad.getPrevState();
    }

    public static void quickSave() {
        Main.saveFile = SaveAndLoad.serialize(state.getState());
        try {
            SaveAndLoad.saveToFile(state);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Main.saveFile);
    }

    public static void quickLoad() {
        state = SaveAndLoad.deserialize(Main.saveFile);
    }

    public static void loadFromFile() throws IOException {
        state = SaveAndLoad.loadFromFile();
    }

    public static void cloneShapes() {
        ArrayList<BaseShape> tmpArr = new ArrayList<>();
        for (
                int i = 0; i < state.selectedShapes.size(); i++) {
            BaseShape tmp = state.selectedShapes.get(i);
            BaseShape shape = tmp.cloneShape();
            tmpArr.add(shape);
        }
        state.clearSelections();
        for (
                int i = 0; i < tmpArr.size(); i++) {
            state.allShapes.add(tmpArr.get(i));
            state.selectShape(tmpArr.get(i));
        }
        tmpArr.clear();
        SaveAndLoad.addAction(state);
    }

    public static void delete() {
        for (
                int i = 0; i < state.selectedShapes.size(); i++) {
            state.deleteShape(state
                    .selectedShapes.get(i));
        }
        state.clearSelections();
        SaveAndLoad.addAction(state);
    }

    public static void createCircle() {
        state.add(CIRCLE, 10, 10, new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        SaveAndLoad.addAction(state);
    }

    public static void createSquare() {
        state.add(SQUARE, 10, 10, new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        SaveAndLoad.addAction(state);
    }

    public static void createTriangle() {
        state.add(TRIANGLE, 10, 10, new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        SaveAndLoad.addAction(state);
    }

    public static void moveUP() {
        if (hold) {
            BaseShape.step += fastMov;
        } else {
            BaseShape.step = defaultSpeed;
        }
        for (int i = 0; i < state.selectedShapes.size(); i++) {
            state.selectedShapes.get(i).move(Direction.UP);
        }
        SaveAndLoad.addAction(state);
    }

    public static void moveDOWN() {
        if (hold) {
            BaseShape.step += fastMov;
        } else {
            BaseShape.step = defaultSpeed;
        }
        for (int i = 0; i < state.selectedShapes.size(); i++) {
            state.selectedShapes.get(i).move(Direction.DOWN);
        }
        SaveAndLoad.addAction(state);
    }

    public static void moveLEFT() {
        if (hold) {
            BaseShape.step += fastMov;
        } else {
            BaseShape.step = defaultSpeed;
        }
        for (int i = 0; i < state.selectedShapes.size(); i++) {
            state.selectedShapes.get(i).move(Direction.LEFT);
        }
        SaveAndLoad.addAction(state);
    }

    public static void moveRIGHT() {
        if (hold) {
            BaseShape.step += fastMov;
        } else {
            BaseShape.step = 1;
        }
        for (int i = 0; i < state.selectedShapes.size(); i++) {
            state.selectedShapes.get(i).move(Direction.RIGHT);
        }
        SaveAndLoad.addAction(state);
    }

    public static void scaleUP() {
        for (int i = 0; i < state.selectedShapes.size(); i++) {
            state.selectedShapes.get(i).scale(Direction.UP);
        }
        SaveAndLoad.addAction(state);
    }

    public static void scaleDOWN() {
        for (int i = 0; i < state.selectedShapes.size(); i++) {
            state.selectedShapes.get(i).scale(Direction.DOWN);
        }
        SaveAndLoad.addAction(state);
    }

    public static void selectAll() {
        state.clearSelections();
        for (BaseShape shape : state.allShapes) {
            state.selectShape(shape);
        }
    }

}
