package multi.intent.exam;

import android.os.Parcel;
import android.os.Parcelable;

// 안드로이드에서 intent에 객체를 공유하고 싶은 경우 Parcelable타입으로 정의
// => Parcelable를 implements하고 메소드를 적절하게 overriding
public class User implements Parcelable {
    String name;
    String tel;

    protected User(Parcel in) {
        name = in.readString();
        tel = in.readString();
    }

    // 안드로이드 os가 객체를 복원할 때
    // Creator 타입의 변수 CREATOR를 찾아서 CREATOR의 createFromParcel를 호출한 후 반환되는 값을 이용해서 사용
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // 객체를 intent에 담을때 자동으로 호출되는 메소드
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(tel);
    }

    public User(){

    }
    public User(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
