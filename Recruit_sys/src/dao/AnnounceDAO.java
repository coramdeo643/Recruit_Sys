package dao;

import dto.Announce;
import message.Handling;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnnounceDAO {

    // 공고 추가
    public void addAnnounce(Announce announce) throws SQLException {
        String sql = "INSERT INTO announce (company_name, address, content) VALUES ( ?, ?, ? ) ";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, announce.getCompanyName());
            preparedStatement.setString(2, announce.getAddress());
            preparedStatement.setString(3, announce.getContent());
            preparedStatement.executeUpdate();
        }
    }

    // 공고 전체 조회
    public List<Announce> getAllAnnounce() throws SQLException {
        List<Announce> announceList = new ArrayList<>();
        String sql = "select * from announce ";
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int companyId = resultSet.getInt("company_id");
                String companyName = resultSet.getString("company_name");
                String address = resultSet.getString("address");
                String content = resultSet.getString("content");
                int available = resultSet.getInt("available");

                announceList.add(new Announce(companyName, address, content));
            }
        }
        return announceList;
    }

    // 공고 선택 조회
    public List<Announce> choiceAnnounce(String comName, String content1) throws SQLException {
        List<Announce> announceList = new ArrayList<>();
        String sql = "select * from announce where company_name = ? or content like ? ";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, '%' + comName + '%');
            preparedStatement.setString(2, '%' + content1 + '%');
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int companyId = resultSet.getInt("company_id");
                String companyName = resultSet.getString("company_name");
                String address = resultSet.getString("address");
                String content = resultSet.getString("content");
                int available = resultSet.getInt("available");

                announceList.add(new Announce(companyName, address, content));
            }
        }
        return announceList;
    }

    // 회사 주소로 공고를 조회
    public List<Announce> selectAnnounceByCompanyAddress(String AnnounceAddress) throws SQLException {
        List<Announce> announceList = new ArrayList<>();
        // SELECT 쿼리
        // Conn, Pstmt, Rs
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String selectSql = "select * from announce where address = ? ";
            connection = DatabaseUtil.getConnection();
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, "address");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int companyId = resultSet.getInt("company_id");
                String name1 = resultSet.getString("company_name");
                String address = resultSet.getString("address");
                String contents = resultSet.getString("content");
                int available = resultSet.getInt("available");
                announceList.add(new Announce(name1, address, contents));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(Handling.FORMAT_EXCEPTION);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return announceList;
    }

    // 공고 삭제
    public int deleteApplication(String name, String content) throws SQLException {
        // 해당 id를 받아서 삭제 쿼리 실행
        // 자동 삭제하기

//        List<Announce> announceList = new ArrayList<>();
//        // try() {} catch(E . e) {} <-- 자동 리소스 닫아 주는 기능
//        // try {} catch (E . e) {}
        PreparedStatement preparedStatement = null;
        int resultSet1 = 0;
        Connection connection = DatabaseUtil.getConnection();

        try {
            connection.setAutoCommit(false);
            String deleteSql = " delete from announce where company_name = ? and content like ? ";
            connection = DatabaseUtil.getConnection();
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, content);
            resultSet1 = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(Handling.FORMAT_EXCEPTION);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return resultSet1;
    }
}