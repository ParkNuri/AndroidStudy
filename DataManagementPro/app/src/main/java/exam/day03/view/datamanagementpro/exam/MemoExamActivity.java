package exam.day03.view.datamanagementpro.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import exam.day03.view.datamanagementpro.R;

public class MemoExamActivity extends AppCompatActivity {
    EditText editText;
    boolean [] permission_state = new boolean[2];
    // [0] : READ
    // [1] : WRITE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_exam);
        editText = findViewById(R.id.editText);

        String [] permission = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        if(ContextCompat.checkSelfPermission(this, permission[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, permission[1]) == PackageManager.PERMISSION_GRANTED){
            permission_state[0] = true;
            permission_state[1] = true;
            ToastMsg("모든 권한 설정 완료!");
        }
        else {
            for(int i = 0;i < permission.length;i++) {
                if (ContextCompat.checkSelfPermission(this, permission[i]) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{permission[i]}, 1000+i);
                    permission_state[i] = false;
                } else {
                    permission_state[i] = true;
                }
            }
            ToastMsg("권한 설정 미완료");
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1000 && grantResults.length>0){
            //권한의 성공 설정에 대한 결과가 있다는 의미
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                permission_state[0] = true;
                ToastMsg("읽기 권한 설정 마무리 완료");
            }else{
                ToastMsg("읽기 권한 설정을 하지 않았으므로 기능을 사용할 수 없습니다");
            }
        }
        if(requestCode==1001 && grantResults.length>0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                permission_state[1] = true;
                ToastMsg("저장 권한 설정 마무리 완료");
            }else{
                ToastMsg("저장 권한 설정을 하지 않았으므로 기능을 사용할 수 없습니다");
            }
        }
    }
    public void saveFile(View v){
        if(permission_state[1]){
            ToastMsg("파일 저장 가능");

            String state = Environment.getExternalStorageState();
            if(state.equals(Environment.MEDIA_MOUNTED)){
            ToastMsg("저장소 사용 가능");

            File external = Environment.getExternalStorageDirectory();

            String dirpath = external.getAbsolutePath()+"/android/data/"+getPackageName()+"/mynote";

            File file = new File(dirpath);

            if(!file.exists()){
                file.mkdir();
            }

            FileWriter fw = null;
                try {
                    String today = new SimpleDateFormat("yyyymmdd").format(new Date(System.currentTimeMillis())).toString();
                    fw = new FileWriter(file+"/" +"20200410_memo.txt");
                    fw.write(editText.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(fw!=null){
                        try {
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }else{
            ToastMsg("파일 저장 권한 설정 미완료");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }
    }
   /* public void readFile(View v){
        FileReader fr = null;
        BufferedReader br = null;
        if(permission_state[0]){
            try {
                fr = new FileReader(new SimpleDateFormat("yyyymmdd").format(new Date(System.currentTimeMillis()))+"_memo.txt");
                br = new BufferedReader(fr);

                while (br.readLine()!=null){
                    editText.setText(br.readLine());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(br!=null){
                        br.close();
                    }
                    if(fr!=null){
                        fr.close();
                    }
                } catch (IOException e) {
                        e.printStackTrace();
                    }

            }
        }
    }*/

    public void ToastMsg(String msg){
        Toast.makeText(MemoExamActivity.this,msg,Toast.LENGTH_LONG).show();
    }
}
