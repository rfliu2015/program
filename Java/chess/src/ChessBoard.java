import sun.plugin.dom.core.CoreConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChessBoard extends JPanel {
    /* 棋盘本身的属性 */
    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int GRID_SPAN = 35;
    private static final int MARGIN = 20;

    /* 棋盘里的棋子 */
    private List<Point> chessList = new ArrayList<>();
    private boolean isBlack = true;  //默认黑棋先下
    private int xIndex;  //最后下的棋子的位置
    private int yIndex;


    private boolean gameOver = false;

    /**
     * 构造函数.
     */
    public ChessBoard() {
        /* 外观设置. */
        setBackground(Color.ORANGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameOver) {
                    return;
                }
                //这里有四舍五入
                int x = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
                int y = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;

                if (x < 0 || x > COLS || y < 0 || y > ROWS) {
                    return; //do nothing
                }

                if (!findChess(x, y)) {
                    Point p = new Point(x, y, isBlack ? Color.BLACK : Color.WHITE);
                    chessList.add(p);

                    xIndex = x;
                    yIndex = y;

                    repaint();
                }

                if (isWin()) {
                    gameOver = true;

                    System.out.println("Game Over!");
                } else {
                    isBlack = !isBlack;
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /**
     * 清空棋盘, 重新开始.
     */
    public void clear() {
        chessList.clear();
        isBlack = true;
        gameOver = false;
        repaint();
    }

    /**
     * 悔棋
     */
    public void back() {
        if (chessList.size() < 1) {
            return;
        }

        Point last = chessList.remove(chessList.size() - 1);
        isBlack = !isBlack;
        xIndex = last.getX();
        yIndex = last.getY();
        gameOver = false;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color originColor = g.getColor();
        //画网格
        for (int i = 0; i <= ROWS; ++i) {
            g.drawLine(MARGIN, MARGIN + i * GRID_SPAN, MARGIN + COLS * GRID_SPAN, MARGIN + i * GRID_SPAN);
        }
        for (int j = 0; j <= COLS; j++) {
            g.drawLine(MARGIN + j * GRID_SPAN, MARGIN, MARGIN + j * GRID_SPAN, MARGIN + ROWS * GRID_SPAN);
        }

        /*
        画棋子
         */
        for (Point p : chessList) {
            int xPos = p.getX() * GRID_SPAN + MARGIN;
            int yPos = p.getY() * GRID_SPAN + MARGIN;

            g.setColor(p.getColor());
            g.fillOval(xPos - Point.DIAMETER / 2, yPos - Point.DIAMETER / 2, Point.DIAMETER, Point.DIAMETER);
        }
        //最后一个棋子描红框
        if (chessList.size() != 0) {
            int xLastPos = chessList.get(chessList.size() - 1).getX() * GRID_SPAN + MARGIN;
            int yLastPos = chessList.get(chessList.size() - 1).getY() * GRID_SPAN + MARGIN;
            g.setColor(Color.RED);
            g.drawRect(xLastPos - Point.DIAMETER / 2, yLastPos - Point.DIAMETER / 2, Point.DIAMETER, Point.DIAMETER);
        }

        g.setColor(originColor);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MARGIN * 2 + GRID_SPAN * COLS, MARGIN * 2 + GRID_SPAN * ROWS);
    }

    /**
     * 判断对局是否结束.
     * @return
     */
    private boolean isWin() {
        if (chessList.size() < 5) {
            return false;
        }

        Color c = (isBlack ? Color.BLACK : Color.WHITE);
        int contiueCount = 1;
        /*
        向左查找.
         */
        for (int x = xIndex - 1, y = yIndex; x >= 0; x--) {
            if (getChess(x, y, c) != null) {
                contiueCount++;
            } else break;
        }
        for (int x = xIndex + 1, y = yIndex; x <= COLS; x++) {
            if (getChess(x, y, c) != null) {
                contiueCount++;
            } else break;
        }
        if (contiueCount >= 5) {
            return true;
        } else contiueCount = 1;
        /*
        上下查找.
         */
        for (int x = xIndex, y = yIndex - 1; y >= 0; y--) {
            if (getChess(x, y, c) != null) {
                contiueCount++;
            } else break;
        }
        for (int x = xIndex, y = yIndex + 1; y <= ROWS; y++) {
            if (getChess(x, y, c) != null) {
                contiueCount++;
            } else break;
        }
        if (contiueCount >= 5) {
            return true;
        } else contiueCount = 1;
        /*
        左下-右上查找.
         */
        for (int x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {
            if (findChess(x, y) && getChess(x, y, c) != null) {
                contiueCount++;
            } else break;
        }
        for (int x = xIndex + 1, y = yIndex + 1; x <= COLS && y <= ROWS; x++, y++) {
            if (findChess(x, y) && getChess(x, y, c) != null) {
                contiueCount++;
            } else break;
        }
        if (contiueCount >= 5) {
            return true;
        } else contiueCount = 1;
        /*
        左上右下查找
         */
        for (int x = xIndex + 1, y = yIndex - 1; x <= COLS && y >= 0; x++, y--) {
            if (findChess(x, y) && getChess(x, y, c) != null) {
                contiueCount++;
            } else break;
        }
        for (int x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= ROWS; x--, y++) {
            if (findChess(x, y) && getChess(x, y, c) != null) {
                contiueCount++;
            } else break;
        }
        if (contiueCount >= 5) {
            return true;
        }

        return false;
    }


    private boolean findChess(int x, int y) {
        for (Point p : chessList) {
            if (p != null && p.getY() == y && p.getX() == x)
                return true;
        }

        return false;
    }

    private Point getChess(int x, int y, final Color c) {
        for (Point p : chessList) {
            if (p != null && p.getX() == x && p.getY() == y && p.getColor() == c) {
                return p;
            }
        }
        return null;
    }

    private void printChessBoard() {
        for (Point p : chessList) {
            System.out.print(p + " ");
        }
        System.out.println();
    }
}