package spring;

public class DuplicateMemberException extends RuntimeException{ // 동일 이메일 갖고있는 회원이 존재
	
	public DuplicateMemberException(String message) {
		super(message);
	}
}
