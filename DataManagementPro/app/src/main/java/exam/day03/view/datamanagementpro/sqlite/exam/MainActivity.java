package exam.day03.view.datamanagementpro.sqlite.exam;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

import exam.day03.view.datamanagementpro.R;
import exam.day03.view.datamanagementpro.sqlite.exam.DBHandler;
import exam.day03.view.datamanagementpro.sqlite.exam.Product;


public class MainActivity extends
        AppCompatActivity implements AdapterView.OnItemClickListener, OnClickListener {
    DBHandler handler;
    EditText edtName;
    EditText edtSu;
    EditText edtPrice;
    ListView listview;

    ArrayList<HashMap<String, String>> listdata;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        handler = DBHandler.open(this);

        findViewById(R.id.btnIns).setOnClickListener(this);
        findViewById(R.id.btnResult).setOnClickListener(this);
        findViewById(R.id.btnResult2).setOnClickListener(this);
        findViewById(R.id.btnSearch).setOnClickListener(this);
        listview = findViewById(R.id.list);
        listview.setOnItemClickListener(this);

        edtName = (EditText) findViewById(R.id.edtName);
        edtSu = (EditText) findViewById(R.id.edtSu);
        edtPrice = (EditText) findViewById(R.id.edtPrice);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIns:
                insertView(v);
                break;
            case R.id.btnResult:
                resultView(1);
                break;
            case R.id.btnResult2:
                resultView(2);
                break;
            case R.id.btnSearch:
                searchView();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("viewwork","parent: "+parent+"   view: "+view+"   position: "+position+"   id: "+id);
        Intent intent = new Intent(MainActivity.this, ReadActivity.class);

        //Product dto = new Product();
    }

    public void insertView(View v) {
        long result = handler.insert(edtName.getText().toString(), Integer.parseInt(edtPrice.getText().toString()), Integer.parseInt(edtSu.getText().toString()));

        if (result == 0) {
            Toast.makeText(this, "삽입 실패", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "삽입 성공", Toast.LENGTH_LONG).show();
        }
    }

    public void resultView(int type) {
        Log.d("viewwork", "btn clicked");
        ArrayList<Product> prodlist = handler.select(null);

        // view에 prdlist 리스트로 뿌리기

		setListView(type, prodlist);
    }

    public void setListView(int type, ArrayList<Product> prodlist) {
        HashMap<String, String> prodhash = new HashMap<>();
        SimpleAdapter adapter = null;
        String[] cols = null;
        int listtype = 0;
        int[] listItem = null;
        listdata = new ArrayList<HashMap<String, String>>();
        for (Product prd : prodlist) {
            if (type == 1) {
                prodhash.put("products", prd.toString());
                cols =  new String[]{"products"};
                listtype = android.R.layout.simple_list_item_1;
                listItem = new int[]{android.R.id.text1};
            } else if (type == 2) {
                prodhash.put("name", prd.name);
                prodhash.put("price", "price : " + prd.price + ", total price : " + prd.total);
                cols = new String[]{"name", "price"};
                listtype = android.R.layout.simple_list_item_2;
				listItem = new int[]{android.R.id.text1, android.R.id.text2};
            }
            listdata.add(prodhash);
        }
        adapter = new SimpleAdapter(this, listdata, listtype, cols, listItem);
        listview.setAdapter(adapter);
    }

    public void searchView() {
        Log.d("viewwork", "btn clicked");
        ArrayList<Product> prodlist = new ArrayList<>();

        listdata = new ArrayList<>();
        if (!edtName.getText().equals("")) {
            prodlist.addAll(handler.select(edtName.getText().toString()));
        } else if (!edtPrice.getText().equals("")) {
            prodlist.addAll(handler.select(edtPrice.getText().toString()));
        }

        // view에 prdlist 리스트로 뿌리기
        if(prodlist!=null&&prodlist.size()>0){
        	setListView(2, prodlist);
		}


    }

    public void detailView() {    // list 하나 클릭시
        //해당 row의 index로 넘기기
        String idx = "";
        ArrayList<Product> prodlist = handler.select(idx);

        // Intent로 데이터 넘기기

    }
}



















