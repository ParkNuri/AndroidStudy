Android Studio																				2020.04.10

---

# Permission

### Manifests.xml

```xaml
---</application> 하단
<permission 				android:name="com.exam.perission.JAVA_PERMISSION"
        android:label="JAVA_PERMISSION"
        android:description="@string/per_msg"
        android:protectionLevel="normal"		// 일반/
        />

```



### [ Permission 종류 ]

* 일반 permission

* 위험 permission

* 사용자 정의 permission



### 



안드로이드에 등록되어 있는 System 권한

안드로이드 Developer 사이트 > 문서 > 검색 > "시스템 권한"

![image-20200410104000753](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200410104000753.png)

### 기본 권한

: 

1. 인터넷 권한



### 위험 권한

: Activity를 실행하거나 버튼을 누르거나 어떤 기능을 사용할 때 권한에 대한 처리를 할 수 있도록 구현

ex ) Camera

* 사용 메소드

  * ContextCompat.checkSelfPermission : permission의 현재 상태를 확인하는 메소드

    * PERMISSION_DENIED : permission이 부여되지 않은 상태
    * PERMISSION_GRANTED : permission이 부여되어 있는 상태

    

  * ActivityCompat.requestPermissions : checkSelfPermission 메소드의 리턴값이 PERMISSION_DENIED인 경우.

    권한이 체크되어 있지 않은 경우 권한을 요청하는 메세지를 표시 ( 이 메소드 이외에도 제공되는 메소드는 여러 개 )

    
    
  * onRequestPermissionsResult : requestPermissions의 결과로 호출되는 메소드 퍼미션 설정 정보를 매개변수로 넘긴다.
    
    * requestCode : permission요청할 때 넘긴 요청코드
    * permissions : 요청한 permission 목록
    * grantResults : permission 설정 성공 결과
    
    
    
    

 * 처리 순서

   1. 현재 사용하려고 하는 권한이 설정되어 있는지 체크
      
      - checkSelfPermission을 이용
      
   2. 1번에서 리턴값이 PERMISSION_DENIED인 경우 사용자가 권한을 설정할 수 있도록 메세지를 표시
      
      * requestPermissions
      
   3. 요청 처리 후 자동으로 호출되는 메소드를 통해 다음에 어떤 처리를 할 것인지 정의

      => 권한 성공 -> 기능이 실행되도록

      => 권한 실팽 -> Preference를 통해 설정할 수 있도록 액티비티를 이동하거나 

  



권한부여 = 공통 작업 -> common 패키지에 묶어서







###

내부 저장소 package 위치 

device file explorer > data > data 

: app을 지우면 지워짐

내부저장소이기 때문에 app을 통해서만 접근 가능



외부 저장소 package 위치

device file explorer > sdcard > android > data

device file explorer > mnt > sdcard

device file explorer > storage