package service;

import dao.CompanyDAO;
import dao.UserDAO;
import dto.Company;
import dto.User;

import java.sql.SQLException;
import java.util.List;

public class RecruitSystemService {
    private UserDAO userDAO = new UserDAO();
    private CompanyDAO companyDAO;

    // ----------------------- 유저 -------------------------
    public void addUser(User user) {
        if (user != null && !user.getName().trim().isEmpty()) {
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

    public List<User> getAllUser() {
        List<User> userList = null;

        try {
            userList = userDAO.getAllUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

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
    public void addCompany(Company company) {
        if (company != null && !company.getName().trim().isEmpty()) {
            try {
                companyDAO.addCompany(company);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Company> getAllCompany() {
        List<Company> companyList = null;

        try {
            companyList = companyDAO.getAllCompany();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companyList;
    }

    public static void main(String[] args) {
        RecruitSystemService service = new RecruitSystemService();
        service.addUser(new User(0, "김철수", "a@gmail.com", "asfadsfasd", "dsadsad"));
        List<User> userList = service.getAllUser();
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i));
        }
    }
}
