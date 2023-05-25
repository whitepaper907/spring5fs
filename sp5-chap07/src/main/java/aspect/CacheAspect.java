package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class CacheAspect {
	
	private Map<Long, Object> cache = new HashMap<>();
	
	/*
	@Pointcut("execution(public * chap07..*(long))") // 첫번째인자가 long -> factorial(long)
	public void cacheTarget() {
		
	}*/
	
	//@Around("cacheTarget")
	@Around("CommonPointcut.commonTarget()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		Long num = (Long)joinPoint.getArgs()[0]; // 첫 번째 인자를 Long 타입으로 구함
		if(cache.containsKey(num)) { // 구한 키값이 존재하면
			System.out.printf("CacheAspect : Cache에서 구함[%d]\n", num);
			return cache.get(num); // 객체 구해서 리턴
		}
		//존재 x면 프록시 대상 객체 실행
		Object result = joinPoint.proceed();
		cache.put(num, result); // 결과 cache에 추가
		System.out.printf("CacheAspect: Cache에 추가[%d]\n", num);
		return result; // 객체 리턴
	}
}
