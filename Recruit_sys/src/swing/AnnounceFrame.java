package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnnounceFrame extends JFrame implements ActionListener {

    // 샘플 데이터
    // ArrayList<AnnounceDTO> = new Array*(

    private JPanel topPanel;
    private JLabel idLabel;
    private JButton logoutButton;
    private JButton listButton;
    private JButton myPageButton;

    private JPanel mainPanel;

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

        //TODO SAMPLE DATA -- 삭제 예정
        ArrayList<String> sampleList = new ArrayList<>();
        sampleList.add("삼성전자 - 2025년 상반기 신입사원 채용 (연구개발/생산기술)");
        sampleList.add("LG전자 - AI 소프트웨어 개발자 수시 채용");
        sampleList.add("SK하이닉스 - 메모리 반도체 설계 경력사원 모집");
        sampleList.add("카카오 - 프론트엔드 개발자 (경력 3년 이상)");
        sampleList.add("네이버 - 클라우드 엔지니어 채용");
        sampleList.add("쿠팡 - 물류 자동화 시스템 개발자");
        sampleList.add("라인플러스 - 백엔드 개발자 (Java/Kotlin)");
        sampleList.add("엔씨소프트 - 게임 클라이언트 프로그래머");
        sampleList.add("넷마블 - UX/UI 디자이너 신입 채용");
        sampleList.add("현대자동차 - 자율주행 소프트웨어 개발");
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String post : sampleList) {
            listModel.addElement(post);
        }
        JList<String> jobList = new JList<>(listModel);
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
        new AnnounceFrame();
    }
}
