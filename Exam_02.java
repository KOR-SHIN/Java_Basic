package z_exam;

public class Exam_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//[3-1] 다음중 형변환을 생략할 수 있는 것은? (모두 고르시오)
		System.out.println("[조건]");
		System.out.println("1. byte b = 10;\n"
				+ "2. char ch = 'A';\n"
				+ "3. int in = 100;\n"
				+ "4. long lo = 1000L;\n");
		System.out.println("[보기]");
		System.out.println("1. b = (byte) in;\n"
				+ "2. ch = (char) b;\n"
				+ "3. short s = (short) ch;\n"
				+ "4. float f = (float) lo;\n"
				+ "5. in = (int) ch;\n");
		System.out.println("[정답]");
		System.out.println("4. float f = (float) lo;\n"
				+ "5. in = (int) ch;\n"
				);
		
		//[3-2] 다음 연산의 결과를 적으시오.
		int x = 2;
		int y = 5;
		char c = 'A'; //A=65
		System.out.println("[3-2 정답]");
		System.out.println(1+x << 33);
/*		사칙연산은 대입연산보다 먼저 적용되며 x=2이기 때문에 3 << 33 이다.
		x는 int형이기 때문에 32bit이다. 따라서 32번의 이동은 0번이동과 같으므로 3 << 1이다.
		결과값은 3*2 = 6이다.
*/		

		System.out.println(y >= 5 || x <0 && x > 2);
/*		대입연산자와 단항연산자를 제외한 연산자의 연산방향은 왼쪽에서 오른쪽이며
  		비교연산자는 논리연산자보다 우선순위가 높기때문에 비교연산자 => 논리연산자 순으로 연산된다
  		y >= 5 || x < 0 => true || false => '||'는 둘 중 하나가 true이면 true이기 때문에 결과값은 true이다
  		true && x > 2 => true && true => '&&'는 둘 다 true일때만 true이다 따라서 결과값은 true이다.
*/
		System.out.println(y += 10 - x++);
/*		단항연산자 '++' => 사칙연산자 '-' -> 대입연산자(복합연산자) '+=' 순서로 실행된다.
 		x=2로 초기화되어있다. 위에서 x를 비트연산했지만 출력문 안에서 실행했기 때문에 값이 바뀌지 않는다.
 		하지만 위의 단항연산자는 후위식으로 작성됐기 때문에 해당문장 다음에 x값이 증가한다.
 		따라서 x=2이다.
 		단항연산자 다음은 사칙연산이므로 10 - 2 = 8이다.
 		사칙연산 다음은 대입연산자이고 'y+=3'은 y=y+8과 같기때문에 y = 5+8 = 13이다 
*/
		System.out.println(x+=2);
/* 		'x+=2'는 'x = x+2'와 같은의미이다.
		위에서 비트연산을 했지만 출력문 안에서 실행했기 때문에 x값은 변하지 않았지만 단항연산자 '++'는 x값에 변화를 준다.
		따라서 x++연산을 했기때문에 현재 x=3이다.
		x = x+2이므로 x=5이다.
*/
		System.out.println(!('A' <= c && c <= 'Z') );
/*		c = 'A'로 초기화되어 있다.
 		또한 ()는 우선순위가 매우 높기때문에 안에있는 내용이 우선적으로 실행된다.
 		산술연산 > 비교연산 > 논리연산 > 대입연산 순서로 연산된다.
 		논리연산자 '&&'는 좌항과 우항이 모두 true일때만 true를 반환한다.
 		c='A'이기때문에 좌항과 우항은 모두 true이다 ()안의 값은 true가 된다.
 		하지만 괄호앞에 논리부정연산자 '!'가 있기때문에 true -> false로 반전된다.
 		따라서 결과값은 false이다.
*/
		System.out.println('C'- c);
		//c='A'= 65이고 'C'=67이다 따라서 'C' - c = 2이다.
		
		System.out.println('5' - '0');
		//'5' = 53, '0' = 48 이기때문에 '5'-'0'=5이다

		System.out.println(c+1);
		//char + int => int + int 이고 c='A'=65이기 때문에 c+1 = 66이다.
		
		System.out.println(++c);
		//현재 c='A'이고 단항연산자 단독으로 사용됐기 때문에 c='B'가 된다.
		
		System.out.println(c++);
		//위에서 c값을 증가시켜서 c='B'가 되었다. 하지만 여기서는 단항연산자가 후위식으로 사용됐기 때문에
		//출력은 'B'로 되고 다음줄 부터 c='C'가 된다.
		
		System.out.println(c);
		//위에서 언급했듯이 단항연산자의 후위식은 다음줄 부터 적용된다 따라서 c='C'이다.
		//괄호안에서 수행된 것은 괄호안에서 소멸되기 때문에 영향을 주지않지만 단항연산자는 값 자체가 바뀐다.
		
		//[3-3] 아래의 변수 num의 값에 따라 "양수", "음수", "0"을 출력하는 코드이다. 삼항 연산자를 이용하여 완성하여라.
		int num = 10;
		String result = num == 0 ? "0" : num>0 ? "양수" : "음수"; 
		//3항 연산자 중첩 => (조건식) ? true : (조건식) ? true : false
		System.out.println("\n[3-3 정답]");
		System.out.println(result);
		
		//[3-4]아래의 코드는 사과를 담는데 필요한 바구니의 수를 구하는 코드이다. 만약 사과가 123개이고 하나의 바구니에는 10개의
		//사과를 담을 수 있다면 13개의 바구니가 필요하다  코드를 완성하여라.
		int apples = 123;
		int bucket = 10;
		int numOfBucket = (apples % bucket == 0) ? (apples/bucket) : (apples/bucket)+1;
		System.out.println("\n[3-4 정답]");
		System.out.println(numOfBucket);
/*		
		apples % bucket == 0 인 경우 : bucket의 크기가 10이기 때문에 사과의 갯수를 10으로 나누어 떨어진다는 것은 바구니의 갯수가 몫이된다.
		apples % bucket != 0 인 경우 : apples > bucket인 조건에서 10으로 나누어 떨어지지 않았다는 것은 10보다 작은 나머지가 있는 것이고
		 							문제 조건에서 나머지가 있는 경우 바구니가 1개 더 필요하다는 조건이 있기 때문에 (apples/bucket) 결과값에 1을 더해준다
		
*/
		//[3-5]아래의 코드는 변수 num의 값 중에서 백의 자리 이하를 버리는 코드이다. 만일 변수 num의 값이 '456'이라면 '400이 된다' 코드를 완성하여라.
		int num2 = 456;
		int result2 = (num2 / 100); // (정수 / 정수) 연산이기 때문에 결과값도 정수를 가진다.
		System.out.println("\n[3-5 정답]");
		System.out.println(result2 * 100);
		
		//[3-6]아래 코드의 문제점을 수정해서 실행 결과와 같은 결과를 얻도록 하시오.
		byte b = 20;
		byte a = 10;
		byte c2 = (byte)(a+b); 
		//기존식에서 byte a가 선언되어 있지 않은 오류가 있었음
		
		char ch = 'A';
		ch = (char)(ch + 34); 
		//기존식 ch = ch+2 => ch+2는 int + int로 캐스팅되기 때문에 ch = (char)(ch+2)로 수정
		//결과값이 'C'이지만 예상 결과값은 'c'이기 때문에 아스키코드표를 참고하여 (char)(ch+34)로 수정
		
		float f = (float)3/2;
		//기존식 float f = 3/2 => 3/2는 정수/정수이기때문에 결과값도 정수를 가진다 따라서 3과 2중 하나를 float으로 캐스팅한다.
		
		long l = 3000 * 3000 * 3000L;
		//기존식 long l = 3000 * 3000 * 3000 => 정수의 기본형은 int이기 때문에 'L'을 쓰지않으면 int로 인식한다.
		// 									  int->long은 자동형변환이 일어나기 때문에 생략가능하지만 'L'을 명시적으로 쓰는게 좋다.
		
		float f2 = 0.1f;
		double d = 0.1f; 
		/*
		 기존식 double d = 0.1;은 문법상 오류가 없지만 boolean 아래에 d==f2값이 true가 나와야하기 때문에
		f2와 d의 타입을 일치시키기 위해 명시적으로 f를 써서 double d = 0.1f로 수정
		
		방법2) f2, d의 타입만 맞추면 true이기 때문에 float f2 = 0.1f를 double f2 = 0.1로 변경하는 방법도 가능하다.
		*/
		boolean result3 = (d==f2);
		System.out.println("\n[3-6 정답]");
		System.out.println("c=" + c2);
		System.out.println("ch=" + ch);
		System.out.println("f=" + f);
		System.out.println("l=" + l);
		System.out.println("result3=" + result3);
		
		//[3-7] num의 값보다 크면서도 가장 가까운 10의 배수에서 변수 num의 값을 뺀 나머지를 구하는 코드를 완성하여라
		int num3 = 24;
		int result4 = ((num3/10)+1) * 10 - num3;
		System.out.println("\n[3-7 정답]");
		System.out.println("result4 = " + result4);
		
		//[3-8] 화씨를 섭씨로 변환하는 코드이다. 변환공식은 C=5/9 * (F - 32)이다.
		//      변환 결과값은 소수점 셋째자리에서 반올림해야한다.
		int fahrenheit = 100;
		float formula = (float)5/9 * (fahrenheit - 32);
		//공식을 사용해서 화씨 -> 섭씨 변환
		
		float celcius = (int)(formula*1000 + 0.5) / 1000f;
		/*
			소수점 셋째자리에서 반올림하기위해 formula*1000을 곱해 소수점 셋째자리까지 정수로 만들어준다
			반올림은 0.5미만은 버리기 때문에 곱해진 수에 0.5를 더한다.
			불필요한 소수점을 제거하기 위해 (int)로 캐스팅해준다.
			정수로 변경된 소수점 부분을 다시 소수점으로 변경하기 위해 1000F로 나눠준다
				(1000으로 나눌경우 정수/정수 이기때문에 소수점이 없어지기 때문에 반드시 1000F로 나눈다)
		*/
		System.out.println("\n[3-8 정답]");
		System.out.println("Fahrenheit : " + fahrenheit);
		System.out.println("Celcius : " + celcius);
	}

}
