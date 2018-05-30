import java.util.Arrays;

public class Grid {
    static final int ROWS = 20;
    static final int COLS = 20;
    static final int GRID_SPAN = 30;  //每一个格子的大小

    private final int initX = 3;  //蛇头的初始坐标.
    private final int initY = 3;
    private Snake snake = null;
    //    private Direction direction = Direction.LEFT;  //指明将要移动的方向
    private Node food;

    private boolean[][] inSnake;  //记录蛇身占据的空间.

    public Grid() {
        snake = new Snake(new Node(initX, initY));
        //food位置随机生成
        food = new Node((int) (Math.random() * COLS), (int) (Math.random() * ROWS));

        //除蛇头位置为true.
        inSnake = new boolean[GRID_SPAN * COLS / GRID_SPAN][GRID_SPAN * ROWS / GRID_SPAN];
        for (boolean[] sta : inSnake) {
            Arrays.fill(sta, false);
        }
        inSnake[initX][initY] = true;
    }

    /**
     * 驱动蛇身的移动, 并且更新各个状态数值.
     */
    public boolean move() {
        Node oldTail = snake.getTail();
//        System.out.println("oldTail:" + oldTail);

        if (snake.move() && headInGrid(snake.getHead())) {  //成功移动则更新grid的status状态, null则失败.
            Node newHead = snake.getHead();
//            System.out.println("newHead:" + newHead);
            inSnake[newHead.getX()][newHead.getY()] = true;
            inSnake[oldTail.getX()][oldTail.getY()] = false;
        } else
            return false;

        if (snake.eat(food) != null)
            createFood();
        return true;
    }

    private void createFood() {
        int x, y;
        while (true) {
            x = (int) (COLS * Math.random());
            y = (int) (ROWS * Math.random());

            if (!inSnake[x][y])  //食物不可出现在蛇身上
                break;
        }
        food = new Node(x, y);
    }

    /**
     * 有点多余的功能, 由键盘控制方向, 改变蛇头方向的中间代理
     *
     * @param newDir
     * @return
     */
    public boolean changeDirection(Direction newDir) {
        if (snake.changeDirection(newDir)) {
            return true;
        }
        return false;
    }

    public Snake getSnake() {
        return snake;
    }

    public Node getFood() {
        return food;
    }

    private boolean headInGrid(Node node) {
        int x = node.getX(), y = node.getY();
        return x >= 0 && x < COLS - 1 && y >= 0 && y < ROWS - 1;
    }
}

