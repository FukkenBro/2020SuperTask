import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TriangleShape extends BaseShape {

    private final int red;
    private final int green;
    private final int blue;

    public final int x1 = x + 20;
    public final int y1 = y + 2 * r + 5;

    public final int x2 = x + (2 * r + 5) / 2;
    public final int y2 = y + 5;

    public final int x3 = x + 2 * r + 5;
    public final int y3 = y + 2 * r + 5;

    public TriangleShape(int r, int g, int b) {
        super();
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public TriangleShape(int x, int y, int red, int green, int blue) {
        super(x, y, ShapeType.TRIANGLE);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void draw() {
        if (!selected) {
            gc.setFill(Color.rgb(red, green, blue));
            gc.setStroke(Color.rgb(red, green, blue));
            gc.setLineWidth(lineWidth);
            int a = x + 20;
            int b = x + (2 * r + 5) / 2;
            int c = x + 2 * r + 5;

            int d = y + 2 * r + 5;
            int e = y + 5;
            int f = y + 2 * r + 5;
            gc.fillPolygon(new double[]{x + 5, x + (2 * r + 5) / 2.0, x + 2 * r + 5}, new double[]{y + 2 * r + 5, y + 5, y + 2 * r + 5}, 3);
//            System.out.println("{" + a + ":" + d + "}");
//            System.out.println("{" + b + ":" + e + "}");
//            System.out.println("{" + c + ":" + f + "}");
//            System.out.println();
//            System.out.println("{" + a+":"+ b+":"+ c+"     "+ d+":"+ e+":"+ f+"}");
        } else {
            gc.setFill(Color.rgb(red, green, blue));
            gc.setStroke(Color.rgb(red, green, blue));
            gc.setLineWidth(lineWidth);
            double a = x + lineWidth + (lineWidth / 3);
            double b = x + (lineWidth / 3) + (2 * r) / 2;
            double c = x - (lineWidth / 3) + 2 * r;

            double d = y + 2 * r;
            double e = y + lineWidth + (lineWidth / 3 * 2);
            double f = y + 2 * r;
            gc.strokePolygon(new double[]{a, b, c}, new double[]{d, e, f}, 3);
//            System.out.println("selected");
//            System.out.println("{" + a + ":" + d + "}");
//            System.out.println("{" + b + ":" + e + "}");
//            System.out.println("{" + c + ":" + f + "}");
//            System.out.println();
//            getLines();
//            System.out.println("{" + a+":"+ b+":"+ c+"     "+ d+":"+ e+":"+ f+"}");
        }
    }

    @Override
    public boolean pointCollision(double x, double y) {
        {
            /* Calculate area of triangle ABC */
            double A = area(x1, y1, x2, y2, x3, y3);

            /* Calculate area of triangle PBC */
            double A1 = area(x, y, x2, y2, x3, y3);

            /* Calculate area of triangle PAC */
            double A2 = area(x1, y1, x, y, x3, y3);

            /* Calculate area of triangle PAB */
            double A3 = area(x1, y1, x2, y2, x, y);

            /* Check if sum of A1, A2 and A3 is same as A */
            return (A == A1 + A2 + A3);
        }
    }

    static double area(double x1, double y1, double x2, double y2, double x3, double y3) {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) +
                x3 * (y1 - y2)) / 2.0);
    }

    @Override
    public boolean shapeCollision() {
        return false;
    }

    public ArrayList<Integer[]> getLines() {
        ArrayList<Integer[]> result = new ArrayList<>();
        //ax+by=c
        int a1 = y2 - y1;
        int b1 = x2 - x1;
        int c1 = a1 * (x1) + b1 * (y1);
        Integer[] eq1 = new Integer[3];
        eq1[0] = a1;
        eq1[1] = b1;
        eq1[2] = c1;
        System.out.println("#1  " + a1 + "x + " + b1 + "y = " + c1);

        int a2 = y3 - y1;
        int b2 = x3 - x1;
        int c2 = a2 * (x3) + b2 * (y3);
        Integer[] eq2 = new Integer[3];
        eq2[0] = a2;
        eq2[1] = b2;
        eq2[2] = c2;
        System.out.println("#1  " + a2 + "x + " + b2 + "y = " + c2);

        int a3 = y3 - y2;
        int b3 = x3 - x2;
        int c3 = a3 * (x3) + b3 * (y3);
        Integer[] eq3 = new Integer[3];
        eq3[0] = a3;
        eq3[1] = b3;
        eq3[2] = c3;
        System.out.println("#1  " + a3 + "x + " + b3 + "y = " + c3);
        result.add(eq1);
        result.add(eq2);
        result.add(eq3);
        return result;
    }

    public double[] getCentroid() {
        double[] centroid = new double[2];
        double x = (x1 + x2 + x3) / 3;
        double y = (y1 + y2 + y3) / 3;
        centroid[0] = x;
        centroid[1] = y;
        return centroid;
    }

}
