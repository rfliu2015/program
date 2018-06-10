package iframe;

import JComPz.ReaderMap;
import dao.Dao;
import model.Reader;
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

public class ReaderModiAndDelIFrame extends JInternalFrame {
    JPanel mainPanel;
    JPanel southPanel;
    private String[] columns = new String[]{"姓名", "性别", "年龄", "证件号码", "有效日期", "最大借书量", "联系方式",
            "押金", "证件类型", "职业", "ISBN", "创建时间"};
    private JTextField name;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField age;
    private JTextField occupation;
    private JComboBox<String> cardComboBox;
    private JTextField cardNum;
    private JTextField startDate;
    private JTextField maxNum;
    private JTextField validDate;
    private JTextField tel;
    private JTextField deposit;
    private JTextField readerNum;
    private JTable table;

    private Object[][] getTableItems(List<Reader> readerList) {
        Object[][] items = new Object[readerList.size()][columns.length];

        for (int i = 0; i < items.length; i++) {
            Reader reader = readerList.get(i);
            items[i][0] = reader.getName();
            items[i][1] = reader.getSex();
            items[i][2] = reader.getAge();
            items[i][3] = reader.getIdentityCard();
            items[i][4] = reader.getValidDate();
            items[i][5] = reader.getMaxNum();
            items[i][6] = reader.getTelNumber();
            items[i][7] = reader.getKeepMoney();
            items[i][8] = reader.getIdentityCardType();
            items[i][9] = reader.getOccupation();
            items[i][10] = reader.getNumber();
            items[i][11] = reader.getStartDate();
        }

        return items;
    }

    public ReaderModiAndDelIFrame() {
        super("读者信息修改与删除");
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        setIconifiable(true);
        setClosable(true);
        setBounds(80, 40, 600, 480);

        /* main panel setting */
        mainPanel = new JPanel();
        setMainPanel();
        this.add(mainPanel);

        /* north panel setting */
        ImageIcon icon = CreatecdIcon.add("readerModiAndDel.jpg");
        JLabel imageLabel = new JLabel(icon);
        this.add(imageLabel, BorderLayout.NORTH);

        /* south panel setting */
        southPanel = new JPanel();
        setSouthPanel();
        this.add(southPanel, BorderLayout.SOUTH);

    }

    private void setMainPanel() {
        mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        mainPanel.setBounds(100, 120, 590, 460);
        BorderLayout borderLayout = new BorderLayout();
        mainPanel.setLayout(borderLayout);

        /* scroll panel setting */
        table = new JTable(getTableItems(Dao.selectReader()), columns);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mainPanel.add(scrollPane);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();

                /* read from table */
                String _name = table.getValueAt(r, 0).toString().trim();
                String _sex = table.getValueAt(r, 1).toString().trim();
                String _age = table.getValueAt(r, 2).toString().trim();
                String _cardNum = table.getValueAt(r, 3).toString().trim();
                String _date = table.getValueAt(r, 4).toString().trim();
                String _maxNum = table.getValueAt(r, 5).toString().trim();
                String _tel = table.getValueAt(r, 6).toString().trim();
                String _keepMoney = table.getValueAt(r, 7).toString().trim();
                String _cardType = table.getValueAt(r, 8).toString().trim();
                String _job = table.getValueAt(r, 9).toString().trim();
                String _number = table.getValueAt(r, 10).toString().trim();
                String _startDate = table.getValueAt(r, 11).toString().trim();

                /* write to textfield */
                name.setText(_name);
                JRadioButton button = _sex.equals("男") ? maleButton : femaleButton;
                JRadioButton another = _sex.equals("男") ? femaleButton : maleButton;
                button.setSelected(true);
                another.setSelected(false);
                age.setText(_age);
                cardNum.setText(_cardNum);
                validDate.setText(_date);
                maxNum.setText(_maxNum);
                tel.setText(_tel);
                deposit.setText(_keepMoney);
                cardComboBox.setSelectedItem(_cardType);
                occupation.setText(_job);
                readerNum.setText(_number);
                startDate.setText(_startDate);
            }
        });

        /* reader panel setting */
        GridLayout gridLayout = new GridLayout(0, 4);
        JPanel readerPanel = new JPanel(gridLayout);
        readerPanel.setPreferredSize(new Dimension(590, 170));
        readerPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        mainPanel.add(readerPanel, BorderLayout.SOUTH);

        //第一行
        JLabel nameLabel = new JLabel("姓    名");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        name = new JTextField(13);
        readerPanel.add(nameLabel);
        readerPanel.add(name);

        JLabel sexLabel = new JLabel("性   别");
        sexLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel buttonPanel = new JPanel();
        maleButton = new JRadioButton("男");
        femaleButton = new JRadioButton("女");
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);
        buttonPanel.add(maleButton);
        buttonPanel.add(femaleButton);
        maleButton.setSelected(true);
        readerPanel.add(sexLabel);
        readerPanel.add(buttonPanel);

        //第二行
        JLabel ageLabel = new JLabel("年    龄");
        ageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        age = new JTextField(13);
        readerPanel.add(ageLabel);
        readerPanel.add(age);

        JLabel occupationLabel = new JLabel("职    业");
        occupationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        occupation = new JTextField(13);
        readerPanel.add(occupationLabel);
        readerPanel.add(occupation);

        //第三行
        JLabel cardTypeLabel = new JLabel("有效证件");
        cardTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardComboBox = new JComboBox<>(ReaderMap.getAllCardTypes());
        readerPanel.add(cardTypeLabel);
        readerPanel.add(cardComboBox);

        JLabel cardNumLabel = new JLabel("证件号码");
        cardNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardNum = new JTextField(13);
        readerPanel.add(cardNumLabel);
        readerPanel.add(cardNum);

        //第四行
        JLabel startDateLabel = new JLabel("办证日期");
        startDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startDate = new JTextField(13);
        readerPanel.add(startDateLabel);
        readerPanel.add(startDate);

        JLabel maxNumLabel = new JLabel("最大借书量");
        maxNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        maxNum = new JTextField(13);
        readerPanel.add(maxNumLabel);
        readerPanel.add(maxNum);

        //第五行
        JLabel validDateLabel = new JLabel("会员有效日期");
        validDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        validDate = new JTextField();
        readerPanel.add(validDateLabel);
        readerPanel.add(validDate);

        JLabel telLabel = new JLabel("电    话");
        telLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tel = new JTextField(13);
        readerPanel.add(telLabel);
        readerPanel.add(tel);

        //第六行
        JLabel depositLabel = new JLabel("押    金");
        depositLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deposit = new JTextField(13);
        readerPanel.add(depositLabel);
        readerPanel.add(deposit);

        JLabel readerNumLabel = new JLabel("读者编号");
        readerNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        readerNum = new JTextField(13);
        readerPanel.add(readerNumLabel);
        readerPanel.add(readerNum);
    }

    private void setSouthPanel() {
        southPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 20, 5);
        southPanel.setLayout(flowLayout);
        southPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
        southPanel.setPreferredSize(new Dimension(600, 40));

        /* add two buttons */
        JButton modifyButton = new JButton("修改");
        JButton delButton = new JButton("删除");
        southPanel.add(modifyButton);
        southPanel.add(delButton);
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                String isbn = table.getValueAt(row, 10).toString().trim();
                boolean successful = Dao.deleteReader(isbn);
                if (successful) {
                    JOptionPane.showMessageDialog(null, "删除读者成功!");

                    //更新表格信息
                    DefaultTableModel model = new DefaultTableModel();
                    table.setModel(model);
                    model.setDataVector(getTableItems(Dao.selectReader()), columns);
                }
            }
        });
        modifyButton.addActionListener(new ActionListener() {
            Reader reader;
            String _name;
            String _sex;
            String _age;
            String _cardNum;
            String _validDate;
            String _maxNum;
            String _tel;
            String _deposit;
            String _cardType;
            String _occupation;
            String _isbn;
            String _startDate;

            @Override
            public void actionPerformed(ActionEvent e) {
                checkValid();
                buildReader();


                boolean successful = Dao.updateReader(reader);
                if (successful) {
                    JOptionPane.showMessageDialog(null, "读者信息更新成功");

                    //更新表格信息
                    DefaultTableModel model = new DefaultTableModel();
                    table.setModel(model);
                    model.setDataVector(getTableItems(Dao.selectReader()), columns);
                }
            }

            private void checkValid() {
                _name = name.getText().trim();
                if (checkEmpty(_name)) return;

                _sex = maleButton.isSelected() ? "男" : "女";
                _age = age.getText().trim();
                if (checkEmpty(_age)) return;

                _cardNum = cardNum.getText().trim();
                if (checkEmpty(_cardNum)) return;

                _validDate = validDate.getText().trim();
                if (checkEmpty(_validDate)) return;

                _maxNum = maxNum.getText().trim();
                if (checkEmpty(_maxNum)) return;

                _tel = tel.getText().trim();
                if (checkEmpty(_tel)) return;

                _deposit = deposit.getText().trim();
                if (checkEmpty(_deposit)) return;

                _cardType = cardComboBox.getSelectedItem().toString();

                _occupation = occupation.getText().trim();
                if (checkEmpty(_occupation)) return;

                _isbn = readerNum.getText().trim();
                if (checkEmpty(_isbn)) return;

                _startDate = startDate.getText().trim();
                if (checkEmpty(_startDate)) return;
            }

            private void buildReader() {
                reader = new Reader();

                reader.setName(_name);
                reader.setSex(_sex);
                reader.setAge(Integer.parseInt(_age));
                reader.setIdentityCard(_cardNum);
                reader.setIdentityCardType(ReaderMap.getCardType(_cardType));
                reader.setMaxNum(Integer.parseInt(_maxNum));
                reader.setTelNumber(_tel);
                reader.setKeepMoney(Double.parseDouble(_deposit));
                reader.setOccupation(_occupation);
                reader.setNumber(_isbn);
                reader.setStartDate(_startDate);
                reader.setValidDate(_validDate);
            }

            private boolean checkEmpty(String string) {
                return string.length() == 0;
            }
        });
    }
}
