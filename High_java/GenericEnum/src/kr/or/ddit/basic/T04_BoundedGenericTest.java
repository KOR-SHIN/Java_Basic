package kr.or.ddit.basic;

class Util2 {
	
	// Number클래스의 서브클래스만을 타입으로 받는 메서드
	// 따라서 타입으로 들어오는 것들은 모두 Number클래스를 상속받았기 때문에
	// compare메서드를 사용가능하다.
	public static <T extends Number> int compare(T t1, T t2) {
		
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}

}


public class T04_BoundedGenericTest {

	public static void main(String[] args) {
		
		int result1 = Util2.compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 2);
		System.out.println(result2);
		
		
		
		
	}
}
