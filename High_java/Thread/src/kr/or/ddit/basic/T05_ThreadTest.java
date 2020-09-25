package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class T05_ThreadTest {
	
	public static void main(String[] args) {
		/**
		 * 싱글 쓰레드의 경우 사용자의 입력을 받는 작업과 카운트를 하는 작업을 동시에 수행 불가능하다.
		 */
		Thread th1 = new Thread();
		
		String input = JOptionPane.showInputDialog("아무거나 입력하세요.");
		
		int i=10;
		while(i != 0) {
			System.out.println(i--);
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("카운트가 종료되었습니다.");
	}
}
