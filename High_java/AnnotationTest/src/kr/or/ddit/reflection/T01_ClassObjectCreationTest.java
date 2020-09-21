package kr.or.ddit.reflection;


/**
 * 
 * 1. 리플렉션은 클래스, 또는 멤버변수, 메서드, 생성자에 대한 정보를 가져오거나 수정할 수 있다.
 * 2. Reflection API는 java.lang.reflection패키지와 java.lang.Class를 통해서 제공된다.
 * 3. java.lang.Class의 주요 메서드
 * 	getName(), getSuperClass(), getInterface(), getModifier()
 * 4. java.lang.relfect패키지의 주요 메서드 
 *	Field, Method, Constructor, Modifier 등
 */
public class T01_ClassObjectCreationTest {

	public static void main(String[] args) throws ClassNotFoundException {
		
		// 방법 1. Class.forName() 메서드 이용
		Class<?> klass = Class.forName("kr.or.ddit.reflection.T01_ClassObjectCreationTest");
		System.out.println(klass);
		
		// 방법 2. getClass() 메서드 이용
		T01_ClassObjectCreationTest obj = new T01_ClassObjectCreationTest();
		klass = obj.getClass();
		
		// 방법 3. .class이용
		klass = T01_ClassObjectCreationTest.class;
		System.out.println(klass);
	
	}
}
