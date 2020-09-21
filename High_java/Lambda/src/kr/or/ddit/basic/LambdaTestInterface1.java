package kr.or.ddit.basic;

/* 추상메서드를 한 개만 가진 함수적 인터페이스 */

@FunctionalInterface
public interface LambdaTestInterface1 {
	// 반환값이 없고 매개변수도 없는 추상메서드 선언
	public void test();
}

@FunctionalInterface
interface LambdaTestInterface2 {
	// 반환값이 없고 매개변수는 있는 추상메서드 선언
	public void test(int a);
}

@FunctionalInterface
interface LambdaTestInterface3 {
	// 반환값, 매개변수가 있는 추상메서드 선언
	public int test(int a, int b);
}