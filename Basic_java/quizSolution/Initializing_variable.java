package ExZip;
/**
 * @author kor-SHIN
 * 클래스변수 -> 인스턴스변수 순서로 초기화
 * 자동초기화 -> 명시적 초기화 -> 초기화블럭 -> 생성자 순으로 초기화
 *
 */

class Init {
	static int action;
	int action2 = 5; //4. 명시적 초기화가 가장 먼저 수행됨으로 action2값은 5로 초기화된다.
	
	static {
		//2. 클래스 블록을 통해 action값이 4로 변경된다.
		action = 4;
	}
	
	{
		//5. 명시적 초기화 후 초기화블록을 통해 action2값은 4로 변경된다.
		action2 = 4;
	}
	
	Init() {
		//7. Init(int action2)생성자가 this()를 통해 기본 생성자를 호출하여 action2값은 7로 변경된다.
		action2 = 7;
	}
	
	Init(int action2){
		//6. 초기화 블록 후 생성자를 통해 값이 초기화 된다.
		this();
		this.action2 = action2; //8. 매개변수 action2는 9이기 때문에 결과적으로 action2값은 9가 된다.
	}
}
public class Initializing_variable {

	public static void main(String[] args) {
		Init init 
		//1. Init 클래스가 메모리에 로드되고 클래스변수 action이 생성된 후 0으로 초기화된다.
		= 
		new Init(9);
		//3. new를 통해 Init클래스의 인스턴스가 생성되고 인스턴스변수 action2가 메모리에 로드된다.
	
		System.out.println("Class Variable Action : " + Init.action);
		System.out.println("Instance Variable Action2 : " + init.action2);
	}
}
