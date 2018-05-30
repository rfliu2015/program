import java.awt.*;

public class MovingShape extends Thread {
    private Component app;

    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width;
    private int height;

    private int type = (int) (Math.random() * 3);
    private Color color = new Color((int) (Math.random() * 128) + 128,
            (int) (Math.random() * 128) + 128, (int) (Math.random() * 128) + 128);
    private boolean alive = true;

    public MovingShape(Component app) {
        this.app = app;

        x = (int) (app.getWidth() / 4 * 3 * Math.random() + app.getWidth() / 4);
        y = (int) (app.getHeight() / 4 * 3 * Math.random() + app.getHeight() / 4);
        dx = (int) (app.getSize().width * Math.random() / 40);
        dy = (int) (app.getSize().width * Math.random() / 40);
        if (dx == 0) {
            dx += 10;
        }
        if (dy == 0) {
            dy += 10;
        }

        width = (int) (app.getSize().width * Math.random() / 8);
        height = (int) (app.getSize().width * Math.random() / 8);

        start();
    }

    @Override
    public void run() {
        while (alive) {
            if (x < 0 || x > app.getWidth() - width) dx = -dx;
            if (y < 0 || y > app.getHeight() - height) dy = -dy;
            x += dx;
            y += dy;

            /* 准备画出此图形 */
            Graphics g = app.getGraphics();
            if (g == null) {
                System.out.println("No graphics!");
                System.exit(1);
            }

            switch (type) {
                case 0:  //矩形
                    g.setColor(color);
                    g.fillRect(x, y, width, height);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, width, height);
                    break;
                case 1:  //圆形
                    g.setColor(color);
                    g.fillOval(x, y, width, height);
                    g.setColor(Color.BLACK);
                    g.drawOval(x, y, width, height);
                    break;
                case 2:
                    g.setColor(color);
                    g.fillRoundRect(x, y, width, height, width / 2, height / 2);
                    g.setColor(Color.BLACK);
                    g.drawRoundRect(x, y, width, height, width / 2, height / 2);
                    break;
            }

            try {
                int sleepTime = 60;
                sleep(sleepTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void goToDie() {
        alive = false;
    }
}
