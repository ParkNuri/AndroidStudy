## Intent 실행 순서

[기본 실행]

1. Intent 객체를 생성하고 실행할 activity의 정보와 데이터를 셋팅
2. 안드로이드os에 intent 객체를 넘기며 의뢰
   * 액티비티 실행 함수 : startActivity
3. intent에 설정되어 있는 activity 호출
4. 호출된 activity에서는 안드로이드 os가 넘겨준 intent를 가져오기
5. intent에 셋팅된 데이터를 꺼내 활용



