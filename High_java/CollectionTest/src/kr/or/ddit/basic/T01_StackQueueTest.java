package kr.or.ddit.basic;

import java.util.LinkedList;

public class T01_StackQueueTest {

	public static void main(String[] args) {
		

		LinkedList<String> stack = new LinkedList<>();
		
		/**
		 * <Stack Method>
		 * push(E e) : 스택에 자료를 입력함
		 * pop() : 맨 위에있는 값을 꺼냄(삭제)
 		 * peek() : 맨 위에있는 값을 조회(삭제 X)
		 */

		System.out.println("<LinkedList를 이용한 Stack>");
		stack.push("건");
		stack.push("곤");
		stack.push("감");
		stack.push("이");

		System.out.println("현재 Stack 값 : " + stack);
		
		String data = stack.pop();
		System.out.println("꺼내온 값 : " + data);
		System.out.println("꺼내온 값 : " + stack.pop());
		System.out.println("현재 stack 값 : " + stack);
		
		stack.push("킴");
		System.out.println("입력 값 : " + "킴");
		System.out.println("현재 stack 값 : " + stack);
		System.out.println("꺼내온 자료 : " + stack.pop());
		
		System.out.println("=========================================");
		System.out.println();
		
		
		/**
		 * <Queue>
		 * offer(E e) 자료입력
		 * poll() 자료출력 (삭제)
		 */
		
		LinkedList<String> q = new LinkedList<>();
		
		System.out.println("<LinkedList를 이용한 Queue>");

		q.offer("건");
		q.offer("곤");
		q.offer("감");
		q.offer("이");
		
		System.out.println("현재 Queue : " + q);

		String temp = q.poll();
		System.out.println("꺼내온 값 : " + temp);
		System.out.println("꺼내온 값 : " + q.poll());
		System.out.println("현재 Queue : " + q);

		if(q.offer("성준")) {
			System.out.println("신규 등록 자료 : 성준" );
		}
		System.out.println("현재 Queue : " + q);
		System.out.println("꺼재온 자료 : " + q.poll());
		
		System.out.println("=========================================");
		System.out.println();
		
		
		
		
		
		
		
		
	}
}
