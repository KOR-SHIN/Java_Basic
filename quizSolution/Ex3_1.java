package ExZip;

public class Ex3_1 {
	public static void main(String[] args) {
		
		//Card클래스의 폭을 출력하라
		System.out.println("Card Width : " + Card.width);
	
		//Card클래스의 높이를 출력하라
		System.out.println("Card Height : " + Card.height);
	
		//Card클래스의 객체를 생성해 주세요 변수명  cd1
		Card cd1 = new Card();
		
		//변수 cd1의 이름을 "Daniel"로 변경하여라.
		cd1.name = "Daniel";
		
		//변수 cd1의 직원 번호를 19961210으로 변경하여라.
		cd1.number = 19961210;
		
		//Card클래스의 객체를 생성해 주세요 변수명 cd2
		Card cd2 = new Card();
		
		//변수 cd2의 이름을 "nayeon"으로 변경하여라.
		cd2.name = "nayeon";
		
		//변수 cd2의 직원 번호를 19950922로 변경하여라.
		cd2.number = 19950922;
		
		//결과를 출력하여라.
		System.out.println("1번 카드의 이름은" + cd1.name + ", 직원번호 " + cd1.number 
							+ ", 폭은 " + cd1.width + ", 높이는" + cd1.height);
		
		//Card클래스의 폭을 70으로 변경하여라.
		Card.width = 70;
		
		//Card클래스의 높이를 100으로 변경하여라.
		Card.height = 100;
		
		//결과를 출력하여라.
		System.out.println("2번 카드의 이름은" + cd2.name + ", 직원번호 " + cd2.number 
							+ ", 폭은 " + cd2.width + ", 높이는" + cd2.height);
	}
}

class Card{
	String name;
	int number;
	static int width = 100;
	static int height = 250;
}
