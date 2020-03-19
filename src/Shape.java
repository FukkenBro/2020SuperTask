public interface Shape {


    public void draw();

    public void moveUp();

    public void moveDown();

    public void moveLeft();

    public void moveRight();

    public boolean pointCollision(double x, double y);
}
