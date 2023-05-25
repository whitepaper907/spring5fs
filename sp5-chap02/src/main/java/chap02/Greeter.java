package chap02;

public class Greeter {
	private String format;
	
	public String greet(String guest) { // 새로운 문자열 생성
		return String.format(format, guest); // setFormat 메서드를 이용해 설정
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
}
