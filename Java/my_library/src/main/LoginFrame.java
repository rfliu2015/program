package main;

import model.Operator;
import util.CreatecdIcon;
import dao.Dao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame {
    private JButton loginButton;
    private JTextField username;
    private JPasswordField password;

    private JPanel mainPanel;
    private JPanel southPanel;

    public LoginFrame() {
        super();

        /* frame的设置 */
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        setTitle("图书馆管理系统登录");
        setSize(279, 194);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);                                        //设置窗体不可改变大小

        /* frame的位置设定 */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        /* northPanel setting */
        ImageIcon loginIcon = CreatecdIcon.add("login.jpg");
        JLabel imageLabel = new JLabel(loginIcon);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.GREEN);
        imageLabel.setPreferredSize(new Dimension(279, 60));
        this.add(imageLabel, BorderLayout.NORTH);

        /* mainPanel setting */
        setMainPanel();
        this.add(mainPanel);

        /* southPanel setting */
        setSouthPanel();
        this.add(southPanel, BorderLayout.SOUTH);

        /* at last */
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }

    private class BookLoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Operator user = Dao.check(username.getText(), new String(password.getPassword()));
            if (user != null && user.getName() != null) {
                try {
                    Library library = new Library();
                    library.setVisible(true);
                    LoginFrame.this.setVisible(false);
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
        mainPanel = new JPanel(new BorderLayout());
        GridLayout gridLayout = new GridLayout(2, 2);
        gridLayout.setHgap(5);
        gridLayout.setVgap(20);
        mainPanel.setLayout(gridLayout);
        mainPanel.setBorder(new EmptyBorder(0, 0, 5, 5));

        JLabel usernameLabel = new JLabel("用 户 名:");
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        username = new JTextField(10);
        mainPanel.add(usernameLabel);
        mainPanel.add(username);

        JLabel pwdLabel = new JLabel("密      码:");
        pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        password = new JPasswordField(10);
        password.setEchoChar('*');
        //password.setDocument(new MyDocument(6));
        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {  //回车键的操作
                if (e.getKeyCode() == 10) {
                    loginButton.doClick();
                }
            }
        });

        mainPanel.add(pwdLabel);
        mainPanel.add(password);
    }

    private void setSouthPanel() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 20, 5);
        southPanel = new JPanel(layout);
        southPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));

        loginButton = new JButton("登录");
        loginButton.addActionListener(new BookLoginAction());

        JButton reset = new JButton("重置");
        reset.addActionListener(new BookResetAction());

        southPanel.add(loginButton);
        southPanel.add(reset);
    }
}

