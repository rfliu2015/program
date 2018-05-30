import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;

public class GameView {  //也可以继承自JPanel
    private Grid grid;  //要画的内容
    private JPanel canvas;  //提供画笔
    private static final int WIDTH = Grid.GRID_SPAN * Grid.COLS;
    private static final int HEIGHT = Grid.GRID_SPAN * Grid.ROWS;

    public GameView(Grid grid) {
        this.grid = grid;
    }

    /**
     * 得到画笔, 并画出grid所有内容.
     */
    void init() {
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                drawBackground(g);
                drawSnake(g, grid.getSnake());
                drawFood(g, grid.getFood());
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(GameView.WIDTH, GameView.HEIGHT);
            }
        };
    }

    void draw() {
        canvas.repaint();
    }

    public JPanel getCanvas() {
        return canvas;
    }


    private void drawBackground(Graphics g) {
        canvas.setBackground(Color.ORANGE);
    }

    private void drawSnake(Graphics g, @NotNull final Snake snake) {
        snake.drawSelf(g);
    }

    private void drawFood(Graphics g, Node food) {
        fillNode(g, food, Color.RED);
    }

    private void fillNode(Graphics g, @NotNull Node node, Color c) {
        Color ic = g.getColor();
        g.setColor(c);
        g.fillRect(node.getX() * Grid.GRID_SPAN, node.getY() * Grid.GRID_SPAN,
                (int) (Grid.GRID_SPAN * 0.9), (int) (Grid.GRID_SPAN * 0.9));
        g.setColor(ic);
    }
}
