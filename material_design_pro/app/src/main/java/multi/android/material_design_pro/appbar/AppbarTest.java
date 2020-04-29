package multi.android.material_design_pro.appbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import multi.android.material_design_pro.R;

// appbar에 이미지 추가
public class AppbarTest extends AppCompatActivity {
    // androidx.appcompat
    Toolbar toolbar;
    ImageView app_bar_image;
    CollapsingToolbarLayout toolbarLayout;
    FloatingActionButton fab;
    ListView listView;
    ArrayList<String> datalist = new ArrayList<>();
    BottomAppBar bottomAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_test);

        toolbar = findViewById(R.id.toolbar);
        app_bar_image = findViewById(R.id.app_bar_image);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.mylistview);
        bottomAppBar = findViewById(R.id.bottom_Bar);

        app_bar_image.setImageResource(R.drawable.lee);

        // floating action bar의 설정을 bottom app bar에서 설정 가능
        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        bottomAppBar.setFabCradleRoundedCornerRadius(100);
        bottomAppBar.setFabCradleMargin(20);

        // 1. Appbar에 텍스트 추가, 변경
        toolbar.setTitle("this is toooooooooolbar");

        toolbarLayout.setExpandedTitleColor(Color.LTGRAY);
        toolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        // 접혔을때 가운데
        toolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
        // 펼쳐졌을때 오른쪽 위

        toolbarLayout.setExpandedTitleGravity(Gravity.RIGHT+Gravity.TOP);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                datalist);
        listView.setAdapter(adapter);

        // FloatingActionButton을 눌렀을때 대화상자가 뜨고
        // 입력한 데이터가 listview에 추가되도록 구현

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AppbarTest.this);
                // AlertDialog의 title 정의
                builder.setTitle("데이터 입력");

                // AlertDialog에 보여질 화면을 inflate
                LayoutInflater inflater = getLayoutInflater();

                View dialogView = inflater.inflate(R.layout.input, null);

                // AlertDialog에 추가할 버튼 정의
                // PositiveButton : 주기능
                builder.setPositiveButton("확인", new DialogListener());

                // NegativeButton : 부기능
                builder.setNegativeButton("취소", null);

                // AlertDialog에 화면 설정
                builder.setView(dialogView);
                builder.show();

            }
        });
    }
    class DialogListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            // AlertDialog에서 입력하는 내용을 ListView에 추가하기
            AlertDialog inputAlert = (AlertDialog) dialog;
            EditText input = inputAlert.findViewById(R.id.input);
            String data = input.getText().toString();
            datalist.add(data);

            // ArrayList에 데이터를 추가한 후 adapter가 갖고 있는 데이터를 업데이트
            // => Adapter에게 데이터가 변경되었음을 알려주는 작업
            ArrayAdapter<String> adapter = (ArrayAdapter<String>)listView.getAdapter();
            adapter.notifyDataSetChanged();




        }
    }
}
