package service;

import dto.*;
import dao.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    // 기본 변수
    /// 유저 변수
    private User user = new User();
    private UserDAO userDAO = new UserDAO();
    private List<User> userList = new ArrayList<>();

    public void start() {

    }

    public void login() {

    }

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
            } catch (StringIndexOutOfBoundsException e) {
                throw new RuntimeException("이메일치고는 너무 짧은것 같아요...");
            }

            if (!checkEmail.equals(".com")) {
                throw new RuntimeException("잘못된 이메일 형식입니다.");
            }

            if(address != null && address.length() < 4) {
                System.out.println(address.length());
                throw new RuntimeException("3글자 이하의 주소는 입력할 수 없습니다.");
            }

            try {
                user = authenticateUser(email, password);
                if (user == null) {
                    userDAO.addUser(new User(0, name, email, password,address));
                    System.out.println("회원가입이 완료되었습니다!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.err.println("회원가입을 할 수 없습니다");
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("뭔가 잘못됐어요... 올바르게 입력해주세요.");
        }
    }

    // 전체 유저 조회하는 기능
    public List<User> getAllUser() {
        try {
            userList = userDAO.getAllUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    // 특정 유저 검색하는 기능
    public List<User> getSelectedUser(String name, String checkAddress) {
        try {
            userList = userDAO.getSelectedUser(name, checkAddress);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    // 인증 - 유저 추가랑 연계
    public User authenticateUser(String email, String password) {
        try {
            user = userDAO.authenticateUser(email, password);
            if (user == null) {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}