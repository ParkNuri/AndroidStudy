package exam.day03.view.map_location_pro.map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import exam.day03.view.map_location_pro.R;
/*
* SupportMapFragment로부터 지도 객체를 추출해야 지도에 여러가지 작업을 처리할 수 있다.
* 구글맵은 카메라로 지도를 비추고 있는 형태가 모델링되어있기 때문에 이 위치를 찾는데 시간이 걸린다.
* 따라서 내부에서 자동으로 전달될 수 있도록 작업 - v2로 바뀌면서 적용된 내용
* 1. FragmentManager를 이용해서 SupportMapFragment를 find
* 2. OnMapReadyCallback을 구현하고 onMapReady 메소드를 오버라이딩
* 3. SupportMapFragment 객체의 getMapAsync메소드를 이용해서 1번에서 구현한 OnMapReadyCallback객체를 연결
* 4. 맵이 준비되었을때 자동으로 onMapReady메소드가 호출되면서 매개변수로 구글맵이 전달된다.
*
* */

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    MarkerOptions markerOptions; //marker에 대한 정보를 담고 있는 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Map 프레그먼트를 추출
        // 현재 xml문서에 정의된 fragment를 추출할 경우 FragmentManager를 이용해서 추출
        FragmentManager manager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) manager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);  // Listener와 연결
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("mymap", " 준비완료");
        map = googleMap;
        if(map!=null){
            // 위도, 경도를 셋팅
            LatLng myloc = new LatLng(47.5575776,10.7476117);
            // 구글맵이 v2가 되면 카메라개념이 추가
            // 지도는 카메라로 아래를 내려다보는듯한 내용이 모델링
            // 변경사항에 관련된 내용을 담고 잇는 객체 - CameraUpdate
            // CameraUpdate객체에 변경할 값들을 셋팅해서 매개변수로 전달
            // CameraUpdate객체를 만드는 객체
            // CameraUpdateFactory객체의 여러 메소드를 통해서 CameraUpdate객체를 생성
            // 해당 위경도로 지도 이동
            //map.moveCamera(CameraUpdateFactory.newLatLng(myloc));
            // 지도 이동 + 줌 15
            //map.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 15));
            // target() - 화면에 출력되기 위해서 특정 위치의 중앙으로 이동
            // zoom() - 지도의 확대 축소 레벨을 설정
            CameraPosition.Builder builder = new CameraPosition.Builder();
            builder.target(myloc);  // 위경도 셋팅
            builder.zoom(15);       // 줌 레벨 셋팅
            CameraPosition position = builder.build();
            map.moveCamera(CameraUpdateFactory.newCameraPosition(position));

        }
    }
    public void setPosition(View view){
        LatLng myloc = new LatLng(37.5009706,127.0385546);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(myloc, 15));
    }
    public void setMarker(View view){
        LatLng myloc = new LatLng(37.5009706,127.0385546);
        markerOptions = new MarkerOptions();
        markerOptions.position(myloc);   // marker를 출력할 위치
        markerOptions.title("멀티캠퍼스");   // marker를 클릭했을때 보여줄 풍선 도움말 타이틀
        markerOptions.snippet("IT교육센터");  // 풍선도움말 내용(추가텍스트)
        map.addMarker(markerOptions);   // marker가 생성되어 map에 추가
    }
    public void addCircle(View view){
        // 반경을 반투명한 원으로 표현
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.strokeWidth(10);  //circle 선의 width
        circleOptions.strokeColor(0); // 선색. 0이면 안보임
        circleOptions.fillColor(Color.parseColor("#42ffff6f")); // color code 앞의 두자리 숫자 : 투명도
        circleOptions.center(new LatLng(37.5009706,127.0385546));
        circleOptions.radius(500);  // m 단위
        map.addCircle(circleOptions);
    }
    public void changeMarker(View view){
        // drawable 폴더의 resource로 저장되어 있는 img 파일을 bitmap의 형식으로 읽어오기
        BitmapDrawable bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.pngwave);
        //
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        map.addMarker(markerOptions);
        // marker 여러 개 생성시 for문 사용
    }
}
