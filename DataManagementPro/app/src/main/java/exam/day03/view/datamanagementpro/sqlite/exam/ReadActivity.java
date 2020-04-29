package exam.day03.view.datamanagementpro.sqlite.exam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import exam.day03.view.datamanagementpro.R;


public class ReadActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read);
		Intent intent = getIntent();
		Bundle data = intent.getExtras();
		TextView t = (TextView)findViewById(R.id.show);


	
	}

}
