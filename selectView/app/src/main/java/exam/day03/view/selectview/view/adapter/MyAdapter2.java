package exam.day03.view.selectview.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import exam.day03.view.selectview.R;

// 성능 개선을 위한 작업을 추가
// 1. 한 번 만든 view는 재사용
// 2. findViewById 한 번 작업한 view에 대한 정보는 저장해 놓고 다시 사용
public class MyAdapter2 extends ArrayAdapter<User> {
    private Context context;
    private int resId;
    private ArrayList<User> datalist;
    // row마다 사용자가 설정한 값을 position과 함께 저장
    // 해당 position에 대한 설정 값을 같이 출력
    // 저장하는 시점은 사용자가 설정을 끝낸 시점 - focus를 잃어버리는 시점
    HashMap<Integer, SaveUserState> saveData = new HashMap<Integer, SaveUserState>();

    public MyAdapter2(Context context, int resource, ArrayList<User> objects) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        long start = System.nanoTime();
        Log.d("getView", "GetView: "+position);

        UserViewHolder holder;
        // view 생성 - 매개변수로 전달되는 convertview 재사용
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);

            // ==== 최초작업이므로 view를 찾아서 가져오기 ====
            holder = new UserViewHolder(convertView);

            // 홀더를 저장
            convertView.setTag(holder);
        }
        else{
            // ==== 최초가 아니라 view를 재사용 중이라면 ====
            holder = (UserViewHolder) convertView.getTag();
        }
        // ArrayList에서 return된 list 항목의 번호와 동일한 데이터를 구하기
        User user = datalist.get(position);

        if(user!=null) {
            // 위에서 생성한 view의 각 요소에 데이터를 연결
            ImageView imgView = holder.myImg;
            TextView nameView = holder.nameView;
            TextView telNumView = holder.telNumView;
            final EditText editText = holder.editText;

            // data를 view에 setting
            imgView.setImageResource(user.myImg);
            nameView.setText(user.name);
            telNumView.setText(user.telNum);

            // view를 만들 때 해당 list에 저장된 내용이 있는지 체크해서 값을 출력하기
            SaveUserState state = saveData.get(position);


            if(state==null){            // 저장된 객체가 없으면
                editText.setText("");
            }
            else{                       // 저장된 객체가 있으면 객체에서 data를 추출해서 출력
                editText.setText(state.data);
            }

            // EditText가 focus를 잃어버리는 시점에 입력한 데이터 저장
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //focus를 잃을 때 => !hasFocus
                    if(!hasFocus){
                        String data = editText.getText().toString();
                        SaveUserState userState = new SaveUserState();
                        userState.data = data;
                        saveData.put(position, userState);
                    }
                }
            });

        }
        long end = System.nanoTime();

        Log.d("getView", "cost time : "+(end-start));
        return convertView;
    }



}
