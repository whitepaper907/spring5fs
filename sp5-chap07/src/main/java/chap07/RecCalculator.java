package chap07;

public class RecCalculator implements Calculator{ //재귀함수 사용

	@Override
	public long factorial(long num) {
		if(num==0) 
			return 1;
		else
			return num * factorial(num-1);
	}
	
}
