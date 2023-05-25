package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.aspectj.lang.annotation.Around;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;

@Aspect //위 코드는 메서드 실행 전/후(Around Aspect)에 사용할 공통기능이다
@Order(1)
public class ExeTimeAspect {

//	@Pointcut("execution(public * chap07..*(..))") // 실제 Advice가 적용되는 JoinPoint
//	private void publicTarget() {
//	}
	
	//@Around("publicTarget()") // PointCut에 공통기능을 적용
	@Around("CommonPointcut.commonTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable { // ProceedingJoinPoint : 프록시 대상 객체의 메서드 호출 시 사용
		long start = System.nanoTime(); //메서드 실행 전 
		try {
			Object result = joinPoint.proceed(); // 실제 메서드를 호출  -> 대상 객체의 메서드 실행
			return result;
		} finally {
			long finish = System.nanoTime(); //메서드 실행 후
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행시간 = %d ns\n", joinPoint.getTarget().getClass().getSuperclass(),
					sig.getName(), Arrays.toString(joinPoint.getArgs()), (finish-start));
		}
	}

}