package exam.day03.view.support_lib.fragment.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import exam.day03.view.support_lib.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Fragment1 fragment1 = new Fragment1();
    Fragment2 fragment2 = new Fragment2();
    Fragment3 fragment3 = new Fragment3();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_main);

        Button btn1 = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        setFragment(v);
    }
    public void setFragment(View v){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.button:
                Toast.makeText(MainActivity.this, "btn1 clicked", Toast.LENGTH_LONG).show();
                fragmentTransaction.replace(R.id.container, fragment1);
                break;
            case R.id.button2:
                Toast.makeText(MainActivity.this, "btn2 clicked", Toast.LENGTH_LONG).show();
                fragmentTransaction.replace(R.id.container, fragment2);
                break;
            case R.id.button3:
                Toast.makeText(MainActivity.this, "btn3 clicked", Toast.LENGTH_LONG).show();
                fragmentTransaction.replace(R.id.container, fragment3);
        }
        fragmentTransaction.commit();
    }
}
