package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 부모 클래스가 Serializable 인터페이스를 구현하고 있지 않는 경우
 * 부모객체의 필드값 처리 방법
 * 
 *  1. 부모클래스가 Serializable 인터페이스를 구현하도록 한다.
 *  	=> 만약 부모클래스가 Serializable을 구현했다면, 서브클래스의 의사와 상관없이 직렬화 될 수 있는 문제가 있다.
 *  
 *  2. 서브클래스의 writeObject()와 readObject메서드를 이용하여
 *     부모객체의 필드값을 처리할 수 있도록 직접 구현한다.
 */
public class T16_NoneSerializableParentTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		FileOutputStream fos = new FileOutputStream("d:/test/noneSerializbleParent.txt");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		
		oos.writeObject(child);
		oos.flush(); // 생략가능
		oos.close();
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////

		FileInputStream fis = new FileInputStream("d:/test/noneSerializbleParent.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Child child2 = (Child)ois.readObject();
		
		System.out.println("ParentName : " + child2.getParentName());
		System.out.println("ChildName : " + child2.getChildName());
		
		ois.close();
	}
}

class Parent {
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}

class Child extends Parent implements Serializable {
	
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	/**
	 * 직렬화가 될 때 자동으로 호출된다.
	 * (접근 제어자가 private이 아니면 자동으로 호출되지 않는다)
	 * @param oos
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream oos) throws IOException {
		// ObjectOutputStream 객체의 메서드를 이용하여 부모 객체의 필드값을 직렬화
		oos.writeUTF(getParentName());
		oos.defaultWriteObject();
	}
	
	/**
	 * 직렬화가 될 때 자동으로 호출된다.
	 * (접근 제어자가 private이 아니면 자동으로 호출되지 않는다)
	 * @param ois
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		setParentName(ois.readUTF());
		ois.defaultReadObject();
	}
	
}
