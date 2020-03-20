package build.data;

public interface Shape {

    void draw();

    void move(Direction direction);

    void scale(Direction direction);

    BaseShape cloneShape();

    boolean pointCollision(double x, double y);

}
