package swing;

import dto.User;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame implements ActionListener {
    private final UserService userService = new UserService();
    static String email;

    private JPanel panelA;
    private JLabel idLabel;
    private JTextField idField;
    private JLabel pwLabel;
    private JPasswordField pwField;
    private JButton userLogin;
    private JButton userLogout;

    private JPanel panelB;
    private JButton userCreate;
    private JButton companyCreate;

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
        companyCreate = new JButton("회사등록");
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
        companyCreate.setSize(80,50);
        panelB.add(companyCreate);

        setVisible(true);
    }

    private void addEventListener() {
        userCreate.addActionListener(this);
        companyCreate.addActionListener(this);
        userLogin.addActionListener(this);
        pwField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    userLogin.doClick();
                    pwField.transferFocus(); // 연속으로 클릭되는 걸 막기 위해 포커스 이동
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton targetB = (JButton) e.getSource();
        if (targetB == userCreate) {
            new UserCreateFrame();
        } else if (targetB == userLogin) {
            if (idField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.","",JOptionPane.PLAIN_MESSAGE);
            } else if (String.valueOf(pwField.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "", JOptionPane.PLAIN_MESSAGE);
            } else {
                //TODO
                // 1. 로그인 기능 연결
                email = idField.getText();
                User user = userService.authenticateUser(email, String.valueOf(pwField.getPassword()));
                if (user != null) {
                    new AnnounceFrame();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "존재하지 않는 회원입니다.","로그인 오류",JOptionPane.PLAIN_MESSAGE);
                }
            }
        } else if (targetB == companyCreate) {
            new CompanyCreateFrame();
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
