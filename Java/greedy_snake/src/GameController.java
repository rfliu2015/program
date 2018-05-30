import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener, Runnable {
    private Grid grid;
    private GameView gameView;
    private boolean running = true;


    public GameController(Grid grid, GameView gameView, boolean running) {
        this.grid = grid;
        this.gameView = gameView;
        this.running = running;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                grid.changeDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                grid.changeDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                grid.changeDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                grid.changeDirection(Direction.RIGHT);
                break;
        }

    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(200);
                if (grid.move()) {
                    gameView.draw();
                } else
                    running = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* 处理结束的情况 */
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
