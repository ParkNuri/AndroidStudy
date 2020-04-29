package multi.android.material_design_pro.tab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import multi.android.material_design_pro.R;
import multi.android.material_design_pro.exam.ListTestFragment;
import multi.android.material_design_pro.exam.ViewFragment1;
import multi.android.material_design_pro.exam.ViewFragment3;

public class TabTest extends AppCompatActivity {
    ViewFragment1 viewFragment1 ;
    ListTestFragment viewFragment2;
    ViewFragment3 viewFragment3 ;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
    TabLayout tabLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_test);
        viewFragment1 = new ViewFragment1();
        viewFragment2 = new ListTestFragment();
        viewFragment3 = new ViewFragment3();

        tabLayout = findViewById(R.id.mytab);
        bottomNavigationView = findViewById(R.id.bottomNavi);

        //탭 추가
        tabLayout.addTab(tabLayout.newTab().setText("설정"));

        //처음 실행할때 보여줄 프레그먼트 지정
        getSupportFragmentManager().beginTransaction().
                replace(R.id.content_container,viewFragment1).commit();

        //탭에 이벤트 연결하기
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //탭이 선택될때 호출되는 메소드
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();//탭의 순서를 받아오기
                Log.d("tab",position+"");
                Fragment fragment = null;
                if(position==0){
                    fragment = viewFragment1;
                }else if(position ==1){
                    fragment = viewFragment2;
                }else{
                    fragment = viewFragment3;
                }
                //탭을 선택할 때 지정된 프레그먼트 객체가 show되도록 연결
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_container,fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //BottomNavigationView이벤트 연결
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.bottom_item2){
                    getSupportFragmentManager().beginTransaction().
                       replace(R.id.content_container,viewFragment2).commit();
                }
                return false;
            }
        });
    }
}











