package f_oop2;

import java.util.Arrays;
import java.util.Scanner;

public class DeckTest {
	public static void main(String[] args) {


		// Card card = new Card();
		// System.out.println(card.toString());
		//
		// Card card2 = new Card(2, 5);
		// System.out.println(card2.toString());

		// 8. 카드 한 벌의 객체를 생성
		Deck deck = new Deck();

		// 9. 0번 index의 카드 한 장을 뽑기
		System.out.println("pick(0) : " + deck.pick(0));

		// 10. 임의의 index번째 카드 한 장을 뽑기
		System.out.println("Random pick : " + deck.pick());

		// 11. 카드를 자동 섞기
		System.out.println("Card Shuffle(Auto)");
		deck.shuffle();

		//자동섞기 중복검사
		Card[] tmp = Arrays.copyOf(deck.c, deck.c.length);
		for(int i=1; i<tmp.length; i++) {
			if(tmp[i] == tmp[i-1]) {
				System.out.println("중복발견");;
			}
		}
		
		// 12. 0번의 index의 카드 한 장을 뽑기
		System.out.println("pick(0) : " + deck.pick(0));

		// 13. 카드 1000번 섞기
		System.out.println("Card Shuffle(1000)");
		deck.shuffle(1000);

		//인덱스 횟수만큼 반복하는 섞기 중복검사
		tmp = Arrays.copyOf(deck.c, deck.c.length);
		for(int i=1; i<tmp.length; i++) {
			if(tmp[i] == tmp[i-1]) {
				System.out.println("중복발견");;
			}
		}
		
		
		// 14. 0번 index의 카드 한 장을 뽑기
		System.out.println("pick(0) : " + deck.pick(0));
		
	}
}

class Card {

	static final int KIND_MAX = 4;
	static final int NUM_MAX = 13;

	static final int SPADE = 1;
	static final int DIAMOND = 2;
	static final int HEART = 3;
	static final int CLOVER = 4;

	static int width = 100;
	static int height = 250;

	int kind;
	int number;

	Card(int kind, int number) {
		this.kind = kind;
		this.number = number;
	}

	Card() {
		this(SPADE, 1);
	}

	@Override
	public String toString() {

		String kind = "";
		String number = "";

		switch (this.kind) {
		case 1:
			kind = "SPADE";
			break;
		case 2:
			kind = "DIAMOND";
			break;
		case 3:
			kind = "HEART";
			break;
		default:
			kind = "CLOVER";
		}

		switch (this.number) {
		case 1:
			number = "A";
			break;
		case 11:
			number = "J";
			break;
		case 12:
			number = "Q";
			break;
		case 13:
			number = "K";
			break;
		default:
			number = this.number + "";

		}

		return "Card [kind=" + kind + ", number=" + number + "]";
	}
}

class Deck {
	// 1. 카드의 수량을 저장할 수 있는 변수 CARD_NUM을 선언하고 Card클래스의 변수를 사용하여 초기화하여라.
	static final int CARD_NUM = Card.KIND_MAX * Card.NUM_MAX;

	// 2. 카드 52장을 저장할 수 있는 변수 c를 선언하고 생성하여라.
	Card[] c = new Card[CARD_NUM];

	// 3.기본생성자를 작성하여라 (변수 c의 모든방에 카드 1,1 ~ 4,13까지 생성하여 저장하여라)
	Deck() {
		int idx = 0;

		for (int kind = 1; kind < Card.KIND_MAX + 1; kind++) {
			for (int number = 1; number < Card.NUM_MAX + 1; number++) {
				c[idx++] = new Card(kind, number);
			}
		}
	}

	// 4. c에서 임의의 index번째 카드 한 장을 반환하는 메서드(pick)를 작성하여라.
	public Card pick() {
		int randomIdx = (int) (Math.random() * CARD_NUM);
		return c[randomIdx];
	}

	// 5. 사용자로부터 입력받은 index번째 카드 한 장을 반환하는 메서드(pick)를 작성하여라.
	// 단 입력받은 값이 0~51사이의 정수라면 입력받은 index번째 카드를 반환하고, 그렇지않으면 랜덤번호를 출력하고
	// 임의의 한 장을 반환하도록 하여라.
	public Card pick(int idx) {
		if (0 <= idx && idx < 52) {
			return c[idx];
		} else {
			return pick();
		}
	}

	// 6. c의 카드를 섞는 메서드(shuffle)을 작성하여라 - 자동섞기
	// 단, 카드 섞는 법 : 연습문제 5-6의 방법을 활용
	// 배열의 index순서대로 index의 요소와 임의의 요소를 골라서 값을 바꾼다.
	public void shuffle() {
		for (int i = 0; i < CARD_NUM; i++) {
			int randomIdx = (int)(Math.random()*c.length);
			
			Card temp = c[i];
			c[i] = c[randomIdx];
			c[randomIdx] = temp;			
		}
		
	}

	// 7. 사용자로부터 입력받은 횟수만큼 c의 카드를 섞는 메서드(shuffle)을 작성하여라.
	// 단, 임의의 방 두개를 뽑아 두개의 index번째 요소의 위치를 바꾼다.
	// 이를 사용자로부터 입력받은 횟수만큼 반복한다.
	public void shuffle(int idx) {

		for (int i = 0; i < idx; i++) {

			int randomIdx = (int) (Math.random() * c.length); 
			int randomIdx2 = (int) (Math.random() * c.length);
			
			Card temp = c[randomIdx];
			c[randomIdx] = c[randomIdx2];
			c[randomIdx2] = temp;

		}

	}

}