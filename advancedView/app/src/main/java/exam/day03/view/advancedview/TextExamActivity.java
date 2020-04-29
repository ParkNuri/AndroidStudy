package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextExamActivity extends AppCompatActivity {

    EditText txtinput;
    TextView outputview;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_exam);

        outputview = findViewById(R.id.outputview);
        txtinput = findViewById(R.id.inputmsg);

        Button sendbtn = findViewById(R.id.send);

        username = "누리";
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = txtinput.getText()+"";
                outputview.append(msg+".......("+username+")\n");
            }
        });
    }
}
