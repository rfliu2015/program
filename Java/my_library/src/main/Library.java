package main;

import iframe.BookLoginIFrame;
import util.CreatecdIcon;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Library extends JFrame {
    private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();

    public Library() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("图书馆管理系统");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setSize(800, 600);
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        JMenuBar menuBar = creatMenuBar();
        setJMenuBar(menuBar);  //setJMenuBar not setMenuBar
        JToolBar toolBar = createToolBar();
        getContentPane().add(toolBar, BorderLayout.NORTH);

        final JLabel label = new JLabel();
        label.setIcon(null);
        label.setBounds(0, 0, 0, 0);
        DESKTOP_PANE.add(label);
        DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = e.getComponent().getSize();
                label.setSize(size);
                label.setText("<html><img width=" + size.width + " height=" + size.height
                        + " src='" + this.getClass().getResource("/backImg.jpg") + "'></html>");
            }
        });

        getContentPane().add(DESKTOP_PANE);
    }

    private JMenuBar creatMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu baseMenu, bookOrderMenu, borrowManagerMenu, sysManagerMenu;

        /* baseMenu "基础数据维护"*/
        baseMenu = new JMenu();
        baseMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));

        JMenu readerInfoManagerMItem, bookTypeManagerMItem, bookInfoManagerMItem;
        readerInfoManagerMItem = new JMenu("读者信息管理");
        bookTypeManagerMItem = new JMenu("图书类别管理");
        bookInfoManagerMItem = new JMenu("图书信息管理");
        baseMenu.add(readerInfoManagerMItem);
        baseMenu.add(bookTypeManagerMItem);
        baseMenu.add(bookInfoManagerMItem);

        bookInfoManagerMItem.add(MenuActions.BOOK_ADD);
        bookInfoManagerMItem.add(MenuActions.BOOK_MODI_AND_DEL);

        /* bookOrderMenu, "新书订购管理" */
        bookOrderMenu = new JMenu();  //这里string与icon矛盾
        bookOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));

        /* borrowManagerMenu "借阅管理"*/
        borrowManagerMenu = new JMenu();
        borrowManagerMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));

        /* sysManagerMenu "系统维护"*/
        sysManagerMenu = new JMenu();
        sysManagerMenu.setIcon(CreatecdIcon.add("jcwhcd.jpg"));

        JMenu userManager = new JMenu("用户管理");
        sysManagerMenu.add(userManager);

        /* At last add these 4 menu */
        menuBar.add(baseMenu);
        menuBar.add(bookOrderMenu);
        menuBar.add(borrowManagerMenu);
        menuBar.add(sysManagerMenu);


        return menuBar;
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));

        /* bookAddButton */
        JButton bookAddButton = new JButton(MenuActions.BOOK_ADD);
        ImageIcon icon = new ImageIcon(Library.class.getResource("/bookAddtb.jpg"));
        bookAddButton.setIcon(icon);
        bookAddButton.setHideActionText(true);
        toolBar.add(bookAddButton);

        /* bookModiAndDelButton */
        JButton bookModiAndDelButton = new JButton(MenuActions.BOOK_MODI_AND_DEL);
        bookModiAndDelButton.setIcon(CreatecdIcon.add("bookModiAndDeltb.jpg"));
        bookModiAndDelButton.setHideActionText(true);
        toolBar.add(bookModiAndDelButton);

        /* bookTypeAddButton */
        JButton bookTypeAddButton = new JButton();
        ;//action
        bookTypeAddButton.setIcon(CreatecdIcon.add("bookTypeAddtb.jpg"));
        bookTypeAddButton.setHideActionText(true);
        toolBar.add(bookTypeAddButton);

        /* bookBorrowButton */
        JButton bookBorrowButton = new JButton();
        ;//action
        bookBorrowButton.setIcon(new ImageIcon(
                this.getClass().getResource("/bookBorrowtb.jpg")));
        bookBorrowButton.setHideActionText(true);
        toolBar.add(bookBorrowButton);

        /* bookOrderButton */
        JButton bookOrderButton = new JButton();
        bookOrderButton.setIcon(CreatecdIcon.add("bookOrdertb.jpg"));
        bookOrderButton.setHideActionText(true);
        toolBar.add(bookOrderButton);

        /* bookCheckButton */
        JButton bookCheckButton = new JButton();
        ;//action
        bookCheckButton.setIcon(CreatecdIcon.add("newBookChecktb.jpg"));
        bookCheckButton.setHideActionText(true);
        toolBar.add(bookCheckButton);

        /* readerAddButton */
        JButton readerAddButton = new JButton();
        ;//action
        readerAddButton.setIcon(new ImageIcon(
                getClass().getResource("/readerAddtb.jpg")));
        readerAddButton.setHideActionText(true);
        toolBar.add(readerAddButton);

        /* readerModiAndDelButton */
        JButton readerModiAndDelButton = new JButton();
        ;//action
        readerModiAndDelButton.setIcon(new ImageIcon(
                getClass().getResource("/readerModiAndDeltb.jpg")));
        readerModiAndDelButton.setHideActionText(true);
        toolBar.add(readerModiAndDelButton);

        /* exitButton */
        JButton exitButton = new JButton(MenuActions.EXIT);
        exitButton.setIcon(CreatecdIcon.add("exittb.jpg"));
        exitButton.setHideActionText(true);
        toolBar.add(exitButton);

        return toolBar;
    }

    /**
     * main方法启动程序.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new BookLoginIFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addIFrame(JInternalFrame iframe) {
        DESKTOP_PANE.add(iframe);
        iframe.setVisible(true);
    }
}
