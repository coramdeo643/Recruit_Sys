package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    private JPanel panelA;
    private JLabel idLabel;
    private JTextField idField;
    private JLabel pwLabel;
    private JPasswordField pwField;
    private JButton userLogin;
    private JButton userLogout;

    private JPanel panelB;
    private JButton userCreate;
//    private JButton userList;
//    private JButton companyCreate;
//    private JButton companyList;
//    private JButton announceCreate;
//    private JButton announceApply;
//    private JButton announceList;

    public LoginFrame() {
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("Recruitment System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelA = new JPanel();
        idLabel = new JLabel("이  메  일");
        idField = new JTextField(10);
        pwLabel = new JLabel("비밀번호");
        pwField = new JPasswordField(10);
        panelB = new JPanel();
        userLogin = new JButton("로그인");
        userLogout = new JButton("로그아웃");
        userCreate = new JButton("회원등록");
    }

    private void setInitLayout() {
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.pink);

        // panelA
        panelA.setSize(200, 70);
        panelA.setLocation(50, 50);
        panelA.setBackground(Color.PINK);
        add(panelA);
        panelA.add(idLabel);
        panelA.add(idField);
        panelA.add(pwLabel);
        panelA.add(pwField);
        userLogin.setSize(80, 50);
        userLogin.setLocation(250, 55);
        add(userLogin);
        //panelB
        panelB.setSize(400, 70);
        panelB.setLocation(0, 150);
        panelB.setBackground(Color.PINK);
        add(panelB);
        userCreate.setSize(80, 50);
        panelB.add(userCreate);

        setVisible(true);
    }

    private void addEventListener() {
        userCreate.addActionListener(this);
        userLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton targerB = (JButton) e.getSource();
        if (targerB == userCreate) {
            new userCreateFrame();
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
