import java.awt.*;

public class Point {

    private int x;
    private int y;
    private Color color;
    public static final int DIAMETER = 32;

    public Point(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public String getStringColor() {
        return color == Color.BLACK ? "Black" : "White";
    }

    @Override
    public int hashCode() {
        return getX() * 100 + getY() * 10 + (color == Color.BLACK ? 1 : 0);
    }

    @Override
    public boolean equals(Object obj) {
        Point p = (Point) obj;
        return p.getX() == getX() && p.getY() == getY() && p.color == color;
    }

    @Override
    public String toString() {
        return "(x: " + getX() + ", y:" + getY() + ") color:" + getStringColor();
    }
}
