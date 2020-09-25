package kr.or.ddit.basic;

public class T02_ThreadTest {

	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		t1.start();
		
		Thread t2 = new Thread(new MyThread2());
		t2.start();
		
		MyThread3 r = new MyThread3();
		Thread t3 = new Thread(r);
		t3.start();
		
	}
}

class MyThread extends Thread {
	
	@Override
	public void run() {
		for(int i=1; i<201; i++) {
			System.out.print("#");
			try {
				Thread.sleep(100); // sleep()의 단위는 ms이다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}

class MyThread2 implements Runnable {
	
	@Override
	public void run() {
		for(int i=1; i<201; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}

class MyThread3 implements Runnable {
	
	@Override
	public void run() {
		for(int i=1; i<201; i++) {
			System.out.print("@");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}


