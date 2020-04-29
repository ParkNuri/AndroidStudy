package multi.android.material_design_pro.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import multi.android.material_design_pro.R;
import multi.android.material_design_pro.exam.ListTestFragment;
import multi.android.material_design_pro.exam.ViewFragment1;
import multi.android.material_design_pro.exam.ViewFragment3;

public class TabTest2 extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    // fragment를 담을 ArrayList
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
    // tab 문자열을 담을 ArrayList
    ArrayList<String> tabdatalist = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_test2);

        tabLayout = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);

        tabLayout.setTabTextColors(Color.BLUE, Color.WHITE);
        for(int i=1;i<=10;i++)
        {
            ChildFragment fragment = new ChildFragment();
            fragment.setTitle("작업중인 프레그먼트 : "+i);
            fragmentArrayList.add(fragment);
            tabdatalist.add("tab"+i);
            tabLayout.addTab(tabLayout.newTab().setText("tab"+i));
        }
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragmentArrayList.size());
        pager.setAdapter(adapter);

        // TabLayout과 ViewPager를 연결
        // - ViewPager의 getPageTitle 메소드를 호출해서 탭의 문자열을 셋팅
        tabLayout.setupWithViewPager(pager);
    }
    class PagerAdapter extends FragmentStatePagerAdapter{

        public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        // viewPager와 tab을 연결하기 위해서 탭에 출력될 문자열을 만들어내는 메소드
        // setupWithViewPager 내부에서 탭의 문자열을 출력하기 위해서 호출한다.
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabdatalist.get(position);
        }
    }

}











