Android Studio																																				2020.04.06

---

# View : 1. Buttons (check box, radio)

### 1) checkbox 구현하기

#### ① Activity

```java
CheckActivity.java

package exam.day03.view.selectview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckActivity extends AppCompatActivity {
    // 뷰의 주소값을 담을 참조변수
    TextView text1;
    CheckBox[] checkArr = new CheckBox[3];
    Switch myswitch;
    Button showStatus;
    Button setCheckBtn;
    Button clearCheckBtn;
    Button reverseCheckStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);
        // 뷰의 주소 값을 가지고 온다. - 26버전부터는 캐스팅 클래스를 정의하지 않아도 된다.
        text1 = findViewById(R.id.checkTxt);
        checkArr[0] = findViewById(R.id.check1);
        checkArr[1] = findViewById(R.id.check2);
        checkArr[2] = findViewById(R.id.check3);
        showStatus = findViewById(R.id.btnCheck1);
        setCheckBtn = findViewById(R.id.btnCheck2);
        clearCheckBtn = findViewById(R.id.btnCheck3);
        reverseCheckStats = findViewById(R.id.btnCheck4);

        myswitch = findViewById(R.id.switch1);
        CheckBoxListener listener = new CheckBoxListener();
        // 체크박스에 리스너를 설정한다.
        for (int i = 0; i < checkArr.length; i++) {
            checkArr[i].setOnCheckedChangeListener(listener);
        }
        myswitch.setOnCheckedChangeListener(listener);
        showStatus.setOnClickListener(listener);
        setCheckBtn.setOnClickListener(listener);
        clearCheckBtn.setOnClickListener(listener);
        reverseCheckStats.setOnClickListener(listener);
    }

    //체크박스들의 상태를 TextView에 출력하기
    public void getCheckStatus() {
        text1.setText("");
        for (int i = 0; i < checkArr.length; i++) {
            //isChecked()는 체크박스가 선택되어 있으면 true리턴
            if (checkArr[i].isChecked()) {
                text1.append(checkArr[i].getTag() + "번 체크박스가 체크가 설정됨\n");
            }

        }
    }

    //모든 체크박스의 상태를 체크 상태로 설정
    public void setCheckVal(boolean chkVal) {
        for (int i = 0; i < checkArr.length; i++) {
            checkArr[i].setChecked(chkVal);
        }
    }

    //체크박스가 선택되어 있으면 해제, 해제되어있으면 선택
    public void toggle() {
        for (int i = 0; i < checkArr.length; i++) {
            checkArr[i].toggle();
        }
    }

    class CheckBoxListener
            implements CompoundButton.OnCheckedChangeListener,
            View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCheck1:
                    setCheckVal(true);
                    break;
                case R.id.btnCheck2:
                    getCheckStatus();
                    break;
                case R.id.btnCheck3:
                    setCheckVal(false);
                    break;
                case R.id.btnCheck4:
                    toggle();

            }
        }
        //체크박스 순서, 표현할 텍스트뷰, 체크상태
        //체크박스의 순서를 어떻게 넘겨 줄 것인가
        //해당 view의 xml file에서 android:tag 속성을 사용해서 식별할 string 정의 후
        //getTag 함수를 사용하여 정의한 string 불러오기
        public void display(int index, TextView txtView, boolean checkState){
            if(checkState){
                txtView.setText(index+"번째 체크박스가 선택");
            }
            else{
                txtView.setText(index+"번째 체크박스가 해제");
            }

        }

        //체크박스의 상태가 변경될때 호출되는 메소드
        //체크박스와 스위치가 선택되면 Toast로 "XXX체크박스 선택"
        //해제되면 "XXX체크박스 해제"
        // 스위치도 체크 해제에 따라 토스트 출력
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.d("selectView", buttonView.toString() + ":::::::" + isChecked+"----"+buttonView.getId());

            if (buttonView instanceof Switch) {
                /*if (isChecked) {

                    Log.d("selectView", "----work!----");
                    Toast.makeText(getApplicationContext(), buttonView.getText() + " 스위치 선택", Toast.LENGTH_SHORT).show();
                } else if (!isChecked) {
                    Toast.makeText(getApplicationContext(), buttonView.getText() + " 스위치 해제", Toast.LENGTH_SHORT).show();
                }*/
                if(buttonView.getId()==R.id.switch1){
                    if(buttonView.isChecked()){
                        Toast.makeText(CheckActivity.this, buttonView.getText()+" 활성",Toast.LENGTH_LONG).show();
                    }else if(!(buttonView.isChecked())){
                        Toast.makeText(CheckActivity.this, buttonView.getText()+" 비활성",Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                //체크되면 TextView에 체크 메세지가 출력
                display(Integer.parseInt(buttonView.getTag()+""),text1,isChecked);
/*                if (isChecked) {
                    Toast.makeText(buttonView.getContext(), buttonView.getText() + " 선택", Toast.LENGTH_SHORT).show();
                } else if (!isChecked) {
                    Toast.makeText(buttonView.getContext(), buttonView.getText() + " 해제", Toast.LENGTH_SHORT).show();
                }*/
            }
        }
    }

}
```



#### ② layout.xml

![image-20200406172259610](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200406172259610.png)

```xml
check.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".CheckActivity">

    <TextView
        android:id="@+id/checkTxt"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />
    <Switch
        android:id="@+id/switch1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
    <CheckBox
        android:id="@+id/check1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="체크박스1"
        android:tag="1"
        android:textAppearance="@style/TextAppearance.AppCompat.Large"/>
    <CheckBox
        android:id="@+id/check2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="체크박스2"
        android:tag="2"
        android:textAppearance="@style/TextAppearance.AppCompat.Large"/>
    <CheckBox
        android:id="@+id/check3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="체크박스3"
        android:tag="3"
        android:textAppearance="@style/TextAppearance.AppCompat.Large"/>\
    <Button
        android:id="@+id/btnCheck1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="체크상태설정"
        />
    <Button
        android:id="@+id/btnCheck2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="체크상태확인"
        />
    <Button
        android:id="@+id/btnCheck3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="체크상태해제"
        />
    <Button
        android:id="@+id/btnCheck4"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="체크상태변경"
        />
</LinearLayout>
```



### 2) radio 구현하기

#### ① Activity

```java
package exam.day03.view.selectview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RadioActivity extends AppCompatActivity {
    // 뷰의 주소값을 담을 참조변수
    RadioButton radio3, radio4;
    RadioGroup group1, group2;
    TextView text1, text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        // 뷰의 주소 값을 얻어온다.
        radio3 = (RadioButton)findViewById(R.id.radioButton3);
        radio4 = (RadioButton)findViewById(R.id.radioButton4);
        group1 = (RadioGroup)findViewById(R.id.group1);
        group2 = (RadioGroup)findViewById(R.id.group2);
        text1 = (TextView)findViewById(R.id.textView);
        text2 = (TextView)findViewById(R.id.textView2);

        // 라디오 그룹에 리스너를 설정한다.
        /*RadioListener listener = new RadioListener();
        group1.setOnCheckedChangeListener(listener);
        group2.setOnCheckedChangeListener(listener);*/
    }

    public void radioCheck(View v){
        radio3.setChecked(true);
        radio4.setChecked(true);
    }
    public void getCheckStatus(View v){
        //★★★★difference★★★★
        //라디오 그룸 내에서 선택되어 있는 라디오 버튼을 반환
        int id1 = group1.getCheckedRadioButtonId();
        int id2 = group2.getCheckedRadioButtonId();
        RadioButton radio1 = findViewById(id1);
        RadioButton radio2 = findViewById(id2);

/*
        text1.setText(id1+"radio button is selected");
        text2.setText(id2+"radio button is selected");
*/

        text1.setText(radio1.getText()+"radio button is selected");
        text2.setText(radio2.getText()+"radio button is selected");

    }
    //CompoundButton <= check box 시
    //RadioGroup <= radio 버튼시
    class RadioListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Log.d("radio","group getid: "+group.getId()+"========== checkedId: "+checkedId);
            switch (group.getId()){
                case R.id.group1:
                    switch (checkedId){
                        case R.id.radioButton:
                            Log.d("radio","1번 그룹의 1-1버튼");
                    }
                    break;
                case R.id.group2:

            }
        }
    }
}
```



#### ② layout.xml

```xml
activity_radio.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">


    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="TextView"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="TextView"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <RadioGroup
        android:id="@+id/group1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <RadioButton
            android:id="@+id/radioButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:checked="true"
            android:text="라디오 1-1"
            android:textAppearance="@style/TextAppearance.AppCompat.Large" />

        <RadioButton
            android:id="@+id/radioButton2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="라디오 1-2"
            android:textAppearance="@style/TextAppearance.AppCompat.Large" />

        <RadioButton
            android:id="@+id/radioButton3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="라디오 1-3"
            android:textAppearance="@style/TextAppearance.AppCompat.Large" />
    </RadioGroup>

    <RadioGroup
        android:id="@+id/group2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <RadioButton
            android:id="@+id/radioButton4"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="라디오 2-1"
            android:textAppearance="@style/TextAppearance.AppCompat.Large" />

        <RadioButton
            android:id="@+id/radioButton5"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="라디오 2-2"
            android:textAppearance="@style/TextAppearance.AppCompat.Large" />

        <RadioButton
            android:id="@+id/radioButton6"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:checked="true"
            android:text="라디오 2-3"
            android:textAppearance="@style/TextAppearance.AppCompat.Large" />
    </RadioGroup>

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="라디오 체크하기"
        android:onClick="radioCheck"/>

    <Button
        android:id="@+id/button2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="체크 상태 확인하기"
        android:onClick="getCheckStatus"/>
</LinearLayout>
```





# View : 2. List 구현하기

### 1) 