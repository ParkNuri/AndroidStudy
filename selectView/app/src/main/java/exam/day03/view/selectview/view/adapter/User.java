package exam.day03.view.selectview.view.adapter;

// row에 출력할 데이터 정보를 담는 객체
public class User {
    int myImg;
    String name;
    String telNum;

    public User(int myImg, String name, String telNum) {
        this.myImg = myImg;
        this.name = name;
        this.telNum = telNum;
    }
    // android는 app의 특성상 해킹의 위험이 낮기 때문에 getter/setter 사용 x
    // 내장 DB에 저장

    //debugging 용. 작업 완료시 삭제
    @Override
    public String toString() {
        return "User{" +
                "myImg=" + myImg +
                ", name='" + name + '\'' +
                ", telNum='" + telNum + '\'' +
                '}';
    }
}
