package multi.android.material_design_pro.recycler.exam2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import multi.android.material_design_pro.R;


public class CircleRecyclerTest extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recycler_test);

        recyclerView = findViewById(R.id.list);
        // R.drawable에는 디자인에 필요한 정적 이미지만 저장 가능
        int[] imgs = {R.drawable.gong, R.drawable.lee, R.drawable.jang, R.drawable.jung, R.drawable.so};
        List<CircleItem> recycler_circle_data = new ArrayList<CircleItem>();

        for(int img : imgs){
            recycler_circle_data.add(new CircleItem(img));
        }

        CircleItemAdapter adapter = new CircleItemAdapter(this, R.layout.circle_item, recycler_circle_data);

        //GridLayoutManager manager = new GridLayoutManager(this, 2);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
    }
}
