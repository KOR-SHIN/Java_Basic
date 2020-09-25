package kr.or.ddit.basic;

public class T04_ThreadTest {

	public static void main(String[] args) {

		// single Thread
		SumThread th1 = new SumThread(1, 2000000000L);

		th1.start();
		long st_time = System.currentTimeMillis();

		try {
			th1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("소요시간(Single-Thread) : " + (System.currentTimeMillis() - st_time));

		// multi Thread
		SumThread[] ths = new SumThread[] { 
				new SumThread(1L, 500000000L), 
				new SumThread(500000000L, 1000000000L),
				new SumThread(1000000000L, 1500000000L),
				new SumThread(1500000000L, 2000000000L), 
		};
		
		st_time = System.currentTimeMillis();
		
		for(SumThread th : ths) {
			th.start();
		}
		
		for(SumThread th : ths) {
			try {
				th.join();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("소요시간(Multi-Thread) : " + (System.currentTimeMillis() - st_time));
		
		// 지금 예제에서는 멀티 스레드환경이 훨씬 빠르다.
		// 이러한 결과가 가능한 것은 테스트 환경의 8코어라서 가능한 것이다.
		// 만약 싱글코어 환경에서 테스트했다면, 하나의 코어가 4개의 Thread를 가지고 작업을 해야하기 때문에
		// Context-switching이 자주 발생할 수 있기때문에 싱글코어보다 실행속도가 느릴수 있다.
		// 프로세스 또는 스레드 간의 작업전환을 context-switching이라고 한다.
		// 작업전환은 현재 진행 중인 작업상태, 예를들면 다음에 실행해야할 위치(PC, 프로그램 카운터)등의 정보를
		// 저장하고 읽어오는 시간이 소요된다.
	}
}

class SumThread extends Thread {
	private long max, min;

	
	public SumThread(long min, long max) {
		super();
		this.max = max;
		this.min = min;
	}

	@Override
	public void run() {
		
		long sum = 0;
		for(long i=min; i<max; i++) {
			sum += i;
		}
		System.out.println(min + " ~ " + max + "까지의 합 : " + sum);
	}
}