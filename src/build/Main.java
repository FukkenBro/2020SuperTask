package build;

import build.data.*;
import build.driver.MainLayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

import static build.data.ShapeType.*;


public class Main extends Application {

    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 500;
    public static GraphicsContext gc;
    public static boolean showPreroll = true;

    private static final Random RANDOM = new Random();
    private long time;
    private double hue = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFxSample");

        Canvas canvas = new Canvas();
        canvas.setWidth(BOARD_WIDTH);
        canvas.setHeight(BOARD_HEIGHT);

        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();

        gc = canvas.getGraphicsContext2D();

        MainLayer.add(CIRCLE, 10, 10, 255, 0, 0);
        MainLayer.add(SQUARE, 10, 150, 0, 255, 0);
        MainLayer.add(TRIANGLE, 10, 300, 0, 0, 255);

        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnMouseClicked(this::handleMouseClick);

        drawFrame();
    }

    private void handleKeyPressed(KeyEvent event) {
        int buttonHoldDelay = 100;
        double fastMov = 1;
        double defaultSpeed = 1;
        boolean hold = false;
        if (System.currentTimeMillis() - time <= buttonHoldDelay) {
            hold = true;
        }
        switch (event.getCode()) {
            case SHIFT:
                ArrayList<BaseShape> tmpArr = new ArrayList<>();
                for (int i = 0; i < MainLayer.selectedShapes.size(); i++) {
                    BaseShape tmp = MainLayer.selectedShapes.get(i);
                    BaseShape shape = tmp.cloneShape();
                    tmpArr.add(shape);
                }
                MainLayer.clearSelections();
                for (int i = 0; i < tmpArr.size(); i++) {
                    MainLayer.allShapes.add(tmpArr.get(i));
                    MainLayer.selectShape(tmpArr.get(i));
                }
                tmpArr.clear();
                break;
            case DELETE:
                for (int i = 0; i < MainLayer.selectedShapes.size(); i++) {
                    MainLayer.deleteShape(MainLayer.selectedShapes.get(i));
                }
                MainLayer.clearSelections();
                break;
            case DIGIT1:
                MainLayer.add(CIRCLE, 10, 10, RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255));
                break;
            case DIGIT2:
                MainLayer.add(TRIANGLE, 10, 10, RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255));
                break;
            case DIGIT3:
                MainLayer.add(SQUARE, 10, 10, RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255));
                break;
            case UP:
                if (hold) {
                    BaseShape.step += fastMov;
                } else {
                    BaseShape.step = defaultSpeed;
                }
                for (int i = 0; i < MainLayer.selectedShapes.size(); i++) {
                    MainLayer.selectedShapes.get(i).move(Direction.UP);
                }
                break;
            case DOWN:
                if (hold) {
                    BaseShape.step += fastMov;
                } else {
                    BaseShape.step = defaultSpeed;
                }
                for (int i = 0; i < MainLayer.selectedShapes.size(); i++) {
                    MainLayer.selectedShapes.get(i).move(Direction.DOWN);
                }
                break;
            case LEFT:
                if (hold) {
                    BaseShape.step += fastMov;
                } else {
                    BaseShape.step = defaultSpeed;
                }
                for (int i = 0; i < MainLayer.selectedShapes.size(); i++) {
                    MainLayer.selectedShapes.get(i).move(Direction.LEFT);
                }
                break;
            case RIGHT:
                if (hold) {
                    BaseShape.step += fastMov;
                } else {
                    BaseShape.step = 1;
                }
                for (int i = 0; i < MainLayer.selectedShapes.size(); i++) {
                    MainLayer.selectedShapes.get(i).move(Direction.RIGHT);
                }
                break;
            case EQUALS:
                for (int i = 0; i < MainLayer.selectedShapes.size(); i++) {
                    MainLayer.selectedShapes.get(i).scale(Direction.UP);
                }
                break;
            case PLUS:
                for (int i = 0; i < MainLayer.selectedShapes.size(); i++) {
                    MainLayer.selectedShapes.get(i).scale(Direction.UP);
                }
                break;
            case MINUS:
                for (int i = 0; i < MainLayer.selectedShapes.size(); i++) {
                    MainLayer.selectedShapes.get(i).scale(Direction.DOWN);
                }
                break;
            case A:
                if (event.isControlDown()) {
                    MainLayer.clearSelections();
                    for (BaseShape shape : MainLayer.allShapes) {
                        MainLayer.selectShape(shape);
                    }
                }
                break;
        }
        time = System.currentTimeMillis();
        drawFrame();
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        double cursorX = mouseEvent.getX();
        double cursorY = mouseEvent.getY();
        System.out.println("Cursor {" + cursorX + ":" + cursorY + "}");
        if (!mouseEvent.isControlDown()) {
            MainLayer.clearSelections();
        }
        for (BaseShape shape : MainLayer.allShapes) {
            if (shape.pointCollision(cursorX, cursorY)) {
                if (!shape.selected) {
                    MainLayer.selectShape(shape);
                } else {
                    MainLayer.deselectShape(shape);
                }
            }
        }
        drawFrame();
    }

    private void drawFrame() {
        gc.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        gc.setFill(Color.rgb(0, 0, 0));
        gc.fillText("Controls: move: arrows; create: 1-3; inc/dec: w/q; select: page up/down; Mouse click: Ctrl-merge, Shift-clone", 50, 80);
        for (int i = 0; i < MainLayer.allShapes.size(); i++) {
            MainLayer.allShapes.get(i).draw();
        }
    }

}