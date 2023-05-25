package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//AnnotationConfigApplictionContext 클래스는 자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리
public class main {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext(AppContext.class); // AppContext에 정의한 @Bean 설정 정보를 읽어와 객체 생성 및 초기화
		Greeter g = ctx.getBean("greeter", Greeter.class); // 빈 객체 검색에 사용됨(이름, 타입)
		String msg = g.greet("스프링"); //greet 실행 
		System.out.println(msg);
		ctx.close();
	}
}
