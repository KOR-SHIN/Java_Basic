package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class T06_WildCardTest {

	public static void main(String[] args) {
		
		// FruitBox2<? extends Fruit> fruitbox1 = new FruitBox2<Fruit>();
		// FruitBox2 클래스 자체에 상한제한이 걸려있다.
		// new연산자 뒤에 생략된 Generic의 대입된 타입은 ?가 아니라 Fruit이다.
		FruitBox2<?> fruitbox1 = new FruitBox2();
		FruitBox2<?> fruitbox2 = new FruitBox2<>(); // 위와 동일함
		
		// FruitBox2<?>는 Fruitbox2<? extends Fruit>을 의미함
		// Object는 FruitBox2의 Generic범위를 초과하였음
//		FruitBox2<?> fruitbox3 = new FruitBox2<Object>(); 
		
		// 두 타입(Object, Fruit)이 일치하지 않음
		// Object는 FruitBox2의 Generic범위를 초과하였음
//		FruitBox2<Object> fruitbox4 = new FruitBox2<Fruit>(); 

		// FruitBox2<? extends Fruit> fruitbox1 = new FruitBox2<Fruit>();
		FruitBox2<?> fruitbox5 = new FruitBox2<Fruit>();
		
		FruitBox2<? extends Fruit> fruitbox6 = new FruitBox2<Apple>(); 
		FruitBox2<? extends Fruit> fruitbox7 = new FruitBox2<Grape>(); 
		
		// new 연산자는 타입이 명확해야 객체생성이 가능하다 (wildCard와 new연산자는 함께 사용이 불가능하다)
//		FruitBox2<? extends Fruit> fruitbox8 = new FruitBox2<? extends Fruit>(); 
		
	}
	
}

class FruitBox2 <T extends Fruit> {

	List<T> itemList = new ArrayList<>();
	
	public void addItem(T item) {
		itemList.add(item);
	}

	public List<T> getItemList() {
		return itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "FruitBox2 [itemList=" + itemList + "]";
	}
	
}