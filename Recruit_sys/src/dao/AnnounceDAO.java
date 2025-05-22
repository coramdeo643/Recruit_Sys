package dao;

import dto.Announce;
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
            preparedStatement.setString(1, announce.getCompany_name());
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
                int user_id = resultSet.getInt("user_id");
                int company_id = resultSet.getInt("company_id");
                String companyName = resultSet.getString("company_name");
                String address = resultSet.getString("address");
                String content = resultSet.getString("content");
                int available = resultSet.getInt("available");

                announceList.add(new Announce(id, user_id, company_id, companyName, address, content, available));
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
                int user_id = resultSet.getInt("user_id");
                int company_id = resultSet.getInt("company_id");
                String companyName = resultSet.getString("company_name");
                String address = resultSet.getString("address");
                String content = resultSet.getString("content");
                int available = resultSet.getInt("available");

                announceList.add(new Announce(id, user_id, company_id, companyName, address, content, available));
            }
        }
        return announceList;
    }

    // 회사 이름과, 내용을 값을 받아서 조회 하는 기능
    public List<Announce> selectApplication(String name, String content) throws SQLException {
        List<Announce> announceList = new ArrayList<>();
        // SELECT 쿼리
        // Conn, Pstmt, Rs
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String selectSql = "select * from announce where company_name = ? like content = ? ";
            connection = DatabaseUtil.getConnection();
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, "company_name");
            preparedStatement.setString(2, "content");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                int company_id = resultSet.getInt("company_id");
                String name1 = resultSet.getString("company_name");
                String address = resultSet.getString("address");
                String contents = resultSet.getString("content");
                int available = resultSet.getInt("available");
                announceList.add(new Announce(id, user_id, company_id, name1, address, contents, available));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet1 = 0;

        try {
            String deleteSql = " delete from announce where company_name = ? and content like ? ";
            connection = DatabaseUtil.getConnection();
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, content);
            resultSet1 = preparedStatement.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException();
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




//            while(resultSet) {
//                int id = resultSet.getInt("id");
//                String name1 = resultSet.getString("company_name");
//                String contents = resultSet.getString("content");
//                announceId.add(new Announce());
//            }
//
//            preparedStatement1 = connection.prepareStatement(deleteSql);
//            preparedStatement1.setString(1, name);
//            preparedStatement1.setString(2, "%" + content + "%");
//
//            int result = 0;
//            try {
//                result = preparedStatement1.executeUpdate();
//                System.out.println("111111111111 로깅 확인");
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.out.println("22222222222 오류 추적 확인");
//                throw new SQLException();
//            }
//
//            if (result >= 0) {
//                System.out.println("삭제 성공 했습니다");
//            } else {
//                System.out.println("삭제에 실패했습니다.");
//            }
//
//            System.out.println("3333333333333333333333");
//            Integer available = resultSet.getInt("available");
//
//            System.out.println("44444444444444444444");
//            if (available < 1) {
//                System.out.println("구인 종료");
//            } else {
//                System.out.println(" 구인 중");
//            }
//        } catch (SQLException e) {
//            throw new SQLException();
//        } finally {
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (preparedStatement1 != null) {
//                    preparedStatement1.close();
//                }
//
//                if (preparedStatement2 != null) {
//                    preparedStatement2.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }

    }

    public static void main(String[] args) throws SQLException {

        // 선택 공고 조회 테스트
        Scanner scanner = new Scanner(System.in);
        System.out.println("조회 할 공고를 선택해주세요");
        String name = scanner.next();
        String content = scanner.next();
        scanner.close();

        AnnounceDAO announceDAO = new AnnounceDAO();
        List<Announce> announceList1 = new ArrayList<>();

        try {
            announceList1 = announceDAO.selectApplication(name, content);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < announceList1.size(); i++) {
            System.out.println(announceList1.get(i));
        }

        System.out.println("&&건이 조회 되었습니다");

        List<Announce> announceList = new ArrayList<>();
        int test = announceDAO.deleteApplication("카카오", "모집");
        announceList = announceDAO.getAllAnnounce();

        for(Announce a : announceList) {
            System.out.println(a);
        }

        // 공고 추가 테스트
        // 1. 광고 DAO 메모리 올리기
        // 2. 광고 추가 기능 테스트 (DB 조회)

        //        AnnounceDAO announceDAO = new AnnounceDAO();
        //        Announce announce = new Announce(1, "네이버", "경기도 성남시 분당구 불정로 6", "흑흑", 1);
        //        announceDAO.addAnnounce(announce);


        // 공고 전체 조회 테스트1
        //        AnnounceDAO announceDAO1 = new AnnounceDAO();
        //        List<Announce> announceList = new ArrayList<>();
        //        try {
        //            announceList = announceDAO1.getAllAnnounce();
        //        } catch (SQLException e) {
        //            throw new RuntimeException(e);
        //        }
        //
        //        for (int i = 0; i < announceList.size(); i++) {
        //            System.out.println(announceList.get(i));
        //        }
        //    }

        // 공고 선택 조회
        //        AnnounceDAO announceDAO = new AnnounceDAO();
        //        List<Announce> announceList1 = new ArrayList<>();
        //        try {
        //            announceList1 = announceDAO.choiceAnnounce("삼성전자", "모집");
        //        } catch (SQLException e) {
        //            throw new RuntimeException(e);
        //        }
        //        for (int i = 0; i < announceList1.size(); i++) {
        //            System.out.println(announceList1.get(i));
        //
        //        }
        //
        //


    }
}