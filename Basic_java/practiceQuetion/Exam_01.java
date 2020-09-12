package z_exam;

public class Exam_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*		
		[2-4] 다음 문장들의 출력결과를 적으세요.
		System.out.println(true + "");
		boolean + String => String + String => String
		"true" + "" => "true"
		*/		
		
		
//		[2-1] 다음 표의 빈 칸에 8개의 기본형(primitive type)을 알맞은 자리에 넣으세요.
		System.out.println("[2-1]");
		System.out.println("      |  1 byte  |  2byte  |  4byte  |  8byte  |");
		System.out.println("      ------------------------------------------");
		System.out.println("논리형   |  boolean |         |         |         |");
		System.out.println("      ------------------------------------------");
		System.out.println("문자형   |          |  char   |         |         |");
		System.out.println("      ------------------------------------------");
		System.out.println("정수형   |  byte    |  short  |  int    |  long   |");
		System.out.println("      ------------------------------------------");
		System.out.println("실수형   |          |         |  float  |  double |\n");
		
//		[2-2]다음의 문장에서 리터럴, 변수, 상수, 키워드를 적으시오.
		System.out.println("[2-2]");
		System.out.println("[보기]");
		System.out.println("int i = 100;\n"
						 + "long l = 100L;\n"
						 + "final float PI = 3.14f;\n");
		System.out.println("[정답]");
		System.out.println("리터럴 : 100, 100, 3.14");
		System.out.println("변수 : i, l, PI");
		System.out.println("키워드 : int, long, final, float");
		System.out.println("상수 : 3.14\n");
/*		상수와 리터럴은 값 자체를 의미한다 따라서 100, 100, 3.14이다.
		변수의 선언과 초기화는 [변수타입] [변수명] = [값]이므로 i, l, PI는 변수이다.
		키워드는 자바에서 예약어로 지정되어 있고, 이클립스에서 입력할시 자주색으로 보여진다 따라서 키워드는 int, long, final, float이다
*/		
//		[2-3] 다음 중 기본형 (Primitive type)이 아닌 것은?
		System.out.println("[2-3]");
		System.out.println("[보기]\t 1.int\t2.Byte\t3.double  4.boolean");
		System.out.println("[정답]\t Byte\n");
		//byte는 기본형이지만 자바는 대소문자를 구분하기 때문에 Byte는 기본형이 아니다.
		
//		[2-4] 다음 문장들의 출력결과를 적으세요. 오류가 있는 문장의 경우, 괄호 안에 '오류'라고 적으시오
		System.out.println("[2-4]");
		System.out.println("\"1\" + \"2\" => \"12\" ");
		//String + String => String
		
		System.out.println("true + \"\" => \"true\" "); 
		//boolean + String => String + String => String
		
		System.out.println("\'A\' + \'B\' => 131"); 
		// char + char => int + int => int
		
		System.out.println("\'1\' + 2   => 51");
		// char + int => int + int => int
		
		System.out.println("\'1\' + \'2\' => 99");	
		//char + char => int + int => int
		System.out.println(" 4 + 24.3715F => 28.3715F"); 
		//int + float => float + float => float
		
		System.out.println("\'A\' + 3.14  => 68.14");
		//char + double => int + double => double + double => double
		
		System.out.println("\'J\' + \"ava\" => \"Java\" ");
		//char + String => int + String => String + String => String
		
		System.out.println("true + null => error\n");
		//참조형의 기본형 null인 경우 어떤 타입의 참조형인지 알 수 없어서 type일치를 못 시키기때문에 연산불가능하다.
		
//		[2-5]다음 중 키워드가 아닌 것은? (모두 고르시오)
		System.out.println("[2-5]");
		System.out.println("[보기]\t1. if\t2.True\t3.NULL\t4.Class\t5.System");
		System.out.println("[정답]\tTrue, NULL, Class, System\n");
		//True, NULL, Class, System은 소문자인 경우 키워드이지만 자바는 대소문자를 구분하기 때문에 앞 글자가 대문이라서 예약어가 아니다.
		
//		[2-6]다음 중 변수의 이름으로 사용할 수 있는 것은? (모두 고르시오)
		System.out.println("[2-6]");
		System.out.println("[보기]\t1.$ystem  2.channel#5  3.7eleven  4.if");
		System.out.println("     \t5.자바  6.new  7.$MAX_NUM  8.hello@com");
		System.out.println("[정답]\t$ystem, If, $MAX_NUM, 자바\n");
/*		자바 변수명명에서 특수문자는 '$','_'만 사용가능하다.
		channel#5 : '#'은 사용불가능한 특수문자이다.
		7eleven : 변수는 숫자로 시작할 수 없다.
		if, new : 예약어는 변수명으로 사용 불가능하다.
		hello@com : '@'는 사용불가능한 특수문자이다.
*/
		
		
//		[2-7]참조형 변수(reference type)와 같은 크기의 기본형(primitive type)은? (모두 고르시오)
		System.out.println("[2-7]");
		System.out.println("[보기]\t1.int  2.long  3.short  4.float  5.double");
		System.out.println("[정답]\tint, float\n");
/*		잠조형 변수는 모두 4byte의 크기를 가진다
		int(4byte), long(8byte), short(2byte), float(4byte), double(8byte)*/
		
//		[2-8]다음 중 형변환을 생략할 수 있는 것은? (모두 고르시오)
		System.out.println("[2-8]");
		System.out.println("[조건]");
		System.out.println("byte b = 10;\n"
						 + "char ch = 'A';\n"
						 + "int i = 100;\n"
						 + "long l = 1000L;\n");
		System.out.println("[보기]");
		System.out.println("1. b = (byte)i;\n"
						  +"2. ch = (char)b;\n"
						  +"3. short s = (short)ch;\n"
						  +"4. float f = (float)l;\n"
						  +"5. i = (int)ch;\n");
		System.out.println("[정답]");
		System.out.println("float f = (float)l 생략가능\n"
						+  "i = (int)ch 생략가능\n");
/*		1. b = (byte)i : int를 byte타입 변수에 대입하는 것이기 때문에 강제형변환을 해야한다. (int > byte)
		2. ch = (char)b : char는 byte보다 크지만, 연산할 때 4byte보다 작은 자료형들은 int로 형변환된다.
						    따라서 char형 변수에 int를 대입하는 것이기 때문에 강제형변환을 해야한다. (int > char)
		3. short s = (short)ch : char와 short는 둘 다 크기가 2byte지만, 연산할 때 4byte보다 작은 자료형은 int로 형변환된다.
			    				  따라서 short형 변수에 int를 대입하는 것이기 때문에 강제형변환을 해야한다. (int > char)
*/
		
//		[2-9] char 타입의 변수에 저장될 수 있는 정수 값의 범위는?
		System.out.println("[2-9]");
		System.out.println("char 범위 : 0 ~ 2^(16)-1\n");
		//char는 부호가 없으므로 2byte = 16bit를 모두 범위에 사용한다.
		
//		[2-10]다음중 변수를 잘못 초기화 한 것은? (모두 고르시오)
		System.out.println("[2-10]");
		System.out.println("[보기]");
		System.out.println("1. byte b = 256;\n"
						 + "2. char c = '';\n"
						 + "3. char answer = 'no'\n"
						 + "4. float f = 3.14\n"
						 + "5. double d = 1.4e3f;\n");
		System.out.println("[정답]");
		System.out.println("1. byte b = 256; (overflow)\n"
						  +	"2. char c = ''; (char는 ''안에 아무것도 넣지않고 초기화 불가능)\n"
						  + "3. char answer = 'no' (char는 단 한개의 문자만 저장가능)\n"
						  + "4. float f = 3.14 (실수형의 기본형은 double이기 때문에 값뒤에 'f'를 명시하지 않으면 double로 인식)\n");

	}

}
