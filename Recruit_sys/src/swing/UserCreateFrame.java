package swing;

import dao.UserDAO;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCreateFrame extends JFrame implements ActionListener {

    private final UserService userService = new UserService();

    private JPanel panelA;
    private JLabel idLabel;
    private JTextField emailField;
    private JButton idCheckButton;
    private JLabel pwLabel;
    private JPasswordField pwField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel addrLabel;
    private JTextField addrField;

    private JPanel panelB;
    private JButton userCreateButton;

    private final JTextField[] textFields = new JTextField[5];

    public UserCreateFrame() {
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("회원등록");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelA = new JPanel();
        idLabel = new JLabel("이  메  일");
        emailField = new JTextField(10);
        idCheckButton = new JButton("중복확인");
        pwLabel = new JLabel("비밀번호");
        pwField = new JPasswordField(10);
        nameLabel = new JLabel("이        름");
        nameField = new JTextField("", 10);
        addrLabel = new JLabel("주        소");
        addrField = new JTextField("", 10);

        panelB = new JPanel();
        userCreateButton = new JButton("회원등록");

        textFields[0] = emailField;
        textFields[1] = pwField;
        textFields[2] = nameField; 
        textFields[3] = addrField;

//        String email = textFields[0].getText();
//        String pw = textFields[1].getText();
//        String name = textFields[2].getText();
//        String addr = textFields[3].getText();
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

        panelA.add(idLabel);
        panelA.add(emailField);
        idCheckButton.setSize(90, 30);
        idCheckButton.setLocation(250, 50);
        add(idCheckButton);
        panelA.add(pwLabel);
        panelA.add(pwField);
        panelA.add(nameLabel);
        panelA.add(nameField);
        panelA.add(addrLabel);
        panelA.add(addrField);

        panelB.setSize(400, 40);
        panelB.setLocation(0, 180);
        panelB.setBackground(Color.pink);
        add(panelB);

        panelB.add(userCreateButton);

        setVisible(true);

    }

    private void addEventListener() {
        userCreateButton.addActionListener(this);
        idCheckButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton targetB = (JButton) e.getSource();

        if (targetB == userCreateButton) {
            String email = textFields[0].getText();
            String pw = textFields[1].getText();
            String name = textFields[2].getText();
            String addr = textFields[3].getText();
            userService.addUser(email, pw, name, addr);

            this.dispose();
        } else if (targetB == idCheckButton) {
            if (emailField.getText().isEmpty()) {
                JOptionPane.showMessageDialog
                        (null,"입력된 값이 없어요. 아이디를 입력해주세요.",
                                "중복확인", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            UserDAO userDAO = new UserDAO();

        }
    }

    public static void main(String[] args) {
        new UserCreateFrame();
    }
}
