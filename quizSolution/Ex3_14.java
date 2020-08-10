package ExZip;

import java.util.Vector;

public class Ex3_14 {
	public static void main(String[] args) {
	
		//12. Tv의 객체를 생성하여라 (가격 )
		Tv tv = new Tv(100);
		
		//13. Computer의 객체를 생성하여라 (가격 300)
		Computer computer = new Computer(200);
		
		//14. Buyer의 객체를 생성하여라 (돈 1000)
		Buyer buyer = new Buyer(1000);
		
		//15. Computer를 구매하여라.
		buyer.buyComputer(computer);
		
		//16. Tv를 2대 구매하여라
		buyer.buyTv(tv);
		buyer.buyTv(tv);
		
		//17. 구매된 물품의 계산서를 출력하여라.
		//buyer.summary(computer, tv);
		
		//18. Tv한대를 반품하여라
		buyer.refund(tv);
		
		//19. 구매된 물품의 계산서를 출력하여라.
		buyer.summary(computer, tv);
	}
}

class Product{
	int price;
	int bonusPoint;
	
	//1. 매개변수로 price하나를 가지는 생성자를 작성하여라.
	//	(단, 매개변수 값으로 인스턴스변수 price를 초기화 하고 가격의 10%에 해당되는
	//	 금액을 bonusPoint에 저장하여라.
	Product(int price){
		this.price = price;
		bonusPoint = this.price/10; 
	}
}

class Tv extends Product {
	//2. 매개변수가 하나인 생성자를 작성하여라 (매개변수 : int타입의 price)
	//	Product의 매개변수가 하나인 생성자를 호출하여라. 이 때, 매개변수로 받은 price에 저장된 값을 넘겨준다.
	Tv(int price){
		super(price);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Tv";
	}
	
}

class Computer extends Product {
	//4. 매개변수가 하나인 생성자를 작성하여라 (매개변수 : int타입의 price)
	//	 Product의 매개변수가 하나인 생성자를 호출하여라. 이 때 매개변수로 받은 price에 저장된 값을 넘겨준다.
	Computer(int price) {
		super(price);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Computer";
	}
}

class Buyer {
	int money;
	int bonusPoint;
	Vector item = new Vector();
	
	//6. 매개변수가 하나인 생성자를 작성하여라 (매개변수 : int타입의 money)
	//	 매개변수의 저장된 값으로 인스턴스변수 money의 값을 초기화 하여라.
	Buyer (int money) {
		this.money = money;
	}
	
	Buyer() {
		
	}
	
	//7. Tv를 구매하는 buy메서드를 작성하여라.
	public void buyTv(Tv tv) {
		item.add(tv);
		this.bonusPoint += tv.bonusPoint;
	}
	
	//8. Computer를 구매하는 buy메서드를 작성하여라.
	public void buyComputer(Computer computer) {
		item.add(computer);
		this.bonusPoint += computer.bonusPoint;
	}
	
	//9. 매개변수의 다형성을 이용하여 7,8번 메서드를 하나로 작성하여라
	//	 물건의 종류가 늘어나도 메서드를 여러 개 만들지 않아도 된다.
	public void buyProduct(Product product){
		item.add(product);
		this.bonusPoint += product.bonusPoint;
	}
	
	//10. 구매한 물품을 반품하는 refund메서드를 작성하여라.
	public void refund(Product product) {
		item.remove(product);
		this.bonusPoint -= product.bonusPoint;
	}
	
	//11. 지금까지 구매된 내역을 결과와 같이 출력하는 summary메서드를 작성하여라.
	public void summary(Computer computer, Tv tv) {
		int totalPrice = 0;
		
		System.out.println("\t영     수     증\n구매내역");
		for(int i=0; i<item.size(); i++){
			if(item.get(i) instanceof Computer) {
				System.out.println("\tComputer : " + computer.price);
				totalPrice += computer.price;
				
			} else if(item.get(i) instanceof Tv) {
				System.out.println("\tTv : " + tv.price);
				totalPrice += tv.price;
			}
		}
		
		System.out.println("구매하신 물품의 총 금액은 " + totalPrice + "만원이며\n"
							+ "고객님의 보너스 포인트는 " + this.bonusPoint + "만 포인트입니다.\n"
							+ "이용해주셔서 감사합니다.");
		
		
		
	}  	
}
