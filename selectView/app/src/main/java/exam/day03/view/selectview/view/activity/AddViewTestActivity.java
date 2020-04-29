package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import exam.day03.view.selectview.R;

public class AddViewTestActivity extends AppCompatActivity {
    // 나(AddViewTestActivity)는 context
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ///////////////////////////////////////////////////////////////////
        //|||         |||
        //|||         ||||
        //|||         ||| ||
        //|||         |||||||
        //|||||||||   |||  |||

        // activity에서 직접 layout 만들기
        final LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // LayoutParams : layout의 속성을 관리
        //              - LinearLayout의 inner class
        // width/height 지정
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        // Layout에 추가할 view를 생성 - 상위 view의 크기 정보를 갖고 있는 LayoutParams 설정
        Button btn = new Button(this);
        btn.setText("코드로 만들어진 버튼");

        // button은 상위 contents 에 종속적이기 때문에 상위의 width/height 값을 넘겨줘야한다.
        btn.setLayoutParams(params);

        //Layout에 View를 추가
        layout.addView(btn);

        //layout setting
        setContentView(layout);


        //android.content.Context :
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Button btn2 = new Button(AddViewTestActivity.this);
                btn2.setText("이벤트로 만들어진 객체");
                layout.addView(btn2);
            }


        });
    }
}
