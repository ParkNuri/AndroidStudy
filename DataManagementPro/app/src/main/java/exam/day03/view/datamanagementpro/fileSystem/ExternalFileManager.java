package exam.day03.view.datamanagementpro.fileSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import exam.day03.view.datamanagementpro.R;

public class ExternalFileManager extends AppCompatActivity {
    // 외부 저장소는 개인 데이터들을 포함하고 있는 공간을 공유하기 때문에 권한을 받아야한다.
    //     <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    // 안드로이드 Q 버전부터 external storage 권한 정책이 변경되었다.
    // Q 이전은 Write 권한 필요 o , Read 권한 필요 o
    //  => android:requestLegacyExternalStorage="true" 설정 필수
    // Q 부터는 Write 권한 필요 x , Read 권한 필요 o

    // 외부 저장소는 app을 삭제해도 데이터가 남아있다.

    TextView outView;
    boolean perState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_file_mgr);

        outView = findViewById(R.id.fileTxt);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
            ToastMsg("권한이 설정되었습니다");
            perState = true;
        }else{
            ToastMsg("권한을 설정하세요");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1000 && grantResults.length>0){
            //권한의 성공 설정에 대한 결과가 있다는 의미
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                perState = true;
                ToastMsg("권한 설정 마무리 완료");
            }else{
                ToastMsg("권한 설정을 하지 않았으므로 기능을 사용할 수 없습니다");
            }
        }
    }
/*    public void openExternalFile(View v){
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {

            try {
                fos = openFileOutput("myfile.txt", MODE_PRIVATE);
                dos = new DataOutputStream(fos);
                dos.writeUTF("테스트 중...");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (dos != null) {
                        dos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            perState = true;
            ToastMsg("권한 설정 완료");
        }else{
            ToastMsg("권한 설정하세요");
        }
    }*/
    public void saveExternalFileSystem(View v){

        /*if(Build.VERSION.SDK_INT <Build.VERSION_CODES.Q) {*/
            if (perState==true) {
                ToastMsg("권한 설정 완료");

                String state = Environment.getExternalStorageState();
                if(state.equals(Environment.MEDIA_MOUNTED)){ // 현재 사용 가능한 상태
                    ToastMsg("사용 가능");
                    File external = Environment.getExternalStorageDirectory();
                    //String dirPath = external.getAbsolutePath()+"/myApp";
                    // 외부저장소/임의의디렉토리 생성
                    // => 앱을 삭제해도 데이터는 남아있다.
                    // 외부저장소/android/data/앱의package명 으로 directory 생성
                    // => 앱 삭제시 데이터도 같이 삭제된다.
                    String dirPath = external.getAbsolutePath();
                    String pkg = getPackageName();
                    File dir = new File(dirPath+"/android/data/"+pkg);

                    //File dir = new File(dirPath);

                    // 디렉터리가 없으면 생성
                    if(!dir.exists()){
                        dir.mkdir();
                    }

                    FileWriter fw = null;
                    try {
                        fw = new FileWriter(dir+"/test1.txt");
                        fw.write("외부 저장소 test.. ing");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                            try {
                                if(fw!=null) {
                                    fw.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                    }
                }else{
                    ToastMsg("사용 불가능");
                }
            } else {
                ToastMsg("권한 설정하세요");
            }

    }
    public void ToastMsg(String text){
        Toast.makeText(ExternalFileManager.this, text, Toast.LENGTH_LONG).show();
    }
}
