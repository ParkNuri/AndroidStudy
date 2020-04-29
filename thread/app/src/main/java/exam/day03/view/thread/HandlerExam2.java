package exam.day03.view.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

public class HandlerExam2 extends AppCompatActivity {
    int num;
    Handler handler;
    TextView numView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_exam);

        numView = findViewById(R.id.numView);

        handler = new Handler();
    }
    public void btn_click(View view){
        // 버튼을 누르면 스레드를 start
        new NumThread().start();
    }
    // 요청하는 thread와 지속적으로 ui를 변경할 스레드를 분리
    // TextView의 값을 지속적으로 변경하는 쓰레드
    class UIUpdateThread implements Runnable{
        @Override
        public void run() {
            numView.setText(num+"");
        }
    }
    // 지속해서 값을 만드는 스레드
    class NumThread extends Thread{
        public void run(){
            for(int i=0;i<=10;i++){
                num = i;
                // handler에게 UI를 변경하는 스레드를 전달하며 요청
                handler.post(new UIUpdateThread());
                SystemClock.sleep(1000);
            }
        }
    }
}
