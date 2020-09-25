package kr.or.ddit.basic;

public class T15_SyncThreadTest {

	public static void main(String[] args) {

		ShareObject sObj = new ShareObject();

		WorkerThread wh1 = new WorkerThread("Worker 1", sObj);
		WorkerThread wh2 = new WorkerThread("Worker 2", sObj);

		wh1.start();
		wh2.start();
	}

}

// 공동으로 사용할 객체
class ShareObject {
	private int sum = 0;

//	public synchronized void add() { // method자체에 synchronized를 붙여 동기화

	public void add() {
		synchronized (this) { 
			// synchronized block을 통하여 동기화
			// 동기화처리는 최소화하여 사용하는것이 좋기때문에 메서드에 붙이는것 보다는 block으로 하는게 좋다.
			// 멀티스레드 환경에서 동기화를 처리하지않으면 기대하는 결과값과 다른 값을 얻는 상황이 생긴다.
			// 동기화처리를 하면 하나의 스레드가 작업을 종료하고 나갈떄 까지 다른 스레드가 접근하지 못한다 (BLOCKED)
			// 동기화처리를 하게되면 해당 영역은 임계영역(critical section)으로 지정되는 것
			int n = sum;
			n += 10;
			sum = n;
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}

	}
}

// 작업을 수행하는 스레드 클래스
class WorkerThread extends Thread {

	ShareObject sObj;

	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			sObj.add();
		}
	}
}