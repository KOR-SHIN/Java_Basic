package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 *  컴퓨터는 가위 바위 보를 난수를 이용하여 출력
 *  사용자의 가위 바위 보는 Dialog()메서드를 이용해서 입력받는다.
 *  
 *  입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 *  5초안에 입력이 없으면 게임을 진것으로 처리한다.
 *  
 *  5초안에 입력이 완료되면 승패를 출력한다.
 *  
 *  결과에시)
 *  ===결과===
 *  컴퓨터 : 바위
 *  사용자 : 보
 *  결  과 : 사용자가 승리하였습니다.
 */
public class T07_ThreadGame {

	public static void main(String[] args) {
		
		CountDown th1 = new CountDown();
		
		th1.start();
		System.out.println("컴퓨터와 가위 바위 보 게임을 시작합니다.");
		String userPick = JOptionPane.showInputDialog("가위 바위 보중에 입력하세요");
		th1.interrupt(); // 카운트다운 중단
		
		String[] computerMenu = {"가위", "바위", "보"};
		String computerPick = computerMenu[(int)(Math.random()*3)];
		
		if(userPick.equals("가위") || userPick.equals("바위") || userPick.equals("보")) {
			if(userPick.equals(computerPick)) {
				printResult(computerPick, userPick, "비겼습니다");
				
			} else if(userPick.equals("가위") && computerPick.equals("바위")
					|| userPick.equals("바위") && computerPick.equals("보")
					|| userPick.equals("보") && computerPick.equals("가위")) {
				printResult(computerPick, userPick, "패배");
				
			} else if(userPick.equals("가위") && computerPick.equals("보")
					|| userPick.equals("바위") && computerPick.equals("가위")
					|| userPick.equals("보") && computerPick.equals("바위")) {
				printResult(computerPick, userPick, "승리");
			}
		} else {
			System.out.println("잘못된 입력입니다.");
			System.out.println("프로그램을 다시 시작하세요.");
			System.exit(0);
		}
	}
	
	public static void printResult(String computerPick, String userPick, String result) {
		
		if(result.equals("비겼습니다")) {
			System.out.println("===결과===");
			System.out.println("컴퓨터 : " + computerPick);
			System.out.println("사용자 : " + userPick);
			System.out.println("결  과 : " + result);
			return;
		}
		
		System.out.println("===결과===");
		System.out.println("컴퓨터 : " + computerPick);
		System.out.println("사용자 : " + userPick);
		System.out.println("결  과 : 사용자가 " + result + "하였습니다.");
	}
}

class CountDown extends Thread {
	@Override
	public void run() {
		int cnt = 5;
		while( cnt != 0 && !isInterrupted() ) {
			System.out.println(cnt--);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				interrupt();
			}
		}
		
		if(cnt==0) {
			System.out.println("시간초과로 패배하였습니다.");
			System.exit(0);
		}
	}
}
