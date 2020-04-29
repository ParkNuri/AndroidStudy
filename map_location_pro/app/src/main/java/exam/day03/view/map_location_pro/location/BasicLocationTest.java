package exam.day03.view.map_location_pro.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import exam.day03.view.map_location_pro.R;

// 위치 정보를 가져오는 방법 - 두 가지 제공
// 표준 방법인 LocationManager를 통해 가져오기
// 위치 정보를 제공하는 객체의 종류와 현제 사용할 수 있는 위치 정보 제공자를 확인
// 오류 상황 - avd에서 gps모듈을 제공하지 않는다.
public class BasicLocationTest extends AppCompatActivity {
    LocationManager locationManager;
    TextView result;
    boolean permission_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_location_test);

        result = findViewById(R.id.txtview);
        // 위험 권한 체크
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            permission_state = true;
            Toast.makeText(this, "hi",Toast.LENGTH_LONG).show();
        }else {
            permission_state = false;
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
        }
        startLocationService();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1000 && permission_state == true){

        }
    }

    public void startLocationService(){
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // 위치정보를 담는 객체
        // getLastKnownLocation(위치정보를 제공하는 provider)
        // : 위치 정보를 제공하는 제공자로부터 위치정보를 담고 있는 Location 객체를 가져오기
        // Manifest에 권한 2개 등록
        // "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"
        try {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); // 마지막 location 정보를 가지고있는 location 정보를 가져와라
            // 실내에 있거나 emulator 사용시 gps 모듈 사용 불가능하기 때문에 null로 들어옴
            if(location!=null){
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                Log.d("location","latitude : "+lat+"  longitude : "+lon);
            }else{
                Log.d("msg", "location 객체 생성 실패");
            }
        }catch (SecurityException e){
            Log.d("msg", e.getMessage());
        }

    }
}
