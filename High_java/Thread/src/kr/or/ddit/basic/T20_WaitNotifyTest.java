package kr.or.ddit.basic;

public class T20_WaitNotifyTest {
	public static void main(String[] args) {
		DataBox db = new DataBox();
		
		ProcedureThread pth = new ProcedureThread(db);
		ConsumerThread cth = new ConsumerThread(db);
		
		pth.start();
		cth.start();
	}
}

// 공유객체 클래스
class DataBox {
	private String data;
	
	// data가 null일때 data값을 변경하는 부분
	public synchronized String getData() {
		if(data == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String returnData = data;
		System.out.println("읽어온 데이터 : " + returnData);
		data = null;
		System.out.println(Thread.currentThread().getName() + "notify() 호출");
		
		notify();
		
		return returnData;
	}
	
	// data가 null인 경우에만 자료를 생성하는 메서드
	public synchronized void setData(String data) {
		if(this.data != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.data = data;
		System.out.println("생성된 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName() + "notify() 호출");
		
		notify();
	}
}

// 데이터 생성만 하는 쓰레드
class ProcedureThread extends Thread {
	private DataBox db;

	public ProcedureThread(DataBox db) {
		super("ProcedureThread");
		this.db = db;
	}
	
	@Override
	public void run() {
		for(int i=0; i<11; i++) {
			String data = "Data" + i;
			System.out.println("db.setDate(" + data + ")");
			db.setData(data);
		}
	}
}

// 데이터를 읽기만하는 쓰레드
class ConsumerThread extends Thread {
	private DataBox db;

	public ConsumerThread(DataBox db) {
		super("ConsumerThread");
		this.db = db;
	}
	
	@Override
	public void run() {
		for(int i=0; i<11; i++) {
			System.out.println("db.getData() : " + db.getData());
		}
	}
	
	
	
}