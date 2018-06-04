package iframe;

import util.CreatecdIcon;

import javax.swing.*;
import java.awt.*;

public class ReadAddIFrame extends JInternalFrame {

    private JPanel mainPanel;

    public ReadAddIFrame() {
        super("读者相关信息添加");
        setLayout(new BorderLayout());
        setIconifiable(true);
        setClosable(true);
        setBounds(100, 100, 500, 350);

        /* north panel */
        ImageIcon icon = CreatecdIcon.add("readAdd.jpg");
        JLabel northLabel = new JLabel(icon);
        northLabel.setOpaque(true);
        northLabel.setBackground(Color.CYAN);
        this.add(northLabel, BorderLayout.NORTH);

        /* center panel */
        mainPanel = new JPanel();
        setMainPanel();
        this.add(mainPanel);

    }

    private void setMainPanel() {
        final GridLayout mainLayout = new GridLayout(0, 4);
        mainPanel.setLayout(mainLayout);
        mainLayout.setVgap(5);
        mainLayout.setHgap(10);

        //第一行
        JLabel nameLabel = new JLabel("姓  名");
        JTextField name = new JTextField(13);
        mainPanel.add(nameLabel);
        mainPanel.add(name);

        final FlowLayout radioLaout = new FlowLayout();
        JPanel radioPanel = new JPanel(radioLaout);
        radioLaout.setHgap(0);
        radioLaout.setVgap(0);
        JRadioButton maleButton = new JRadioButton("男");
        JRadioButton femaleButton = new JRadioButton("女");
        radioPanel.add(maleButton);
        radioPanel.add(femaleButton);
        maleButton.setSelected(true);
        mainPanel.add(radioPanel);

        //第二行
    }
}
