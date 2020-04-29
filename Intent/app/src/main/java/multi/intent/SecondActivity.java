package multi.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        // intent 객체 추출
        Intent intent = getIntent();

        // intent 객체에서 공유된 값 꺼내기
        String msg = intent.getStringExtra("info");
        int num = intent.getIntExtra("num",0);

        Toast.makeText(this, "value from 1: "+msg+", "+num,Toast.LENGTH_SHORT).show();

        Button bt2 = findViewById(R.id.bt2);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();   // activity 종료
            }
        });
    }
}
