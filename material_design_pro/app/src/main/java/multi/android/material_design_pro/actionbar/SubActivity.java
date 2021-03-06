package multi.android.material_design_pro.actionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import multi.android.material_design_pro.R;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 안드로이드에서 제공하는 뒤로가기 버튼을 actionbar에 추가하기
        // actionbar를 얻어오기
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);  /*화면에 추가*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // R -> 내가 가진
        // android.R => 안드로이드가 가진
        if (id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
