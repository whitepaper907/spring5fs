package spring;

import java.time.LocalDateTime;

public class MemberRegisterService { // 회원가입 서비스
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) { // 생성자를 통해 의존 객체를 주입 받음
		this.memberDao = memberDao; // 필드에 할당
	}

	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null) {
			throw new DuplicateMemberException("dup email" + req.getEmail());
		}
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberDao.insert(newMember); //멤버 저장
		return newMember.getId();
	}
}
