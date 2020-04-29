package multi.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // "이것좀 실행해줘"하고 안드로이드 객체에 넘겨주기
                // 사용할 activity들은 반드시 Manifests에 등록되어 있어야 한다.
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // intent에 공유할 데이터 저장
                // intent.putExtra("저장할 데이터명::key", 데이터::value)
                intent.putExtra("info", "msg from firstAct");

                intent.putExtra("num", 1111);

                // 의뢰
                startActivity(intent);

            }
        });
    }
}
