package chap07;

public class ImpeCalculator implements Calculator{ //for문 사용

	@Override
	public long factorial(long num) {
		long result =1;
		for(long i=1;i<num;i++) {
			result *= i;
		}
		
		return result;
		
	}
	
}
