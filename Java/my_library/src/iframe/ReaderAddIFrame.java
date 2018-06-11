package iframe;

import JComPz.ReaderMap;
import dao.Dao;
import model.Reader;
import util.CreatecdIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReaderAddIFrame extends JInternalFrame {

    private JPanel mainPanel;
    private JPanel southPanel;
    private JTextField name;
    private JRadioButton maleButton;
    private JTextField age;
    private JTextField occupation;
    private JComboBox<String> cardsComboBox;
    private JTextField cardNum;
    private JTextField maxBorrow;
    private JTextField validDate;
    private JTextField tele;
    private JTextField startDate;
    private JTextField readerNum;
    private JTextField deposit;
    private JRadioButton femaleButton;

    public ReaderAddIFrame() {
        super("读者相关信息添加");
        setLayout(new BorderLayout());
        setIconifiable(true);
        setClosable(true);
        setBounds(100, 100, 500, 350);

        /* north panel */
        ImageIcon icon = CreatecdIcon.add("readerAdd.jpg");
        JLabel northLabel = new JLabel(icon);
        northLabel.setOpaque(true);
        northLabel.setBackground(Color.CYAN);
        this.add(northLabel, BorderLayout.NORTH);

        /* center panel */
        mainPanel = new JPanel();
        setMainPanel();
        this.add(mainPanel);

        /* south panel */
        southPanel = new JPanel();
        setSouthPanel();
        this.add(southPanel, BorderLayout.SOUTH);
    }

    private void setMainPanel() {
        final GridLayout mainLayout = new GridLayout(0, 4);
        mainPanel.setLayout(mainLayout);
        mainLayout.setVgap(5);
        mainLayout.setHgap(10);

        //第一行
        JLabel nameLabel = new JLabel("姓  名");
        name = new JTextField(13);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(nameLabel);
        mainPanel.add(name);

        JLabel sexLabel = new JLabel("性  别");
        sexLabel.setHorizontalAlignment(SwingConstants.CENTER);

        final FlowLayout radioLaout = new FlowLayout();
        JPanel radioPanel = new JPanel(radioLaout);
        radioLaout.setHgap(0);
        radioLaout.setVgap(0);
        ButtonGroup buttonGroup = new ButtonGroup();
        maleButton = new JRadioButton("男");
        femaleButton = new JRadioButton("女");
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);
        radioPanel.add(maleButton);
        radioPanel.add(femaleButton);
        maleButton.setSelected(true);
        mainPanel.add(sexLabel);
        mainPanel.add(radioPanel);

        //第二行
        JLabel ageLabel = new JLabel("年  龄");
        age = new JTextField(13);
        ageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(ageLabel);
        mainPanel.add(age);

        JLabel occupationLabel = new JLabel("职  业");
        occupationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        occupation = new JTextField(13);
        mainPanel.add(occupationLabel);
        mainPanel.add(occupation);

        //第三行
        JLabel cardLabel = new JLabel("有效证件");
        cardsComboBox = new JComboBox<>(ReaderMap.getAllCardTypes());
        cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(cardLabel);
        mainPanel.add(cardsComboBox);

        JLabel cardNumLabel = new JLabel("证件号码");
        cardNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardNum = new JTextField(13);
        mainPanel.add(cardNumLabel);
        mainPanel.add(cardNum);

        //第四行
        JLabel maxBorrowLabel = new JLabel("最大借书量");
        maxBorrowLabel.setHorizontalAlignment(SwingConstants.CENTER);
        maxBorrow = new JTextField(13);
        mainPanel.add(maxBorrowLabel);
        mainPanel.add(maxBorrow);

        JLabel validDateLabel = new JLabel("有效日期");
        validDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        validDate = new JTextField(13);
        mainPanel.add(validDateLabel);
        mainPanel.add(validDate);

        //第五行
        JLabel teleLabel = new JLabel("电  话");
        teleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tele = new JTextField(13);
        mainPanel.add(teleLabel);
        mainPanel.add(tele);

        JLabel depositLabel = new JLabel("押  金");
        depositLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deposit = new JTextField(13);
        mainPanel.add(depositLabel);
        mainPanel.add(deposit);

        //第六行
        JLabel startDateLabel = new JLabel("办证日期");
        startDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startDate = new JTextField(13);
        mainPanel.add(startDateLabel);
        mainPanel.add(startDate);

        JLabel readerNumLabel = new JLabel("读者编号");
        readerNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        readerNum = new JTextField(13);
        mainPanel.add(readerNumLabel);
        mainPanel.add(readerNum);


    }

    public void setSouthPanel() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        southPanel.setLayout(layout);
        JButton saveButton = new JButton("保存");
        JButton returnButton = new JButton("返回");
        southPanel.add(saveButton);
        southPanel.add(returnButton);

        /* 按钮的监听器事件 */
        saveButton.addActionListener(new ActionListener() {
            Reader reader;
            private String name_;
            private String sex_;
            private String age_;
            private String occupation_;
            private String card_;
            private String cardNum_;
            private String maxBorrow_;
            private String validDate_;
            private String tele_;
            private String deposit_;
            private String startDate_;
            private String readerNum_;

            @Override
            public void actionPerformed(ActionEvent e) {
                checkValid();
                buildReader();
                if (Dao.insertReader(reader)) {
                    JOptionPane.showMessageDialog(null, "读者信息添加成功!");
                    doDefaultCloseAction();
                }
            }

            private void checkValid() {
                name_ = name.getText().trim();
                if (checkEmpty(name_)) return;

                sex_ = maleButton.isSelected() ? "男" : "女";

                age_ = age.getText().trim();
                if (checkEmpty(age_)) return;

                occupation_ = occupation.getText().trim();
                if (checkEmpty(occupation_)) return;

                cardNum_ = cardNum.getText().trim();
                if (checkEmpty(cardNum_)) return;

                maxBorrow_ = maxBorrow.getText().trim();
                if (checkEmpty(maxBorrow_)) return;

                validDate_ = validDate.getText().trim();
                if (checkEmpty(validDate_)) return;

                tele_ = tele.getText().trim();
                if (checkEmpty(tele_)) return;

                deposit_ = deposit.getText().trim();
                if (checkEmpty(deposit_)) return;

                startDate_ = startDate.getText().trim();
                if (checkEmpty(startDate_)) return;

                readerNum_ = readerNum.getText();
                if (checkEmpty(readerNum_)) return;

            }

            private void buildReader() {
                reader = new Reader();
                reader.setName(name_);
                reader.setSex(sex_);
                reader.setAge(Integer.parseInt(age_));
                reader.setIdentityCard(cardNum_);
                reader.setValidDate(validDate_);
                reader.setMaxNum(Integer.parseInt(maxBorrow_));
                reader.setTelNumber(tele_);
                reader.setKeepMoney(Double.parseDouble(deposit_));


                String cardType = (String) cardsComboBox.getSelectedItem();
                reader.setIdentityCardType(ReaderMap.getCardType(cardType));

                reader.setOccupation(occupation_);
                reader.setNumber(cardNum_);
                reader.setStartDate(startDate_);
            }

            private boolean checkEmpty(String string) {
                return string.length() == 0;
            }
        });
        returnButton.addActionListener(e -> ReaderAddIFrame.this.doDefaultCloseAction());
    }

    public static void main(String[] args) {
        new ReaderAddIFrame();
    }
}
