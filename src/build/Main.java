package build;

import build.data.Structure;
import build.driver.KeyHandler;
import build.driver.MouseHandler;
import build.driver.SaveAndLoad;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import static build.data.ShapeType.*;


public class Main extends Application {

    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 500;
    public static GraphicsContext gc;
    public static boolean showPreroll = true;

    public static Structure state = new Structure();
    public static String saveFile;

    public static long time;
    public static final int buttonHoldDelay = 100;
    public static boolean hold = false;

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

        scene.setOnKeyPressed(event -> {
            try {
                handleKeyPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        scene.setOnMouseClicked(this::handleMouseClick);

        if (showPreroll) {
            preroll();
        }

        state.add(CIRCLE, 10, 10, 255, 100, 0);
        state.add(SQUARE, 10, 150, 175, 100, 0);
        state.add(TRIANGLE, 10, 300, 100, 100, 0);
        SaveAndLoad.addAction(state);
    }


    private void handleKeyPressed(KeyEvent event) throws IOException {
        hold = false;
        if (System.currentTimeMillis() <= time + buttonHoldDelay) {
            hold = true;
        }
        switch (event.getCode()) {
            case Z:
                if (event.isControlDown()) {
                    KeyHandler.redo();
                }
                break;
            case F5:
                KeyHandler.quickSave();
                break;
            case F6:
                KeyHandler.quickLoad();
                break;
            case F7:
                KeyHandler.loadFromFile();
                break;
            case SHIFT:
                KeyHandler.cloneShapes();
                break;
            case DELETE:
                KeyHandler.delete();
                break;
            case DIGIT1:
                KeyHandler.createCircle();
                break;
            case DIGIT2:
                KeyHandler.createSquare();
                break;
            case DIGIT3:
                KeyHandler.createTriangle();
                break;
            case UP:
                KeyHandler.moveUP(hold);
                break;
            case DOWN:
                KeyHandler.moveDOWN(hold);
                break;
            case LEFT:
                KeyHandler.moveLEFT(hold);
                break;
            case RIGHT:
                KeyHandler.moveRIGHT(hold);
                break;
            case EQUALS:
                KeyHandler.scaleUP();
                break;
            case PLUS:
                KeyHandler.scaleUP();
                break;
            case MINUS:
                KeyHandler.scaleDOWN();
                break;
            case A:
                if (event.isControlDown()) {
                    KeyHandler.selectAll();
                }
                break;
        }
        time = System.currentTimeMillis();
        drawFrame();
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        double cursorX = mouseEvent.getX();
        double cursorY = mouseEvent.getY();
        if (!mouseEvent.isControlDown()) {
            state.clearSelections();
        }
        MouseHandler.clickSelection(cursorX, cursorY);
        drawFrame();
    }

    private void drawFrame() {
        gc.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        gc.setFill(Color.rgb(0, 0, 0));
        for (int i = 0; i < state.allShapes.size(); i++) {
            state.allShapes.get(i).draw();
        }
    }

    private void preroll() {
        gc.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        gc.setFill(Color.hsb(50, 0.5, 1));
        gc.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        gc.setFill(Color.hsb(0, 0, 0));
        gc.setFont(Font.font(20));
        gc.fillText("Controls: \nMove: ARROWS (hold to move faster) \nCreate shapes: 1-3 \nDelete selected: DELETE \nScale UP/DOWN: +/-" +
                "\nSelect-all: CRTL+A \nSelect-one: Mouse Click \nMerge: CTRL+Mouse Click \nClone: SHIFT" +
                "\nQuick-Save to file: F5 \nQuick-Load: F6 \nLoad from File: F7 \nUndo last action: CTRL+Z" +
                "\n\n\n " +
                "PRESS ANY KEY TO CONTINUE", BOARD_WIDTH / 2 - 150, 50);
        for (int i = 0; i < state.allShapes.size(); i++) {
            state.allShapes.get(i).draw();
        }
    }

}