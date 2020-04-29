package exam.day03.view.map_location_pro.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.se.omapi.SEService;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import exam.day03.view.map_location_pro.R;

// 위치 정보를 가져오는 방법 - 두 가지 제공
// 표준 방법인 LocationManager를 통해 가져오기
// 위치 정보를 제공하는 객체의 종류와 현제 사용할 수 있는 위치 정보 제공자를 확인
// 오류 상황 - avd에서 gps모듈을 제공하지 않는다.
// => 다른 provider 정보를 확인하고 사용할 수 있도록 작업
// (GPS, Network모듈을 함께 호출해서 먼저 받아지는 Provider를 이용해서 작업할 수 있도록 구현)

// LocationListener 위치가 바뀔때마다
public class BasicLocationTest2 extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    TextView result;
    boolean permission_state;
    List<String> provider_list; // 전체 위치 제공자 목록
    List<String> enableProvider_list;   // 사용 가능한 위치 제공자 목록

    String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_location_test);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        result = findViewById(R.id.txtview);    // 결과를 출력할 뷰

        // 위험 권한 체크
        // FINE => GPS, COARSE => NETWORK
        if(ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED | ContextCompat.checkSelfPermission(this, permissions[1]) != PackageManager.PERMISSION_GRANTED ){

            permission_state = false;
            Toast.makeText(this, "권한이 없습니다.",Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, permissions, 1000);
        }else {
            permission_state = true;
            Toast.makeText(this, "권한을 설정했습니다.",Toast.LENGTH_LONG).show();
            // 권한 설정이 완료되면 Provider목록을 가져와서 출력
            getProvidersList();
            getLocation();
        }
        //startLocationService();
    }
    // 전체 위치제공자 객체 목록과 사용 가능한 객체 목록을 출력
    public void getProvidersList(){
        String msg = "";
        // 제공되는 모든 provider 목록을 가져오기
        // passive : 이전에 사용했던 정보
        // gps
        // network
        provider_list = locationManager.getAllProviders();
        Log.d("msg", provider_list.size()+"");
        msg = "전체 목록 \n: " + msg;
        for(String prov : provider_list){
            msg = msg + prov + ", ";
        }

        // 사용 가능한 목록
        enableProvider_list = locationManager.getProviders(true);
        Log.d("msg", enableProvider_list.size()+"");
        msg = msg + "\n사용 가능한 목록 \n: " ;
        for(String prov : enableProvider_list){
            msg = msg + prov + ", ";
        }
        result.append(msg);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1000 && grantResults.length>0){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED & grantResults[1]==PackageManager.PERMISSION_GRANTED){
                permission_state = true;
                Toast.makeText(this, "권한을 설정했습니다.", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "권한 설정을 하지 않았으므로 기능을 사용할 수 없습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void getLocation(){
        for(String provider : enableProvider_list){
            Location location = null;
            try {
                // 현재 위치 받아내기
                location = locationManager.getLastKnownLocation(provider);
                if(location != null){
                    // 위치정보 (위도, 경도, 고도, ...)
                    printInfo(provider, location);

                    // 이벤트 연결
                    locationManager.requestLocationUpdates(provider, 10000, 1, this);
                }
                Log.d("msg","=====success=====");
            }catch (SecurityException e){
                Log.d("msg", "====="+e.getMessage()+"=====");
            }
        }

    }

    // 위치정보를 출력하는 메소드
    public void printInfo(String provider, Location location){
        if(location!=null){
            result.append("====================\nprovider: "+provider+"\n====================\n");
            result.append("Latitude=>"+location.getLatitude()+"\n");
            result.append("Longitude=>"+location.getLongitude()+"\n");

            Date date = new Date(location.getTime());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            result.append("시간:"+simpleDateFormat.format(date)+"\n");


        }
    }
    public void startLocationService(){
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

    // 위치 정보가 변경되면 호출되는 메소드
    @Override
    public void onLocationChanged(Location location) {
        Log.d("msg", "위치정보가 변경되었습니다.");
        String provider = location.getProvider();   //현재 정보를 제공하는 provider

        result.append("+++++++++++++\nprovider: "+provider+"\n\"+++++++++++++\n");
        result.append("Latitude=>"+location.getLatitude()+"\n");
        result.append("Longitude=>"+location.getLongitude()+"\n");

        Date date = new Date(location.getTime());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        result.append("시간:"+simpleDateFormat.format(date)+"\n");

    }

    // 모듈 변경될때
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    // 수신 가능
    @Override
    public void onProviderEnabled(String provider) {

    }
    // 수신 불가능
    @Override
    public void onProviderDisabled(String provider) {

    }
}
