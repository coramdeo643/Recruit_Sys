package swing;

import dao.CompanyDAO;
import service.RecruitSystemService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CompanyCreateFrame extends JFrame implements ActionListener {

    private final RecruitSystemService recruitSystemService = new RecruitSystemService();

    private JPanel panelA;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel addrLabel;
    private JTextField addrField;

    private JPanel panelB;
    private JButton companyCreateButton;

    private final JTextField[] textFields = new JTextField[2];

    public CompanyCreateFrame(){
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        setTitle("회사등록");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelA = new JPanel();
        nameLabel = new JLabel("이        름");
        nameField = new JTextField("", 10);
        addrLabel = new JLabel("주        소");
        addrField = new JTextField("", 10);

        panelB = new JPanel();
        companyCreateButton = new JButton("회사등록");

        textFields[0] = nameField;
        textFields[1] = addrField;


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

        panelA.add(nameLabel);
        panelA.add(nameField);
        panelA.add(addrLabel);
        panelA.add(addrField);

        panelB.setSize(400, 40);
        panelB.setLocation(0, 180);
        panelB.setBackground(Color.pink);
        add(panelB);

        panelB.add(companyCreateButton);

        setVisible(true);
    }

    private void addEventListener() {
        companyCreateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton targetB = (JButton) e.getSource();

        if(targetB == companyCreateButton){
            String name = textFields[0].getText();
            String addr = textFields[1].getText();
            recruitSystemService.addCompany(name, addr);

        }

        if(targetB == companyCreateButton){
            this.dispose();
        }
        CompanyDAO companyDAO = new CompanyDAO();
    }

    public static void main(String[] args) {
        new CompanyCreateFrame();
    }
}
