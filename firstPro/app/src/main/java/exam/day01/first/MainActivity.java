package exam.day01.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void okClickEvent(View v){
        Toast.makeText(this,"확인버튼 클릭", Toast.LENGTH_LONG).show();
    }
    public void cancelClickEvent(View v){
        Toast.makeText(this,"취소버튼 클릭", Toast.LENGTH_SHORT).show();
    }
    public void delClickEvent(View v){
        Toast.makeText(this,"삭제버튼 클릭", Toast.LENGTH_LONG).show();
    }
}
