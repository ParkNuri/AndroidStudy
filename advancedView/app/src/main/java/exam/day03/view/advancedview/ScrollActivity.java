package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ScrollActivity extends AppCompatActivity {
    View img1;
    View img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_exam01);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
    }
    public void upclick(View v){
        up();
    }
    public void dwclick(View v){
        down();
    }
    // 버튼을 선택할 때 마다 이미지가 교체되어 보이도록 구현
    public void down(){
        //0번에 해당하는 img를 화면에 보이도록
            img1.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.VISIBLE);
     }
    public void up(){
        //0번에 해당하는 img를 화면에 보이도록

        img1.setVisibility(View.VISIBLE);
        img2.setVisibility(View.INVISIBLE);
    }
}
