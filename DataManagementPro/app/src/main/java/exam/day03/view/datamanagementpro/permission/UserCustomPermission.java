package exam.day03.view.datamanagementpro.permission;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import exam.day03.view.datamanagementpro.R;

public class UserCustomPermission extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useApp(v);
            }
        });
    }
    public void useApp(View v){
        Intent intent = new Intent("com.exam.selectview");
        startActivity(intent);
    }

}
