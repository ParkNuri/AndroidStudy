package exam.day03.view.thread.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import exam.day03.view.thread.R;

public class AsyncExam extends AppCompatActivity {
    Button btn1;
    Button btn2;
    TextView textView;
    ProgressBar bar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_exam);

        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView);
        bar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.imageView);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskExam asyncTaskExam = new AsyncTaskExam();
                asyncTaskExam.execute();
            }
        });

    }
    class AsyncTaskExam extends AsyncTask<Void, Integer, Integer>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btn1.setEnabled(false);
            btn2.setEnabled(true);
            bar.setMax(50);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            int sum = 0;
            for (int i =1;i<=50;i++){
                sum=sum+i;
                SystemClock.sleep(100);
                publishProgress(i);
            }
            return sum;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.setText(values[0]+"");
            bar.setProgress(values[0]);
            if(values[0]%2==0){ // even
                imageView.setImageResource(R.drawable.d1);
            }else{  // odd
                imageView.setImageResource(R.drawable.d2);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            textView.setText(integer+"");
            btn1.setEnabled(true);
            btn2.setEnabled(false);
        }
    }

}
