package kr.or.ddit.basic;

/**
 * 
 *	static final int A = 0; 
 *	static final int B = 1; 
 *	static final int C = 2; 
 *	static final int D = 3; 
 *	static final int E = 4;
 *
 *	enum {A, B, C, D};
 *
 *	열거형 데이터를 선언하는 방법
 *	enum 열거형이름 { 상수값1, 상수값2, 상수값3 ... }
 *
 *	열거형은 상수의 값뿐만 아니라 타입까지 관리하기 때문에 타입에 안전성이 제공된다.
 *
 */
public class T08_EnumTest {
	
	// city열거형 객체 선언(기본값을 사용하는 열거형)
	public enum City { 서울, 부산, 대구, 대전, 광주 };
	
	// 데이터값을 임의로 치환한 열거형 객체 선언
	// 데이터 값을 정해 줄 경우에는 생성자를 만들어서 괄호속의 값이 변수에 저장되도록 해야한다.
	public enum Season {
		봄("3월부터 5월까지"), 여름("5월부터 8월까지"), 가을("8월부터 11월까지"), 겨울("11월부터 2월까지");
		
		// 객체의 멤버를 저장할 변수를 선언
		private String str;
		
		// 생성자 (열거형의 생성자는 제어자가 묵시적으로 private이다)
		// 외부에서 접근하여 상수값을 변경하면 안되기때문에 private이다
		Season (String data) { // => private Season(String data)
			str = data;
		}
		
		// 값을 반환하는 메서드
		public String getStr() {
			return str;
		}
		
	}
	
	public static void main(String[] args) {
		/**
		 * 
		 * 열거형에서 사용되는 메서드
		 * 1. name() => 열거형 상수의 이름을 문자열로 반환한다.
		 * 2. ordinal() => 열거형 상수의 순서값을 반환한다 (0부터 시작)
		 * 3. valueOf("열거형 상수이름") => 지정된 열거형에서 "열거형상수이름"과 일치하는 열거형상수를 반환한다.
		 * 
		 */
		
		City myCity1;
		City myCity2;
		
		myCity2 = City.서울;
		myCity1 = City.valueOf("서울");
		myCity1  = City.valueOf(City.class, "서울");
		
		// toString이 override되어있어서, 참조변수 이름을 쓰면 .name()과 동일한 결과를 리턴한다.
		System.out.println("myCity1 : " + myCity1);
		System.out.println("myCity1의 ordinal : " + myCity1.ordinal());
		System.out.println();
		
		System.out.println("myCity2 : " + myCity2.name());
		System.out.println("myCity2의 ordinal : " + myCity2.ordinal());
		System.out.println();
		System.out.println("----------------------------------------------");
		
		Season ss = Season.valueOf("여름");
		System.out.println("name => " + ss.name());
		System.out.println("ordinal => " + ss.ordinal());
		System.out.println("get메서드 => " + ss.getStr());
		System.out.println("----------------------------------------------");
		
		// 열거형이름.values() => 데이터를 배열로 반환한다.
		Season[] sArr = Season.values();
		
		for(int i = 0; i < sArr.length; i++) {
			System.out.println(sArr[i].name() + " : " + sArr[i].getStr());
		}
		System.out.println();
		
		for(City c : City.values()) {
			System.out.println(c + " : " + c.ordinal());
		}
		
		City city = City.대구;
		
		// 열거형의 상수들은 하나의 객체이다.
		// 따라서 상수값은 주소이고, 그 주소값은 상수이기 때문에 변하지않는다.
		// 그렇기때문에 `==`을 사용해서 비교가 가능하다. (equals보다 속도가 빠르다)
		System.out.println(city == City.대전);
		System.out.println(city == City.대구);
		
		// 열거형의 상수들은 비교연산자(>, <)으로 비교불가능하다.
		// 하지만 Comparable인터페이스가 구현되어 있으므로, compareTo로 비교가능하다.
		System.out.println("대구 => " + city.compareTo(City.대구));
		System.out.println("서울 => " + city.compareTo(City.서울));
		System.out.println("대전 => " + city.compareTo(City.대전));
		
		
		
		

		
	}
	
}
