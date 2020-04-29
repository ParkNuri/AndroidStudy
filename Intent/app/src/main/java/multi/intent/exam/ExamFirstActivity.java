package multi.intent.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import multi.intent.R;

public class ExamFirstActivity extends AppCompatActivity {
    EditText nameView;
    EditText telView;
    Button btn;
    Button btn2;
    TextView resultView;

    public static final int INPUT_BTN=1000;
    public static final int OBJECT_BTN=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.firstexam);

        nameView = findViewById(R.id.EditText01);
        telView = findViewById(R.id.EditText02);
        btn = findViewById(R.id.Button01);
        btn2 = findViewById(R.id.Button02);

        resultView = findViewById(R.id.first_return);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //명시적 intent
                Intent intent = new Intent(ExamFirstActivity.this, ExamSecondActivity.class);

                Log.d("output","firstView | "+nameView.getText().toString()+"    "+telView.getText().toString());
                // nameView.getText()은 Editable타입으로 return 되므로 toString을 사용해 형변환!!!
                intent.putExtra("name", nameView.getText().toString());
                intent.putExtra("tel", telView.getText().toString());

                startActivityForResult(intent, INPUT_BTN);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ExamFirstActivity.this, ExamSecondActivity.class);

                User dto = new User(nameView.getText().toString(),telView.getText().toString());

                intent.putExtra("dto", dto);

                startActivity(intent);
            }
        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==INPUT_BTN){
            if(resultCode==RESULT_OK){
                boolean chkResult = data.getBooleanExtra("bestmem",false);
                if(chkResult==true){
                    resultView.setText("우수회원설정");
                }else{
                    resultView.setText("");
                }

            }
        }
    }

}
