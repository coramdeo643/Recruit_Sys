package handling;

public class IllegalUserFormat extends RuntimeException {
    public IllegalUserFormat() {
        System.err.println("잘못된 이메일 형식입니다");
    }

    public IllegalUserFormat(Exception e) {
        System.err.println("잘못된 이메일 형식입니다");
    }
}
