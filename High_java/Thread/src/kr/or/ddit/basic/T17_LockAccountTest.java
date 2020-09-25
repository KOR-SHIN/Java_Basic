package kr.or.ddit.basic;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock을 이용한 동기화 처리방법
 */
public class T17_LockAccountTest {
	public static void main(String[] args) {
		LockAccount lAcc = new LockAccount();
		lAcc.setBalance(10000);
		
		BankThread2 bt1 = new BankThread2(lAcc);
		BankThread2 bt2 = new BankThread2(lAcc);
		
		bt1.start();
		bt2.start();
	}
}

class LockAccount {
	private int balance;

	// lock 객체 생성 (private final로 선언하는 것을 권장)
	// private으로 지정하지 않으면 외부에서 lock 객체를 사용할 수 있기 때문에 문제가 생길 수 있다.
	// synchronized보다 임계영역 지정을 광범위하게 할 수 있다 (동기화는 성능문제로 이어지기때문에 장점이자 단점이다)
	// 임계영역 지정범위가 넓은만큼 관리를 세밀하게 해야한다.
	private final ReentrantLock lock = new ReentrantLock();
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금처리
	public void deposit(int money) {
		// Lock 객체의 lock()은 동기화 시작, unlock()은 동기화 끝을 의미한다.
		// lock()으로 동기화를 설정한 곳에는 반드시 unlock()으로 해제해야한다.
		lock.lock();
		balance += money;
		lock.unlock();
	}
	
	// 출금처리
	public boolean withdraw(int money) {
		lock.lock();
		boolean check = false;
		
		// try-catch문을 사용할 때는 unlock()메서드 호출은 finally block에서 수행한다.
		try {
			if(money <= balance) {
				balance -= money;
				System.out.println("Method 내부에서의 balance : " + getBalance());
				check = true;
			}
		} catch(Exception e) {
			check = false;
		} finally {
			lock.unlock();
		}
		
		return check;
	}
}

class BankThread2 extends Thread {
	private LockAccount lAcc;

	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println("Thread 안에서 result : " + result + ", balance : " + lAcc.getBalance() );
	}
	
	
	
}

