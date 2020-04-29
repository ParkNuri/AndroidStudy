package multi.intent.exam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import multi.intent.R;

public class ExamSecondActivity extends AppCompatActivity {
    TextView outputView;
    Button btn;
    CheckBox chk;

    String name;
    String tel;

    boolean bestmem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.exam_secondview);

        outputView = findViewById(R.id.exam_result_txt);
        btn = findViewById(R.id.exam_close);
        chk = findViewById(R.id.member_state);

        final Intent intent = getIntent();


        Log.d("output","name:"+name+",   tel:"+tel);

        outputView.setText("입력한 ID: "+name+",\t입력한 pass: "+tel);

        if(name==null){
            User dto = intent.getParcelableExtra("dto");
            outputView.setText(dto.name+", "+ dto.tel);
        }else{
            name = intent.getStringExtra("name");
            tel = intent.getStringExtra("tel");
            //output.
        }

        chk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(chk.isChecked()){
                    bestmem = true;
                }else if(!chk.isChecked()){
                    bestmem = false;
                }
            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("bestmem",bestmem);

                setResult(RESULT_OK, intent);

                finish();
            }
        });





    }
}
