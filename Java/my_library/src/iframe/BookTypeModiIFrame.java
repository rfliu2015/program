package iframe;

import JComPz.BookTypeMap;
import dao.Dao;
import model.BookType;
import util.CreatecdIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class BookTypeModiIFrame extends JInternalFrame {
    private JPanel mainPanel;
    private JPanel southPanel;
    private String[] columns = new String[]{"图书类别编号", "图书类别名称", "可借天数", "罚款"};
    private JTextField typeId;
    private JComboBox<BookType> bookTypes;
    private JTextField days;
    private JTextField fine;
    private ComboBoxModel<BookType> model;
    private JTable table;

    public BookTypeModiIFrame() {
        super("图书类型修改");
        setIconifiable(true);
        setClosable(true);
        setBounds(100, 100, 480, 400);
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        /* main panel setting */
        mainPanel = new JPanel();
        setMainPanel();
        this.add(mainPanel);

        /* south panel setting */
        southPanel = new JPanel();
        setSouthPanel();
        this.add(southPanel, BorderLayout.SOUTH);

        /* north image label */
        ImageIcon icon = CreatecdIcon.add("bookTypeAdd.jpg");
        JLabel imageLabel = new JLabel(icon);
        this.add(imageLabel, BorderLayout.NORTH);
    }

    private void setMainPanel() {
        BorderLayout borderLayout = new BorderLayout(0, 5);
        mainPanel.setLayout(borderLayout);
        mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        mainPanel.setPreferredSize(new Dimension(480, 300));

        /* scroll panel */
        table = new JTable(getTableItems(), columns);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        mainPanel.add(scrollPane);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();

                typeId.setText(table.getValueAt(r, 0).toString());

                int id = Integer.parseInt(typeId.getText().trim());
                //这里不能直接bookTypes去setselectedItem
                model = bookTypes.getModel();
                model.setSelectedItem(BookTypeMap.getType(id));

                days.setText(table.getValueAt(r, 2).toString());
                fine.setText(table.getValueAt(r, 3).toString());
            }
        });

        /* 其他信息 */
        JPanel secondPanel = new JPanel();
        secondPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        GridLayout gridLayout = new GridLayout(0, 4);
        gridLayout.setHgap(5);
        gridLayout.setVgap(3);
        secondPanel.setLayout(gridLayout);
        mainPanel.add(secondPanel, BorderLayout.SOUTH);


        JLabel typeIdLabel = new JLabel("类别编号");
        typeIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        typeId = new JTextField();
        secondPanel.add(typeIdLabel);
        secondPanel.add(typeId);

        JLabel typeNamesLabel = new JLabel("类别名称");
        typeNamesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bookTypes = new JComboBox<>(Dao.selectBookCategory());
        secondPanel.add(typeNamesLabel);
        secondPanel.add(bookTypes);

        JLabel daysLabel = new JLabel("可借天数");
        daysLabel.setHorizontalAlignment(SwingConstants.CENTER);
        days = new JTextField();
        secondPanel.add(daysLabel);
        secondPanel.add(days);

        JLabel fineLabel = new JLabel("罚款");
        fineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fine = new JTextField();
        secondPanel.add(fineLabel);
        secondPanel.add(fine);
    }

    private void setSouthPanel() {
        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT, 15, 0);
        southPanel.setLayout(flowLayout);

        JButton addButton = new JButton("修改");
        JButton closeButton = new JButton("关闭");
        southPanel.add(addButton);
        southPanel.add(closeButton);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doDefaultCloseAction();
            }
        });

        addButton.addActionListener(new ActionListener() {
            private BookType bookType;
            private int id;
            private String name;
            private int days_;
            private double fine_;

            @Override
            public void actionPerformed(ActionEvent e) {
                checkValid();
                buildBookType();

                boolean bool = Dao.updateBookType(bookType);
                if (bool) {
                    JOptionPane.showMessageDialog(null, "图书类别修改成功!");

                    DefaultTableModel defaultTableModel = new DefaultTableModel();
                    table.setModel(defaultTableModel);
                    defaultTableModel.setDataVector(getTableItems(), columns);
//                    table.setModel((TableModel) model);
                }
            }

            private void checkValid() {
                String tmpString = typeId.getText().trim();
                if (checkEmpty(tmpString)) return;
                id = Integer.parseInt(tmpString);

                name = model.getSelectedItem().toString();

                tmpString = days.getText().trim();
                if (checkEmpty(tmpString)) return;
                days_ = Integer.parseInt(tmpString);

                tmpString = fine.getText().trim();
                if (checkEmpty(tmpString)) return;
                fine_ = Double.parseDouble(tmpString);
            }

            private void buildBookType() {
                bookType = new BookType();

                bookType.setId(id);
                bookType.setFinePerDay(fine_);
                bookType.setTypeName(name);
                bookType.setDaysAvailable(days_);
            }

            private boolean checkEmpty(String string) {
                return string.length() == 0;
            }
        });
    }

    private Object[][] getTableItems() {
        Vector<BookType> bookTypes = Dao.selectBookCategory();
        Object[][] items = new Object[bookTypes.size()][columns.length];

        for (int i = 0; i < bookTypes.size(); i++) {
            BookType bookType = bookTypes.get(i);
            Object[] item = items[i];

            item[0] = bookType.getId();
            item[1] = bookType.getTypeName();
            item[2] = bookType.getDaysAvailable();
            item[3] = bookType.getFinePerDay();
        }

        return items;
    }
}
