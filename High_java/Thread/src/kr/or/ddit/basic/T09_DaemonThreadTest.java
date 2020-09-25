package kr.or.ddit.basic;

public class T09_DaemonThreadTest {

	public static void main(String[] args) {
	
		AutoSaveThread th1 = new AutoSaveThread();
		th1.setDaemon(true);
		th1.start();
		
		try {
			Thread.sleep(100 * 1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("<< Main Thread TERMINATED >>");
	}
}

class AutoSaveThread extends Thread {
	@Override
	public void run() {
		while(true) {
			
			try {
				Thread.sleep(3 * 1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			saveMsg();
		}
	}
	
	public void saveMsg() {
		System.out.println("현재 File이 저장되었습니다.");
	}
	
	
}
