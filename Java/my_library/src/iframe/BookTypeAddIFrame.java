package iframe;

import dao.Dao;
import model.BookType;
import util.CreatecdIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Vector;

public class BookTypeAddIFrame extends JInternalFrame {

    private JPanel southPanel;
    private JPanel mainPanel;
    private JTextField name;
    private JTextField days;
    private JTextField fine;

    public BookTypeAddIFrame() {
        super("图书类别添加");
        setBounds(100, 100, 400, 300);
        setIconifiable(true);
        setClosable(true);
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        /* normal image label */
        ImageIcon icon = CreatecdIcon.add("bookAdd.jpg");
        JLabel imageLabel = new JLabel(icon);
        this.add(imageLabel, BorderLayout.NORTH);

        /* main mainPanel setting */
        mainPanel = new JPanel();
        setMainPanel();
        this.add(mainPanel);

        /* two buttons in south */
        southPanel = new JPanel();
        setSouthPanel();
        this.add(southPanel, BorderLayout.SOUTH);

    }

    private void setMainPanel() {
        GridLayout gridLayout = new GridLayout(0, 2);
        mainPanel.setLayout(gridLayout);
        gridLayout.setVgap(10);
        mainPanel.setBorder(new EmptyBorder(20, 10, 10, 10));

        /* 第一行 */
        JLabel nameLabel = new JLabel("图书类别名称");
        name = new JTextField();
        mainPanel.add(nameLabel);
        mainPanel.add(name);
        //检查输入的名称不重复
        name.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String name_ = name.getText().trim();

                if (typeExist(name_)) {  //如果类型已经存在
                    JOptionPane.showMessageDialog(null, "图书类别已经存在！");
                    name.setText("");
                    return;
                }
            }

            private boolean typeExist(String type) {
                Vector<BookType> bookTypes = Dao.selectBookCategory();
                for (BookType bookType : bookTypes) {
                    if (type.equals(bookType.getTypeName()))
                        return true;
                }

                return false;
            }
        });

        /* 第二行 */
        JLabel daysLabel = new JLabel("可 借 天 数");
        days = new JTextField(13);
        days.setText("30");
        mainPanel.add(daysLabel);
        mainPanel.add(days);

        //第三行
        JLabel fineLabel = new JLabel("迟还一天罚款");
        fine = new JTextField(13);
        fine.setText("单位是角");
        mainPanel.add(fineLabel);
        mainPanel.add(fine);
    }

    private void setSouthPanel() {
        southPanel = new JPanel();

        JButton saveButton = new JButton("保存");
        JButton closeButton = new JButton("关闭");
        saveButton.addActionListener(new SaveBookTypeActionListener());
        closeButton.addActionListener(e -> BookTypeAddIFrame.this.doDefaultCloseAction());

        southPanel.add(saveButton);
        southPanel.add(closeButton);
    }

    /**
     * 执行保存操作时候的动作
     */
    private class SaveBookTypeActionListener implements ActionListener {
        private BookType bookType;
        private String cName;
        private int cDays;
        private double cFine;

        @Override
        public void actionPerformed(ActionEvent e) {
            checkValid();
            buildBookType();

            boolean bool = Dao.insertBookType(bookType);
            if (bool) {
                JOptionPane.showMessageDialog(null, "图书类别添加成功！");
                doDefaultCloseAction();
            }
        }

        private void checkValid() {
            cName = name.getText().trim();
            if (checkEmpty(cName)) return;

            String days_ = days.getText().trim();
            if (checkEmpty(days_)) return;
            cDays = Integer.parseInt(days_);

            String fine_ = fine.getText().trim();
            if (checkEmpty(fine_)) return;
            cFine = Double.parseDouble(fine_);
        }

        private void buildBookType() {
            bookType = new BookType();
            bookType.setTypeName(cName);
            bookType.setDaysAvailable(cDays);
            bookType.setFinePerDay(cFine);
            bookType.setId(Dao.getBookTypeMaxId() + 1);
        }

        boolean checkEmpty(String string) {
            return string.length() == 0;
        }
    }
}
