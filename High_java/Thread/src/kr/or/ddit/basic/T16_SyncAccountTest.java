package kr.or.ddit.basic;

/**
 * 은행의 입출금을 스레드로 처리하는 예제
 */
public class T16_SyncAccountTest {
	public static void main(String[] args) {
		SyncAccount sAcc = new SyncAccount();
		sAcc.setBalance(10000);
		
		BankThread bt1 = new BankThread(sAcc);
		BankThread bt2 = new BankThread(sAcc);
		
		bt1.start();
		bt2.start();
	}
}

// 은행의 입출금을 관리하는 클래스
class SyncAccount {
	private int balance;

	// withdraw영역이 동기화 처리가 되어있고, 내부에서 getBalance()를 호출한다.
	// 동기화 영역내에서 호출되는 메서드를 동기화한 것이다.
	public synchronized int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금처리를 수행하는 메서드
	public synchronized void deposit(int money) {
		balance += money;
	}
	
	// 출금처리를 수행하는 메서드
	// 동기화 영역에서 호출하는 메서드도 동기화 처리를 해주어야 한다.
	public synchronized boolean withdraw(int money) {
		if(money <= balance) {
				balance -= money;
				System.out.println("Method 내부에서의 balance : " + getBalance());
				return true;
		}
		return false;
	}
}

class BankThread extends Thread {
	
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}



	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000);
		System.out.println("Thread 내부에서 result : " + result + ", balance : " + sAcc.getBalance());
	}
}