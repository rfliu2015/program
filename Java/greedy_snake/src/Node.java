import java.awt.*;

public class Node {
    private int x;
    private int y;
    private Direction direction;  //具有方向方便以头节点的方向控制运动

    /**
     * 两节点是否相同位置.用以判断是否吃了food.
     *
     * @param n1
     * @param n2
     * @return
     */
    public static boolean samePosition(Node n1, Node n2) {
        return n1.x == n2.x && n1.y == n2.y;
    }

    public Node(int x, int y) {
        this(x, y, Direction.RIGHT);
    }

    public Node(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        direction = dir;
    }

    /**
     * 借助画笔, node自己画自己.
     *
     * @param g
     * @param c
     */
    public void drawSelf(Graphics g, Color c) {
        Color ic = g.getColor();
        g.setColor(c);
        g.fillRect(x * Grid.GRID_SPAN, y * Grid.GRID_SPAN,
                (int) (Grid.GRID_SPAN * 0.9), (int) (Grid.GRID_SPAN * 0.9));
        g.setColor(ic);
    }

    public int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object obj) {
        Node nobj = (Node) obj;
        return nobj.x == x && nobj.y == y && nobj.direction == direction;
    }

    @Override
    public String toString() {
        return "(x: " + x + "  y: " + y + "   dir:" + direction + ")";
    }
}
