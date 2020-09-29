package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01_FileTest {
	public static void main(String[] args) throws IOException {
		// File 객체 만들기
		
		// 1. new File(String fileName or filePath)
		// 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는 
		// '\'를 사용하거나 '/'를 사용할 수 있다.
		File file = new File("d:/D_Other/test.txt"); // path를 참조하는 객체만 생성한 것(경로안에 생성되지는 않음)
		System.out.println("file 이름 : " + file.getName());
		System.out.println("디렉토리(폴더) 여부 : " + file.isDirectory());
		System.out.println("--------------------------------------------");

		File file2 = new File("d:/D_Other");
		System.out.print(file2.getName() + "은");
		if(file2.isFile()) { // path를 참조하여 속성값을 조회한다.
			System.out.println("file 입니다.");
		} else {
			System.out.println("디렉토리 입니다.");
		}
		System.out.println("--------------------------------------------");
		
		// 2. new File(file parent, String child)
		// 'parent'디렉토리 안에있는 'child'파일 또는 디렉토리를 갖는다.
		// 많이 사용하지 않음
		File file3 = new File(file2, "test.txt"); // file2경로 안에있는 test.txt파일을 참조하는 객체
		
		// 한글은 한 글자에 3byte, 영어는 1byte로 인식한다.
		System.out.println(file3.getName() + "의 용량 크기 : " + file3.length() + "bytes");
		System.out.println("--------------------------------------------");
		
		// 3. new File(String parent, String child)
		// ./abc.hwp == abc.hwp (현재 디렉토리에서 찾을때 상대경로를 사용한 경우)
//		File file4 = new File("d:/D_Other", "test.txt");
		File file4 = new File("./test.txt");
		System.out.println("절대 경로 : " + file4.getAbsolutePath()); // 현재위치와 상관없이 참조가능한 경로 (절대경로)
																	  // 현재위치에 따라 달라지는 경로 (상대경로)
		System.out.println("경로      : " + file4.getPath()); // File 객체에 지정한 경로
		System.out.println("표준 경로 : " + file4.getCanonicalPath()); // ??
		System.out.println("현재 클래스의 URL : " + T01_FileTest.class.getResource("T01_FileTest.class")); // 지정한 클래스의 경로를 URL형식으로 보여줌
		System.out.println("현재 클래스의 URL : " + T01_FileTest.class.getResource("T01_FileTest.class").getPath()); 
		System.out.println("--------------------------------------------");
		
		/**
		 * 디렉토리(폴더) 만들기
		 * 
		 * 1. mkdir() 
		 * File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		 * 중간의 경로가 모두 미리 만들어져 있어야 한다.
		 * 
		 * 2. mkdirs()
		 * 중간의 경로가 없을경우 중간의 경로도 새롭게 만든 후 마지막 위치의
		 * 디렉토리를 만들어 준다.
		 * 
		 * 두 메서드 모두 만들기를 성공하면 true, 실패하면 false를 반환한다.
		 * 권한이 없는경우에는 동작하지 않는다.
		 */

		File file5 = new File("d:/D_Other/연습용");
		if(file5.mkdir()) {
			System.out.println(file5.getName() + " 만들기 성공");
		} else {
			System.out.println(file5.getName() + " 만들기 실패");
		}
		System.out.println("--------------------------------------------");
		
		File file6 = new File("d:/D_Other/test/java/src"); // 실제경로는 D_Other까지있지만 mkdirs()를 사용하면
														   // test, java, src까지 만들어줌
		
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + " 만들기 성공");
		} else {
			System.out.println(file6.getName() + " 만들기 실패");
		}
		System.out.println("--------------------------------------------");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
