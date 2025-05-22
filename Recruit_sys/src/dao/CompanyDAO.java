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

    // 회사목록 전체 조회
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

                company = new Company(id,name1, address);

            } else {
                return null;
            }
        }
        return company;
    }

    public static void main(String[] args) {

        CompanyDAO companyDAO = new CompanyDAO();

        try {
            // companyDAO.addCompany(new Company(2, "r", "r")); // 추가 테스트
            ArrayList<Company> companyArrayList = new ArrayList<>();
            //companyDAO.authenticateCompany("카오"); // 인증 테스트

            for (int i = 0; i < companyDAO.getAllCompany().size(); i++) {
                System.out.println(companyDAO.getAllCompany().get(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
