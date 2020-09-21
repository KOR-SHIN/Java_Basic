package kr.or.ddit.basic;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

/**
 *	<Wild Card>
 *	<? extends T> => 와일드카드의 상한 제한, T와 그 자손들만 가능하다.
 *	<? super T> => T와 그 조상들만 가능하다. (Object제외)
 *	<?> => 모든 타입이 가능하다 <? extends Object>와 동일하다. 
 *
 */
public class T05_GenericWildCardTest {

	public static void main(String[] args) {
		
		FruitBox<Fruit> fruitBox = new FruitBox<>();
		FruitBox<Apple> appleBox = new FruitBox<>();
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());

		appleBox.add(new Apple());
		appleBox.add(new Apple());
		
		Juicer.makeJuice(fruitBox);
		Juicer.makeJuice2(fruitBox);
		Juicer.makeJuice3(fruitBox);
		Juicer.makeJuice4(fruitBox);
		
		Juicer.makeJuice(appleBox);
//		Juicer.makeJuice2(appleBox);
		Juicer.makeJuice3(appleBox);
		Juicer.makeJuice4(appleBox);
		
		
	}
	
}

class Juicer {
	
	// class와 method모두 generic타입이 아니다.
	// 따라서 대입된 타입(Fruit)을 명시해줘야 한다.
	static void makeJuice2(FruitBox<Fruit> fruit) {
		
		String str = "";
		int cnt = 0;
		
		for(Fruit f : fruit.getFruitList()) {
			if(cnt == 0) {
				str += f;
			} else {
				str += ", " + f;
			}
			cnt++;
		}
		System.out.println(str + "=> 쥬스 완성!");
	}
	
	// 클래스 메서드에서 generic타입을 사용하기 위해 wildCard를 사용
	static void makeJuice(FruitBox<? extends Fruit> box) {

		StringBuilder fruitStr = new StringBuilder();
		
		int cnt = 0;
		
		for(Fruit f : box.getFruitList()) {
			if(cnt == 0) {
				fruitStr.append(f);
			} else {
				fruitStr.append(", ").append(f);
			}
			cnt++;
		}
		
		System.out.println(fruitStr + "=> 쥬스 완성!");
	}
	
	// Generic Method로 선언하여 클래스 메서드에서 Generic타입을 사용가능
	// 매개변수 타입이 <T>이기 때문에 모든 타입을 매개변수로 받을 수 있다.
	// 따라서 enhanced-for에서 객체의 Type이 Fruit이라고 확신할 수 없기 때문에
	// for(Fruit f) -> for(T f)로 변경해야 한다.
	static <T> void makeJuice3(FruitBox<T> box) {
		String str = "";
		int cnt = 0;
		
		for(T f : box.getFruitList()) {
			if(cnt == 0) {
				str += f;
			} else {
				str += ", " + f;
			}
			cnt++;
		}
		System.out.println(str + "=> 쥬스 완성!");
	}
	
	// Generic Method로 선언하여 클래스 메서드에서 Generic타입을 사용가능
	// Generic타입의 매개변수로 Fruit과 그 자손타입만 들어오도록 제한
	// 매개변수는 Fruit과 Fruit의 서브클래스이기 때문에 
	// enhanced-for에서 타입을 Fruit으로 지정가능하다.
	static <T extends Fruit> void makeJuice4(FruitBox<T> box) {
		String str = "";
		int cnt = 0;
		
		for(Fruit f : box.getFruitList()) {
			if(cnt == 0) {
				str += f;
			} else {
				str += ", " + f;
			}
			cnt++;
		}
		System.out.println(str + "=> 쥬스 완성!");
	}
	 
	
	
}

class Apple extends Fruit {

	public Apple() {
		super("Apple");

	}
	
}

class Grape extends Fruit {

	public Grape() {
		super("Grape");

	}
	
}

class FruitBox<T> {
	private List<T> fruitList;
	
	public FruitBox() {
		fruitList = new ArrayList<>();
	}

	public List<T> getFruitList() {
		return fruitList;
	}

	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
}

class Fruit {
	
	private String name;

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일 [" + name + "]";
	}
}