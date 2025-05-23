package swing;

import dto.Announce;
import service.AnnounceService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AnnounceFrame extends JFrame implements ActionListener {

    private final AnnounceService announceService = new AnnounceService();
    private JPanel topPanel;
    private JPanel subPanel;
    private JLabel idLabel;
    private JButton logoutButton;

    private JButton jobListButton;
    private JButton userListButton;
    private JButton companyListButton;

    private JPanel mainPanel;


    //TODO
    // 1. 채용공고 추가 버튼 > insert 기능 연결
    private JButton announceInsertButton;
    private JTextField companyName;
    private JTextField address;
    private JTextField content;

    // 2. 채용공고 삭제 버튼 > delete 기능 연결
    private JButton announceDeleteButton;



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
        subPanel = new JPanel();
        idLabel = new JLabel("사용자이메일@email.com");
        logoutButton = new JButton("로그아웃");
        jobListButton = new JButton("채용공고 목록");
        userListButton = new JButton("유저 목록");
        companyListButton = new JButton("회사 목록");
        announceInsertButton = new JButton("공고 추가");
        announceDeleteButton = new JButton("공고 삭제");

        companyName = new JTextField("", 5);
        address = new JTextField("", 5);
        content = new JTextField("", 5);

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

        subPanel.setLocation(0,50);
        subPanel.setSize(800, 100);
        subPanel.setBackground(Color.WHITE);
        subPanel.setLayout(null);
        add(subPanel);

        idLabel.setSize(100,30);
        idLabel.setLocation(10,10);
        if (LoginFrame.email != null) {
            idLabel.setText(LoginFrame.email + "님 환영합니다!");
        }
        topPanel.add(idLabel);

        logoutButton.setSize(100,30);
        logoutButton.setLocation(200,20);
        topPanel.add(logoutButton);

        jobListButton.setSize(100,30);
        jobListButton.setLocation(100,10);
        topPanel.add(jobListButton);

        userListButton.setSize(100,30);
        userListButton.setLocation(100,10);
        topPanel.add(userListButton);

        companyListButton.setSize(100,30);
        companyListButton.setLocation(150,10);
        topPanel.add(companyListButton);

        announceInsertButton.setSize(100,30);
        announceInsertButton.setLocation(250,10);
        subPanel.add(announceInsertButton);

        announceDeleteButton.setSize(100,30);
        announceDeleteButton.setLocation(announceInsertButton.getX() + 110,10);
        subPanel.add(announceDeleteButton);

        mainPanel.setLocation(0, 150);
        mainPanel.setSize(800,350);
        mainPanel.setBackground(Color.PINK);
        add(mainPanel);

        List<Announce> sampleList = announceService.getAllAnnounce();
        DefaultListModel<Announce> listModel = new DefaultListModel<>();
        for (Announce post : sampleList) {
            listModel.addElement(post);
        }
        JList<Announce> jobList = new JList<>(listModel);
        jobList.setFixedCellHeight(40);
        jobList.setBackground(new Color(240, 248, 255));
        jobList.setForeground(new Color(25, 25, 112));
        JScrollPane scrollPane = new JScrollPane(jobList);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        mainPanel.add(scrollPane);

        setVisible(true);
    }

    private void addEventListener() {
        logoutButton.addActionListener(this);
        jobListButton.addActionListener(this);
        userListButton.addActionListener(this);
        companyListButton.addActionListener(this);
        announceInsertButton.addActionListener(this);
        announceDeleteButton.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JButton targetB = (JButton) e.getSource();
        if (targetB == logoutButton) {
            new LoginFrame();
            this.dispose();
        } else if (targetB == userListButton) {
            new GetAllUserFrame();
            this.dispose();
        } else if (targetB == companyListButton) {
            new GetAllCompanyFrame();
            this.dispose();
        } else if (targetB == announceInsertButton) {
            companyName.setLocation(150, 50);
            address.setLocation(150, 50);
            content.setLocation(150, 50);

            subPanel.add(companyName);
            subPanel.add(address);
            subPanel.add(content);
            setVisible(true);
        } else if (targetB == announceDeleteButton) {

        }
    }

    public static void main(String[] args) {
        new AnnounceFrame();
    }
}
