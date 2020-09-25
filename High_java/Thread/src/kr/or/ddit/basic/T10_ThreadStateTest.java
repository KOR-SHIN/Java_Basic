package kr.or.ddit.basic;

public class T10_ThreadStateTest {

	/**
	 * NEW : Thead가 생성되었지만 start()가 호출되지 않은 상태
	 * RUNNABLE : 실행 중 또는 실행 가능한 상태
	 * BLOCKED : 동기화 블럭에 의해서 일시정지된 상태 (Lock이 풀릴 때 까지 기다리는 상태)
	 * WAITING, TIMED-WAITING : Thread의 작업이 종료되지 않았지만 실행 불가능(UNRUNNABLE)한 일시정지 상태
	 * TERMINATED : Thread작업이 종료된 상태
	 */
	
	public static void main(String[] args) {
		statePrintThread spt = new statePrintThread(new TargetThread());
		spt.start();
	}
	
}

class TargetThread extends Thread {
	@Override
	public void run() {
		for(long i=1; i<10000000000L; i++) {
			// 시간지연 loop
			// RUNNABLE 
		}
		try {
			Thread.sleep(1500); // TIMED_WAITING
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		for(long i=1; i<1000000000L; i++) {
			// 시간지연 loop
		}
	}
	
}

/**
 * Thread의 상태를 출력하기 위한 변수
 */
class statePrintThread extends Thread {
	private Thread targetThread; // 상태출력용 Thread를 저장할 변수
	
	public statePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true) {
			Thread.State state = targetThread.getState();
			System.out.println("Current Status : " + state);
			
			/* State클래스의 반환값은 enum이기때문에 type도 검사가 된다. */
			if(state == Thread.State.NEW) {
				// State가 NEW이면 Thread를 실행함
				targetThread.start();
			}
			
			if(state == Thread.State.TERMINATED) {
				// Thread가 소멸되면 반복문을 종료함
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
	}
}






