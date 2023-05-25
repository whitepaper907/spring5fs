package spring;

public class ChangePasswordService { // 암호 변경 기능
	
	private MemberDao memberDao;

	public ChangePasswordService(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email); // 해당 email을 가진 멤버
		if(member == null) // 익셉션 
			throw new MemberNotFoundException();
		
		member.changePassword(oldPwd, newPwd); // 암호가 일치한 경우 실행됨
		
		memberDao.update(member); //데이터 보관
		
	}
	
	public ChangePasswordService() {
	}

	public void setMemberDao(MemberDao memberDao) { // 의존주입
		this.memberDao = memberDao;
	}

	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email); // 해당 email을 가진 멤버
		if(member == null) // 익셉션 
			throw new MemberNotFoundException();
		
		member.changePassword(oldPwd, newPwd); // 암호가 일치한 경우 실행됨
		
		memberDao.update(member); //데이터 보관
		
		
	}

}
