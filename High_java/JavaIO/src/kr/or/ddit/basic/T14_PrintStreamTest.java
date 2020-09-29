package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * print 기능 제공 보조 스트림 예제 
 */
public class T14_PrintStreamTest {
	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("d:/test/printStreaTest.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/test/printStreaTest2.txt");
		
		// PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 OutputStream의 서브클래스.
		// PrintStream은 IOException을 발생시키지 않는다.
		// println & print 등 메서드 호출시마다 autoflush기능을 제공한다.
		
		PrintStream ps = new PrintStream(fos);
		ps.print("안녕하세요 PrintStream입니다\n");
		ps.println("안녕하세요 PrintStream2입니다");
		ps.println("안녕하세요 PrintStream3입니다");
		ps.println(ps);
		ps.println(3.14);
		
		/**
		 * printStream은 데이터를 문자로 출력하는 기능을 수행 (System.out)
		 * 향상된 기능의 PrintWriter가 추가되었지만 계속 사용된다.
		 * 
		 * PrintWriter가 PrintStream보다 다양한 언어의 문자를 처리하는데 적합하다.
		 * 둘 다 기본적으로 autoflush 기능을 제공한다.
		 */
		
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos2, "UTF-8"));
		writer.print("안녕하세요 PrintWriter입니다.\r\n");
		writer.println("안녕하세요. PrintWriter2 입니다.");
		writer.println("안녕하세요. PrintWriter3 입니다.");
		
		writer.close(); // autoflush
		
		
	}
}
