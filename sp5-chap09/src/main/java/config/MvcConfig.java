package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

@Configuration
@EnableWebMvc //MVC 설정 활성화 , 설정 생성
public class MvcConfig implements WebMvcConfigurer{ // mvc 개별 설정을 도와줌 
	
	@Override // 매핑 경로 '/'루 주었을때, JSP/HTML/CSS 등을 올바르게 처리하기 위해 설정 추가
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override // JSP를 이용해 컨트롤러 실행 결과를 보여주기 위한 설정
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/",".jsp");
	}
}
