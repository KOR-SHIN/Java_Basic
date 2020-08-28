package f_oop2;

import java.util.Vector;

public class ProductTest {

	public static void main(String[] args) {

		ProductList list = new ProductList();

		Product nb = new NoteBook("MacPro", 300);
		Product nb2 = new NoteBook("MS Surface", 350);

		Product st = new Styler("Samsung Styler", 400);
		Product st2 = new Styler("Mini Styler", 100);
		
		Product fri = new Fridge("LG Signature Fridge", 500);

		Buyer cus = new Buyer("buyer", 1000);

		cus.buy(nb2);
		cus.buy(nb);
		cus.buy(st2);

		cus.summary();

	}
}

class Product {

	String name;
	int price;
	int bonus;

	Product(String name, int price) {
		this.name = name;
		this.price = price;
		bonus = price / 10;
	}
}

class NoteBook extends Product {

	NoteBook(String name, int price) {
		super(name, price);
	}

	@Override
	public String toString() {
		return "NoteBook";
	}
}

class Styler extends Product {

	Styler(String name, int price) {
		super(name, price);
	}

	@Override
	public String toString() {
		return "Styler";
	}
}

class Fridge extends Product {

	Fridge(String name, int price) {
		super(name, price);
	}

	@Override
	public String toString() {
		return "Fridge";
	}
}

class Buyer {

	String name;
	int money;
	int mileage;

	Vector item = new Vector(); // 매개변수가 없는 생성자를 호출하면 기본적으로 10의 크기를 가진 Vector생성

	Buyer() {

	}

	Buyer(String name, int money) {
		this.name = name;
		this.money = money;
	}

	void buy(Product prod) {
		// 고객이 상품을 구매하기 전에 보유금액을 확인하고, 매장의 재고가 있는지 확인한다.
		// 재고가 없다면, '해당 상품을 품절되었습니다 죄송합니다.' 문장을 출력하고 메서드를 끝낸다.
		// 재고가 있고 금액이 충분하면, 고객의 item에 상품을 추가하고 해당상품의 금액만큼 고객의 보유금액을 차감하고 마일리지는
		// 상품가격의 10%를 누적시킨다.
		if (prod.price > money) {
			System.out.println("보유금액이 부족합니다.");
			return;
		}

		mileage += prod.bonus;
		money -= prod.price;
		item.add(prod);
		System.out.println(name + "고객님" + prod.name + "를 구매해주셔서 감사합니다.");

	}

	void summary() {

		System.out.println("\t영\t수\t증");
		System.out.println("구매목록");
		int total = 0;

		for (int i = 0; i < item.size(); i++) {

			System.out.println("\t" + ((Product) item.get(i)).name + " : "
									+ ((Product) item.get(i)).price);
			total += ((Product) item.get(i)).price;
		}

		System.out.println(name + "고객님의 총 구매금액은 " + total + "만원이고, 보유마일리지는 "
				+ mileage + "만원 입니다.");
		System.out.println("오늘도 좋은하루 보내십시오.");
	}

	// 1. summary
	/*
	 * 영 수 증 구매목록 MacBook 300만원 Styler 200만원 총합 500만원
	 * 
	 * XXX고객님은 잔액은 XXX만원이고 마일리지는 XXX입니다. 오늘도 좋은하루 보내십시오.
	 */

	// 2. refund
	/*
	 * 고려사항 - 물건을 구매한 내역이 없는경우는 반품할 필요가 없다 null check - 내가 구매한 물건만 반품해야 한다. - 돈을
	 * 돌려줘야하고, 마일리지를 반납해야한다. 해당 상품의 매장 재고를 한 개늘리고, 고객의 구매이력에서 해당상품의 개수를 한 개 줄인다
	 * 고객의 구매이력에서 해당상품을 구매한 이력이 없는 경우, 환불이 불가능하다
	 */
	void refund(Product prod) {

		for (int i = 0; i < item.size(); i++) {

		}
	}

}

// 3. 물품의 수량을 관리하도록 프로그램을 작성하시오 (Tv를 몇대를 샀는지 ??)
class ProductList {

}

// 4. 고객목록을 관리하시오. (누가 무엇을 샀는지, 누가 무엇을 반품했는지) 바이어 아이디를 객체로 넘겨서 바이어 클래스를 참조??
class CustomerList {

}
