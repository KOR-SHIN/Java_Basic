package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T18_SyncCollectionTest {

	/**
	 * Vector, Hashtable등 예쩐부터 존재하던 Collection클래스들은 내부에 동기화 처리가 되어있다.
	 * 하지만 최근 새로 구성된 Collection들은 동기화 처리가 되어있지 않다.
	 * 따라서 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리를 한 후에 사용해야 한다.
	 */
	
	// 동기화 처리를 하지않은 경우
	private static List<Integer> list = new ArrayList<>();
	
	// 동기화 처리를 한 경우
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	public static void main(String[] args) {

		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<10000; i++) {
//					list.add(i);
					list2.add(i);
				}
			}
		};
		
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r),
				new Thread(r), new Thread(r),
				new Thread(r), new Thread(r),
		};
		
		long st_time = System.currentTimeMillis();
		
		for(Thread t : ths) {
			t.start();
		}
		
		for(Thread t : ths) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("소요시간 : " + (System.currentTimeMillis() - st_time));
		
//		System.out.println("list의 개수 : " + list.size());
		System.out.println("list2의 개수 : " + list2.size());
	}
}
