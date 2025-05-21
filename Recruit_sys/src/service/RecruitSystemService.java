package service;

import dto.*;
import dao.*;

import java.sql.SQLException;
import java.util.List;

public class RecruitSystemService {
    private UserDAO userDAO = new UserDAO();
    private CompanyDAO companyDAO = new CompanyDAO();
    private AnnounceDAO announceDAO = new AnnounceDAO();

    // ----------------------- 유저 -------------------------
    // 유저 추가하는 기능
    public void addUser(User user) {
        if (user.getName() != null && !user.getName().trim().isEmpty() ||
            user.getEmail() != null && !user.getEmail().trim().isEmpty()) {

            String checkEmail = user.getEmail().substring(user.getEmail().length() - 4);

            if(!checkEmail.equals(".com")) {
                throw new RuntimeException("잘못된 이메일 형식입니다.");
            }

            try {
                User user1 = userDAO.authenticateUser(user.getName());
                if (user1 == null) {
                    userDAO.addUser(user);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.err.println("유저를 추가할 수 없습니다");
                throw new RuntimeException(e);
            }
        }
    }

    // 전체 유저 조회하는 기능
    public List<User> getAllUser() {
        List<User> userList = null;

        try {
            userList = userDAO.getAllUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    // 특정 유저 검색하는 기능
    public List<User> getSelectedUser(String name, String checkAddress) {
        List<User> userList = null;

        try {
            userList = userDAO.getSelectedUser(name, checkAddress);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    // ----------------------- 회사 -------------------------
    // 회사 추가하는 기능
    public void addCompany(Company company) {
        if (company.getName() != null && !company.getName().trim().isEmpty() ||
            company.getAddress() != null && !company.getAddress().trim().isEmpty()) {
            try {
                Company company1 = companyDAO.authenticateCompany(company.getName());
                if(company1 != null) {
                    companyDAO.addCompany(company);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 회사 전부 조회하는 기능
    public List<Company> getAllCompany() {
        List<Company> companyList = null;

        try {
            companyList = companyDAO.getAllCompany();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companyList;
    }

    // ------------------ 공고 -----------------------

    public static void main(String[] args) {
        RecruitSystemService service = new RecruitSystemService();
        service.addUser(new User(0, "박철수", "v@gmail.bom", "asfa1242fasd", "경상남도"));
        List<User> userList = service.getAllUser();

        for (User u : userList) {
            System.out.println(u);
        }
    }
}
