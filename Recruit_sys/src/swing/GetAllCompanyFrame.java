package swing;

import dto.Company;
import service.CompanyService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GetAllCompanyFrame extends JFrame implements ActionListener {

    private final CompanyService companyService = new CompanyService();
    private List<Company> companyList = new ArrayList<>();
    private DefaultListModel<Company> listModel = new DefaultListModel<>();
    private static JScrollPane scrollPane;
    private JList<Company> jobList;

    private JPanel topPanel;
    private JLabel idLabel;
    private JButton logoutButton;
    private JButton jobListButton;
    private JButton userListButton;
    private JButton companyListButton;

    private JPanel subPanel1;
    private JLabel companyNameLabel;
    private JTextField name;
    private JLabel companyAddrLabel;
    private JTextField address;
    private JButton checkButton;

    private JPanel subPanel2;
    private JButton companySearchButton;

    private JPanel mainPanel;

    public GetAllCompanyFrame() {
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("회사 목록");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topPanel = new JPanel();
        idLabel = new JLabel("사용자이메일@email.com");
        logoutButton = new JButton("로그아웃");
        jobListButton = new JButton("채용공고 목록");
        userListButton = new JButton("유저 목록");
        companyListButton = new JButton("회사 목록");

        subPanel1 = new JPanel();
        checkButton = new JButton("검색하기");
        companyNameLabel = new JLabel("이름 입력 : ");
        name = new JTextField("", 5);
        companyAddrLabel = new JLabel("     주소 입력 : ");
        address = new JTextField("", 5);


        mainPanel = new JPanel();
    }

    private void setInitLayout() {
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.PINK);

        // topPanel ---------------------------------------------------------
        topPanel.setLocation(0, 0);
        topPanel.setSize(800, 50);
        topPanel.setBackground(Color.PINK);
        add(topPanel);

        if (LoginFrame.email != null) {
            idLabel.setText(LoginFrame.email + "님 환영합니다!");
        }
        topPanel.add(idLabel);

        logoutButton.setSize(100, 30);
        topPanel.add(logoutButton);

        jobListButton.setSize(100, 30);
        topPanel.add(jobListButton);

        userListButton.setSize(100, 30);
        topPanel.add(userListButton);

        companyListButton.setSize(100, 30);
        topPanel.add(companyListButton);

        // subPanel1 ---------------------------------------------------------
        subPanel1.setLocation(0, 60);
        subPanel1.setSize(800, 40);
        subPanel1.setBackground(Color.PINK);
        add(subPanel1);

        subPanel1.add(companyNameLabel);
        subPanel1.add(name);
        subPanel1.add(companyAddrLabel);
        subPanel1.add(address);
        subPanel1.add(checkButton);

        // mainPanel ---------------------------------------------------------
        mainPanel.setLocation(0, 160);
        mainPanel.setSize(800, 350);
        mainPanel.setBackground(Color.PINK);
        add(mainPanel);

        companyList = companyService.getAllCompany();
        for (Company post : companyList) {
            listModel.addElement(post);
        }

        JList<Company> jobList = new JList<>(listModel);
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
        userListButton.addActionListener(this);
        companyListButton.addActionListener(this);
        checkButton.addActionListener(this);
        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkButton.doClick();
                    name.transferFocus(); // 연속으로 클릭되는 걸 막기 위해 포커스 이동
                }
            }
        });

        address.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkButton.doClick();
                    address.transferFocus(); // 연속으로 클릭되는 걸 막기 위해 포커스 이동
                }
            }
        });
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
        } else if (targetB == jobListButton) {
            new AnnounceFrame();
            this.dispose();
        } else if (targetB == checkButton) {
            if (name.getText().trim().isEmpty() && address.getText().trim().isEmpty()) {
                companyList = companyService.getAllCompany();
            } else {
                companyList = companyService.getSelectedCompany(name.getText(), address.getText());
            }

            listModel.removeAllElements();
            for (Company post : companyList) {
                listModel.addElement(post);
            }
            jobList = new JList<>(listModel);
            scrollPane.add(jobList);
        }
    }
}
