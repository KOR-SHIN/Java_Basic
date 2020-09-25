package kr.or.ddit.basic;

public class T01_ThreadTest {

	public static void main(String[] args) {
		
		// Single Thread Program
		
		for(int i=0; i<200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for(int i=0; i<200; i++) {
			System.out.print("$");
		}

	}
}
