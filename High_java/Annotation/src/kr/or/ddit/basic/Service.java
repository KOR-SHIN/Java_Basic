package kr.or.ddit.basic;

public class Service {

	@PrintAnnotation
	public void method() {
		System.out.println("메서드 1번 입니다.");
	}

	// 매개변수로 value하나만 지정할 경우 "%"로만 쓸 수 있다.
	// 주의사항 : value만 가능하고 다른것은 불가능하다.
	@PrintAnnotation(value = "%")
	public void method2() {
		System.out.println("메서드 1번 입니다.");
	}
	
	@PrintAnnotation(value="#", count =25)
	public void method3() {
		System.out.println("메서드 1번 입니다.");
	}
}
