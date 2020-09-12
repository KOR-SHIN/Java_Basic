package ExZip;

import java.util.Scanner;

public class Ex3_5 {
	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		Calc cal = new Calc();
		
		System.out.print("첫 번째 정수를 입력하세요 : ");
		int firstNum = scan.nextInt();
		
		System.out.println("'+', '-', '*', '/'만 입력가능합니다.");
		System.out.print("부호를 입력하세요 : ");
		String operator = scan.next();
		
		System.out.print("두 번째 정수를 입력하세요 : ");
		int secondNum = scan.nextInt();
		
		switch(operator){
		case "+" :
			System.out.println(cal.add(firstNum, secondNum));
			break;
		case "-" :
			System.out.println(cal.substract(firstNum, secondNum));
			break;
		case "*" :
			System.out.println(cal.multiply(firstNum, secondNum));
			break;
		case "/" :
			System.out.println(cal.divide(firstNum, secondNum));
			break;
		default : 
			System.out.println("사칙연산자가 아닙니다.");
			System.out.println("계산기 프로그램을 종료합니다.");
		}
		
	}
}

class Calc{
	//1. 두 개의 int타입 입력받아 두 인자의 합의 결과를 반환하는 인스턴스메서드 add를 구현하라.
	public int add(int a, int b) {
		return a+b;
	}
	
	//2. 두 개의 int타입 입력받아 앞의 인자에서 뒤의 인자를 뺀 결과를 반환하는 인스턴스 메서드 substract를 구현하라.
	public int substract(int pre, int suf) {
		return pre-suf;
	}
	
	//3. 두 개의 int타입 입력받아 두 인자의 곱의 결과를 반환하는 인스턴스 메서드 multiply를 구현하라. (Overflow 고려)
	public long multiply(int a, int b) {
		return a*b;
	}
	
	//4. 두 개의 int타입 입력받아 앞의 인자를 뒤의 인자로 나누기한 결과를 반환하는 인스턴스 메서드 divide를 구현하라
	//	(단, 결과는 소수점 두 번째 자리에서 반올림하여 첫 번째 자리까지 표현하라.
	public float divide(int number, int divider) {
		float result = (float)number / divider;
		result = (int)(result*10 + 0.5) / 10f;
		return result;
	}
}
