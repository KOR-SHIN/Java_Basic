package kr.or.ddit.basic;

public class T13_ThreadStopTest {

	/**
	 * Thread의 stop()을 호출하면 스레드가 바로 작업을 중지한다.
	 * 이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서 나중에 실행되는
	 * 프로그램에 영향을 줄 수 있다.
	 * 이러한 이유로 stop()은 현재 Deprecated되어있다.
	 */
	
	public static void main(String[] args) {
//		ThreadStopEx1 th1 = new ThreadStopEx1();
//		th1.start();
//		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		th1.stop(); // Thread가 곧바로 강제종료되기 때문에 자원정리가 print되지않음
//		th1.setStop(true);
		
		//----------------------------------------------------------------------------------------
		
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.interrupt();

	}
}

class ThreadStopEx1 extends Thread {
	
	private boolean stop;

	public void setStop(boolean stop) {
		this.stop = stop;
	}


	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드 작업중");
		}
		System.out.println("자원정리 중");
		System.out.println("<< THREAD1 TERMINATED >>");
	}
}

class ThreadStopEx2 extends Thread {
	
	@Override
	public void run() {
		// 방법 1
		// sleep메서드나 join()메서드 등을 사용했을 때 interrupt를 호출하면 interruptException이 발생되고
		// 해당 스레드의 interrupted상태가 false로 변하면서 Runnable상태로 전환된다.
//		
//		try {
//			while(true) {
//				System.out.println("스레드 처리중");
//				Thread.sleep(1);
//				System.out.println(isInterrupted());
//			}
//		}catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		// 방법 2
//		while(true) {
//			System.out.println("스레드 처리중");
//			
//			if(this.isInterrupted()) { // interrupt()메서드가 호출되면 true를 반환한다.
//				System.out.println("인스턴스용 isInterrupted()");
//				break;
//			}
//		}
//		System.out.println("자원정리 중");
//		System.out.println("<< THREAD2 TERMINATED >>");
//		}
	
		// 방법 3
		// 스레드의 정적 메서드를 이용하는 방법
		while(true) {
			System.out.println("스레드 처리중");
			if(Thread.interrupted()) {
				System.out.println("정적메서드 isInterrupted()");
				break;
			}
		}
		System.out.println("자원정리 중");
		System.out.println("<< THREAD2 TERMINATED >>");
	}	
}
