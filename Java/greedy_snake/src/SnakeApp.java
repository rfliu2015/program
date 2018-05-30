import javax.swing.*;
import java.awt.*;

public class SnakeApp implements Runnable {
    private JFrame window;

    /**
     * 并没有什么用处的构造器.
     */
    SnakeApp() {
        window = new JFrame("贪吃蛇");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * 构造一些必要的组件以及流程处理.
     */
    @Override
    public void run() {
        /* 创建grid, 交给gameView去显示 */
        Grid grid = new Grid();

        /* 创建gameVies */
        GameView gameView = new GameView(grid);
        gameView.init();
        JPanel canvas = gameView.getCanvas();

        window.add(canvas, BorderLayout.CENTER);
        window.pack();  //最好不要在构造器里如此
        window.setVisible(true);

        /* 添加监听器. */
        GameController gameController = new GameController(grid, gameView, true);
        window.addKeyListener(gameController);
        new Thread(gameController).start();
    }

    public static void main(String[] args) {
        SnakeApp snakeApp = new SnakeApp();
        SwingUtilities.invokeLater(snakeApp);
    }
}
