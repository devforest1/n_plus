## 개발환경
1. JDK : 17
2. IDE : IntelliJ
3. STS version : 3.1.10
4. Language : Java

## 플로그인
<pre>
  <code>
    dependencies {
    	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    	implementation 'org.springframework.boot:spring-boot-starter-web'
    	compileOnly 'org.projectlombok:lombok'
    	developmentOnly 'org.springframework.boot:spring-boot-devtools'
    	runtimeOnly 'com.h2database:h2'
    	annotationProcessor 'org.projectlombok:lombok'
    	testImplementation 'org.springframework.boot:spring-boot-starter-test'
    }
  </code>
</pre>


### 프로젝트 생성 이유
- JPA 를 사용시 ManyToOne이나 OneToMany 를 사용하는 경우가 많다. 사용할 경우 상위 객체를 조회하면서 하위 객체도 불러오는 경우가 있다. 이슈 상황을 재현하고, 해결방법을 테스트하기 위해 프로젝트를 생성하였다.


### N+1 원인
- JPA Repository를 활용해 인터페이스 메소드를 호출할 때 (Read 시)
- 1:N 또는 N:1 관계를 가진 엔티티를 조회할 때 발생함
- JPA Fetch 전략이 EAGER 전략으로 조회하는 경우
- JPA Fetch 전략이 LAZY 전략으로 데이터를 가져온 이후에 연관 관계인 하위 엔티티를 다시 조회하는 경우


### 해결 방법
- Fetch Join, EntityGraph 이용하여 해결할 수 있음



