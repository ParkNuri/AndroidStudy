package exam.day03.view.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AsyncTaskTest extends AppCompatActivity {
    TextView view1;
    TextView view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_test);
        view1 = findViewById(R.id.txtView1);
        view2 = findViewById(R.id.txtView2);

        AsyncTaskExam asyncTaskExam = new AsyncTaskExam();

        // protected String doInBackground(Integer... integers) 에 전달될 변수.
        // 가변 매개변수이기 때문에 여러 개 전달 가능.
        // class AsyncTaskExam extends AsyncTask의 제네릭(<Integer, Long, String>) 중
        // 첫번째 위치의 변수 타입으로 정의됨(<Integer)
        // 전달해 줄 게 없을때 => 첫번째 generic을 Void로 정의
        asyncTaskExam.execute(10, 20);


    }

    public void btn_click(View view) {
        long now_time = System.currentTimeMillis();
        view1.setText(now_time + "");
    }

    // AsyncTask 클래스를 상속하여 작업할 클래스를 정의
    // Void
    class AsyncTaskExam extends AsyncTask<Integer, Long, String> {
        // doInBackground 실행 전
        // Background에서 작동할 작업을 정의
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("myasync", "onPreExcute 호출.. 작업시작....");
        }

        // doInBackground의 가변매개변수 타입 <- AsyncTask class의 첫번째 generic
        @Override
        protected String doInBackground(Integer... integers) {
            int num1 = integers[0];
            int num2 = integers[1];
            for (int i = 1; i <= 10; i++) {
                SystemClock.sleep(1000);
                Log.d("myasync", "i = " + i + ", num1 = " + num1 + ", num2 =" + num2);
                long now_time = System.currentTimeMillis();
                publishProgress(now_time);
            }
            return "모든 처리작업 완료";
        }

        // view를 변경하는 작업
        // doInBackground의 가변매개변수 타입 <- AsyncTask class의 두번째 generic
        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
            // doInBackground에서 발생하는 값을 이용해서 화면을 변경하고 싶은 경우
            view2.setText("AsyncTest : "+ values[0]);
        }

        // 중간에 작업이 취소되면..
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        // doInBackground 종료 후
        // doInBackground의 가변매개변수 타입 <- AsyncTask class의 세번째 generic
        @Override
        protected void onPostExecute(String s) {
            Log.d("myasync", "onPostExecute "+s);
            super.onPostExecute(s);
            view2.setText("반환값: "+s);
        }

    }
}
