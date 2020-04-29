package exam.day03.view.selectview.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import exam.day03.view.selectview.R;

public class MyAdapter extends ArrayAdapter<User> {
    private Context context;
    private int resId;
    private ArrayList<User> datalist;

    public MyAdapter(Context context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resId = resource;
        this.datalist = objects;
    }

    // list 갯수를 반환
    @Override
    public int getCount() {
        return datalist.size();
    }

    // 매개변수로 전달받은 순서에 있는 list항목 반환
    @Override
    public User getItem(int position) {
        return datalist.get(position);
    }

    // list의 한 항목을 만들때 호출되는 메소드 - list 항목이 100개면 100번 호출
    // position => list 순서 (0부터 시작)
    // convertView => 한 항목에 대한 view

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        long start = System.nanoTime();
        Log.d("getView", "GetView: "+position);

        // view 생성
        // getSystemService는 context가 갖고있는 함수이기 때문에 Activity(context를 상속받고있음)에서만 변수없이 사용 가능
        // context 변수를 통해 사용가능
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //convertView = inflater.inflate(resId, parent);
        convertView = inflater.inflate(resId, null);

        // ArrayList에서 return된 list 항목의 번호와 동일한 데이터를 구하기
        User user = datalist.get(position);

        // 위에서 생성한 view의 각 요소에 데이터를 연결
        ImageView imgView = convertView.findViewById(R.id.img); //
        TextView nameView = convertView.findViewById(R.id.txtcust1);
        TextView telNumView = convertView.findViewById(R.id.txtcust2);

        // data를 view에 setting
        imgView.setImageResource(user.myImg);
        nameView.setText(user.name);
        telNumView.setText(user.telNum);

        long end = System.nanoTime();

        Log.d("time", "cost time : "+(end-start));
        return convertView;
    }



}
