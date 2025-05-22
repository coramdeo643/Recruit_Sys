package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetAllCompanyFrame extends JFrame implements ActionListener {

    private JPanel topPanel;
    private JLabel idLabel;
    private JButton logoutButton;
    private JButton listButton;
    private JButton myPageButton;

    private JPanel mainPanel;

    public GetAllCompanyFrame(){
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("회사 목록");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topPanel = new JPanel();
        idLabel = new JLabel("사용자이메일@email.com");
        logoutButton = new JButton("로그아웃");
        listButton = new JButton("채용공고 목록");
        myPageButton = new JButton("회사 목록");

        mainPanel = new JPanel();
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

        mainPanel.setLocation(0, 100);
        mainPanel.setSize(800,350);
        mainPanel.setBackground(Color.PINK);
        add(mainPanel);
    }

    private void addEventListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
