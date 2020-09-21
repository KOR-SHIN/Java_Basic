package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

public class T08_equals_HashcodeTest {

	public static void main(String[] args) {
		
		/**
		 * 해시함수(Hash Function)은 임의의 길이의 데이터를 고장된 길이의 데이터로
		 * 매핑해주는 함수이다.
		 * 해시함수에서 얻어지는 값은 해시값, 해시코드, 해시체크섬 또는 간단하게 해시라고 한다.
		 * 
		 * HashSet, HashMap, Hashtable과 같은 객체들을 사용하는 경우
		 * 객체가 서로 같은지를 비교하기 위해 equals() 메서드와 hashcode()메서드를 호출한다.
		 * 그래서 객체가 서로 같은지 여부를 결졍하려면 두 메서드를 오버라이딩 해야한다.
		 * 
		 * HashSet, HashMap, Hashtable에서는 객체가 같은지 여부를 데이터를 추가할 때 검사한다.
		 * 
		 * equals() Method는 두 객체의 내용(값)이 같은지 비교하는 메서드이고,
		 * hashCode()메서드는 두 객체가 같은 객체인지를 비교하는 메서드이다.
		 * hashCode()는 메모리주소를 기반으로 값을 생성한다.
		 * 
		 * equals() Method와 hashCode() Method에 관한 규칙
		 * 1. 두 객체가 같으면 반드시 같은 hashCode값을 가져야 한다.
		 * 2. 두 객체가 같으면 equals() Method를 호출할 때 반드시 true를 return해야한다.
		 * 		=> 객체 a, b가 같다면 a.equals(b)와 b.equals(a) 둘 다 true를 반환해야 한다.
		 * 3. 두 객체의 hashCode가 같다고 해서 두 객체가 반드시 같은 객체는 아니지만 두 객체가 같으면
		 *    반드시 같은 hashCode값을 가져야 한다.
		 * 4. equals() Method를 overriding하는경우 반드시 hashCode도 overriding해야한다.
		 * 5. hashCode는 기본적으로 Map에 있는 각 객체에 대한 메모리 주소 매핑 정보를 기원으로 한 정수 값을 반환한다.
		 *    그러므로 클래스에서 hashCode() Method를 Overriding하지 않으면 절대로 두 객체가 같은 것으로 간주 될 수 없다.
		 *    hashCode() Method에서 사용하는 '해싱 알고리즘'에서 서로 다른 객체에 대하여 같은 hashCode값을 만들어 낼 수 있다.
		 *    그러므로 객체가 같지 않더라도 hashCode값이 같을 수 있다. (hashCode만을 가지고 객체의 일치여부를 판단할 수 없다)
		 *    
		 *    
		 * 객체비교 순서
		 * hashCode()값을 보고 두 객체의 일치여부를 판단한다(equals보다 속도가 빠르기 때문에 hashCode()를 사용해서 먼저 비교해본다)
		 * 	=> hashCode()는 메모리기반으로 값을 생성하기 때문에 두 객체가 달라도 같은 hashCode를 반환할 수 있다.
		 *  => 따라서 equals()로 한 번 더 점검한다.
		 * hashCode()값이 일치하면 내용(값)이 일치하는지 equals() Method를 사용하여 두 객체의 일치여부 확인
		 * 
		 * 
		 */
		
		Person p1 = new Person(1, "강감찬");
		Person p2 = new Person(1, "강감찬");
		Person p3 = new Person(3, "이순신");
		
		// equals를 오버라이드 하지않으면 Object클래스의 equals를 사용한다.
		// Object클래스에서는 메모리를 기반으로 비교하기 때문에 절대 같은 객체로 판단되지 않는다.
		// 따라서 Object의 equals와 hashcode를 오버라이드하여 객체 일치여부를 판단해줘야 한다.
		System.out.println("p1.equals(p2) : " + p1.equals(p2));
		System.out.println("p1 == p2 : " + (p1 == p2));
		
		Set<Person> set = new HashSet<>();
		set.add(p1);
		set.add(p2);
		
		System.out.println();
		System.out.println("[p1, p2,등록 후 데이터]");
		for(Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		
		System.out.println();
		System.out.println("p3 add 성공여부 : " + set.add(p3));
		
		System.out.println();
		System.out.println("[p3 등록 후 데이터]");
		for(Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		
		System.out.println();
		System.out.println("remove(p2) 성공여부 : " + set.remove(p2));
		
		System.out.println();
		System.out.println("[remove(p2) 후 데이터]");
		for(Person p : set) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		
		
		
	}
}

class Person {
	
	private int id;
	private String name;
	
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		// p1.equals(p2)인 경우
		if (this == obj)
			// p1과 p2가 같은객체인가
			return true;
		
		if (obj == null)
			// p2가 null인가
			return false;
		
		if (getClass() != obj.getClass())
			// p1과 p2의 클래스타입이 같은가
			return false;
		
		// 클래스 타입이 같다면 p2를 다운캐스팅한다.
		// 인스턴스 멤버의 값을 참조하기 위함
		Person other = (Person) obj;
		
		if (id != other.id)
			// p1과 p2의 id값이 같은가
			return false;
		
		if (name == null) {
			if (other.name != null)
				// p1의 name은 null인데 p2의 name이 null이 아닌경우
				return false;
			
		} else if (!name.equals(other.name))
			// p1과 p2의 name이 같지않은경우
			// String클래스의 equals를 사용했기 때문에 문자열의 내용으로 비교한다.
			return false;
		
		return true;
	}
	
	

	
	
	
	
	
}