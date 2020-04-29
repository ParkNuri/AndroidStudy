package exam.day03.view.datamanagementpro.sqlite.exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHandler {
    ExamDBHelper dbHelper;
    SQLiteDatabase db;

    public DBHandler(Context context) {
        this.dbHelper = new ExamDBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public static DBHandler open(Context context) {
        DBHandler dbHandler = new DBHandler(context);
        return dbHandler;
    }

    public long insert(String name, int price, int amount) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("su", amount);
        contentValues.put("totPrice", price * amount);

        long i = db.insert("product", null, contentValues);
        if (i > 0) {
            Log.d("dbwork", i + "개 행 삽입 성공!");
        } else {
            Log.d("dbwork", "삽입 실패ㅠ");
        }
        return i;
    }

    public ArrayList<Product> select(@Nullable String words) {
        String sql = "select * from product";

        String selection = null;
        String[] arg = null;

        if(words!=null){
            selection = "name like ?";
            words = "%"+words+"%";
            arg = new String[]{words};
        }

        Cursor cursor = db.query("product", null, selection, arg, null, null, null);

        int count = cursor.getCount();

        Log.d("dbwork", count + " row selected");
        ArrayList<Product> prodlist = new ArrayList<Product>();
        while (cursor.moveToNext()) {
            int idx = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            int amount = cursor.getInt(3);
            int total = cursor.getInt(4);

            prodlist.add(new Product(idx, name, price, amount, total));
        }
        return prodlist;
    }

}
