import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;


public class Snake {
    private List<Node> body = new LinkedList<>();  //方便头尾节点的插入和删除
    private Node head = null;
    private Node tail = null;

    public Snake(Node node) {
        body.add(node);
        head = tail = node;
    }

    /**
     * 判断蛇是否吃到了食物.若能吃到, 则蛇身增长.
     *
     * @param food
     * @return
     */
    public Node eat(@NotNull Node food) {
        if (Node.samePosition(head, food)) {
            addTail();
            return food;
        } else {
            return null;
        }

    }

    /**
     * 蛇自身的移动,实际上是新的头节点产生, 尾巴移动到前一个位置.
     *
     * @return
     */
    public boolean move() {
        if (head == null) return false;

        /* 注意这里顺序很重要！不可搞错！ */
        if (head != tail) {
            head = getPreviousNode(head);
            tail = body.get(body.size() - 2);
            // tail = getPreviousNode(tail);  //这样处理不对
        } else {
            head = getPreviousNode(head);
            tail = head;
        }

        //顺序很重要
        body.remove(body.size() - 1);
        body.add(0, head);

        //head&tail的位置已经移动,判断是否撞到自己
        if (!snakeCrush())
            return true;
        return false;
    }

    /**
     * 改变蛇头的移动方向, 在蛇身为长度1时候, 可任意移动方向.
     *
     * @param newDir
     * @return
     */
    public boolean changeDirection(Direction newDir) {
        if (!Direction.isOpposite(head.getDirection(), newDir) || head == tail) {
            head.setDirection(newDir);
            return true;
        }
        return false;
    }

    /**
     * 借助画笔, 画出蛇身, 需要调用每个node的draw, 或者把任务交给每个node本身.
     *
     * @param g
     */
    public void drawSelf(Graphics g) {
        body.get(0).drawSelf(g, Color.WHITE);  //头是特殊颜色

        for (int i = 1; i < body.size(); i++) {
            body.get(i).drawSelf(g, Color.BLACK);
        }
    }

    public Node getHead() {
        return head;
    }

    /**
     * 放弃用的方法, 封装性不好.
     *
     * @return
     */
    public List<Node> getBody() {
        return body;
    }

    public Node getTail() {
        return tail;
    }

    /**
     * 蛇吃了food后尾部增长
     */
    public void addTail() {
        if (tail == null) return;

        //不要忘了修改tail
        tail = getNextNode(tail);
        body.add(tail);
    }

    private boolean snakeCrush() {
        for (int i = 1; i < body.size(); i++) {
            if (Node.samePosition(head, body.get(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 根据node的方向, 指出它前进方向的一个节点.
     *
     * @param node
     * @param dir
     * @return
     */
    private Node getPreviousNode(@NotNull Node node, Direction dir) {
        int x = node.getX(), y = node.getY();
        switch (dir) {
            case RIGHT:
                x++;
                break;
            case LEFT:
                x--;
                break;
            case DOWN:
                y++;
                break;
            case UP:
                y--;
                break;
        }
        return new Node(x, y, dir);
    }

    private Node getPreviousNode(@NotNull Node node) {
        return getPreviousNode(node, node.getDirection());
    }

    private Node getNextNode(@NotNull Node node, Direction dir) {
        int x = node.getX(), y = node.getY();
        switch (dir) {
            case RIGHT:
                x--;
                break;
            case LEFT:
                x++;
                break;
            case DOWN:
                y--;
                break;
            case UP:
                y++;
                break;
        }
        return new Node(x, y, dir);
    }

    private Node getNextNode(@NotNull Node node) {
        return getNextNode(node, node.getDirection());
    }


}
