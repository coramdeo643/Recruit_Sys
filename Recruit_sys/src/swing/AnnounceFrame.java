package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnounceFrame extends JFrame implements ActionListener {

    // 샘플 데이터
    // ArrayList<AnnounceDTO> = new Array*(

    private JPanel topPanel;
    private JLabel idLabel;
    private JButton logoutButton;
    private JButton listButton;
    private JButton myPageButton;

    public AnnounceFrame() {
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("채용공고 목록");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topPanel = new JPanel();
        idLabel = new JLabel("사용자이메일@email.com");
        logoutButton = new JButton("로그아웃");
        listButton = new JButton("채용공고 목록");
        myPageButton = new JButton("마이페이지");
    }

    private void setInitLayout() {
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.gray);

        topPanel.setLocation(0,0);
        topPanel.setSize(800,50);
        topPanel.setBackground(Color.PINK);
        //topPanel.setLayout(null);
        add(topPanel);

        idLabel.setSize(100,30);
        idLabel.setLocation(50,10);
        topPanel.add(idLabel);

        logoutButton.setSize(100,30);
        logoutButton.setLocation(200,20);
        topPanel.add(logoutButton);

        listButton.setSize(100,30);
        listButton.setLocation(100,10);
        topPanel.add(listButton);

        myPageButton.setSize(100,30);
        myPageButton.setLocation(150,10);
        topPanel.add(myPageButton);

        setVisible(true);
    }

    private void addEventListener() {
        logoutButton.addActionListener(this);
        listButton.addActionListener(this);
        myPageButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton targetB = (JButton) e.getSource();
        if (targetB == logoutButton) {
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new AnnounceFrame();
    }
}
