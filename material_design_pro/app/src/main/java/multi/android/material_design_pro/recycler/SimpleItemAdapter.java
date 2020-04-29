package multi.android.material_design_pro.recycler;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import multi.android.material_design_pro.R;
// RecyclerView에서 사용하는 Adapter를 customizing
// Adapter안에 ViewHolder 포함 - 정의 (ListView사용할 때와 동일한 역할)
//            -----------
//              L__Inner Class로 정의

// viewHolder findviewbyId
public class SimpleItemAdapter extends RecyclerView.Adapter<SimpleItemAdapter.ViewHolder> {

    Context context;
    int row_res_id; // row를 구성하는 layout (ex. R.layout.xxxx)
    List<SimpleItem> data; //Recycler에 출력될 전체 데이터

    public SimpleItemAdapter(Context context, int row_res_id, List<SimpleItem> data) {
        this.context = context;
        this.row_res_id = row_res_id;
        this.data = data;
    }
    // xml로부터 뷰(한 row에 대한 뷰)를 만들어서 ViewHolder를 넘기는 작업
    // View를 구성하는 구성요소의 리소스를 가져오는 작업을 하는 객체

    // 1. onCreateViewHolder에서 row에 대한 뷰를 inflate해서 생성
    // 2. ViewHolder 객체를 만들어서 1번에서 생성한 뷰를 넘긴다.
    // 3. ViewHolder 객체 안에서 onCreateViewHolder 메소드에서 리턴받은 객체에서
    // 데이터를 연결할 뷰를 찾아온다.
    // 4. onBindViewHolder메소드에서 ViewHolder가 갖고 있는 구성요소에 데이터를 연결하기
    @NonNull
    @Override
    public SimpleItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // activity가 아니기 때문에 getLayoutInflater 불가
        // => LayoutInflater.from으로 inflater 받아오기
        View view = LayoutInflater.from(context).inflate(row_res_id, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleItemAdapter.ViewHolder holder, int position) {
        Log.d("recycler", "onBindViewHolder: " + position);
        // ViewHolder가 찾아놓은 TextView에 data 연결
        holder.txtview.setText(data.get(position).getData());
        // TextView에 클릭이벤트 연결
        holder.txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "데이터 연결완료", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // RecyclerView에 출력할 데이터의 갯수 리턴
    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtview;
        // 전달 받은 row하나로부터의 객체 뽑아내기
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtview = itemView.findViewById(R.id.itemview);
            Log.d("recycler","ViewHolder!");
        }
    }
}
