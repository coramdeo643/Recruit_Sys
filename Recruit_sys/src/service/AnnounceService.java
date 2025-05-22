package service;

import dao.AnnounceDAO;
import dto.Announce;
import java.sql.SQLException;
import java.util.List;

public class AnnounceService {

    private final AnnounceDAO announceDAO = new AnnounceDAO();

    // 공고를 추가하는 서비스
    public void addAnnounce(Announce announce) throws SQLException {
        if (announce.getCompany_name() == null || announce.getContent() == null
                || announce.getCompany_name().trim().isEmpty() || announce.getContent().trim().isEmpty()) {
            throw new SQLException("공고 이름과 공고 내용은 필수값 입니다.");
        }
    }

    // 공고 전체 조회 서비스
    public List<Announce> getAllAnnounce() throws SQLException {
        return getAllAnnounce();
    }

    // 공고 선택 조회 서비스
    public List<Announce> choiceAnnounce(String name, String content) throws SQLException {
        if (name == null || name.trim().isEmpty() ||
                content == null || content.trim().isEmpty()) {
            throw new SQLException("올바른 공고 이름과 공고 내용을 선택 해주세요");
        }
        return announceDAO.choiceAnnounce(name, content);
    }

    // 회사 이름과, 내용을 값을 받아서 조회 하는 기능
    public List<Announce> selectApplication(String name, String content) throws SQLException {
        if (name == null || name.trim().isEmpty() ||
                content == null || content.trim().isEmpty()) {
            throw  new SQLException("올바른 공고 이름과 공고 내용을 입력 해주세요");
        }
        return announceDAO.selectApplication(name,content);
    }

    // 공고 삭제 서비스
    public void deleteApplication(String name, String content) throws SQLException {
        if (name ==null || content == null) {
            throw new SQLException("올바른 공고 이름과 공고 내용을 입력 해주세요");
        }
        announceDAO.deleteApplication(name,content);

    }
}