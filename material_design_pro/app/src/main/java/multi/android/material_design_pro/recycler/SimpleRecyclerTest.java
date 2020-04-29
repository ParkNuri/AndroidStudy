package multi.android.material_design_pro.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import multi.android.material_design_pro.R;


public class SimpleRecyclerTest extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recycler_test);

        recyclerView = findViewById(R.id.list);

        // 1. Recycler에 출력할 데이터 준비
        List<SimpleItem> recycler_simple_data = new ArrayList<SimpleItem>();
        for(int i = 0;i < 10;i++){
            SimpleItem item = new SimpleItem("simple_item"+i);
            recycler_simple_data.add(item);

        }
        // 2. Adapter 생성
        SimpleItemAdapter adapter = new SimpleItemAdapter(this, R.layout.simple_item, recycler_simple_data);


        // 3. Recycler에 layout을 설정
        // (RecyclerView에 설정할 layout 객체 생성)
        //    LinearLayout, GridLayout
        // LinearLayoutManager : LinearLayout을 설정할 수 있는 매니저 객체
        // getApplicationContext() == this
        //LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        //manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // spancount: 열방향. 열로 몇 줄 출력?
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        // setHasFixedSize 고정 사이즈 2(spanCount)줄
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);

        // 4. Recycler와 adapter를 연결
        recyclerView.setAdapter(adapter);
        // 5. 추가적인 요소들을 적용할 수 있다. - 꾸미기, 애니메이션
    }
}
