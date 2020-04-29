package exam.day03.view.selectview.view.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import exam.day03.view.selectview.R;

public class ViewHolder {
    ImageView img;
    TextView name;
    TextView date;
    TextView memo;
    CheckBox check;

    public ViewHolder(View parentView) {
        this.img = parentView.findViewById(R.id.imgView);
        this.name = parentView.findViewById(R.id.nametxt);
        this.date = parentView.findViewById(R.id.datetxt);
        this.memo = parentView.findViewById(R.id.memotxt);
        this.check = parentView.findViewById(R.id.check);
    }
}
