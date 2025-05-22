package dao;

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
                String password = "";
                String address = rs.getString("address").substring(0, 3).trim() + "****";

                userList.add(new User(id, name, email, password, address));
            }
        }
        return userList;
    }

    // 선택 유저 조회 --> 회사가 넘겨받아서 사용할 메서드git
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
                String password = "";
                String address = rs.getString("address").substring(0, 3).trim() + "****";

                userList.add(new User(id, user_name, email, password, address));
            }
        }
        return userList;
    }

    // 유저 인증
    public User authenticateUser(String email, String password) throws SQLException {
        String checkSql = "select * from user where email = ? and password = ? ";
        User user = null;

        try(Connection conn = DatabaseUtil.getConnection();) {
            PreparedStatement pstmt = conn.prepareStatement(checkSql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("user_name");
                String email1 = rs.getString("email");
                String password1 = rs.getString("password");
                String address = rs.getString("address");

                user = new User(id, name, email1, password1, address);
            } else {
                return null;
            }
        }
        return user;
    }
}
