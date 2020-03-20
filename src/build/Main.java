package build;

import build.data.*;
import build.data.Structure;
import javafx.application.Application;
import javafx.application.Platform;
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


        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnMouseClicked(this::handleMouseClick);

        preroll();
        Structure.add(CIRCLE, 10, 10, 255, 100, 0);
        Structure.add(SQUARE, 10, 150, 175, 100, 0);
        Structure.add(TRIANGLE, 10, 300, 100, 100, 0);
        //drawFrame();
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
                for (int i = 0; i < Structure.selectedShapes.size(); i++) {
                    BaseShape tmp = Structure.selectedShapes.get(i);
                    BaseShape shape = tmp.cloneShape();
                    tmpArr.add(shape);
                }
                Structure.clearSelections();
                for (int i = 0; i < tmpArr.size(); i++) {
                    Structure.allShapes.add(tmpArr.get(i));
                    Structure.selectShape(tmpArr.get(i));
                }
                tmpArr.clear();
                break;
            case DELETE:
                for (int i = 0; i < Structure.selectedShapes.size(); i++) {
                    Structure.deleteShape(Structure.selectedShapes.get(i));
                }
                Structure.clearSelections();
                break;
            case DIGIT1:
                Structure.add(CIRCLE, 10, 10, RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255));
                break;
            case DIGIT2:
                Structure.add(TRIANGLE, 10, 10, RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255));
                break;
            case DIGIT3:
                Structure.add(SQUARE, 10, 10, RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255));
                break;
            case UP:
                if (hold) {
                    BaseShape.step += fastMov;
                } else {
                    BaseShape.step = defaultSpeed;
                }
                for (int i = 0; i < Structure.selectedShapes.size(); i++) {
                    Structure.selectedShapes.get(i).move(Direction.UP);
                }
                break;
            case DOWN:
                if (hold) {
                    BaseShape.step += fastMov;
                } else {
                    BaseShape.step = defaultSpeed;
                }
                for (int i = 0; i < Structure.selectedShapes.size(); i++) {
                    Structure.selectedShapes.get(i).move(Direction.DOWN);
                }
                break;
            case LEFT:
                if (hold) {
                    BaseShape.step += fastMov;
                } else {
                    BaseShape.step = defaultSpeed;
                }
                for (int i = 0; i < Structure.selectedShapes.size(); i++) {
                    Structure.selectedShapes.get(i).move(Direction.LEFT);
                }
                break;
            case RIGHT:
                if (hold) {
                    BaseShape.step += fastMov;
                } else {
                    BaseShape.step = 1;
                }
                for (int i = 0; i < Structure.selectedShapes.size(); i++) {
                    Structure.selectedShapes.get(i).move(Direction.RIGHT);
                }
                break;
            case EQUALS:
                for (int i = 0; i < Structure.selectedShapes.size(); i++) {
                    Structure.selectedShapes.get(i).scale(Direction.UP);
                }
                break;
            case PLUS:
                for (int i = 0; i < Structure.selectedShapes.size(); i++) {
                    Structure.selectedShapes.get(i).scale(Direction.UP);
                }
                break;
            case MINUS:
                for (int i = 0; i < Structure.selectedShapes.size(); i++) {
                    Structure.selectedShapes.get(i).scale(Direction.DOWN);
                }
                break;
            case A:
                if (event.isControlDown()) {
                    Structure.clearSelections();
                    for (BaseShape shape : Structure.allShapes) {
                        Structure.selectShape(shape);
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
            Structure.clearSelections();
        }
        for (int i = Structure.allShapes.size()-1; i >=0 ; i--) {
            BaseShape shape = Structure.allShapes.get(i);
            if (shape.pointCollision(cursorX, cursorY)) {
                if (!shape.selected) {
                    Structure.selectShape(shape);
                    break;
                } else {
                    Structure.deselectShape(shape);
                    break;
                }
            }
        }
        drawFrame();
    }


    private void drawFrame() {
        gc.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        gc.setFill(Color.rgb(0, 0, 0));
        for (int i = 0; i < Structure.allShapes.size(); i++) {
            Structure.allShapes.get(i).draw();
        }
    }

    private void preroll() {
        gc.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        gc.setFill(Color.hsb(50, 0.5, 1));
        gc.fillRect(0,0,BOARD_WIDTH,BOARD_HEIGHT);
        gc.setFill(Color.hsb(0, 0, 0));
        gc.fillText("Controls: \nMove: ARROWS; \nCreate shapes: 1-3; \nDelete selected: DELETE; \nScale UP/DOWN: +/-; \nSelect-all: CRTL+A; \nSelect-one: Mouse Click \nMerge: CTRL+Mouse Click, \nClone: SHIFT\n\n\n\n PRESS ANY KEY TO CONTINUE", BOARD_WIDTH/2-80, BOARD_HEIGHT/2-100);
        for (int i = 0; i < Structure.allShapes.size(); i++) {
            Structure.allShapes.get(i).draw();
        }
    }

}