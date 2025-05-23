package swing;

import dto.User;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GetAllUserFrame extends JFrame implements ActionListener {

    private final UserService userService = new UserService();
    private List<User> userList = new ArrayList<>();
    private DefaultListModel<User> listModel = new DefaultListModel<>();
    private static JScrollPane scrollPane;
    private JList<User> jobList;
    private JPanel topPanel;
    private JLabel idLabel;
    private JButton logoutButton;

    private JButton jobListButton;
    private JButton userListButton;
    private JButton companyListButton;

    private JPanel subPanel;
    private JTextField name;
    private JLabel nameLabel;
    private JTextField address;
    private JLabel addressLabel;
    private JButton checkButton;

    private JPanel mainPanel;

    public GetAllUserFrame() {
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("사용자 목록");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topPanel = new JPanel();
        idLabel = new JLabel("사용자이메일@email.com");
        logoutButton = new JButton("로그아웃");
        jobListButton = new JButton("채용공고 목록");
        userListButton = new JButton("유저 목록");
        companyListButton = new JButton("회사 목록");
        subPanel = new JPanel();
        name = new JTextField("", 5);
        nameLabel = new JLabel("이름 입력: ");
        address = new JTextField("", 5);
        addressLabel = new JLabel("주소 입력: ");
        checkButton = new JButton("검색하기");

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

        idLabel.setSize(100, 30);
        idLabel.setLocation(50, 10);
        if (LoginFrame.email != null) {
            idLabel.setText(LoginFrame.email + "님 환영합니다!");
        }
        topPanel.add(idLabel);

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

        subPanel.setLocation(0, 50);
        subPanel.setSize(800, 100);
        subPanel.setBackground(Color.WHITE);
        add(subPanel);

        nameLabel.setLocation(100, 100);
        subPanel.add(nameLabel);

        name.setLocation(100, 100);
        subPanel.add(name);

        addressLabel.setLocation(100, 100);
        subPanel.add(addressLabel);

        address.setLocation(100, 100);
        subPanel.add(address);

        checkButton.setLocation(100, 100);
        subPanel.add(checkButton);

        mainPanel.setLocation(0, 150);
        mainPanel.setSize(800, 350);
        mainPanel.setBackground(Color.PINK);
        add(mainPanel);

        userList = userService.getAllUser();
        for (User post : userList) {
            listModel.addElement(post);
        }
        jobList = new JList<>(listModel);
        jobList.setFixedCellHeight(40);
        jobList.setBackground(new Color(240, 248, 255)); // 배경색 (앨리스 블루)
        jobList.setForeground(new Color(25, 25, 112)); // 글자색 (미드나잇 블루)
        scrollPane = new JScrollPane(jobList);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        mainPanel.add(scrollPane);

        setVisible(true);
    }

    private void addEventListener() {
        logoutButton.addActionListener(this);
        jobListButton.addActionListener(this);
        companyListButton.addActionListener(this);
        checkButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton targetB = (JButton) e.getSource();
        if (targetB == logoutButton) {
            new LoginFrame();
            this.dispose();
        } else if (targetB == companyListButton) {
            new GetAllCompanyFrame();
            this.dispose();
        } else if (targetB == jobListButton) {
            new AnnounceFrame();
            this.dispose();
        } else if (targetB == checkButton) {
            if (name.getText().trim().isEmpty() && address.getText().trim().isEmpty()) {
                userList = userService.getAllUser();
            } else {
                userList = userService.getSelectedUser(name.getText(), address.getText());
            }

            // 중요한 부분
            listModel.removeAllElements();
            for (User post : userList) {
                listModel.addElement(post);
            }
            jobList = new JList<>(listModel);
            scrollPane.add(jobList);
        }
    }

    public static void main(String[] args) {
        new GetAllUserFrame();
    }
}