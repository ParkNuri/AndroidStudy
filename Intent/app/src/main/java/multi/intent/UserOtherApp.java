package multi.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserOtherApp extends AppCompatActivity {
    public static final int SELECT_VIEW = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_other_test);
    }

    public void calActivity(View view){
        // 다른 앱의 액티비티를 호출
        // 암시적 intent - 정확하게 실행할 액티비티명을 모르는 경우
        // => 해당 action 범주에 있는 모든 앱을 선택지로 불러옴
        // ex) 카카오톡 카메라 기능 클릭시 여러 카메라앱 리스트 호출


        // action명 등록
        Intent intent = new Intent("com.exam.selectview");

        startActivityForResult(intent, SELECT_VIEW);

    }
}
