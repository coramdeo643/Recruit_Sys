package service;

import dao.AnnounceDAO;
import dto.Announce;
import message.Handling;

import java.sql.SQLException;
import java.util.List;

public class AnnounceService {

    private final AnnounceDAO announceDAO = new AnnounceDAO();

    // 공고를 추가하는 서비스
    public void addAnnounce(Announce announce) throws SQLException {
        if (announce.getCompanyName() == null || announce.getContent() == null
                || announce.getCompanyName().trim().isEmpty() || announce.getContent().trim().isEmpty()) {
            throw new SQLException(Handling.ANNOUNCE_MISS_EXCEPTION);
        }
        AnnounceDAO announceDAO1 = new AnnounceDAO();

        try {
            if (announce == null) {
                announceDAO1.addAnnounce(new Announce(announce.getCompanyName(), announce.getAddress(), announce.getContent()));
                System.out.println("공고가 추가 되었습니다");
            }
            List<Announce> announce1 = getAllAnnounce();
        } catch (SQLException e) {
            throw new RuntimeException(Handling.FAIL_ADD_EXCEPTION);
        }
    }

    // 공고 전체 조회 서비스
    public List<Announce> getAllAnnounce() {
        List<Announce> announceList = null;
        try {
            announceList = announceDAO.getAllAnnounce();
        } catch (SQLException e) {
            throw new RuntimeException(Handling.FAIL_GET_EXCEPTION);
        }
        return announceList;
    }

    // 공고 선택 조회 서비스
    public List<Announce> choiceAnnounce(String name, String content) throws SQLException {
        List<Announce> announceList = null;
        try {
            announceList = announceDAO.choiceAnnounce(name, content);
        } catch (SQLException e) {
            throw new RuntimeException(Handling.FAIL_GET_EXCEPTION);
        }
        if (name == null || name.trim().isEmpty() ||
                content == null || content.trim().isEmpty()) {
            throw new SQLException(Handling.ANNOUNCE_INCORRECT_EXCEPTION);
        }
        return announceList;
    }

    // 회사 주소로 공고 조회
    public List<Announce> selectAnnounceAddress(String Address) throws SQLException {
        List<Announce> announceList = null;
        try {
            announceList = announceDAO.selectAnnounceByCompanyAddress(Address);
        } catch (SQLException e) {
            throw new RuntimeException(Handling.FAIL_GET_EXCEPTION);
        }
        if (Address == null || Address.trim().isEmpty()) {
            throw new SQLException(Handling.ADDRESS_EXCEPTION);
        }
        return announceList;
    }

    // 공고 선택 삭제 서비스
    public void deleteApplication(String name, String content) throws SQLException {
        if (name == null || content == null) {
            throw new SQLException(Handling.ANNOUNCE_MISS_EXCEPTION);
        } else {
            System.out.println("공고가 삭제 되었습니다");
        }

        announceDAO.deleteApplication(name, content);
    }
}