package multi.intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BasicAppRun extends AppCompatActivity {

    // 승인받을 권한의 목록
    String[] permission_list = {
      Manifest.permission.CALL_PHONE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_app_run);

        // 권한 체크 메소드 호출
        runPermission();
    }

    // 구글맵 실행
    public void runGoogleMap(View v) {

        // action명
        Uri uri = Uri.parse("geo:128.94.134.88");
        // Intent(action명, uri)Intent 클래스에 미리 정의된 action list가 내장되어있음
        // ACTION_VIEW 뭔가를 보여주는 acition
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
    }

    public void runWebBrowser(View v) {

        // action명
        Uri uri = Uri.parse("https://www.naver.com");
        // Intent(action명, uri)Intent 클래스에 미리 정의된 action list가 내장되어있음
        // ACTION_VIEW 뭔가를 보여주는 acition
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
    }

    public void runDial(View v) {

        // action명
        Uri uri = Uri.parse("tel:01094667352");
        // Intent(action명, uri)Intent 클래스에 미리 정의된 action list가 내장되어있음
        // ACTION_VIEW 뭔가를 보여주는 acition
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);

        startActivity(intent);
    }

    // 전화 걸기
    // 전화 걸기는 권한이 필요하다.
    // MANIFESTS에 Permission 등록
    // <uses-permission android:name="android.permission.CALL_PHONE"/>
    // 이전 버전에서는 여기까지만----- 하지만
    // 새 버전에서는 권한 체크 & 승인처리를 해야한다.
    // 권한이 여러 개이기 때문에 클래스 상단에 String[]으로 선언
    public void runCallPhone(View v) {

        // action명
        Uri uri = Uri.parse("tel:01086980231");
        // Intent(action명, uri)Intent 클래스에 미리 정의된 action list가 내장되어있음
        // ACTION_VIEW 뭔가를 보여주는 acition
        Intent intent = null;
        int chk = PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        // PackageManager.PERMISSION_GRANTED : 정상 허가
        if(chk == PackageManager.PERMISSION_GRANTED){
            Log.d("tel","success");
            intent = new Intent(Intent.ACTION_CALL,uri);
        }else
        {
            Log.d("tel","fail");
            return;
        }
        startActivity(intent);
    }
    public void runCamera(View v){
        Uri uri = Uri.parse("");
        Intent intent = new Intent(Intent.ACTION_Ca,uri);
        startActivity(intent);
    }

    public void runPermission(){
        // 마시멜로우버전 이하는 권한 확인 필요 없기 때문에 예외처리
        // Build.VERSION.SDK_INT : SDK 버전
        // Build.VERSION_CODES.M : 마시멜로우버전 상수
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return;
        }
        //모든 권한을 self check
        for (String permission:permission_list){
            int chk = checkCallingOrSelfPermission(permission);
            if(chk==PackageManager.PERMISSION_DENIED){
                // requestCode 는 사용자 설정.
                requestPermissions(permission_list, 0);
                break;
            }
        }
    }

}
