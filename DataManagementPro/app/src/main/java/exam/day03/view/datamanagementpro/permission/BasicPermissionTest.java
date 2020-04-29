package exam.day03.view.datamanagementpro.permission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import exam.day03.view.datamanagementpro.R;

public class BasicPermissionTest extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_permission_test);
        webView = findViewById(R.id.webview);

        // WebSettings : 안드로이드에서 웹 사용시 필요한 정보를 셋팅할 수 있는 클래스
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        // 실제 웹앱의 주소
        webView.loadUrl("https://m.naver.com");

        // 인터넷 사용시 인터넷 권한 설정을 하지 않으면 ERR_CACHE_MISS 에러 발생
        // MANIFEST.xml에 uses-permission android:name="android.permission.INTERNET" 권한 설정
        // 보안 통신(https)로 인한 ERR_ACCESS_DENIED 에러 발생
        // android:usesCleartextTraffic="true" 설정
        // 권한 설정 후에는 삭제 후 재설치
    }

}
