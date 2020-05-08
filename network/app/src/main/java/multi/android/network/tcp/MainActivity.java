package multi.android.network.tcp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import multi.android.network.R;

public class MainActivity extends AppCompatActivity {
    TextView clientInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clientInfo = findViewById(R.id.clientInfo);
    }
    public void btn_connect(View v){
       
    }
  
    
}
