package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.AuthInfo;
import spring.AuthService;
import spring.WrongIdPasswordException;

@Controller
@RequestMapping("/login")
public class LoginController { // 로그인 요청 처리
	private AuthService authService;

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping
	public String form(LoginCommand loginCommand,
			@CookieValue(value="REMEMBER", required=false) Cookie rCookie) {
		// value=쿠키 이름
		if(rCookie!=null) { //쿠키가 존재하면 
			loginCommand.setEmail(rCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session,
			HttpServletResponse responses) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			
			session.setAttribute("authInfo", authInfo); // 로그인 성공 시 http session에 인증 정보 객체를 저장하도록함
			
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/");
			if(loginCommand.isRememberEmail()) { // 선택여부
				rememberCookie.setMaxAge(60*60*24*30); //30일유지
			} else {
				rememberCookie.setMaxAge(0); //자동삭제
			}
			responses.addCookie(rememberCookie);
			
			return "login/loginSuccess";
		} catch(WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
	
	
}
