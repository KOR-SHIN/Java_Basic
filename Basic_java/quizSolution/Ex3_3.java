package ExZip;

public class Ex3_3 {
	public static void main(String[] args) {
		
		MethodTest test = new MethodTest();
		MethodTest.add01();
		System.out.println("add02 : " + MethodTest.add02(10));
		System.out.println("add03 : " + test.add03(10));
		test.add04();
	}
}

class MethodTest{
	//1. 클래스변수 a를 선언하고 10의 값으로 초기화 해라
	static int a = 10;
	
	//2. 클래스변수 b를 선언하고 20의 값으로 초기화 해라
	static int b = 20;
	
	//3. 인스턴스변수 c를 선언하고 50의 값으로 초기화 해라
	int c = 50;
	
	//4. 클래스변수 a와 b의 합을 출력하는 클래스메서드 add01을 구현하여라.
	public static void add01() {
		System.out.println("add01 : " + (a+b));
	}
	
	//5. 클래스변수 a, b, int 타입의 매개변수 c의 합을 반환하는 클래스메서드 add02를 구현하여라.
	public static int add02(int c) {
		return a+b+c;
	}
	
	
	//6. 인스턴스변수 c와 int 타입의 매개변수 c의 합을 반환하는 인스턴스메서드 add03을 구현하여라.
	public int add03(int c){
		return this.c + c;
	}
	
	//7. 인스턴스변수 c와 0~100사이의 임의의 정수의 합을 출력하는 인스턴스메서드 add04를 구현하여라.
	public void add04() {
		System.out.println("add04 : " + (this.c+(int)(Math.random()*101)));
	}
	
	
	
}
