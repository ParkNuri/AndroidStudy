package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    // 클래스 내의 모든 메소드들이 사용할 수 있게
    ImageView img01;
    ImageView img02;
    int index=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img01 = findViewById(R.id.img01);
        img02 = findViewById(R.id.img02);


    }

    // 버튼이 클릭될 때 호출되는 메소드
    // View를 받아야 한다.
    public void myclick(View v){
        imageChange();
    }
    // 버튼을 선택할 때 마다 이미지가 교체되어 보이도록 구현
    public void imageChange(){
            //0번에 해당하는 img를 화면에 보이도록
        if(index==0){
            img01.setVisibility(View.VISIBLE);
            img02.setVisibility(View.INVISIBLE);
            Log.d("test","value");
            index = 1;
        }
        else if(index==1){
            img01.setVisibility(View.INVISIBLE);
            img02.setVisibility(View.VISIBLE);
            Log.d("test","value");
            index = 0;
        }

    }


}
