package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링 설정 클래스
public class AppContext {
	
	@Bean //빈객체 - 생성한 객체를 스프링이 관리하는 빈 객체로 등록 
	public Greeter greeter() { 
		Greeter g = new Greeter(); // 객체 생성
		g.setFormat("%s, 안녕하세요!"); //초기화
		return g;
	}
	
	@Bean
	public Greeter greeter1() { 
		Greeter g = new Greeter(); // 객체 생성
		g.setFormat("%s, 안녕하세요!"); //초기화
		return g;
	}
}
