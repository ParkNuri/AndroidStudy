package exam.day03.view.map_location_pro.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import exam.day03.view.map_location_pro.R;


public class LocationMapExam2 extends AppCompatActivity implements OnMapReadyCallback {
    String[] permission_list={
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    GoogleMap map;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map_exam);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(permission_list,1000);
        }else{
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int result:grantResults){
            if(result== PackageManager.PERMISSION_DENIED){
                return;
            }
        }
        init();
    }



    // map정보 추출
    public void init(){
        FragmentManager manager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)manager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if(map!=null){
            getMyLocation();
        }
    }
    // location을 추출 - 현재 나의 위치정보 추출
    public void getMyLocation(){
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        // 이전에 측정했었던 값을 가져오고 - 새롭게 측정하는데 시간이 오래 걸릴 수 있으므로
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            for(String permission : permission_list){
                if(checkSelfPermission(permission)== PackageManager.PERMISSION_DENIED){
                    return;
                }
            }
        }
        Location gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if(gps_loc!=null){
            setMyLocation(gps_loc);
        }else if(network_loc!=null){
            setMyLocation(network_loc);
        }
        Log.d("myloc", "===========================");
        // 현재 측정한 값도 가져온다
        MyLocationListener locationListener = new MyLocationListener();
        if(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);
        }else if(locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);
        }

    }
    // location정보를 지도에 세팅하는 메소드
    public void setMyLocation(Location myloc){
        Log.d("myloc", "위도:"+myloc.getLatitude()+", 경도:"+myloc.getLongitude());
        LatLng mylatlng = new LatLng(myloc.getLatitude(),myloc.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mylatlng,15);
        map.moveCamera(cameraUpdate);

        // 현재 위치를 포인트로 표시
        map.setMyLocationEnabled(true);
    }

    //
    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // 현재 위도 경도가 변경되면 호출되는 메소드
            setMyLocation(location);

            // listener 연결 해제
            locationManager.removeUpdates(this);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
