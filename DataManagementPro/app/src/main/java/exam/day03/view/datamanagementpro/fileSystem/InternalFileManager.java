package exam.day03.view.datamanagementpro.fileSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import exam.day03.view.datamanagementpro.R;

public class InternalFileManager extends AppCompatActivity {
    TextView internalTxt;
    // 내부저장소는 해당 앱만 사용하기 때문에 권한 체크 필요없다
    // 외부저장소는 다른 데이터를 건들지 못하도록 하기위해 권한 설정이 필요하다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internalTxt = findViewById(R.id.fileTxt);
    }
    public void saveInternalFile(View v) {
        // 안드로이드 내부저장소는 데이터를 저장하거나 데이터를 읽어올때 스트림을 직접 생성하지 않는다
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        // MODE_APPEND : 기존 파일에 내용 추가
        // MODE_PRIVATE : 기존 파일에 덮어쓰기
        // IO 작업 -> 예외처리
        try {
            fos = openFileOutput("myfile.txt",MODE_PRIVATE);    // new 로 객체생성 x
            dos = new DataOutputStream(fos);
            dos.writeUTF("테스트 중...");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(dos!=null){
                    dos.close();    // close는 하나만 해도 되고 둘다 해도 됨.
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void openInternalFile(View v){
        FileInputStream fis = null;
        DataInputStream dis = null;

        try {
            fis = openFileInput("myfile.txt");
            dis = new DataInputStream(fis);
            String data = dis.readUTF();
            internalTxt.setText(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(dis!=null){
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
