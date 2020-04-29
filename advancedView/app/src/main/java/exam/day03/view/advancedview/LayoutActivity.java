package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

// 이벤트가 발생시킬 source 객체가 있는 activity에서 이벤트를 처리하는 작업도 같이 할 수 있도록 구현
// 1. 이벤트에 반응하는( 미리 정해져있다. ) 클래스를 상속받는다.
// 2. 메소드를 overriding 한다.
// ex) 버튼을 클릭할때
//     실행될 listener => View.OnClickListener의 onClick 메소드 호출
public class LayoutActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView firstView;
    ImageView secondView;
    Button btnup;
    Button btndown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_exam);

        firstView = findViewById(R.id.firstImg);
        secondView = findViewById(R.id.secondImg);
        btnup = findViewById(R.id.btnup);
        btndown = findViewById(R.id.btndown);

        //setOnclickListener라는 메소드를 이용해 이벤트 소스에 이벤트가 발생하였을때
        // 처리할 기능이 구현된 Listener 객체가 어떤 것인지 등록
        btnup.setOnClickListener(this);
        btndown.setOnClickListener(this);
    }

    //onClick 메소드를 호출하는 이벤트는 다양
    //매개변수에 전달되는 view는 이벤트를 발생시킨 소스 객체
    @Override
    public void onClick(View v) {
        Log.d("myevent", "이벤트 발생 - 이벤트 처리");
        if(v.getId()==R.id.btnup){
            imageUp();
        }
        else if(v.getId()==R.id.btndown){
            imageDown();
        }

    }
    public void imageDown(){
        // setImageResource() : image id 받기. image를 리소스에 연결
        firstView.setImageResource(0);
        secondView.setImageResource(R.drawable.beach);
        //setImageResource 메소드를 이용해서 이미지를 변경했다고 하더라도 화면 갱신이 자동으로 되지 않기 때문에
        //때문에 변경되지 않는다.
        //애니메이션을 적용하는 경우는 바로바로 반영이 되어야 하므로 화면을 갱신해야한다.
        secondView.invalidate();
        firstView.invalidate();

    }
    public void imageUp(){
        // setImageResource() : image id 받기. image를 리소스에 연결
        secondView.setImageResource(0);
        firstView.setImageResource(R.drawable.beach);
        //setImageResource 메소드를 이용해서 이미지를 변경했다고 하더라도 화면 갱신이 자동으로 되지 않기 때문에
        //때문에 변경되지 않는다.
        //애니메이션을 적용하는 경우는 바로바로 반영이 되어야 하므로 화면을 갱신해야한다.
        secondView.invalidate();
        firstView.invalidate();

    }
}
