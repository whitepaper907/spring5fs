package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.DuplicateMemberException;
import spring.Member;
import spring.MemberDao;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@RestController
public class RestMemberController {

	private MemberDao memberDao;
	private MemberRegisterService registerService;
	
	@PostMapping("/api/members")
	public void newMember(
			@RequestBody @Valid RegisterRequest regReq,
			HttpServletResponse response) throws IOException{
		try {
			Long newMemberId = registerService.regist(regReq);
			response.setHeader("Loacation", "/api/members/"+newMemberId);
			response.setStatus(HttpServletResponse.SC_CREATED);
		}catch(DuplicateMemberException dupEx) {
			response.sendError(HttpServletResponse.SC_CONFLICT);
		}
		/*
		@RequestBody RegisterRequest regReq, Errors errors, HttpServletResponse response)
		throws IOException {
		try {
			new RegisterRequestValidator().validate(regReq, errors);
			if(errors.hasErrors()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			} catch(DuplicateMemberException dupEx) {
				response.sendError(HttpServletResponse.SC_CONFLICT);
			}
	
		}*/
	}
			
	
	
	@GetMapping("/api/members")
	public List<Member> members(){
		return memberDao.selectAll();
	}
	
	@GetMapping("/api/members/{id}")
	public Member member(@PathVariable Long id) {
		Member member = memberDao.selectById(id);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		return member;
	}

	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoData(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
	}
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.registerService = memberRegisterService;
	}
	
	
}
