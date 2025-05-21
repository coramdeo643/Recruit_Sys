package dao;

import dto.Company;
import dto.User;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // 유저 추가
    public void addUser(User user) throws SQLException {
        String insertSql = "insert into user(user_name, email, password, address) values (?, ?, ?, ?) ";
        try(Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getAddress());
            pstmt.executeUpdate();
        }
    }

    // 전체 유저 조회 --> 회사가 넘겨 받아서 사용할 메서드
    public List<User> getAllUser() throws SQLException {
        List<User> userList = new ArrayList<>();
        String checkSql = "select * from user ";

        try(Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(checkSql);
            ResultSet rs = pstmt.executeQuery()) {

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("user_name");
                String email = rs.getString("email");
                String password = null;
                String address = rs.getString("address").substring(0, 3).trim() + "****";

                userList.add(new User(id, name, email, password, address));
            }
        }
        return userList;
    }

    // 선택 유저 조회 --> 회사가 넘겨받아서 사용할 메서드
    public List<User> getSelectedUser(String name, String checkAddress) throws SQLException {
        List<User> userList = new ArrayList<>();
        String selectUserSql = "select id, user_name, email, address from user where user_name = ? and address like ? ";

        try(Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(selectUserSql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, (checkAddress + "%"));
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String user_name = rs.getString("user_name");
                String email = rs.getString("email");
                String password = null;
                String address = rs.getString("address").substring(0, 3).trim() + "****";

                userList.add(new User(id, user_name, email, password, address));
            }
        }
        return userList;
    }

    // 유저 탈퇴
    public void deleteUser(String name) throws SQLException {
        String deleteSql = "delete from user where user_name = ? ";

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    // 유저 인증
    public User authenticateUser(String name) throws SQLException {
        String checkSql = "select * from user where user_name = ? ";
        User user = null;

        try(Connection conn = DatabaseUtil.getConnection();) {
            PreparedStatement pstmt = conn.prepareStatement(checkSql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("id");
                String name1 = rs.getString("user_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String address = rs.getString("address");

                user = new User(id, name1, email, password, address);
            } else {
                return null;
            }
        }
        return user;
    }

    // TODO 테스트 코드는 이후에 삭제될 예정입니다.
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        List<User> userList = new ArrayList<>();

        try {
//            userDAO.addUser(new User(1, "김철수", "a@naver.com", "asd1234", "부산시 부산진구")); // 유저 추가 메서드 테스트
            userList = userDAO.getAllUser(); // 유저 전체 조회 메서드 테스트
//            userList = userDAO.getSelectedUser("이철수", ""); // 유저 선택 조회 메서드 테스트
//            userDAO.authenticateUser("김철수"); // 유저 인증 메서드 테스트

            for (int i = 0; i < userList.size(); i++) {
                System.out.println(userList.get(i));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
