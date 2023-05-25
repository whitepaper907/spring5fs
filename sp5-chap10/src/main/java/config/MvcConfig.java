package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

@Configuration
@EnableWebMvc //MVC 설정 활성화 , 설정 생성
//HandlerMapping 등록 : @GetMapping 값을 이용해 웹 브라우저 요청을 처리할 컨트롤러 빈을 찾음
//HandlerAdapter 등록 : 결과를 ModelAndView 객체로 변환
public class MvcConfig implements WebMvcConfigurer{ // mvc 개별 설정을 도와줌 ex ViewResolver 설정 추가
	
	@Override // 매핑 경로 '/'루 주었을때, JSP/HTML/CSS 등을 올바르게 처리하기 위해 설정 추가 
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
		//추가함
		//DefaultServerletHttpRequestHandler =  WAS가 제공하는 디폴트 서블릿에 전달 /ex index.html
		//SimpleUrlHandlerMapping 
	}
	
	@Override // JSP를 이용해 컨트롤러 실행 결과를 보여주기 위한 설정
	public void configureViewResolvers(ViewResolverRegistry registry) { // jsp를 위한 viewResolver 설정 가능
		registry.jsp("/WEB-INF/view/",".jsp");  // /WEB-INFO/view/viewname.jsp -> InternalResourceView 리턴
	}
}
