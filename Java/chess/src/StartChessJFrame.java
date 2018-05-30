import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartChessJFrame extends JFrame {
    private JMenuItem startMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem backMenuItem;
    private JButton startBtn;
    private JButton exitBtn;
    private JButton backBtn;
    private ChessBoard chessBoard = new ChessBoard();

    public StartChessJFrame() {
        setTitle("单机版五子棋");

        MyItemListener ls = new MyItemListener();

        /* 添加menu_bar */
        JMenu sysMenu = new JMenu("系统");

        startMenuItem = new JMenuItem("开始");
        exitMenuItem = new JMenuItem("结束");
        backMenuItem = new JMenuItem("悔棋");
        startMenuItem.addActionListener(ls);
        exitMenuItem.addActionListener(ls);
        backMenuItem.addActionListener(ls);
        sysMenu.add(startMenuItem);
        sysMenu.add(exitMenuItem);
        sysMenu.add(backMenuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(sysMenu);
        menuBar.setSize(80, 15);
        setJMenuBar(menuBar);

        /* 添加tool_bar */
        JPanel toolBar = new JPanel();
        startBtn = new JButton("开始");
        exitBtn = new JButton("结束");
        backBtn = new JButton("悔棋");
        startBtn.addActionListener(ls);
        exitBtn.addActionListener(ls);
        backBtn.addActionListener(ls);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar.add(startBtn);
        toolBar.add(exitBtn);
        toolBar.add(backBtn);
        add(toolBar, BorderLayout.SOUTH);

        /* 添加棋盘 */
        add(chessBoard, BorderLayout.CENTER);

        /* 大小等属性设置 */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 650);
        pack();
    }

    public static void main(String[] args) {
        StartChessJFrame f = new StartChessJFrame();
        f.setVisible(true);
    }

    /**
     * 为button, menuItem添加统一管理的监听器.
     */
    private class MyItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if (src == startBtn || src == startMenuItem) {
                System.out.println("开始...");
                chessBoard.clear();
            } else if (src == backBtn || src == backMenuItem) {
                System.out.println("悔棋...");
                chessBoard.back();
            } else if (src == exitBtn || src == exitMenuItem) {
                System.out.println("退出...");

                System.exit(0);
            }

        }
    }
}

