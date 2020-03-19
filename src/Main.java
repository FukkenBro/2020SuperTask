
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Random;


public class Main extends Application {

    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 500;

    private static final Random RANDOM = new Random();

    //    private static BaseShape selected;
    private static ArrayList<BaseShape> selected = new ArrayList<>();
    public static ArrayList<BaseShape> shapes = new ArrayList<>();
    private long time;

    public static GraphicsContext gc;

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

        BaseShape ball1 = new CircleShape(10, 10, 255, 0, 0);
        shapes.add(ball1);
        BaseShape square = new SqareShape(10, 150, 0, 255, 0);
        shapes.add(square);
        BaseShape triangle = new TriangleShape(10, 300, 0, 0, 255);
        shapes.add(triangle);

        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnMouseClicked(this::handleMouseClick);
        drawFrame();
    }

    private void handleKeyPressed(KeyEvent event) {
        int buttonHoldDelay = 100;
        double fastMov = 5;
        double defaultSpeed = 1;
        boolean hold = false;
        if (System.currentTimeMillis() - time <= buttonHoldDelay) {
            hold = true;
        }
        switch (event.getCode()) {
            case Q:
                BaseShape ball = new CircleShape(0, 0, RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255));
                shapes.add(ball);
                break;
            case UP:
                if (hold) {
                    BaseShape.step = fastMov;
                } else {
                    BaseShape.step = defaultSpeed;
                }
                for (int i = 0; i < selected.size(); i++) {
                    selected.get(i).moveUp();
                }
                break;
            case DOWN:
                if (hold) {
                    BaseShape.step = fastMov;
                } else {
                    BaseShape.step = defaultSpeed;
                }
                for (int i = 0; i < selected.size(); i++) {
                    selected.get(i).moveDown();
                }
                break;
            case LEFT:
                if (hold) {
                    BaseShape.step = fastMov;
                } else {
                    BaseShape.step = defaultSpeed;
                }
                for (int i = 0; i < selected.size(); i++) {
                    selected.get(i).moveLeft();
                }
                break;
            case RIGHT:
                if (hold) {
                    BaseShape.step = fastMov;
                } else {
                    BaseShape.step = 1;
                }
                for (int i = 0; i < selected.size(); i++) {
                    selected.get(i).moveRight();
                }
                break;
            case EQUALS:
                for (int i = 0; i < selected.size(); i++) {
                    selected.get(i).scaleUp();
                }
                break;
            case MINUS:
                for (int i = 0; i < selected.size(); i++) {
                    selected.get(i).scaleDown();
                }
                break;
            case A:
                if (event.isControlDown()) {
                    clearSelections();
                    for (BaseShape shape : shapes) {
                        selectShape(shape);
                    }
                }
                break;
        }
        for (int i = 0; i < selected.size(); i++) {
            selected.get(i).collision();
        }
        time = System.currentTimeMillis();
        drawFrame();
    }

    private void drawFrame() {
        gc.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).draw();
        }
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        double cursorX = mouseEvent.getX();
        double cursorY = mouseEvent.getY();
        System.out.println("Cursor {"+cursorX+":"+cursorY+"}");
        if (!mouseEvent.isControlDown()) {
            clearSelections();
        }
        for (BaseShape shape : shapes) {
            if (shape.pointCollision(cursorX, cursorY)) {
                if (!shape.selected) {
                    selectShape(shape);
                } else {
                    deselectShape(shape);
                }
            }
        }
        drawFrame();
    }

    private void clearSelections() {
        for (int i = 0; i < selected.size(); i++) {
            selected.get(i).selected = !selected.get(i).selected;
        }
        selected.clear();
    }

    private void selectShape(BaseShape shape) {
        shape.selected = true;
        selected.add(shape);
    }

    private void deselectShape(BaseShape shape) {
        shape.selected = false;
        selected.remove(shape);
    }

}