---

# Recruitment System (채용 시스템)

이 프로젝트는 **Java Swing**을 활용하여 개발된 간단한 채용 관리 프로그램입니다. **MySQL 8** 데이터베이스와 연동하여 회원, 회사, 채용 공고에 대한 기본적인 관리 기능을 제공하며, 사용자가 직관적으로 시스템을 조작할 수 있는 GUI 환경을 목표로 합니다.

---

## 🚀 주요 기능

### 회원 관리
* **회원 추가**: 새로운 사용자를 등록합니다. (이메일 중복 방지 기능 포함)
* **전체 회원 조회**: 시스템에 등록된 모든 회원의 목록을 확인합니다.
* **선택 회원 조회**: 이름 또는 주소로 특정 회원을 검색하여 조회합니다.
* **회원 인증**: 이메일과 비밀번호를 통해 로그인 기능을 제공합니다.

### 회사 관리
* **회사 추가**: 새로운 회사 정보를 등록합니다. (회사명 중복 방지 기능 포함 예정)
* **전체 회사 조회**: 등록된 모든 회사의 목록을 확인합니다.

### 채용 공고 관리
* **공고 추가**: 새로운 채용 공고를 등록합니다.
* **공고 전체 조회**: 모든 채용 공고 목록을 확인합니다.
* **공고 선택 조회**: 특정 조건으로 공고를 검색하여 조회합니다.
* **공고 삭제**: 등록된 채용 공고를 삭제합니다.

---

## 🛠️ 기술 스택

* **언어**: Java 21
* **데이터베이스**: MySQL 8
* **데이터베이스 연결**: JDBC
* **의존성 관리/코드 간소화**: Lombok
* **개발 환경**: IntelliJ IDEA
* **버전 관리**: Git

---

## 📦 패키지 구조

```
src/main/java
├── util
│   └── DatabaseUtil.java     // 데이터베이스 연결 유틸리티
├── dto
│   ├── Announce.java         // 채용 공고 데이터 객체 (@Getter, @Setter 등 Lombok 적용)
│   ├── Company.java          // 회사 데이터 객체 (@Getter, @Setter 등 Lombok 적용)
│   └── User.java             // 사용자 데이터 객체 (@Getter, @Setter 등 Lombok 적용)
├── dao
│   ├── AnnounceDAO.java      // 채용 공고 데이터 접근 객체 (CRUD)
│   ├── CompanyDAO.java       // 회사 데이터 접근 객체 (CRUD)
│   └── UserDAO.java          // 사용자 데이터 접근 객체 (CRUD)
├── service
│   ├── AnnounceService.java  // 채용 공고 관련 비즈니스 로직
│   ├── CompanyService.java   // 회사 관련 비즈니스 로직
│   └── UserService.java      // 사용자 관련 비즈니스 로직
├── swing                     // Swing UI 프레임 모음
│   ├── AnnounceFrame.java    // 채용 공고 목록 및 관리 UI
│   ├── CompanyCreateFrame.java // 회사 등록 UI
│   ├── GetAllCompanyFrame.java // 회사 목록 조회 UI
│   ├── GetAllUserFrame.java  // 사용자 목록 조회 UI
│   ├── LoginFrame.java       // 로그인 UI (프로그램 시작점)
│   └── UserCreateFrame.java  // 회원 등록 UI
└── Main.java                 // 프로그램 진입점
```

---

## 📅 개발 일정

* **Day 1: 설정 및 설계**
    * GitHub 리포지토리 생성 및 브랜치 전략 수립.
    * MySQL 데이터베이스 및 테이블 스키마 생성.
    * DTO 및 DAO 초기 설계 (ERD 다이어그램 포함).
* **Day 2: DAO 및 DTO 구현**
    * `Announce`, `Company`, `User` DTO 클래스 구현 (Lombok 적용).
    * `AnnounceDAO`, `CompanyDAO`, `UserDAO` 클래스에 기본 CRUD 기능 구현 및 테스트.
    * Swing UI 초기 구현.
* **Day 3: Service 및 Swing 구현**
    * Service 레이어에서 비즈니스 로직 구현 및 DAO와 연동.
    * Swing으로 각 화면 UI (로그인, 회원가입, 목록 등) 완성 및 기능 통합.
    * 초기 버그 수정.
* **Day 4: 테스트 및 마무리**
    * 최종 기능 테스트 및 디버깅.
    * UI 개선 및 코드 리뷰.
    * Git `main` 브랜치로 최종 병합.

---
