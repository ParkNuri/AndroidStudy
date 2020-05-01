# 사용자 정의 adapter만들기

- 안드로이드에서 앱을 구성할 때 목록형식을 가장 많이 사용
- 사용자 정의로 디자인한 뷰를 목록으로 사용하고 싶은 경우
- 안드로이드 내부에서 제공하는 adapter로는 표현하고 싶은 내용(이벤트 연결, 각 목록의 구성을 다르게 생성 등 ...)을 모두 표현할 수 없다. 



[구성요소]

* adapter를 이용해서 출력할 데이터를 저장하는 객체(DTO)

* 사용자 정의 Adapter

  1. 안드로이드에서 제공하는 adapter 클래스 상속

     * list view를 만들때 필요한 정보를 저장할 수 있도록 멤버변수 정의 (Context, row 디자인 리소스, 데이터)

       

  2. 생성자 정의

     * 상속받고 있는 ArrayAdapter의 생성자 호출

       

  3. ArrayAdapter에 정의되어 있는 메소드를 오버라이딩

     * getView : list view의 한 항목이 만들어질때마다 호출

       => 전달된 리소스를 이용해 view 생성(LayoutInflator)

       => 한 row를 구성하는 view를 찾아서 데이터와 연결

       

  4. getView메소드에서 성능개선을 위한 코드를 작성

     * 한 번 생성한 view를 재사용
     * findViewById는 한 번만 찾아오기

     

  5. ViewHolder 객체를 생성

     * row를 구성하는 view를 findViewById하기
     * row에 대한 구성 View를 멤버변수로 선언
     * 생성자에서 findViewById처리를 구현
     * 최초로 뷰를 만들 때( row에 대한 view ) 이 객체를 활용

     

  6. row를 구성하는 뷰에 상태값을 저장하기

     * 각 view의 이벤트를 통해 저장

     * 각 view의 상태값을 저장할 수 있도록 객체 생성

       : 상태값을 저장한 객체를 자료 구조에 저장

          focus를 잃어버릴때 상태를 저장

     * 

       

  7. 

* Adapter를 통해 만들어진 list view를 보여줄 activity

  * main layout 필요
  * 