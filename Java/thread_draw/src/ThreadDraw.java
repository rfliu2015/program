import javax.swing.*;
import java.applet.Applet;
import java.util.ArrayList;
import java.util.List;

public class ThreadDraw extends Applet {
    private List<MovingShape> threadList = new ArrayList<>();

    @Override
    public void init() {
        int num = 8;
        for (int i = 0; i < num; i++) {
            threadList.add(new MovingShape(this));
        }
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        for (MovingShape ms : threadList) {
            ms.goToDie();
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        ThreadDraw td = new ThreadDraw();
        jFrame.add(td);
        jFrame.setSize(800, 500);
        jFrame.setVisible(true);

        td.init();
        td.start();

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
