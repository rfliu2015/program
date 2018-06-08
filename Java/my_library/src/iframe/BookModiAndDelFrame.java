package iframe;

import JComPz.BookMap;
import dao.Dao;
import model.BookInfo;
import model.BookType;
import util.CreatecdIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BookModiAndDelFrame extends JInternalFrame {

    private JPanel mainPanel = new JPanel();
    private JPanel bottomePanel = new JPanel();
    private JTable table;
    private JTextField ISBN;
    private JComboBox<BookType> bookTypeJComboBox;
    private JTextField bookName;
    private JTextField author;
    private JTextField publisher;
    private JTextField translator;
    private JTextField publicationDate;
    private JTextField price;
    private String[] columnNames;
    private ComboBoxModel<BookType> bookTypeModel;

    public BookModiAndDelFrame() {
        super("图书信息修改和删除");
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        setIconifiable(true);
        setClosable(true);
        setBounds(80, 100, 620, 406);

        /* set northPanel */
        ImageIcon icon = CreatecdIcon.add("bookmodify.jpg");
        JLabel north = new JLabel(icon);  //本质上是一个设置了icon的label

        /* set mainPanel */
        setMainPanel();

        /* bottomPanel */
        setBottomePanel();

        /* at last */
        this.add(north, BorderLayout.NORTH);
        getContentPane().add(mainPanel);
        getContentPane().add(bottomePanel, BorderLayout.SOUTH);
        System.out.println("I'm running!");

//        setVisible(true);
    }

    /**
     * 中间的主界面设置.
     */
    private void setMainPanel() {
        /* general view pane setting. */
        BorderLayout bdLayout = new BorderLayout();
        mainPanel.setLayout(bdLayout);
        mainPanel.setBounds(100, 100, 593, 406);
        mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
//        mainPanel.setPreferredSize();

        /* scroll pane. */
        JScrollPane scrollPane = new JScrollPane();
        mainPanel.add(scrollPane);

        columnNames = new String[]{"图书编号", "图书类别", "图书名称", "作者", "译者",
                "出版商", "出版日期", "价格"};
        Object[][] results = getBookInfos(Dao.selectBookInfo());
        table = new JTable(results, columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                String isbn_ = table.getValueAt(row, 0).toString().trim();
                String type_ = table.getValueAt(row, 1).toString().trim();
                String name_ = table.getValueAt(row, 2).toString().trim();
                String author_ = table.getValueAt(row, 3).toString().trim();
                String translator_ = table.getValueAt(row, 4).toString().trim();
                String publisher_ = table.getValueAt(row, 5).toString().trim();
                String date_ = table.getValueAt(row, 6).toString().trim();
                String price_ = table.getValueAt(row, 7).toString().trim();

                ISBN.setText(isbn_);
                bookTypeModel.setSelectedItem(BookMap.getType(type_));
                bookName.setText(name_);
                author.setText(author_);
                publisher.setText(publisher_);
                translator.setText(translator_);
                publicationDate.setText(date_);
                price.setText(price_);
            }
        });
        scrollPane.setViewportView(table);

        /* book pane. */
        JPanel bookPane = new JPanel();
        GridLayout layout = new GridLayout(0, 6);
        layout.setVgap(5);  //组件的水平距离
        layout.setHgap(5);
        bookPane.setLayout(layout);
        mainPanel.add(bookPane, BorderLayout.SOUTH);  //一定不可以忘记这一步

        //第一行
        JLabel bookISBNLabel = new JLabel("书       号：");
        JLabel typeNameLabel = new JLabel("类       别：");
        JLabel bookNameLabel = new JLabel("书    名：");
        ISBN = new JTextField("13位书号");
        bookTypeJComboBox = new JComboBox<>(Dao.selectBookCategory());
        bookTypeModel = bookTypeJComboBox.getModel();
        bookName = new JTextField();
        bookISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);
        typeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(bookISBNLabel);
        bookPane.add(ISBN);
        bookPane.add(typeNameLabel);
        bookPane.add(bookTypeJComboBox);
        bookPane.add(bookNameLabel);
        bookPane.add(bookName);

        //第二行
        JLabel authorLabel = new JLabel("作       者：");
        JLabel publisherLabel = new JLabel("出  版  社：");
        JLabel translatorLabel = new JLabel("译    者：");
        author = new JTextField();
        publisher = new JTextField(18);
        translator = new JTextField();
        authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        publisherLabel.setHorizontalAlignment(SwingConstants.CENTER);
        translatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(authorLabel);
        bookPane.add(author);
        bookPane.add(publisherLabel);
        bookPane.add(publisher);
        bookPane.add(translatorLabel);
        bookPane.add(translator);

        //第三行
        JLabel publishDateLabel = new JLabel("出 版 日 期：");
        JLabel priceLabel = new JLabel("单      价：");
        publicationDate = new JTextField();
        price = new JTextField();
        publishDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bookPane.add(publishDateLabel);
        bookPane.add(publicationDate);
        bookPane.add(priceLabel);
        bookPane.add(price);

    }

    private void setBottomePanel() {
        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        layout.setVgap(5);
        layout.setHgap(30);  //设置组件的水平距离
        bottomePanel.setLayout(layout);
        bottomePanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));

        JButton updateButton = new JButton("修改");
        JButton closeButton = new JButton("关闭");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookModiAndDelFrame.this.doDefaultCloseAction();
            }
        });
        updateButton.addActionListener(new ActionListener() {
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

                boolean success = Dao.updateBook(bookInfo);
                if (success) {
                    JOptionPane.showMessageDialog(null, "修改图书成功！");

                    /* 更新表格内容. */
                    DefaultTableModel model = new DefaultTableModel();
                    table.setModel(model);
                    model.setDataVector(getBookInfos(Dao.selectBookInfo()), columnNames);
                }
            }

            /** 根据输入构建一本书. */
            private void buildBook() {
                bookInfo = new BookInfo();
                bookInfo.setISBN(_isbn);
                bookInfo.setBookName(_bookName);
                bookInfo.setAuthor(_authorName);
                bookInfo.setTranslator(_translatorName);
                bookInfo.setPrice(Double.parseDouble(_price));
                bookInfo.setPublisher(publisher.getText().trim());
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

                _price = price.getText().trim();
                if (_price.length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入有效的价格！");
                    return;
                }

                _publicationDate = publicationDate.getText().trim();
                if (_publicationDate.length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入有效的出版日期!");
                    return;
                }
            }
        });
        bottomePanel.add(updateButton);
        bottomePanel.add(closeButton);
    }

    /**
     * 将图书的列表转为由每个图书的信息组成的二位数组
     *
     * @param list
     * @return
     */
    private Object[][] getBookInfos(List<BookInfo> list) {
        Object[][] results = new Object[list.size()][columnNames.length];

        for (int i = 0; i < list.size(); i++) {
            BookInfo bookInfo = list.get(i);
            results[i][0] = bookInfo.getISBN();
            results[i][1] = bookInfo.getBookType().getTypeName();
            results[i][2] = bookInfo.getBookName();
            results[i][3] = bookInfo.getAuthor();
            results[i][4] = bookInfo.getTranslator();
            results[i][5] = bookInfo.getPublisher();
            results[i][6] = bookInfo.getPublicationDate();
            results[i][7] = bookInfo.getPrice();
        }

        return results;
    }
}
