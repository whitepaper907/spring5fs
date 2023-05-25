package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.RegisterRequest;

public class RegisterRequestValidator implements Validator {
//	@NotBlank
//	@Email
//	private String email;
//	@Size(min=6)
//	private String password;
//	@NotEmpty
//	private String confirmPassword;
//	@NotEmpty
//	private String name;
	
	private static final String emailRegExp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+
	"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	
	public RegisterRequestValidator() {
		pattern = Pattern.compile(emailRegExp);
	}
	
	@Override
	public boolean supports(Class<?> clazz) { // RegisterRequest 클래스로 타입 변환이 가능한지 확인 
		return RegisterRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) { // target : 대상객체 , errors : 에러코드 설정
		RegisterRequest regReq = (RegisterRequest) target;
		if(regReq.getEmail()==null||regReq.getEmail().trim().isEmpty()) { // email 값이 유효한지
			errors.rejectValue("email", "required"); // 값이 존재하지 않으면 required 에러코드
		} else {
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if(!matcher.matches()) {
				errors.rejectValue("email", "bad"); // 정규표현식이 아니면 bad 에러코드
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		//name이 null 이거나 공백문자로만 되어있다면 에러코드 추가 
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		if(!regReq.getPassword().isEmpty()) { //  
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
	}

	
}
