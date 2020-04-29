package multi.android.material_design_pro.recycler.exam2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import multi.android.material_design_pro.R;

public class CircleItemAdapter extends RecyclerView.Adapter<CircleItemAdapter.ViewHolder> {

    Context context;
    int row_res_id;
    List<CircleItem> data;

    public CircleItemAdapter(Context context, int row_res_id, List<CircleItem> data) {
        this.context = context;
        this.row_res_id = row_res_id;
        this.data = data;
    }
    @NonNull
    @Override
    public CircleItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(row_res_id, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CircleItemAdapter.ViewHolder holder, int position) {
        holder.cimgview.setImageResource(data.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView cimgview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cimgview = itemView.findViewById(R.id.circleImgview);
        }
    }
}
