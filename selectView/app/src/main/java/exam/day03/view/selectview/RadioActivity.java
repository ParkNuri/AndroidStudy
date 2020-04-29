package exam.day03.view.selectview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import exam.day03.view.selectview.R;

public class RadioActivity extends AppCompatActivity {
    // 뷰의 주소값을 담을 참조변수
    RadioButton radio3, radio4;
    RadioGroup group1, group2;
    TextView text1, text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        // 뷰의 주소 값을 얻어온다.
        radio3 = (RadioButton)findViewById(R.id.radioButton3);
        radio4 = (RadioButton)findViewById(R.id.radioButton4);
        group1 = (RadioGroup)findViewById(R.id.group1);
        group2 = (RadioGroup)findViewById(R.id.group2);
        text1 = (TextView)findViewById(R.id.textView);
        text2 = (TextView)findViewById(R.id.textView2);

        // 라디오 그룹에 리스너를 설정한다.
        /*RadioListener listener = new RadioListener();
        group1.setOnCheckedChangeListener(listener);
        group2.setOnCheckedChangeListener(listener);*/
    }

    public void radioCheck(View v){
        radio3.setChecked(true);
        radio4.setChecked(true);
    }
    public void getCheckStatus(View v){
        //★★★★difference★★★★
        //라디오 그룸 내에서 선택되어 있는 라디오 버튼을 반환
        int id1 = group1.getCheckedRadioButtonId();
        int id2 = group2.getCheckedRadioButtonId();
        RadioButton radio1 = findViewById(id1);
        RadioButton radio2 = findViewById(id2);

/*
        text1.setText(id1+"radio button is selected");
        text2.setText(id2+"radio button is selected");
*/

        text1.setText(radio1.getText()+"radio button is selected");
        text2.setText(radio2.getText()+"radio button is selected");

    }
    //CompoundButton <= check box 시
    //RadioGroup <= radio 버튼시
    class RadioListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Log.d("radio","group getid: "+group.getId()+"========== checkedId: "+checkedId);
            switch (group.getId()){
                case R.id.group1:
                    switch (checkedId){
                        case R.id.radioButton:
                            Log.d("radio","1번 그룹의 1-1버튼");
                    }
                    break;
                case R.id.group2:

            }
        }
    }
}










