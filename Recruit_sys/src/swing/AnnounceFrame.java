package swing;

import dto.Announce;
import message.Handling;
import service.AnnounceService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnounceFrame extends JFrame implements ActionListener {

    private final AnnounceService announceService = new AnnounceService();
    private JPanel topPanel;
    private JPanel subPanel1;
    private JPanel subPanel2;
    private JLabel idLabel1;
    private JLabel idLabel2;
    private JLabel idLabel3;
    private JLabel idLabel4;
    private JTextField inputComName;
    private JTextField inputContent;
    private JTextField inputAddress;
    private JButton logoutButton;

    private JButton jobListButton;
    private JButton userListButton;
    private JButton companyListButton;

    private JPanel mainPanel;

    private JList<Announce> announceJList;
    private List<Announce> announceList = new ArrayList<>();
    private DefaultListModel<Announce> announceDefaultListModel = new DefaultListModel<>();
    private static JScrollPane scrollPane;

    // 검색 기능을 할 버튼
    private JButton companyInsertButton;
    private JButton companyDeleteButton;
    private JButton companySearchButton;

    public AnnounceFrame() {
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("채용공고 목록");
        setSize(800, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topPanel = new JPanel();
        subPanel1 = new JPanel();
        subPanel2 = new JPanel();
        idLabel1 = new JLabel("사용자이메일@email.com");
        idLabel2 = new JLabel("공고 이름");
        idLabel3 = new JLabel("주소");
        idLabel4 = new JLabel("공고 내용");
        inputComName = new JTextField("", 10);
        inputContent = new JTextField("", 10);
        inputAddress = new JTextField("", 10);
        logoutButton = new JButton("로그아웃");
        jobListButton = new JButton("채용공고 목록");
        userListButton = new JButton("유저 목록");
        companyListButton = new JButton("회사 목록");
        companyInsertButton = new JButton("공고 추가");
        companySearchButton = new JButton("공고 검색");
        companyDeleteButton = new JButton("공고 삭제");

        mainPanel = new JPanel();
    }

    private void setInitLayout() {
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.gray);

        topPanel.setLocation(0, 0);
        topPanel.setSize(800, 50);
        topPanel.setBackground(Color.PINK);
        //topPanel.setLayout(null);
        add(topPanel);

        subPanel1.setLocation(0, 50);
        subPanel1.setSize(800, 50);
        subPanel1.setBackground(Color.WHITE);
        add(subPanel1);

        subPanel2.setLocation(0, 100);
        subPanel2.setSize(800, 50);
        subPanel2.setBackground(Color.ORANGE);
        subPanel2.setLayout(null);
        add(subPanel2);

        idLabel1.setSize(100, 30);
        idLabel1.setLocation(10, 10);
        if (LoginFrame.email != null) {
            idLabel1.setText(LoginFrame.email + "님 환영합니다!");
        }
        topPanel.add(idLabel1);

        idLabel2.setSize(100, 30);
        idLabel2.setLocation(130, 10);
        subPanel2.add(idLabel2);

        idLabel3.setSize(100, 30);
        idLabel3.setLocation(320, 10);
        subPanel2.add(idLabel3);

        idLabel4.setSize(100, 30);
        idLabel4.setLocation(470, 10);
        subPanel2.add(idLabel4);

        subPanel2.add(inputComName);
        inputComName.setSize(90, 30);
        inputComName.setLocation(200, 10);

        subPanel2.add(inputContent);
        inputContent.setSize(90, 30);
        inputContent.setLocation(360, 10);

        subPanel2.add(inputAddress);
        inputAddress.setSize(90, 30);
        inputAddress.setLocation(530, 10);

        logoutButton.setSize(100, 30);
        logoutButton.setLocation(200, 20);
        topPanel.add(logoutButton);

        jobListButton.setSize(100, 30);
        jobListButton.setLocation(100, 10);
        topPanel.add(jobListButton);

        userListButton.setSize(100, 30);
        userListButton.setLocation(100, 10);
        topPanel.add(userListButton);

        companyListButton.setSize(100, 30);
        companyListButton.setLocation(150, 10);
        topPanel.add(companyListButton);

        companyInsertButton.setSize(100, 30);
        companyInsertButton.setLocation(150, 10);
        subPanel1.add(companyInsertButton);

        companySearchButton.setSize(100, 30);
        companySearchButton.setLocation(150, 10);
        subPanel1.add(companySearchButton);

        companyDeleteButton.setSize(100, 30);
        companyDeleteButton.setLocation(150, 10);
        subPanel1.add(companyDeleteButton);

        mainPanel.setLocation(0, 150);
        mainPanel.setSize(800, 350);
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
        companyInsertButton.addActionListener(this);
        companySearchButton.addActionListener(this);
        companyDeleteButton.addActionListener(this);
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
        } else if (targetB == companyInsertButton) {
            if (inputComName != null && !inputComName.getText().trim().isEmpty() &&
                    inputAddress != null && !inputAddress.getText().trim().isEmpty() &&
                    inputContent != null && !inputContent.getText().trim().isEmpty()) {
                try {
                    announceService.addAnnounce(new Announce(inputComName.getText(), inputAddress.getText(), inputContent.getText()));
                    announceList = announceService.getAllAnnounce();
                } catch (SQLException ex) {
                    throw new RuntimeException(Handling.FAIL_ADD_EXCEPTION);
                }

                announceDefaultListModel.removeAllElements();
                for (Announce announce : announceList) {
                    announceDefaultListModel.addElement(announce);
                }
                announceJList = new JList<>();
                scrollPane.add(announceJList);
            }
        } else if (targetB == companySearchButton) {
            if (!inputComName.getText().trim().isEmpty() || !Boolean.parseBoolean(inputAddress.getText().trim()) ||
                    !inputContent.getText().trim().isEmpty()) {
                try {
                    announceService.choiceAnnounce(inputComName.getText(), inputContent.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(Handling.FAIL_GET_EXCEPTION);
                }
                announceDefaultListModel.removeAllElements();
                for (Announce announce : announceList) {
                    announceDefaultListModel.addElement(announce);
                }
                announceJList = new JList<>();
                scrollPane.add(announceJList);
            }
        } else if (targetB == companyDeleteButton) {
            if (inputComName.getText().trim().isEmpty() || inputAddress.getText().trim().isEmpty() ||
                    inputContent.getText().trim().isEmpty()) ;
            {
                try {
                    announceService.deleteApplication(inputComName.getText(), inputContent.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(Handling.FAIL_DEL_EXCEPTION);
                }
                announceDefaultListModel.removeAllElements();
                for (Announce announce : announceList) {
                    announceDefaultListModel.addElement(announce);
                }
                announceJList = new JList<>();
                scrollPane.add(announceJList);
            }
        }
    }
}
