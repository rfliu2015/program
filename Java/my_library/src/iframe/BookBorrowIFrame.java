package iframe;

import dao.Dao;
import main.Library;
import main.LoginFrame;
import model.BookInfo;
import model.Borrow;
import model.Reader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BookBorrowIFrame extends JInternalFrame {
    private JPanel southPanel;
    private JTextField time;
    private JTextField readerNum;
    private JTextField bookNum;
    private JTextField operator;
    private JPanel borrowPanel;

    private String[] columns = new String[]{"书籍编号", "借书日期", "应还日期", "读者编号"};

    public BookBorrowIFrame() {
        setBounds(100, 100, 460, 340);
        BorderLayout borderLayout = new BorderLayout(20, 8);
        setLayout(borderLayout);
        setIconifiable(true);
        setClosable(true);

        /* borrow panel setting */
        borrowPanel = new JPanel();
        setBorrowPanel();
        this.add(borrowPanel, BorderLayout.NORTH);

        /* scroll panel setting */
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        this.add(scrollPane);

        /* south panel setting */
        southPanel = new JPanel();
        setSouthPanel();
        this.add(southPanel);
    }

    private void setBorrowPanel() {
        GridLayout borrowLayout = new GridLayout(0, 4);
        borrowPanel.setLayout(borrowLayout);
        borrowLayout.setHgap(10);
        borrowLayout.setVgap(5);
        borrowPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        //第一行
        JLabel readerNumLabel = new JLabel("读者编号");
        readerNum = new JTextField();
        borrowPanel.add(readerNumLabel);
        borrowPanel.add(readerNum);

        JLabel bookNumLabel = new JLabel("书籍编号");
        bookNum = new JTextField();
        borrowPanel.add(bookNumLabel);
        borrowPanel.add(bookNum);

        //第二行
        JLabel readerNameLabel = new JLabel("读者姓名");
        JTextField readerName = new JTextField();
        readerName.setEditable(false);
        borrowPanel.add(readerNameLabel);
        borrowPanel.add(readerName);

        JLabel bookNameLabel = new JLabel("书籍名称");
        JTextField bookName = new JTextField();
        bookName.setEditable(false);
        borrowPanel.add(bookNameLabel);
        borrowPanel.add(bookName);

        //第三行
        JLabel numsLabel = new JLabel("可借数量");
        JTextField nums = new JTextField();
        nums.setEditable(false);
        borrowPanel.add(numsLabel);
        borrowPanel.add(nums);

        JLabel bookTypeLabel = new JLabel("书籍类别");
        JTextField bookType = new JTextField();
        bookType.setEditable(false);
        borrowPanel.add(bookTypeLabel);
        borrowPanel.add(bookType);

        //第四行
        JLabel depositLabel = new JLabel("押  金");
        JTextField deposit = new JTextField();
        deposit.setEditable(false);
        borrowPanel.add(depositLabel);
        borrowPanel.add(deposit);

        JLabel priceLabel = new JLabel("书籍价格");
        JTextField price = new JTextField();
        price.setEditable(false);
        borrowPanel.add(priceLabel);
        borrowPanel.add(price);

        //finally
        readerNum.addFocusListener(new FocusAdapter() {
            private Reader _reader;
            private String isbn;

            @Override
            public void focusLost(FocusEvent e) {
                isbn = readerNum.getText().trim();
                if (isbn.length() == 0) return;

                _reader = getReader(isbn);

                if (_reader != null) {
                    readerName.setText(_reader.getName());
                    nums.setText(Integer.toString(_reader.getMaxNum()));
                    deposit.setText(Double.toString(_reader.getKeepMoney()));
                } else {
                    JOptionPane.showMessageDialog(null, "读者不存在!");

                    readerNum.setText("");
                }
            }

            private Reader getReader(String isbn) {
                java.util.List<Reader> list = Dao.selectReader();

                for (Reader reader_ : list) {
                    if (reader_.getNumber().trim().equals(isbn))
                        return reader_;
                }
                return null;
            }
        });
        bookNum.addFocusListener(new FocusAdapter() {
            private BookInfo _bookInfo;
            private String isbn;

            @Override
            public void focusLost(FocusEvent e) {
                isbn = bookNum.getText().trim();
                if (isbn.length() == 0) return;

                _bookInfo = Dao.selectBookInfo(isbn);

                if (_bookInfo != null) {
                    bookName.setText(_bookInfo.getBookName());
                    bookType.setText(_bookInfo.getBookType().getTypeName());
                    price.setText(Double.toString(_bookInfo.getPrice()));
                } else {
                    JOptionPane.showMessageDialog(null, "书籍不存在！");
                    bookNum.setText("");
                }
            }
        });
    }

    private void setSouthPanel() {
        BorderLayout southLayout = new BorderLayout();
        southPanel.setLayout(southLayout);

        /* operation panel */
        GridLayout operationLayout = new GridLayout(0, 2);
        JPanel operationPanel = new JPanel(operationLayout);
        southPanel.add(operationPanel);

        JLabel timeLabel = new JLabel("当前时间");
        time = new JTextField();
        time.setEditable(false);
        time.setFocusable(false);
        time.addActionListener(new TimeActionListener());
        operationPanel.add(timeLabel);
        operationPanel.add(time);

        JLabel operatorLabel = new JLabel("操作员");
        operator = new JTextField(LoginFrame.getUser().getName());
        operator.setEditable(false);
        operator.setFocusable(false);
        operationPanel.add(operatorLabel);
        operationPanel.add(operator);

        /* button layout setting */
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        JPanel buttonPanel = new JPanel(flowLayout);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton lendButton = new JButton("借出当前图书");
        buttonPanel.add(lendButton);
    }

    /**
     * 设置TextField当前时间
     */
    private class TimeActionListener implements ActionListener {
        private Timer timer;

        public TimeActionListener() {
            timer = new Timer(1000, this);
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            time.setText("" + java.time.LocalDate.now() + " " + java.time.LocalTime.now());
        }
    }

    private Object[][] getTableItems() {
        java.util.List<Borrow> borrows = Dao.selectBorrow();
        Object[][] items = new Object[borrows.size()][columns.length];

        for (int i = 0; i < borrows.size(); i++) {
            Borrow borrow = borrows.get(i);
            Object[] item = items[i];

            item[0] = borrow.getBookInfo().getISBN();
//            item[1]=borrow.ge
        }
    }
}
