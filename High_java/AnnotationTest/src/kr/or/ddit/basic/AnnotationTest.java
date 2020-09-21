package kr.or.ddit.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		// PrintAnnotation의 static 변수값 출력
		System.out.println(PrintAnnotation.id);
		
		// reflection 기능을 이용한 메서드 실행하기
		// 관련된 메서드 목록 가져오기
		Method[] declaredMethod = Service.class.getDeclaredMethods();
		
		for(Method m : declaredMethod) {
			System.out.println(m.getName());
			for(int i=0; i < m.getDeclaredAnnotation(PrintAnnotation.class).count(); i++) {
				System.out.print(m.getDeclaredAnnotation(PrintAnnotation.class).value());
			}
			
			System.out.println();
			
			Class<Service> klass = Service.class;
			Service service = (Service)klass.newInstance();
			m.invoke(service);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
	}
}
