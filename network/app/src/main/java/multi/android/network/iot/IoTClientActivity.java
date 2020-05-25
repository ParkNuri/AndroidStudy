package multi.android.network.iot;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import multi.android.network.R;

public class IoTClientActivity extends AppCompatActivity {
    IoTAsyncTask asyncTaskExam;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    Socket socket;
    OutputStream os;
    PrintWriter pw;
    String androidId;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_io_t_client);

        // 로그인을 해서 DB에 접속한 후에 (인증 후) 발생된 id를 넘겨받아서 작업
        // 아래 코드는 id가 없기 때문에 랜덤수로 아이디를 할당해줌
        Random r = new Random();
        flag =r.nextInt(2)+1;
        if(flag%2==0){
            androidId = "1111";
        }else{
            androidId = "2222";
        }

        asyncTaskExam = new IoTAsyncTask();
        asyncTaskExam.execute(10,20);
    }
    public void send_msg(final View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message="";
                if(view.getId()==R.id.led_on){
                    message = "led_on";
                }else if(view.getId()==R.id.led_off){
                    message = "led_off";
                }else{
                    message = "다른거";
                }
                pw.println("job/"+message+"/phone/"+androidId);
                pw.flush();
            }
        }).start();
    }
    class IoTAsyncTask extends AsyncTask<Integer,String,String> {
        @Override
        protected String doInBackground(Integer... integers) {
            try {

                socket = new Socket("70.12.116.65", 12345);
                if (socket != null) {
                    ioWork();
                }
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            String msg;
                            try {
                                msg = br.readLine();
                                Log.d("myiot", "서버로 부터 수신된 메시지>>"
                                        + msg);
                            } catch (IOException e) {
                                try {
                                    //2. 서버쪽에서 연결이 끊어지는 경우 사용자는 자원을 반납======
                                    //자원반납
                                    is.close();
                                    isr.close();
                                    br.close();
                                    os.close();
                                    pw.close();
                                    socket.close();

                                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                                break;//반복문 빠져나가도록 설정
                            }
                        }
                    }
                });
                t1.start();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
        // 최초 접속시 서버에게 접속한 아이디에 대한 정보를 보내기
        void ioWork(){
            try {
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);

                os = socket.getOutputStream();
                pw = new PrintWriter(os,true);
                pw.println("phone/"+androidId);
                pw.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
