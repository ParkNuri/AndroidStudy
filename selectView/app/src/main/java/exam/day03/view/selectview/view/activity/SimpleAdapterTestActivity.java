package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

//옛날에 많이 사용하던 방법
//activity 하나가 list로 => view가 필요 없음
public class SimpleAdapterTestActivity extends ListActivity {
    // 두 줄 텍스트로 list view를 구성하기
    ArrayList<HashMap<String,String>> listdata = new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_simple_adapter_test);    // listactivity여서 필요없음

        //리스트를 구성할 샘플 데이터 준비
        HashMap<String,String> item = new HashMap<String, String>();

        item.put("name","박누리");
        item.put("telNum","010-9466-7352");

        listdata.add(item);

        item = new HashMap<String, String>();

        item.put("name","김현정");
        item.put("telNum","010-9444-4352");

        listdata.add(item);

        item = new HashMap<String, String>();

        item.put("name","네네치킨");
        item.put("telNum","02-555-5555");

        listdata.add(item);

        item = new HashMap<String, String>();

        item.put("name","만스피자");
        item.put("telNum","555-5555");

        listdata.add(item);

        //android.R.layout.simple_list_item_2 item이 2개 (두 줄)
        SimpleAdapter adapter = new SimpleAdapter(this,
                listdata,   //HashMap으로 구성된 데이터가 저장된 리스트
                android.R.layout.simple_list_item_2,    //row의 디자인
                new String[]{"name","telNum"},  //HashMap에 저장된 키 리스트
                                                // 위에서 정의한 맵 데이터를 어떤 view에 출력할 것인지
                                                //키의 순서와 동일한 리소스id 순서
                new int[]{android.R.id.text1,android.R.id.text2}  //text1, text2 : andoird.R.layout.simple_list_item_2에 있는 resources
                );

        setListAdapter(adapter);

    }
}
