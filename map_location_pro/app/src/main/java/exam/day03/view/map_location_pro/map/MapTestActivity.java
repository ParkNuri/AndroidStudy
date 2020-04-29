package exam.day03.view.map_location_pro.map;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

import exam.day03.view.map_location_pro.R;


public class MapTestActivity extends AppCompatActivity{
    GoogleMap map;
    MarkerOptions markerOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     

    }
    public void setPosition(View view){
      
    }
    public void setMarker(View view){
        
    }
    public void addCircle(View view){
       

    }
    public void changeMarker(View view){
        
    }
}
