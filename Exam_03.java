package z_exam;
import java.util.*;

public class Exam_03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//[4-1] 다음의 문장들을 조건식으로 표현하라
		System.out.println("[4-1]정답");
		System.out.println("1. int형 변수 x가 10보다 크고 20보다 작을 때 true인 조건식");
		System.out.println("\tif(x>10 && x<20)");
		System.out.println("2. char형 변수 ch가 공백이고 탭이 아닐 때 true인 조건식");
		System.out.println("\tif(ch == ' ' && ch !='	')");
		System.out.println("3. char형 변수 ch가 'x'또는 'X'일 때 true인 조건식");
		System.out.println("\tif(ch=='x' || ch=='X')");
		System.out.println("4. char형 변수 ch가 숫자('0'~'9')일 때 true인 조건식");
		System.out.println("\tif(ch >= '0' && ch <= '9')");
		System.out.println("5. char형 변수 ch가 영문자(대문자 또는 소문자)일 때 true인 조건식");
		System.out.println("\tif( (ch>='a' && ch<='z') || (ch>='A' && ch<='Z') )");
		System.out.println("6. boolean형 변수 powerOn이 false일 때 true인 조건식");
		System.out.println("\tif(powerOn == false)");
		System.out.println("7. 문자열 참조변수 str이 \"yes\"일 때 true인 조건식" );
		System.out.println("\tif(str.equals(\"yes\"))");
		
		/*
			문자열을 비교할때는 반드시 '==', equals()를 구분해서 사용해야 한다.
			문자열은 기본형 변수가 아닌 참조형 변수이기 때문에 주소값을 가진다.
			'=='은 문자열의 주소를 비교하는 것이고, equals()는 문자열의 값 자체를 비교할 때 사용한다.
			String s = 'str; 과 String s2 = new String("str");을 예로들면
			s==s2는 다르지만 s.equlas(s2)는 같다. 	
		*/

		//[4-2]1부터 20까지의 정수 중 2또는 3의 배수가 아닌 수의 총합을 구하시오.
		int sum = 0;
		
		for(int i=1; i<21; i++) {
			if( (i%2==0) || (i%3==0) ) {
				continue;
			} else {
				sum = sum + i;
			}
		}
		System.out.println("\n[4-2] 정답");
		System.out.println(sum+"");
		
		//[4-3]다음의 for문을 while문으로 변경하세요.
		int dan = 2;
		int gob = 1;
		
		System.out.println("\n[4-3] 정답");
		while(dan<10) {
			
			while(gob<10) {
				System.out.println(dan + " * " + gob + " = " + dan*gob);
				gob++;
			}
			gob=1;
			dan++;	
		}
		
		//[4-4]두 개의 주사위를 던졌을 때, 눈의 합이 6이 되는 모든 경우의 수를 출력하는 프로그램을 작성하시오.
		System.out.println("\n[4-4] 정답");
		for(int i=1; i<7; i++) {
			for(int j=1; j<7; j++) {
				if(i+j == 6) {
					System.out.println(i + " + " + j);
				}
			}
		}
		//[4-5]방정식 2x+4y=10의 모든해를 구하시오 (0<=x<=10, 0<=y<=10)
		int x = 0;
		int y = 0;
		System.out.println("\n[4-5] 정답");
		for(x=0; x<=10; x++) {
			for(y=0; y<=10; y++){
				if((2*x)+(4*y)==10) {
					System.out.println("[x : " + x + "]\t[y : " + y +"]");
				}
			}
		}
		
		//[4-6]사용자로부터 두 개의 정수(시작, 끝)를 입력받아 시작(포함)에서 끝(포함)까지의 곱을 출력하시오.
		Scanner sc = new Scanner(System.in);
		
		int start = 0;
		int end = 0;
		long result = 1;
		
		do { //사용자 입력을 반드시 받기위해 do-while사용
			System.out.println("\n(첫 번째 정수 < 두 번째 정수) 를 만족해야 합니다.");
			System.out.println("첫 번째 정수를 입력하세요");
			start = sc.nextInt();
			System.out.println("두 번째 정수를 입력하세요");
			end = sc.nextInt();
		} while(start >= end);
		
		for(int i=start; i<=end; i++) {
			result *= i;
		}
		System.out.println("\n[4-6] 정답");
		System.out.println(start + " ~ " + end + "까지 정수의 곱 : " + result);
		
		//[4-7]1 + (1+2) + (1+2+3) + ... + (1+2+3+...+10)의 결과를 계산하시오.
		//계산과정 전부 출력되도록 만들어야함
				int tmp = 0;
		int result2 = 0;
		boolean flag = false; //처음 1에있는 괄호가 없기때문에 flag를 사용해서 구분해준다.
		
		System.out.println("\n[4-7]정답");
		for(int i=1; i<11; i++) {
			tmp += i;
			result2 += tmp;
			
			if(flag) {
				System.out.print("(");
			}
			
			for(int j=1; j<i+1; j++) {
				System.out.print(j);
				if(j != i) { //i가 3이면 j는 1,2,3을 출력한다. 마지막 숫자에는 +기호가 나오지 않도록 한다.
					System.out.print(" + ");
				}
			}
			
			if(flag) {
				System.out.print(")");
			}
			flag = true;
			
			//i==10이 마지막 수행문을 실행하기 때문에 조건문 사용한다.
			//i < 10이면 마지막 반복이 아니기 때문에 다음 연산을 더해주기 위해 '+'기호를 출력한다.
			if(i==10){ 
				System.out.println(" = " + result2); 
				break;
			} else {
				System.out.print(" + ");
			}
		}
		
		//[4-8] 1 + (-2) + 3 + (-4) ...와 같은 식으로 계속 더했을때, 몇까지 더해야 총합이 100이상 되는지 구하시오
		int sum2 = 0;
		
		for(int i=1; ; i++) {
			
			if(i%2 == 0) { //짝수는 음수로 변환해준다.
				sum2 += -i;
			} else {
				sum2 += i;
			}
			
			if(sum2 >= 100) {
				System.out.println("\n\n[4-8]정답");
				System.out.println(i + "일 때 총합이 100이 된다.");
				break;
			}
		}
		
		//[4-9]사용자로부터 입력받은 정수의 각 자리의 합을 더한 결과를 출력하는 프로그램을 작성하시오.
		//     예를 들어 사용자가 53263을 입력하였다면 결과는 19이다.
		int temp = 0;
		int input = 0;
		
		do {
			System.out.println("\n양의 정수를 입력하세요. (" + Integer.MAX_VALUE + " 이하)");
			input = sc.nextInt();
		} while(input < 0 || input>Integer.MAX_VALUE);
		
		while(true) {
			temp += (input%10);
			input = (input/10);
			
			if(input%10 == 0) {
				break;
			}
		}
		System.out.println("[4-9]정답");
		System.out.println("각 자리의 합 : " + temp);
		
		//[4-10] 피보나치 수열의 10번째 수는 무엇인지 계산하는 프로그램을 작성해라.
		int low = 1;
		int high = 1;
		int result3 = 0;
		
		System.out.println("\n[4-10] 정답");
		for(int i=2; i<10; i++) {
			result3 = (low+high);
			low = high;
			high = result3;
			System.out.println("피보나치 수열의" + (i+1) + "번째 숫자 : " + result3);
		}
		
		//[4-11] 문자열이 숫자인지 판별하는 프로그램을 완성하시오.
		String value = "12o34";
		char ch = ' ';
		boolean isNumber = true;
		
		System.out.println("\n[4-11] 정답");
		for(int i=0; i<value.length(); i++){
			if(value.charAt(i) < '0' || value.charAt(i) > '9') {
				isNumber = !isNumber;
				break;
			}
		}
		if(isNumber) {
			System.out.println(value + "는 숫자입니다.");
		} else {
			System.out.println(value + "는 숫자가 아닙니다.");
		}
		
		//[추가문제] 숫자가 팰린드롬인지 판단하는 프로그램을 출력하시오.
		//팰린드롬 : 12321 처럼 앞에서읽는것과 뒤에서읽는것이 똑같은 숫자
		System.out.println("\n[추가문제]");
		System.out.println("양의 정수를 입력해주세요.");
		input = sc.nextInt(); //사용자 입력숫자
		int cnt = 1; //정수 자리수 카운터
		temp = input; // temp를 사용하는 이유는 while문에서 자릿수 카운트용으로 사용하는 것.
						  // input을 사용하게 되면 사용자가 입력한 값과 달라지기 때문에 값을 복사해서 사용한다.
		int ex = 1;
		
		while(temp/10 != 0) { 
			//cnt = 1인 이유는 temp가 1자리수만 남았을 때 (정수/정수)연산으로 인해 0이 나오기 때문에
			//누락되는 자리수 1개를 미리 카운트 해준다.
			//Math.log10()을 이용해서 구하는 방법도 있다.
			cnt++;
			temp /= 10;
		}

		for(int i=1; i<(cnt/2); i++) { 
			/*
			 좌우대칭을 확인하기 때문에 자리수의 절반만 확인한다.
			자리수가 홀수인 경우에도 가운데 값은 상관없기 때문에 짝수, 홀수를 구분하지 않는다.
			*/
			ex *= 10; //input의 맨 뒷자리에 곱해줄 수를 구한다.
		}
		
		temp = 0; //temp에 값을 저장하기 위해 초기화 한다.
		for(int i=0; i<(cnt/2); i++) {
			temp += ((input%10)*ex);
			input /= 10; //사용한 입력값은 지워준다.
			ex /= 10; //연산 후 10으로 나눠서 자리수를 줄인다.
		}
		
		if(cnt % 2 != 0) {
			//자리수가 홀수인지 짝수인지 확인하고 홀수인경우 마지막 한자리를 잘라준다.
			input /= 10;
		}
		
		if(temp == input && cnt > 1) {
			System.out.println("(좌측, 우측) : (" + input + ", " + temp +")");
			System.out.println("팰린드롬 입니다.");
		} else {
			System.out.println("(좌측, 우측) : (" + input + ", " + temp +")");
			System.out.println("팰린드롬이 아닙니다.");
		}

		
	}
}
