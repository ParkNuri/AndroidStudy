package exam.day03.view.support_lib.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import exam.day03.view.support_lib.R;


public class MainActivity extends AppCompatActivity {
    // 화면에 연결할 fragment 객체를 생성한다.
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnFirst = findViewById(R.id.btnAddFrag);
        Button btnRemove = findViewById(R.id.btnRemoveFrag);
        Button btnSecond = findViewById(R.id.btnSecondFrag);
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment("first");
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment("remove");
            }
        });
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment("second");
            }
        });

    }

    // 구분해 놓은 fragment를 교체해서 보여줄 메소드
    public void setFragment(String name) {
        // fragment 객체를 관리하는 관리자 객체를 구한다.
        FragmentManager fragmentManager = getSupportFragmentManager();

        // fragment 작업을 시작하기 위한 transaction 객체를 구한다.
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (name) {
            case "first":
                // 특정 영역의 fragment를 교체
                // replace(교체할 화면 위치, 교체할 화면)
                transaction.replace(R.id.container, firstFragment);
                break;
            case "second":
                transaction.replace(R.id.container, secondFragment);
                break;
            case "remove":
                // firstFragment를 안 보이도록
                // detach : 한번 제거하면 교체(replace) 불가
                // remove : 교체 가능
                transaction.remove(firstFragment);
        }
        // 작업을 완료했으니 바꿔줘
        // transaction.commitNow() : 지금 당장 처리해달라고 요청
        // transaction.commit() : 스케쥴을 고려해서 적당한 시기에 변경 요청 (prefer)
        transaction.commit();

    }
}
