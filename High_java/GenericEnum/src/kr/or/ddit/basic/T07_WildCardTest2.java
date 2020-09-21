package kr.or.ddit.basic;

import java.util.Arrays;

public class T07_WildCardTest2 {

	public static void main(String[] args) {
		
		Course<Person> personCourse = new Course("일반인과정", 5);
		personCourse.add(new Person("홍길동"));
		personCourse.add(new Person("강감찬"));
		personCourse.add(new Student("한서진"));
		personCourse.add(new HighStudent("고등학생"));
		
		Course<Worker> workerCourse = new Course("직장인과정", 5);
		workerCourse.add(new Worker("직장인"));
		
		Course<Student> studentCourse = new Course("학생과정", 5);
		studentCourse.add(new Student("학생1"));
		studentCourse.add(new HighStudent("고등학생 1"));
		
		Course<HighStudent> highStudentCourse = new Course("고등학생과정", 5);
		highStudentCourse.add(new HighStudent("고등학생 1"));
		
		
		registerCourse(personCourse);
		registerCourse(workerCourse);
		registerCourse(studentCourse);
		registerCourse(highStudentCourse);
		System.out.println("-----------------------------------------");

//		registerCourseStudent(personCourse);
//		registerCourseStudent(workerCourse);
		registerCourseStudent(studentCourse);
		registerCourseStudent(highStudentCourse);
		System.out.println("-----------------------------------------");

		registerWorkerCourse(personCourse);
		registerWorkerCourse(workerCourse);
//		registerWorkerCourse(studentCourse);
//		registerWorkerCourse(highStudentCourse);
		System.out.println("-----------------------------------------");

		
		
		
		
		

	}
	
	/**
	 *	모든 과정을 등록할 수 있는 메서드
	 */
	public static void registerCourse(Course<?> c) {
		System.out.println(c.getName() + "수강생 => " + Arrays.toString(c.getStudent()));
	}
	
	public static void registerCourseStudent(Course<? extends Student> c) {
		//public static <T extends Student> void registerCourseStudent(Course<T> c)와 동일
		System.out.println(c.getName() + "수강생 => " + Arrays.toString(c.getStudent()));
	}
	
	public static void registerWorkerCourse(Course<? super Worker> c) {
		System.out.println(c.getName() + "수강생 => " + Arrays.toString(c.getStudent()));
	}
}



class Person {
	private String name;
	
	public Person(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name : " + name + "]";
	}
}

class Worker extends Person {

	public Worker(String name) {
		super(name);
	}
	
}

class Student extends Person {

	public Student(String name) {
		super(name);
	}

}

class HighStudent extends Student {

	public HighStudent(String name) {
		super(name);
	}
	
}

class Course<T> {
	private String name;
	private T[] student;
	
	public Course(String name, int capacity) {
		
		this.name = name;
		
		// 타입 파라미터로 배열을 생성 시 오브젝트 배열을 생성 후
		// 타입 파라미터 배열로 캐스팅
		// new연산자와 제너릭 타입을 사용할 수 없다 (new연산자는 컴파일 시 정확한 타입을 알아야함)
		student = (T[])(new Object[capacity]);
	}

	public String getName() {
		return name;
	}

	public T[] getStudent() {
		return student;
	}
	
	public void add(T t) {
		for(int i=0; i<student.length; i++) {
			if(student[i] == null) {
				student[i] = t;
				break;
			}
		}
	}
	
}