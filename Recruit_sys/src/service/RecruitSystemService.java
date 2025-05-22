package service;

import dto.*;
import dao.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RecruitSystemService {
    // 기본 변수
    /// 유저 변수
    private User user = new User();
    private UserDAO userDAO = new UserDAO();
    private List<User> userList = new ArrayList<>();

    /// 회사 변수
    private Company company = new Company();
    private CompanyDAO companyDAO = new CompanyDAO();
    private List<Company> companyList = new ArrayList<>();

    /// 공고 변수
    private Announce announce = new Announce();
    private AnnounceDAO announceDAO = new AnnounceDAO();
    private List<Announce> announceList = new ArrayList<>();

    /// 그 외
    private Scanner scanner = new Scanner(System.in);

    // 입력값
    private int selected;
    private int inputLoginType;

    /// 회사 및 공고 정보
    private String inputComName;
    private String inputComAddress;
    private String inputComContent;

    /// 유저 정보
    private String inputUserName;
    private String inputUserEmail;
    private String inputUserAddress;
    private String inputUserPW;

    public void start() {
        while(true) {
            System.out.printf("안녕하세요. 혹시 어떤게 필요하신가요? 1. 로그인 / 2. 프로그램 종료\n입력: ");
            try {
                selected = scanner.nextInt();
            } catch (InputMismatchException e) {
                throw new InputMismatchException("어라? 뭔가 int 값이 아니네요?");
            }

            switch (selected) {
                case 1:
                    System.out.println("로그인을 선택하셨네요!");
                    login();
                    break;
                case 2:
                    System.out.println("프로그램을 종료합니다...");
                    return;
                default:
                    System.err.println("1 또는 2만 입력해주세요!");
            }
        }
    } // Okay

    public void login() {
        System.out.printf("혹시 로그인 유형은 어떻게 되시나요? 1. 회사 관리자 / 2. 구직자 / 3. 프로그램 종료\n입력: ");

        try {
            inputLoginType = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("어라? 뭔가 int 값이 아니네요?");
        }

        while(true) {
            switch (inputLoginType) {
                case 1:
                    System.out.printf("회사 관리자시군요, 회사의 이름을 입력해주세요!\n입력: ");
                    try {
                        inputComName = scanner.next();
                    } catch (InputMismatchException e) {
                        throw new InputMismatchException("String 값만 넣어주세요!");
                    }

                    if(inputComName != null && !inputComName.trim().isEmpty()) {
                        try {
                            company = companyDAO.authenticateCompany(inputComName);
                            if(company != null) {
                                acting(company);
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException("존재하지 않는 회사입니다!");
                        }
                    } else {
                        System.err.println("입력받은 회사 이름이 null 값이거나 아예 비어있습니다.");
                    }
                    break;
                case 2:
                    try {
                        System.out.printf("구직자이시군요, 먼저, 이메일을 입력해주세요!\n입력: ");
                        inputUserEmail = scanner.next();

                        System.out.printf("다음은, 비밀번호를 입력해주세요.\n입력: ");
                        inputUserPW = scanner.next();
                    } catch (InputMismatchException e) {
                        throw new InputMismatchException("String 값만 넣어주세요!");
                    }

                    if(inputUserEmail != null && !inputUserEmail.trim().isEmpty() ||
                        inputUserPW != null && !inputUserPW.trim().isEmpty()) {
                        try {
                            user = userDAO.authenticateUser(inputUserEmail, inputUserPW);
                            if(user != null) {
                                acting(user);
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException("이메일이 없거나 잘못된 비밀번호입니다.");
                        }
                    } else {
                        System.err.println("입력받은 사용자의 이메일과 비밀번호가 null 값이거나 아예 비어있습니다.");
                    }
                case 3:
                    System.out.println("프로그램을 종료합니다...");
                    return;
                default:
                    System.err.println("1~3의 정수값만 입력해주세요!");
            }
        }
    } // Okay

    // TODO 메서드 만들어야 함
    public void logout() {

    }

    public void acting(User user) {
        System.out.printf("안녕하세요! [" + user.getName() + "] 님! 무엇이 필요하신가요?\n1. 전체 공고 조회 / 2. 선택 공고 조회 / 3. 지원서 제출 / 4. 로그아웃\n입력: ");

        try {
            selected = scanner.nextInt();
        } catch(InputMismatchException e) {
            throw new InputMismatchException("어라? 뭔가 int 값이 아니네요?");
        }

        while(true) {
            switch (selected) {
                case 1:
                    System.out.println("전체 공고를 조회합니다");

                    break;
                case 2:
                    System.out.println("공고를 선택 조회합니다.");
                    break;
                case 3:
                    System.out.println("원하는 회사에 지원합니다.");
                    break;
                case 4:
                    System.out.println("로그아웃 되었습니다.");
                    user = null;
                    start();
                    break;
                default:
                    System.err.println("1~4의 정수값만 입력해주세요!");
            }
        }
    }

    public void acting(Company company) {
        System.out.printf("안녕하세요! [" + company.getName() + "] 님! 무엇이 필요하신가요?\n1. 공고 추가 / 2. 전체 공고 조회 / 3. 공고 삭제 / 4. 로그아웃\n입력: ");

        try {
            selected = scanner.nextInt();
        } catch(InputMismatchException e) {
            throw new InputMismatchException("어라? 뭔가 int 값이 아니네요?");
        }

        while(true) {
            switch (selected) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    company = null;
                    start();
                    break;
                default:
                    System.err.println("1~4의 정수값만 입력해주세요!");
            }
        }
    }

    // ----------------------- 유저 -------------------------
    // 유저 추가하는 기능
    public void addUser(String name, String email, String address, String password) {
        if (name != null && !name.trim().isEmpty() ||
            email != null && !email.trim().isEmpty() ||
            address != null && !address.trim().isEmpty() ||
            password != null && !password.trim().isEmpty()) {

            String checkEmail = email.substring(email.length() - 4);

            if(!checkEmail.equals(".com")) {
                throw new RuntimeException("잘못된 이메일 형식입니다.");
            }

            try {
                user = authenticateUser(email, password);
                if (user == null) {
                    userDAO.addUser(new User(0,name, email, address, password));
                    System.out.println("회원가입이 완료되었습니다!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.err.println("회원가입을 할 수 없습니다");
                throw new RuntimeException(e);
            }
        }
    }

    // 전체 유저 조회하는 기능
    public List<User> getAllUser() {
        try {
            userList = userDAO.getAllUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    // 특정 유저 검색하는 기능
    public List<User> getSelectedUser(String name, String checkAddress) {
        try {
            userList = userDAO.getSelectedUser(name, checkAddress);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public User authenticateUser(String email, String password) {
        try {
            user = userDAO.authenticateUser(email, password);
            if (user == null) {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    // ----------------------- 회사 -------------------------
    // 회사 추가하는 기능
    public void addCompany(String name, String address) {
        if (name != null && !name.trim().isEmpty() ||
            address != null && !address.trim().isEmpty()) {
            try {
                company = companyDAO.authenticateCompany(company.getName());
                if(company == null) {
                    companyDAO.addCompany(new Company(0, name, address));
                    System.out.println("회사 등록이 완료되었습니다!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.err.println("회사 등록을 할 수 없습니다");
                throw new RuntimeException(e);
            }
        }
    }

    // 회사 전부 조회하는 기능
    public List<Company> getAllCompany() {
        try {
            companyList = companyDAO.getAllCompany();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companyList;
    }

    public static void main(String[] args) {
        RecruitSystemService service = new RecruitSystemService();
        service.start();
    }
}