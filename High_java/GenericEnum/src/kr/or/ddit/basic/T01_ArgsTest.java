package kr.or.ddit.basic;

/**
 *	<가변형 인수>
 *	메서드의 매개변수의 개수가 실행될 때 마다 다르게 사용한다.
 *	가변형 인수는 메서드 안에서는 배열로 처리된다.
 *	가변형 인수는 한 가지 자료형만 사용할 수 있다.
 *	가변형 인수는 다른 인수들과 함께 사용되는 경우, 맨 뒤에 배치해야 한다.
 *	
 */
public class T01_ArgsTest {

	// 배열을 이용한 메서드
	// 매개변수로 받은 정수들의 합계를 구하는 메서드)이 정수들의 개수는 가변적이다)
	
	// 가변형 인수를 사용하여 합계를 구하는 메서드
	int sumArg(int...data) {
		int sum = 0;
		
		for(int i=0; i<data.length; i++) {
			sum += i;
		}
		return sum;
	}		
	
	// 배열을 매개변수로 받아 합계를 구하는 메서드
	public int sumArr(int[] data) {
		int sum = 0;
		
		for(int i=0; i<data.length; i++) {
			sum += i;
		}
		return sum;
	}
	
	// 가변형 인수가 다른 인수들과 함께 사용되는 경우 (사용 위치주의)
	public String sumArg(String name, int...data) {
		
		int sum = 0;
		
		for(int i=0; i<data.length; i++) {
			sum += i;
		}
		
		return name + "님 점수 : " + sum;
	}
	
	public static void main(String[] args) {
		
		T01_ArgsTest at = new T01_ArgsTest();
		
		int[] nums = {100, 200, 300};
		
		System.out.println(at.sumArg(nums));
		System.out.println(at.sumArg(new int[] {1,2,3,4}));
		System.out.println();
		
		System.out.println(at.sumArg(100, 200, 300));
		System.out.println(at.sumArg(1,2,3,4,5));
		System.out.println();
		
		System.out.println(at.sumArg("길동", new int[] {1,2,3,4}));
	}
}
