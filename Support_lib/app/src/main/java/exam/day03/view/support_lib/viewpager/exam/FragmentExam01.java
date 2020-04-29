package exam.day03.view.support_lib.viewpager.exam;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import exam.day03.view.support_lib.R;


public class FragmentExam01 extends AppCompatActivity {
    multi.android.support_lib.viewpager.exam.ViewFragment1 viewFragment1 ;
//    ViewFragment2 viewFragment2 ;
    multi.android.support_lib.viewpager.exam.ListTestFragment viewFragment2;
    multi.android.support_lib.viewpager.exam.ViewFragment3 viewFragment3 ;
    multi.android.support_lib.viewpager.exam.MapFragment viewFragment4;
    ViewPager fragment_viewPager;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_exam);
        fragment_viewPager = findViewById(R.id.fragment_viewPager);
        viewFragment1 = new multi.android.support_lib.viewpager.exam.ViewFragment1();
        viewFragment2 = new multi.android.support_lib.viewpager.exam.ListTestFragment();
        viewFragment3 = new multi.android.support_lib.viewpager.exam.ViewFragment3();
        viewFragment4 = new multi.android.support_lib.viewpager.exam.MapFragment();
        fragmentArrayList.add(viewFragment1);
        fragmentArrayList.add(viewFragment2);
        fragmentArrayList.add(viewFragment3);
        fragmentArrayList.add(viewFragment4);

        FragAdapter adapter =
                new FragAdapter(getSupportFragmentManager(),
                                            fragmentArrayList.size());
        fragment_viewPager.setAdapter(adapter);
        fragment_viewPager.addOnPageChangeListener(new PageListener());
    }
    public void btn_click(View view){
       fragment_viewPager.setCurrentItem(Integer.parseInt(view.getTag().toString()));
    }

    class FragAdapter extends FragmentStatePagerAdapter {

        public FragAdapter(@NonNull FragmentManager fm, int behavior) {
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
    }

    class PageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //페이지가 변경되었을때
            Toast.makeText(FragmentExam01.this,"페이지가 전환",
                    Toast.LENGTH_LONG).show();
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
