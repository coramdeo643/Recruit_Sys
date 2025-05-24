package service;

import dto.*;
import dao.*;
import message.Handling;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyService {
    /// 회사 변수
    private Company company = new Company();
    private CompanyDAO companyDAO = new CompanyDAO();
    private List<Company> companyList = new ArrayList<>();

    // 회사 추가하는 기능
    public void addCompany(String name, String address) {
        if (name != null && !name.trim().isEmpty() &&
                address != null && !address.trim().isEmpty()) {
            try {
                company = companyDAO.authenticateCompany(company.getName());
                if (company == null) {
                    companyDAO.addCompany(new Company(name, address));
                    System.out.println("회사 등록이 완료되었습니다!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.err.println(Handling.FAIL_ADD_EXCEPTION);
                throw new RuntimeException(e);
            }
        }
    }

    // 회사 전부 조회하는 기능
    public List<Company> getAllCompany() {
        try {
            companyList = companyDAO.getAllCompany();
        } catch (SQLException e) {
            throw new RuntimeException(Handling.FAIL_GET_EXCEPTION);
        }
        return companyList;
    }

    // 회사 선택 조회하는 기능
    public List<Company> getSelectedCompany(String name, String address) {
        try {
            companyList = companyDAO.getSelectedCompany(name, address);
        } catch (SQLException e) {
            throw new RuntimeException(Handling.FAIL_GET_EXCEPTION);
        }
        return companyList;
    }
}