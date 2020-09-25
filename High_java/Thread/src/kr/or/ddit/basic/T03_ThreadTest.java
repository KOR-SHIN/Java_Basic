package kr.or.ddit.basic;

import java.text.NumberFormat;

public class T03_ThreadTest {

	public static void main(String[] args) {
		Thread th1 = new Thread(new MyRunnable());
		
		long st_time = System.currentTimeMillis();
		th1.start();
		
		try {
			th1.join(); // 실행중인 Thread가 종료될 때 까지 기다림. (main Thread가 호출한 것)
						// 원래대로라면 Main Thread가 종료되지만, join()을 사용했기 때문에
						// th1 Thread의 작업이 끝날때까지 기다렸다가 종료한다.
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Run Time : " + (System.currentTimeMillis() - st_time));
	}
}

class MyRunnable implements Runnable {
	
	@Override
	public void run() {

		NumberFormat f = NumberFormat.getInstance();
		f.setGroupingUsed(true);
		
		long sum = 0;
		for(long i=1L; i<100000000; i++) {
			sum += i;
		}
		System.out.println(f.format(sum));
	}
}