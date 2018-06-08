package iframe;

import dao.Dao;
import model.BookInfo;
import model.BookType;
import util.CreatecdIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Vector;

public class BookAddIFrame extends JInternalFrame {

    private JPanel mainPanel;
    private JPanel southPanel = new JPanel();
    private JTextField ISBN;
    private JTextField bookName;
    private JTextField author;
    private JTextField translator;
    private JTextField unitPrice;
    private JComboBox<BookType> bookTypeJComboBox;
    private JComboBox<String> pressBox;
    private JTextField publishingDate;

    public BookAddIFrame() {
        super();
        setTitle("图书信息添加");
        setIconifiable(true);
        setClosable(true);
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        setBounds(100, 100, 596, 260);

        /* mainPanel */
        setMainPanel();

        /* North imageIcon */
        ImageIcon imageIcon = CreatecdIcon.add("bookAdd.jpg");
        JLabel northLabel = new JLabel(imageIcon);
        northLabel.setPreferredSize(new Dimension(400, 80));

        /* bottome panel */
        setsouthPanel();

        /* this.add three part */
        this.add(mainPanel);
        this.add(northLabel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * 主面板, 也是面板中部的各个Lebal和输入框设计.
     */
    private void setMainPanel() {
        GridLayout layout = new GridLayout(0, 4);
        layout.setVgap(5);
        layout.setHgap(5);
        mainPanel = new JPanel(layout);
        mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        /* 第一行 */
        JLabel ISBNLabel = new JLabel("图书编号:");
        ISBN = new JTextField("请输入13位书号", 13);
        ISBN.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String inputISBN = ISBN.getText();
                if (!Dao.selectBookInfo(inputISBN).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "添加书号重复！");
                    ISBN.setText("");
                }
            }
        });
        mainPanel.add(ISBNLabel);
        mainPanel.add(ISBN);

        JLabel bookTypeLabel = new JLabel("类别:");
        bookTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Vector<BookType> bookTypeVector = Dao.selectBookCategory(); //stop here
        bookTypeJComboBox = new JComboBox<BookType>(bookTypeVector);
        mainPanel.add(bookTypeLabel);
        mainPanel.add(bookTypeJComboBox);

        /* 第二行 */
        JLabel bookNameLable = new JLabel("书名:");
        bookName = new JTextField();
        mainPanel.add(bookNameLable);
        mainPanel.add(bookName);

        JLabel authorLabel = new JLabel("作者:");
        authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        author = new JTextField();
        mainPanel.add(authorLabel);
        mainPanel.add(author);

        /* 第三行 */
        JLabel publishingHouseLable = new JLabel("出版社:");
        String[] presses = new String[]{"北京出版社", "天津出版社", "重庆出版社", "人民文学出版社"};
        pressBox = new JComboBox<>(presses);
        mainPanel.add(publishingHouseLable);
        mainPanel.add(pressBox);

        JLabel translatorLabel = new JLabel("译者:");
        translatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        translator = new JTextField();
        mainPanel.add(translatorLabel);
        mainPanel.add(translator);

        JLabel publishingDateLabel = new JLabel("出版日期:");
        publishingDate = new JTextField("2018-05-22");
        mainPanel.add(publishingDateLabel);
        mainPanel.add(publishingDate);

        JLabel unitPriceLabel = new JLabel("单价");
        unitPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        unitPrice = new JTextField();
        mainPanel.add(unitPriceLabel);
        mainPanel.add(unitPrice);

    }

    private void setsouthPanel() {
        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT, 30, 2);
        southPanel.setLayout(flowLayout);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        southPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
//        southPanel.setPreferredSize(new Dimension(200, 80));

        JButton addButton = new JButton("添加");
        JButton closeButton = new JButton("关闭");
        addButton.addActionListener(new ActionListener() {
            private BookInfo bookInfo;
            private String _publicationDate;
            private String _price;
            private String _translatorName;
            private String _authorName;
            private String _bookName;
            private String _isbn;

            @Override
            public void actionPerformed(ActionEvent e) {
                checkValid();  //检查输入不合理的情况
                buildBook();

                boolean success = Dao.insertBook(bookInfo);
                if (success) {
                    JOptionPane.showMessageDialog(null, "添加图书成功！");
                    BookAddIFrame.this.doDefaultCloseAction();
                }
            }

            /**
             * 根据输入构建一本书.
             */
            private void buildBook() {
                bookInfo = new BookInfo();
                bookInfo.setISBN(_isbn);
                bookInfo.setBookName(_bookName);
                bookInfo.setAuthor(_authorName);
                bookInfo.setTranslator(_translatorName);
                bookInfo.setPrice(Double.parseDouble(_price));
                bookInfo.setPublisher((String) pressBox.getSelectedItem());
                bookInfo.setPublicationDate(_publicationDate);

                Object item = bookTypeJComboBox.getSelectedItem();
                if (item == null) return;
                BookType selectedBookType = (BookType) item;
                bookInfo.setBookType(selectedBookType);
            }

            private void checkValid() {
                _isbn = ISBN.getText().trim();
                if (_isbn.length() == 0 || _isbn.length() != 13) {
                    JOptionPane.showMessageDialog(null, "请正确输入ISBN！");
                    return;
                }

                _bookName = bookName.getText().trim();
                if (_bookName.length() == 0) {
                    JOptionPane.showMessageDialog(null, "    请输入有效书名!");
                    return;
                }

                _authorName = author.getText().trim();
                if (_authorName.length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入有效的作者名!");
                    return;
                }

                _translatorName = translator.getText().trim();
                if (_translatorName.length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入有效的翻译者名字！");
                    return;
                }

                _price = unitPrice.getText().trim();
                if (_price.length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入有效的价格！");
                    return;
                }

                _publicationDate = publishingDate.getText().trim();
                if (_publicationDate.length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入有效的出版日期!");
                    return;
                }
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //直接关闭就可以了
                BookAddIFrame.this.doDefaultCloseAction();
            }
        });

        southPanel.add(addButton);
        southPanel.add(closeButton);
    }
}
