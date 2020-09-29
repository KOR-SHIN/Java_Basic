package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;

public class T15_ObjectStreamTest {
	public static void main(String[] args) {
		
		// Member Instance
		Member mem = new Member("홍길동", 29, "대전");
		Member mem2 = new Member("강감찬", 24, "서울");
		Member mem3 = new Member("제갈량", 23, "강원");
		Member mem4 = new Member("서우", 22, "경남");
		Member mem5 = new Member("제천대성", 59, "경북");
		Member mem6 = new Member("이순신", 39, "전남");
		
		try {
			// 출력용 스트림 객체 생성
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/test/member.txt")));
			
			// write 작업
			oos.writeObject(mem);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			oos.writeObject(mem5);
			oos.writeObject(mem6);
			
			System.out.println("<< write 완료 >>");
			oos.close();
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// 저장한 객체를 읽어와 출력하기
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/test/member.txt")));
			
			Object obj = null;
			
			try {
				while((obj = ois.readObject()) != null) {
					// 읽어온 데이터를 원래의 객체형으로 변환 후 사용
					Member member = (Member)obj;
					System.out.println("이름 : " + member.getName());
					System.out.println("나이 : " + member.getAddr());
					System.out.println("주소 : " + member.getAddr());
					System.out.println("------------------------------");
				}
				ois.close();
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		} catch(IOException e) {
			// 더 이상 읽어올 객체가 없으면 예외가 발생함
			e.printStackTrace();
			System.out.println("<< 출력작업 완료 >>");
		}
	}
}

/**
 * 회원정보 VO
 * 자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있다.
 */
class Member implements Serializable {
	
	/**
	 * <transient>
	 * 직렬화 대상에서 제외시키는 키워드
	 * class 멤버는 직렬화의 대상이 아님으로 transient를 별도로 붙이지 않아도 된다.
	 * 직렬화가 되지 않는 멤버변수는 기본값으로 저장된다.
	 * (Reference Type : null)
	 */
	
	private transient String name;
	private transient int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getAddr() {
		return addr;
	}
}
