package kr.or.ddit.basic;

import javax.swing.text.html.HTMLDocument.Iterator;

public class T08_ThreadPriorityTest {
	
	public static void main(String[] args) {
		
		UpperThread th1 = new UpperThread();
		LowerThread th2 = new LowerThread();
		
		th1.setPriority(10);
		th2.setPriority(1);
		
		System.out.println("UpperThread의 우선순위 : " + th1.getPriority());
		System.out.println("LowerThread의 우선순위 : " + th2.getPriority());
		
		th1.start();
		th2.start();
		
		try {
			th1.join();
			th2.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
	}
}

class UpperThread extends Thread {
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.print(ch);
			
			for(long i=0; i<1000000000L; i++) {
				// 시간지연 loop
			}
		}
	}
}

class LowerThread extends Thread {
	@Override
	public void run() {
		for(char ch='a'; ch<='z'; ch++) {
			System.out.print(ch);
			
			for(long i=0; i<1000000000L; i++) {
				// 시간지연 loop
			}
		}
	}
}
