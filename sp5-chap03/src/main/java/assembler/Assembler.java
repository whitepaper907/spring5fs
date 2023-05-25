package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {

		private MemberDao memberDao;
		private MemberRegisterService regSvc;
		private ChangePasswordService pwdSvc;
		
		public Assembler() {
			memberDao = new MemberDao();
			regSvc = new MemberRegisterService(memberDao); // 의존 주입
			pwdSvc = new ChangePasswordService();
			pwdSvc.setMemberDao(memberDao); // 의존 주입
		}

		public MemberDao getMemberDao() {
			return memberDao;
		}

		public MemberRegisterService getMemberRegSvc() {
			return regSvc;
		}

		public ChangePasswordService getChangePwdSvc() {
			return pwdSvc;
		}
		
		
}
