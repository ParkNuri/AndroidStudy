Android Studio																					2020.04.13

---

# Map & Fragment



## google Map 사용하기

1. API key 발급 받기

   ① google APIs login > 라이브러리 > Maps SDK for Android > 사용하기

   ② 사용자 인증 정보 > + 사용자 인증 정보 만들기

   ③ 

   

2. 안드로이드 avd에 google 서비스 사용 체크

   Android SDK Manager > SDK Tools > google play services 체크 > 설치

   

3. build.gradle 수정

   dependencies 에

   ```groovy
   dependencies{
       implementation 'com.google.android.gms:play-services-maps:17.0.0'
       implementation 'com.google.android.gms:play-services-location:17.0.0'
   }
   ```

   

4. 

fragment는 name 속성을 포함해야한다.

fragment는 공간을 채우는 역할만 한다





![image-20200413143657099](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200413143657099.png)

![image-20200413143732786](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200413143732786.png)

![image-20200413143808471](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200413143808471.png)