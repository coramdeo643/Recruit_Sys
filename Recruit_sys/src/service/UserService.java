package service;

import dto.*;
import dao.*;
import message.Handling;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    // 기본 변수
    /// 유저 변수
    private User user = new User();
    private UserDAO userDAO = new UserDAO();
    private List<User> userList = new ArrayList<>();

    // ----------------------- 유저 -------------------------
    // 유저 추가하는 기능
    public void addUser(String email, String password, String name, String address) {
        if (email != null && !email.trim().isEmpty() &&
            password != null && !password.trim().isEmpty() &&
            name != null && !name.trim().isEmpty() &&
            address != null && !address.trim().isEmpty()) {

            String checkEmail = "";
            try {
                checkEmail = email.substring(email.length() - 4);

                if (!checkEmail.equals(".com")) {
                    throw new RuntimeException(Handling.EMAIL_EXCEPTION);
                } else if (address.length() < 4) {
                    System.out.println(address.length());
                    throw new RuntimeException(Handling.ADDRESS_EXCEPTION);
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }

            try {
                user = authenticateUser(email);
                if (user == null) {
                    userDAO.addUser(new User(name, email, password, address));
                    System.out.println("회원가입이 완료되었습니다!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(Handling.FORMAT_EXCEPTION);
            }
        } else {
            throw new RuntimeException(Handling.FORMAT_EXCEPTION);
        }
    }

    // 전체 유저 조회하는 기능
    public List<User> getAllUser() {
        try {
            userList = userDAO.getAllUser();
        } catch (SQLException e) {
            throw new RuntimeException(Handling.FAIL_GET_EXCEPTION);
        }

        return userList;
    }

    // 특정 유저 검색하는 기능
    public List<User> getSelectedUser(String name, String checkAddress) {
        try {
            userList = userDAO.getSelectedUser(name, checkAddress);
        } catch (SQLException e) {
            throw new RuntimeException(Handling.FAIL_GET_EXCEPTION);
        }
        return userList;
    }

    // 인증 - 유저 추가랑 연계
    public User authenticateUser(String email) {
        try {
            user = userDAO.authenticateUser(email);
            if (user == null) {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(Handling.FAIL_GET_EXCEPTION);
        }
        return user;
    }
}