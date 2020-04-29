package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SwitchFrameActivity extends AppCompatActivity {
    LinearLayout loginView;
    LinearLayout signinView;
    LinearLayout detailView;
    TextView outputTextView;
    EditText inputName;
    EditText inputpw;
    EditText inputid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_frame);

        //set views
        signinView = findViewById(R.id.signinView);
        loginView = findViewById(R.id.loginView);
        detailView = findViewById(R.id.detailView);

        outputTextView = findViewById(R.id.resultView);

        inputName = findViewById(R.id.inputname);
        inputpw = findViewById(R.id.inputpw);
        inputid = findViewById(R.id.inputid);
        //메뉴 클릭 event

    }
    //Button이 클릭될때 호출되는 메소드
    //-> View.OnClickListener의 public void onClick(View v) 메소드와 동일한 역할

    public void myclick(View view){
        if(view.getId()==R.id.menuloginbtn){
            loginView.setVisibility(View.VISIBLE);
            signinView.setVisibility(View.INVISIBLE);
            detailView.setVisibility(View.INVISIBLE);
        }
        else if(view.getId()==R.id.menusigninbtn){
            signinView.setVisibility(View.VISIBLE);
            loginView.setVisibility(View.INVISIBLE);
            detailView.setVisibility(View.INVISIBLE);
        }
        else if(view.getId()==R.id.menudetailbtn){
            detailView.setVisibility(View.VISIBLE);
            signinView.setVisibility(View.INVISIBLE);
            detailView.setVisibility(View.INVISIBLE);
        }else if (view.getId()==R.id.loginbtn){

        }else if (view.getId()==R.id.signinbtn){
            outputTextView.setText("name : "+inputName.getText()+
                    "\nid : "+inputid.getText()+
                    "\npassword : "+inputpw.getText());
        }else if (view.getId()==R.id.loginbtn){

        }
    }
}
