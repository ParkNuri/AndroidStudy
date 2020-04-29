package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import exam.day03.view.selectview.R;

public class InflatorTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflator_test);

        Button btn = findViewById(R.id.btnAdd);
        final LinearLayout container = findViewById(R.id.container);

        // layout
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Context가 거의 모든 서비스 상수 정보를 가지고 있다.
                // casting required
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                // attachToRoot : 상속받기위해
                inflater.inflate(R.layout.include_view, container, true);

            }
        });
    }
}
