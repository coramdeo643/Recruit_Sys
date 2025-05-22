package swing;

import dao.CompanyDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CompanyCreateFrame extends JFrame implements ActionListener, MouseListener {

    private JPanel panelA;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton nameCheckButton;
    private JLabel addrLabel;
    private JTextField addrField;

    private JPanel panelB;
    private JButton companyCreateButton;

    public CompanyCreateFrame(){
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("회사등록");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelA = new JPanel();
        nameLabel = new JLabel("이        름");
        nameField = new JTextField("", 10);
        nameCheckButton = new JButton("중복확인");
        addrLabel = new JLabel("주        소");
        addrField = new JTextField("", 10);

        panelB = new JPanel();
        companyCreateButton = new JButton("회사등록");


    }
    private void setInitLayout() {
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.pink);

        panelA.setSize(200, 120);
        panelA.setLocation(50, 50);
        panelA.setBackground(Color.PINK);
        add(panelA);

        panelA.add(nameLabel);
        panelA.add(nameField);
        nameCheckButton.setSize(90, 30);
        nameCheckButton.setLocation(250, 50);
        add(nameCheckButton);
        panelA.add(addrLabel);
        panelA.add(addrField);

        panelB.setSize(400, 40);
        panelB.setLocation(0, 180);
        panelB.setBackground(Color.pink);
        add(panelB);

        panelB.add(companyCreateButton);

        setVisible(true);
    }

    private void addEventListener() {
        companyCreateButton.addActionListener(this);
        nameCheckButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton targetB = (JButton) e.getSource();
        int result = 0;

        if(targetB == companyCreateButton){
            this.dispose();
        } else if (targetB == nameCheckButton) {
            if(nameField.getText().isEmpty()){
                JOptionPane.showMessageDialog
                        (null,"입력된 값이 없어요. 회사 이름을 입력해주세요.",
                                "중복확인", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            CompanyDAO companyDAO = new CompanyDAO();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {
        new CompanyCreateFrame();
    }
}
