package exam.day02.view.layout;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// 액티비티가 실행될때 TextView의 문자열을 변경

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);   //layout을 먼저 setting해야 view를 사용할 수 있다
        TextView tv = findViewById(R.id.second_txtView);  //second_txtView의 id를 자동으로 import해온다.


        tv.setText("안녕~~~~");
    }
}
