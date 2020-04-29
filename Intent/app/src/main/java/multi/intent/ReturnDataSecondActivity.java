package multi.intent;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReturnDataSecondActivity extends AppCompatActivity {
	String code;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.second2);
	    Button bt1 = (Button)findViewById(R.id.btnClose1);
		// inner class에서 사용하려면 final로 정의
		final TextView txt = findViewById(R.id.secondTxt);
		final Intent intent = getIntent();
		code = intent.getStringExtra("code");
	    bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch(code){
					case "call1":
						String data = intent.getStringExtra("info_data");
						Toast.makeText(ReturnDataSecondActivity.this,data,Toast.LENGTH_LONG).show();
						break;

					case "call2":
						String data1 = intent.getStringExtra("data");
						Toast.makeText(ReturnDataSecondActivity.this, data1, Toast.LENGTH_LONG).show();
						txt.setText(data1);
						intent.putExtra("second", "msg from second :: done");

						// 실행 후에 호출한 activity로 되돌아가기
						// 되돌아 갈때 값을 공유하기 위해 intent 객체를 넘긴다.
						// RESULT_CANCELED
						// RESULT_FIRST_USER
						// RESULT_OK
						setResult(RESULT_OK, intent);

						finish();
						break;
				}
			}
		});
	}

}
