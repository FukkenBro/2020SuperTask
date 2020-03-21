package build.driver;

import build.Main;
import build.data.BaseShape;
import build.data.Direction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static build.data.ShapeType.*;

public class KeyHandler {


    private static double defaultSpeed = 1;
    private static double fastMov = 1;

    public static void redo() {
        Main.state = SaveAndLoad.getPrevState();
    }

    public static void quickSave() {
        Main.saveFile = SaveAndLoad.serialize(Main.state.getState());
        try {
            SaveAndLoad.saveToFile(Main.state);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Main.saveFile);
    }

    public static void quickLoad() {
        Main.state = SaveAndLoad.deserialize(Main.saveFile);
    }

    public static void loadFromFile() throws IOException {
        Main.state = SaveAndLoad.loadFromFile();
    }

    public static void cloneShapes() {
        ArrayList<BaseShape> tmpArr = new ArrayList<>();
        for (
                int i = 0; i < Main.state.selectedShapes.size(); i++) {
            BaseShape tmp = Main.state.selectedShapes.get(i);
            BaseShape shape = tmp.cloneShape();
            tmpArr.add(shape);
        }
        Main.state.clearSelections();
        for (
                int i = 0; i < tmpArr.size(); i++) {
            Main.state.allShapes.add(tmpArr.get(i));
            Main.state.selectShape(tmpArr.get(i));
        }
        tmpArr.clear();
        SaveAndLoad.addAction(Main.state);
    }

    public static void delete() {
        for (
                int i = 0; i < Main.state.selectedShapes.size(); i++) {
            Main.state.deleteShape(Main.state.selectedShapes.get(i));
        }
        Main.state.clearSelections();
        SaveAndLoad.addAction(Main.state);
    }

    public static void createCircle() {
        Main.state.add(CIRCLE, 10, 10, new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        SaveAndLoad.addAction(Main.state);
    }

    public static void createSquare() {
        Main.state.add(SQUARE, 10, 10, new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        SaveAndLoad.addAction(Main.state);
    }

    public static void createTriangle() {
        Main.state.add(TRIANGLE, 10, 10, new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        SaveAndLoad.addAction(Main.state);
    }

    public static void moveUP(boolean hold) {
        if (hold) {
            BaseShape.step += fastMov;
        } else {
            BaseShape.step = defaultSpeed;
        }
        for (int i = 0; i < Main.state.selectedShapes.size(); i++) {
            Main.state.selectedShapes.get(i).move(Direction.UP);
        }
        SaveAndLoad.addAction(Main.state);
    }

    public static void moveDOWN(boolean hold) {

        if (hold) {
            BaseShape.step += fastMov;
        } else {
            BaseShape.step = defaultSpeed;
        }
        for (int i = 0; i < Main.state.selectedShapes.size(); i++) {
            Main.state.selectedShapes.get(i).move(Direction.DOWN);
        }
        SaveAndLoad.addAction(Main.state);
    }

    public static void moveLEFT(boolean hold) {

        if (hold) {
            BaseShape.step += fastMov;
        } else {
            BaseShape.step = defaultSpeed;
        }
        for (int i = 0; i < Main.state.selectedShapes.size(); i++) {
            Main.state.selectedShapes.get(i).move(Direction.LEFT);
        }
        SaveAndLoad.addAction(Main.state);
    }

    public static void moveRIGHT(boolean hold) {

        if (hold) {
            BaseShape.step += fastMov;
        } else {
            BaseShape.step = defaultSpeed;
        }
        for (int i = 0; i < Main.state.selectedShapes.size(); i++) {
            Main.state.selectedShapes.get(i).move(Direction.RIGHT);
        }
        SaveAndLoad.addAction(Main.state);
    }

    public static void scaleUP() {
        for (int i = 0; i < Main.state.selectedShapes.size(); i++) {
            Main.state.selectedShapes.get(i).scale(Direction.UP);
        }
        SaveAndLoad.addAction(Main.state);
    }

    public static void scaleDOWN() {
        for (int i = 0; i < Main.state.selectedShapes.size(); i++) {
            Main.state.selectedShapes.get(i).scale(Direction.DOWN);
        }
        SaveAndLoad.addAction(Main.state);
    }

    public static void selectAll() {
        Main.state.clearSelections();
        for (BaseShape shape : Main.state.allShapes) {
            Main.state.selectShape(shape);
        }
    }

}
