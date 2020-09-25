package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class T06_ThreadTest {

	public static void main(String[] args) {
		
		/**
		 * 멀티쓰레드의 경우 사용자의 입력을 받는 부분과 카운트 하는 부분을 나눠서 작업할 수 있다.
		 * 따라서 사용자 응답시간을 줄일 수 있다.
		 */
		ThreadTest_1 th1 = new ThreadTest_1();
		
		th1.start();
		
		String input = JOptionPane.showInputDialog("아무거나 입력하세요");
		System.out.println("입력값 : " + input);
		th1.interrupt(); // thread의 isIterrupted상태를 true로 만든다 (실행중단 요청)
	}
}

class ThreadTest_1 extends Thread {
	@Override
	public void run() {
		int i = 10;
		while(i!=0 && !isInterrupted()) {
			System.out.println(i--);
			try {
				Thread.sleep(1000); // interrupt가 발생하면 InterruptException을 발생시키면서
									// thread의 isIterrupted상태를 false로 초기화 시킨다. (실행가능 상태로 만듬)
			} catch(InterruptedException e) {
				interrupt();
			}
		}
		System.out.println("카운트가 종료되었습니다.");
	}
}
