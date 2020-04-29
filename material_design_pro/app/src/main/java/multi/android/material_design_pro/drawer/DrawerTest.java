package multi.android.material_design_pro.drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import multi.android.material_design_pro.R;

public class DrawerTest extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        drawerLayout = findViewById(R.id.main_drawer);

        // actionbar에 버튼 설정 - 버튼을 선택하면 NavigationView를 보이고
        // 버튼을 재선택하면 NavigationView가 화면에서 사라지도록 설정
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_str, R.string.close_str);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();

        // Navigation view 설정
        NavigationView navigationView = findViewById(R.id.main_drawer_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.item1:
                        //intent 이용해서 activity 띄우기
                        Toast.makeText(DrawerTest.this, "내가 본 레시피", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        Toast.makeText(DrawerTest.this, "스크랩한 레시피", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
                        Toast.makeText(DrawerTest.this, "리뷰한 레시피", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item4:
                        Toast.makeText(DrawerTest.this, "레시피 노트", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });
    }
    //onOptionsItemSelected 메소드 구현해줘야 사용할 수 있다.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Toast.makeText(DrawerTest.this, "clicked!", Toast.LENGTH_LONG).show();
        if(toggle.onOptionsItemSelected(item)){
            //Toast.makeText(DrawerTest.this, "toggle clicked!", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
