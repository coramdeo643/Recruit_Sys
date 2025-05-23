package dao;

import dto.Company;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {


    // 새로운 회사 추가
    public void addCompany(Company company) throws SQLException {
        String sql = "insert into company (company_name, address) values (?, ?) ";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, company.getName());
            pstmt.setString(2, company.getAddress());
            pstmt.executeUpdate();

        }
    }

    // 회사 목록 전체 조회
    public List<Company> getAllCompany() throws SQLException {
        List<Company> companyList = new ArrayList<>();
        String sql = "select * from company";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Company companyDto = new Company();
                companyDto.setId(rs.getInt("id"));
                companyDto.setName(rs.getString("company_name"));
                companyDto.setAddress(rs.getString("address"));
                companyList.add(companyDto);
            }
        }
        return companyList;
    }

    // 회사 선택 조회
    public List<Company> getSelectedCompany(String name, String address) throws SQLException {
        List<Company> companyList = new ArrayList<>();
        String sql = "select * from company where company_name like ? and address like ? ";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            pstmt.setString(2, "%" + address + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String company_name = rs.getString("company_name");
                String address1 = rs.getString("address");
                companyList.add(new Company(id, company_name, address1));
            }
        }
        return companyList;
    }

    // 회사 인증
    public Company authenticateCompany(String name) throws SQLException {
        String sql = "select * from company where company_name = ? ";
        Company company = null;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name1 = rs.getString("company_name");
                String address = rs.getString("address");

                company = new Company(id, name1, address);

            } else {
                return null;
            }
        }
        return company;
    }

    // 테스트 코드
//    public static void main(String[] args)  throws  SQLException {
//
//         // 회사 선택 조회 테스트
//        CompanyDAO companyDAO = new CompanyDAO();
//
//        List<Company> companyList = new ArrayList<>();
//        companyList = companyDAO.getSelectedCompany("네이버", "성남");
//
//        for(Company c : companyList) {
//            System.out.println(c);
//        }
//
//        try {
//            // companyDAO.addCompany(new Company(2, "r", "r")); // 회사추가 테스트
//            ArrayList<Company> companyArrayList = new ArrayList<>();
//            //companyDAO.authenticateCompany("카오"); // 회사 인증 테스트
//
//            for (int i = 0; i < companyDAO.getAllCompany().size(); i++) {
//                System.out.println(companyDAO.getAllCompany().get(i));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//   }
}
