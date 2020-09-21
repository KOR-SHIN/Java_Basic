package kr.or.ddit.basic;

public class T02_LambdaTest {
	public static void main(String[] args) {
		
		// 람다식을 사용하지 않았을 경우
		LambdaTestInterface1 lam1 = new LambdaTestInterface1() {
			
			@Override
			public void test() {
				System.out.println("Hello World");
				System.out.println("익명구현 객체 방식사용");
			}
		};
		lam1.test();

		LambdaTestInterface1 lam2 = () -> { System.out.println("Hello World\n람다식으로 처리하는 방식"); };
		lam2.test();
		System.out.println("----------------------------------------------------------");
	
		/**
		 * <람다식 작성 방법>
		 * 기본형식 : (자료형 이름 매개변수명, ...) -> {실행문들...};
		 * 
		 * 매개변수의 '자료형이름'은 생략할 수 있다.
		 * ex. (int a) -> { System.out.println(a); }; => (a) -> { System.out.println(a); };
		 * 
		 * 매개변수가 한 개일 경우에는 괄호 '( )'를 생략할 수 있다.
		 * (단, `자료형이름`을 지정할 경우에는 괄호를 생략할 수 없다.)
		 * ex. a -> { System.out.println(a); };
		 * 
		 * `실행문`이 1개일 경우에는 `{ }`를 생략할 수 있다.
		 * (이 때, 문장의 끝을 나타내는 세미콜론도 생략한다.)
		 * ex. a -> System.out.println(a)
		 * 
		 * 매개변수가 하나도 없으면 괄호 '( )`를 생략할 수 없다.
		 * ex. () -> System.out.println(a)
		 * 
		 * 반환값이 있을 경우에는 return명령을 사용한다.
		 * ex. (a, b) -> { return a+b; };
		 * 	   (a, b) -> return a+b
		 * 
		 * 실행문에 return문만 있을 경우 return명령과 `{ }`를 생략할 수 있다.
		 * ex. (a, b) -> a+b
		 */
	
		LambdaTestInterface2 lam3 = 
				(int z) -> {
					int result = z + 100;
					System.out.println("result = " + result);
				};
				lam3.test(60);
				
		LambdaTestInterface2 lam4 =
				// 자료형 타입은 생략할 수 있다.
				z -> {
					int result = z + 300;
					System.out.println("result = " + result);
				};
				lam4.test(90);
		System.out.println("----------------------------------------------------------");

		LambdaTestInterface3 lam5 =
				(int x, int y) -> {
					int r = x + y;
					return r;
				};
		int r = lam5.test(20, 50);
		System.out.println("r = " + r);
		
		LambdaTestInterface3 lam6 = (x, y) -> {	return x+y; };
		r = lam6.test(80, 50);
		System.out.println("r = " + r);
		
		// 실행문에 return만 있을경우 return과 `{ }`를 생략할 수 있다.
		LambdaTestInterface3 lam7 = (x, y) -> x + y;
		r = lam7.test(100, 200);
		System.out.println("r = " + r);
		
		LambdaTestInterface3 lam8 = (x, y) -> { return x > y ? x : y; };
		r = lam7.test(100, 200);
		System.out.println("r = " + r);
		
	}
}
