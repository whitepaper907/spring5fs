package aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {
	
	@Pointcut("execution(public * chap07..*(..))") // 실제 Advice가 적용되는 JoinPoint
	public void commonTarget() {
	}
	
}
