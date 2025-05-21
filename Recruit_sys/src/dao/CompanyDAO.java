package dao;

import dto.Company;
import util.Database;

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

        try (Connection conn = Database.getConnection();
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
        try (Connection conn = Database.getConnection();
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
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Company companyDTO = new Company();
                companyDTO.setName(rs.getString("company_name"));
                return companyDTO;
            } else {
                throw new SQLException("회사가 존재하지 않습니다.");
            }
        }
    }

    public static void main(String[] args) {

        CompanyDAO companyDAO = new CompanyDAO();

        try {
            companyDAO.addCompany(new Company(2, "r", "r")); // 추가 테스트
            // ArrayList<Company> companyArrayList = new ArrayList<>();
            //companyDAO.authenticateCompany("카오"); // 인증 테스트

            for (int i = 0; i < companyDAO.getAllCompany().size(); i++) {
                System.out.println(companyDAO.getAllCompany().get(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
