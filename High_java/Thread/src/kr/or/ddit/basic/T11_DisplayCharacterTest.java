package kr.or.ddit.basic;

public class T11_DisplayCharacterTest {

	/**
	 * 3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데 출력을 끝낸 순서대로
	 * 결과를 나타내는 프로그램을 작성하기.
	 */
	
	static String strRank = "";
	public static void main(String[] args) {
		
		DisplayCharacter[] disChars = new DisplayCharacter[] {
				new DisplayCharacter("1번"),
				new DisplayCharacter("2번"),
				new DisplayCharacter("3번"),
				new DisplayCharacter("4번")
		};
		
		for(int i=0; i<disChars.length; i++) {
			disChars[i].start();
		}
		
		for(DisplayCharacter dc : disChars) {
			try {
				dc.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("<< RACE END >>");
		System.out.println("== 경기결과 ==");
		System.out.println("순위 : " + strRank);
	}
	
}

/**
 * 대문자를 출력하는 Thread Class
 */
class DisplayCharacter extends Thread {
	private String name;

	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(name + "의 출력문자 : " + ch);
			
			try {
				// sleep() 메서드의값을 200~500 사이의 난수로 한다.
				Thread.sleep((int)(Math.random()* 301 + 200));
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + "의 출력 종료");
		T11_DisplayCharacterTest.strRank += name + " ";
	}
	
	
	
}