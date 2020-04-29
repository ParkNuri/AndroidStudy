package multi.android.material_design_pro.recycler.exam;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import multi.android.material_design_pro.R;
public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.ViewHolder> {

    Context context;
    int row_res_id;
    List<CardItem> data;

    public CardItemAdapter(Context context, int row_res_id, List<CardItem> data) {
        this.context = context;
        this.row_res_id = row_res_id;
        this.data = data;
    }
    @NonNull
    @Override
    public CardItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(row_res_id, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardItemAdapter.ViewHolder holder, int position) {
        holder.imgview.setImageResource(data.get(position).getImg());
        holder.textView.setText(data.get(position).getSentence());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgview;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgview = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.txtview);
        }
    }
}
