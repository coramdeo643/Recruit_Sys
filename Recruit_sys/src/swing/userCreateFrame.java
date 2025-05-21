package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class userCreateFrame extends JFrame implements ActionListener, MouseListener {

    private JPanel panelA;
    private JLabel idLabel;
    private JTextField idField;
    private JButton idCheckButton;
    private JLabel pwLabel;
    private JPasswordField pwField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel addrLabel;
    private JTextField addrField;

    private JPanel panelB;
    private JButton userCreateButton;

    public userCreateFrame() {
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
        idField = new JTextField(10);
        idCheckButton = new JButton("중복확인");
        pwLabel = new JLabel("비밀번호");
        pwField = new JPasswordField(10);
        nameLabel = new JLabel("이        름");
        nameField = new JTextField("", 10);
        addrLabel = new JLabel("주        소");
        addrField = new JTextField("", 10);

        panelB = new JPanel();
        userCreateButton = new JButton("회원등록");

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
        panelA.add(idField);
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
            this.dispose();
        } else if (targetB == idCheckButton) {
            if (idCheckButton.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"입력된 값이 없어요", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
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
        new userCreateFrame();
    }
}
