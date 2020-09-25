package kr.or.ddit.basic;

import java.time.Year;

public class T12_ThreadYieldTest {

	/**
	 * <yield()>
	 * 현재 실행 대기중인 동등한 우선순위 이상의 다른 스레드에게 실행 기회를 제공한다.
	 * 	(대기중인 스레드중에 현재 실행중인 스레드보다 우선순위가 높은 스레드가 없다면 yield()를 호출한 의미가 없다)
	 * 현재 실행 중인 스레드의 상태를 Runnable상태로 바꾼다. (Waiting이나 Blocked상태로 바뀌지 않는다)
	 * yield()메서드는 실행한다고 해서 현재 실행중인 스레드가 곧바로 Runnable상태로 전이된다고 확신할 수 없다.
	 * 
	 */
	public static void main(String[] args) {
		YieldThread1 th1 = new YieldThread1();
		YieldThread2 th2 = new YieldThread2();

		/* th1은 for문안에 yield가 선언되어 있으므로 th2가 큰 확률로 먼저 작업을 종료함 */
		th1.start();
		th2.start();
	}
}

class YieldThread1 extends Thread {
	
	@Override
	public void run() {
		for(int i=0; i<500; i++) {
			System.out.println("YieldThread1 : " + i);
			yield(); // Thread클래스의 static method
		}
		System.out.println("<< Thread1 종료 >>");
	}
}

class YieldThread2 extends Thread {
	
	@Override
	public void run() {
		for(int i=0; i<500; i++) {
			System.out.println("YieldThread2 : " + i);
		}
		System.out.println("<< Thread2 종료 >>");
	}
}