package kr.or.ddit.basic;


class Util {

	/**
	 * 제너릭 메서드 <T, R> R method(T t) 
	 * 
	 * 파라미터 타입과 리턴타입으로 타입 파라미터를 가지는 메서드
	 * 선언방법 : 리턴타입 앞에 <> 기호를 추가하고 타입 파라미터를 기술 후 사용함
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {

		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		
		return keyCompare&&valueCompare;
	}

}

/**
 *	멀티타입을 가지는 Generic 클래스
 *	
 * @param <K>
 * @param <V>
 */
class Pair<K, V> {

	private K key;
	private V value;

	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	//key와 value값을 출력하는 메서드
	public <K, V> void printKV(K key, V value) {
		// 메서드에 선언된 K, V는 지역변수이다 (클래스에 선언된 것과 별개)
		System.out.println(key.toString() + " : " + value.toString());
	}
	

}

public class T03_GenericMethodTest {
	
	public static void main(String[] args) {
		
		Pair<Integer, String> p1 = new Pair<>(100, "유관순");
		Pair<Integer, String> p2 = new Pair<>(100, "유관순");
		
		boolean result1 = Util.compare(p1, p2);
		
		if(result1) {
			System.out.println("논리적(의미)으로 동일한 객체입니다.");
		} else {
			System.out.println("논리적(의미)으로 다른 객체입니다.");
		}
		
		Pair<String, String> p3 = new Pair<>("001", "홍길동");
		Pair<String, String> p4 = new Pair<>("002", "홍길동");
		
		boolean result2 = Util.compare(p3, p4);
		
		if(result2) {
			System.out.println("논리적(의미)으로 동일한 객체입니다.");
		} else {
			System.out.println("논리적(의미)으로 다른 객체입니다.");
		}
		
		// Generic은 생략가능하다. => p1.printKV() 가능
		// 대부분 Generic을 생략가능하지만 동일한 클래스 내에서 클래스메서드를 호출하는 경우
		// 참조연산자를 사용하지 않고 이름만으로 호출가능하다.
		// 이러한 경우 클래스메서드가 Generic메서드라면, Generic타입을 명시해야한다.
		p1.<String, Integer>printKV("키값", 1000);
	}
	
	
	
	
	
	
	
}