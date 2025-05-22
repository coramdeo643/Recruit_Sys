package swing;

import dto.Company;
import service.RecruitSystemService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GetAllCompanyFrame extends JFrame implements ActionListener {

    private final RecruitSystemService recruitSystemService = new RecruitSystemService();
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

        List<Company> sampleList = recruitSystemService.getAllCompany();
        DefaultListModel<Company> listModel = new DefaultListModel<>();
        for (Company post : sampleList) {
            listModel.addElement(post);
        }
        JList<Company> jobList = new JList<>(listModel);
        jobList.setFixedCellHeight(40);
        jobList.setBackground(new Color(240, 248, 255)); // 배경색 (앨리스 블루)
        jobList.setForeground(new Color(25, 25, 112)); // 글자색 (미드나잇 블루)
        JScrollPane scrollPane = new JScrollPane(jobList);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        mainPanel.add(scrollPane);

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
        new GetAllCompanyFrame();
    }
}
