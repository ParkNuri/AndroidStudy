package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import exam.day03.view.selectview.R;
import exam.day03.view.selectview.view.adapter.MyAdapter2;
import exam.day03.view.selectview.view.adapter.User;
import exam.day03.view.selectview.view.adapter.MyAdapter;

public class CustomAdapterTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter_test);

        TextView info = findViewById(R.id.txtInfo);
        ListView listView = findViewById(R.id.cust_listview);

        // 1. list에 출력할 데이터
        ArrayList<User> datalist = new ArrayList<User>();
        for(int i=1;i<100;i++){
            User user = new User(R.drawable.ic_launcher_foreground, "name"+i,"0000000"+i);

            datalist.add(user);
        }

        // 2. 사용자 정의 어댑터 객체 생성
        MyAdapter2 adapter = new MyAdapter2(this, R.layout.custrow2, datalist);

        //getView를 계속 호출하기때문에 성능 향상을 위해 viewgroup을 생성한다.

        // 3. ListView에 어댑터 연결
        listView.setAdapter(adapter);


    }
}
