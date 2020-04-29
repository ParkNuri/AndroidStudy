package exam.day03.view.map_location_pro.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import exam.day03.view.map_location_pro.R;
// 현재 위치정보를 가져와서 맵에 연결해서 출력 - avd, device

public class LocationMapExam extends AppCompatActivity implements LocationListener, OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraMoveStartedListener {
    LocationManager locationManager;
    boolean permission_state;
    List<String> enableProvider_list;   // 사용 가능한 위치 제공자 목록
    String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map_exam);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        checkPermission();

        FragmentManager manager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)manager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED | ContextCompat.checkSelfPermission(this, permissions[1]) != PackageManager.PERMISSION_GRANTED) {

            permission_state = false;
            Toast.makeText(this, "권한이 없습니다.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, permissions, 1000);
        } else {
            permission_state = true;
            Toast.makeText(this, "권한을 설정했습니다.", Toast.LENGTH_LONG).show();
            // 권한 설정이 완료되면 Provider목록을 가져와서 출력
            getProviders();
            getLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED & grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                permission_state = true;
                Toast.makeText(this, "권한을 설정했습니다.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "권한 설정을 하지 않았으므로 기능을 사용할 수 없습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Location location = null;
        getProviders();
        if (googleMap != null && enableProvider_list!=null && enableProvider_list.size()>0) {
            for(String provider : enableProvider_list) {
                try {
                    location = locationManager.getLastKnownLocation(provider);
                } catch (SecurityException e) {
                    Log.d("msg", e.getMessage());
                }
            }
            Toast.makeText(LocationMapExam.this, "onMapReady works!\n"+location.getLatitude()+", "+location.getLongitude(), Toast.LENGTH_SHORT).show();
            Log.d("myloc", location.getLatitude()+", "+location.getLongitude());
            googleMap.setMyLocationEnabled(true);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
        }
        else{
            Log.d("myloc","enable Provider list is empty");
        }
    }
    public void getProviders(){
        enableProvider_list = locationManager.getProviders(true);
    }

    public void getLocation() {
        for(String provider : enableProvider_list) {
            Location location = null;
            try {
                // 현재 위치 받아내기
                location = locationManager.getLastKnownLocation(provider);
                if (location != null) {
                    // 이벤트 연결
                    locationManager.requestLocationUpdates(provider, 10000, 1, this);
                }
                Log.d("msg", "=====success=====");
            } catch (SecurityException e) {
                Log.d("msg", "=====" + e.getMessage() + "=====");
            }
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("msg", "위치정보가 변경되었습니다.");
        getProviders();
        try {
            Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        } catch (SecurityException e) {
            Log.d("msg", e.getMessage());
        }

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

    @Override
    public void onCameraMove() {

    }

    @Override
    public void onCameraMoveStarted(int i) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

}
