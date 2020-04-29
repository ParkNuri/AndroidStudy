package multi.android.material_design_pro.toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import multi.android.material_design_pro.R;

// Actionbar보다 확장 가능성이 더 크다
// Actionbar는 default로 들어가있기때문에 제거해야한다.
// values > styles.xml
public class ToolbarTest extends AppCompatActivity {
    TextView result;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar_test);
        result = findViewById(R.id.result);
        toolbar = findViewById(R.id.toolbar);

        //actionbar 대신 툴바를 사용하겠다고 정의
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("menu",item.getItemId()+"");
        int id = item.getItemId();
        String msg="";
        switch(id){
            case R.id.option_1:
                msg = "첫 번째 메뉴 선택";
                break;
            case R.id.option_2:
                msg = "공유";
                break;
        }
        result.setText(msg);
        return super.onOptionsItemSelected(item);
    }

}
