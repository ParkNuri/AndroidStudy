package multi.android.support_lib.viewpager.exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import multi.android.support_lib.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment3 extends Fragment {

    public ViewFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view1, container, false);
        TextView txt = v.findViewById(R.id.textView);
        txt.setText("프레그먼트에서 데이터 변경중....");
        return v;
    }
}
