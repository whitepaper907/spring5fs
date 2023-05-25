package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 스프링 mvc에서 해당 클래스를 컨트롤러로 사용함 // 웹 요청을 처리 후 결과를 뷰에 전달 
public class HelloController {

	@GetMapping("/hello") // 메서드가 처리할 요청경로 -> /hello 경로로 들어온 요청을 hello() 메서드를 이용해 처리
	public String hello(Model model, // model : 컨트롤러의 처리 결과를 뷰로 전달할때
			@RequestParam(value = "name", required = false) String name) { //http 요청 파라미터 값을 메서드의 파라미터로 전달 할때, name 파라미터에 전달
		model.addAttribute("greeting","안녕하세요,"+name); // greeting: 모델 속성, 뒤에는 값 // 뷰에 전달할 데이터 설정 
		//request.setAttribute로 request 속성 저장
		return "hello"; // 처리 결과를 보여줄 뷰 이름으로 사용
		//handlerAdapter은 결과값이 String이면 뷰이름으로 갖는 ModelAndView 객체를 생성해 리턴
	}
	
}
