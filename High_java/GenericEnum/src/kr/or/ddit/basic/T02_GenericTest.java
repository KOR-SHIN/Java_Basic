package kr.or.ddit.basic;

public class T02_GenericTest {

	public static void main(String[] args) {

		/**
		 * Generic의 사용이유를 보여주는 예제
		 * Generic을 사용하지않고, Object로 객체를 받으면 형변환이 불가피 해진다.
		 * 형변환을 하기위해서는 반드시 타입을 체크해야 한다.
		 * 이러한 과정은 번거롭기도하지만, 잘못된 형변환으로 인한 오류가 발생할 수 있다.
		 * 하지만 Generic을 사용하면 번거로운 형변환이나 타입체크를 하지않아도 되고
		 * 변수타입을 컴파일 시점에서 확인하기 때문에 타입의 안전성이 제공된다.
		 * 
		 * <generic>
		 * 컴파일 시점에서 타입체크를 하기때문에 타입 안전성 제공
		 * 불필요한 형변환이나 타입체크를 생략할 수 있음
		 */
		NoneGeneric ng = new NoneGeneric();
		ng.setValue("가나다라");
		
		NoneGeneric ng2 = new NoneGeneric();
		ng2.setValue(100);
		
		String strNg = (String)ng.getValue();
		System.out.println("문자열 반환값(strNg) : " + strNg);
		
		Integer intNg2 = (Integer)ng2.getValue();
		System.out.println("정수형 반환값(intNg2) : " + intNg2);
	
		MyGeneric<String> mg = new MyGeneric();
		MyGeneric<Integer> mg2 = new MyGeneric();
		
		mg.setValue("우리나라");
		mg2.setValue(500);
		
		strNg = mg.getValue();
		intNg2 = mg2.getValue();
		
		System.out.println("제너릭 문자열 반환값 : " + strNg);
		System.out.println("제너릭 문자열 반환값 : " + intNg2);
		
	}

}

class NoneGeneric {
	
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}

class MyGeneric<T> {
	
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	
}