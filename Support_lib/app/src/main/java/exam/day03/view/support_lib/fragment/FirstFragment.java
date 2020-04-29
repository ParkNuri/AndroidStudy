package exam.day03.view.support_lib.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import exam.day03.view.support_lib.R;


public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

    // fragment view가 만들어질때 호출되는 메소드
    // 액티비티에 배치될때 호출되는 메소드 -
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first,container,false);
    }
}
