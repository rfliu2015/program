package iframe;

import main.Library;
import model.Operator;
import util.CreatecdIcon;
import dao.Dao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookLoginIFrame extends JFrame {
    private final JButton login = new JButton("登录");
    private final JTextField username = new JTextField(10);
    private final JPasswordField password = new JPasswordField(20);

    public BookLoginIFrame() {
        super();

        /* frame的设置 */
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        setTitle("图书馆管理系统登录");
        setSize(285, 194);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /* frame的位置设定 */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        setMainPanel();

        setVisible(true);
        setResizable(false);                                        //设置窗体不可改变大小
    }

    public static void main(String[] args) {
        new BookLoginIFrame();
    }

    private class BookLoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Operator user = Dao.check(username.getText(), new String(password.getPassword()));
            if (user.getName() != null) {
                try {
                    Library library = new Library();
                    library.setVisible(true);
                    BookLoginIFrame.this.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {//登录失败
                JOptionPane.showMessageDialog(null, "登陆失败！");
                username.setText("");
                password.setText("");
            }
        }
    }

    private class BookResetAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            username.setText("");
            password.setText("");
        }
    }

    private void setMainPanel() {
        /* mainPanel设置 */
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        /* mainPanel的north部分是一个Jlabel */
        JLabel imageLabel = new JLabel();
        ImageIcon loginIcon = CreatecdIcon.add("login.jpg");
        imageLabel.setIcon(loginIcon);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.GREEN);
        imageLabel.setPreferredSize(new Dimension(260, 60));
        mainPanel.add(imageLabel, BorderLayout.NORTH);

        /* mainPanel的center部分是一个Jpanel */
        JPanel centrePanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 2);
        gridLayout.setHgap(5);
        gridLayout.setVgap(20);
        centrePanel.setLayout(gridLayout);

        JLabel usernameLabel = new JLabel("用 户 名:");
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setPreferredSize(new Dimension(0, 0));
        usernameLabel.setMinimumSize(new Dimension(0, 0));
        centrePanel.add(usernameLabel);
        username.setPreferredSize(new Dimension(0, 0));
        centrePanel.add(username);

        JLabel pwdLabel = new JLabel("密    码:");
        pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        password.setEchoChar('*');
        //password.setDocument(new MyDocument(6));
        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {  //回车键的操作
                if (e.getKeyCode() == 10) {
                    login.doClick();
                }
            }
        });

        centrePanel.add(pwdLabel);
        centrePanel.add(password);

        mainPanel.add(centrePanel);

        /* mainPanel的sourthPanel是两个button */
        JPanel southPanel = new JPanel();
        login.addActionListener(new BookLoginAction());
        JButton reset = new JButton("重置");
        reset.addActionListener(new BookResetAction());
        southPanel.add(login);
        southPanel.add(reset);

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        //over
        add(mainPanel);
    }
}

