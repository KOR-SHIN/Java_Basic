package kr.or.ddit.basic;

public class T19_WaitNotifyTest {
	
	/**
	 * wait() : 동기화 영역에서 락을 걸고 wait-set영역(공유객체별로 존재)으로 이동시킨다.
	 * notify() 또는 notifyAll() 메서드 : wait-set영역에 있는 쓰레드를 깨워서 실행할 수 있도록 한다.
	 * 									  notify()는 하나, notifyAll()은 wait-set영역 전부를 깨운다.
	 * 
	 * wait()과 notify(), notifyAll()메서드는 동기화 영역에서만 실행 할 수 있다.
	 * Object 클래스에서 제공하는 메서드이다.
	 * 
	 */
	
	public static void main(String[] args) {
		
		WorkObject workObj = new WorkObject();
		
		ThreadA a = new ThreadA(workObj);
		ThreadB b = new ThreadB(workObj);
		
		a.start();
		b.start();
	}
}


// 공유객체 클래스
class WorkObject {
	// 두 개의 쓰레드중에 하나의 쓰레드가 작업을 종료하고 TERMINATED되면
	// notify를 호출해주는 쓰레드가 없으므로 wait-set에서 계속 대기상태로 있게된다.
	public synchronized void methodA() {
		
		System.out.println("methodA()에서 작업중");

		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized void methodB() {
			
		System.out.println("methodB()에서 작업중");
	
		notify();
			
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}








// workObject의 methodA()메서드만 호출하는 객체
class ThreadA extends Thread {
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<11; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료");
	}
}

// workObject의 methodB()메서드만 호출하는 객체
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<11; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료");
	}
}