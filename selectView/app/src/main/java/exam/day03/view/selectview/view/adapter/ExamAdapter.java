package exam.day03.view.selectview.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class ExamAdapter extends ArrayAdapter<ActorItem> {
    private Context context;
    private int resId;
    private ArrayList<ActorItem> datalist;

    HashMap<Integer, SaveCheckState> saveCheck = new HashMap<Integer, SaveCheckState>();

    public ExamAdapter(Context context, int resource, ArrayList<ActorItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resId = resource;
        this.datalist = objects;
    }

    @Override
    public int getCount() {
        return this.datalist.size();
    }

    @Override
    public ActorItem getItem(int position) {
        return this.datalist.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ActorItem item = datalist.get(position);

        if(item!=null){
            ImageView imgView = holder.img;
            TextView nameView = holder.name;
            TextView dateView = holder.date;
            TextView memoView = holder.memo;
            final CheckBox checkBox = holder.check;

            imgView.setImageResource(item.img);
            nameView.setText(item.name);
            dateView.setText(item.date);
            memoView.setText(item.memo);

            SaveCheckState checkState = saveCheck.get(position);

            if(checkState!=null){
                Log.d("checkBox",checkState.check+"========"+position);
                checkBox.setChecked(checkState.check);
            }else{
                checkBox.setChecked(false);
            }

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SaveCheckState state = new SaveCheckState();
                    state.check = checkBox.isChecked();
                    saveCheck.put(position,state);
                    Log.d("checkBox",checkBox.isChecked()+"========"+position+"-----"+state.check);
                }
            }
            );



        }


        return convertView;
    }
}
