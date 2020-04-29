package multi.android.material_design_pro.recycler.exam;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import multi.android.material_design_pro.R;


public class CardRecyclerTest extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recycler_test);

        recyclerView = findViewById(R.id.list);
        // R.drawable에는 디자인에 필요한 정적 이미지만 저장 가능
        int[] imgs = {R.drawable.gong, R.drawable.lee, R.drawable.jang, R.drawable.jung, R.drawable.so};
        String[] sentences = {"공유의 도깨비","이민호의 신의","검색어를 입력하세요","정우성의 비트","미안하다 사랑한다"};
        List<CardItem> recycler_circle_data = new ArrayList<CardItem>();

        for(int i=0;i<imgs.length;i++){
            recycler_circle_data.add(new CardItem(imgs[i],sentences[i]));
            Log.d("cardv",i+"  img: "+imgs[i]+",  sentences: "+sentences[i]);
        }

        CardItemAdapter adapter = new CardItemAdapter(this, R.layout.circle_item, recycler_circle_data);

        //GridLayoutManager manager = new GridLayoutManager(this, 2);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
    }
}
