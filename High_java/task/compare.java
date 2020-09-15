package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 문제 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 가지는 Student클래스를 만든다.
 *
 * 생성자는 학번, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 * 
 * Student 객체들을 List에 저장하여 관리한다.
 *
 * List에 저장한 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 총점의 역순으로 정렬하는 부분을 구현하시오. 
 * (총점이 같으면 학번의 내림차순으로 정렬되도록 한다) 
 * (학번 정렬 기준은 Student클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다)
 */
public class T04_StudentTest {

	public static void main(String[] args) {
		
		List<Student> list = new ArrayList<>();
		
		list.add(new Student("20131111", "홍길동", 89, 89, 89));
		list.add(new Student("20113123", "강감찬", 91, 97, 100));
		list.add(new Student("20090130", "장보고", 59, 79, 89));
		list.add(new Student("20120870", "제갈량", 100, 100, 100));
		list.add(new Student("20070121", "비서우", 29, 99, 69));
		list.add(new Student("20010121", "신서우", 29, 99, 69));
		list.add(new Student("20050121", "이서우", 29, 99, 69));
		
		//총점기준 내림차순
		System.out.println("============총점의 내림차순 정렬============");
		System.out.println("=========<총점이 같으면 학번의 내림차순>==========");
		Collections.sort(list, new ScoreDesc());
		
		for(int i=1; i<list.size()+1; i++) {
			//Set grade
			list.get(i-1).setGrade(i);
		}
		
		for(Student s : list) {
			System.out.println(s);
		}
		
		System.out.println("==============================================");
		System.out.println();
		
		// 학번의 오름차순 정렬
		Collections.sort(list);
		
		System.out.println("============학번의 오름차순 정렬============");
		for(Student s : list) {
			System.out.println(s);
		}
		
		System.out.println("==============================================");
		System.out.println();
	}
}

class ScoreDesc implements Comparator<Student> {

	@Override
	public int compare(Student st1, Student st2) {
		
		if(st1.getTotal_score() == st2.getTotal_score()) {
			// 총점이 같으면 학번기준 내림차순으로 정렬한다.
			return st1.compareTo(st2) * -1;
		}
		
		return Integer.compare(st1.getTotal_score(), st2.getTotal_score()) * -1;
	}
	
}

class Student implements Comparable<Student> {

	String st_no;
	String st_name;
	int kr_score;
	int eng_score;
	int math_score;
	int total_score;
	int grade;

	public Student(String st_no, String st_name, int eng_score, int math_score, int kr_score) {
		super();
		this.st_no = st_no;
		this.st_name = st_name;
		this.eng_score = eng_score;
		this.math_score = math_score;
		this.kr_score = kr_score;
		this.total_score = this.eng_score + this.math_score + this.kr_score;
	}
	
	
	public int getKr_score() {
		return kr_score;
	}



	public void setKr_score(int kr_score) {
		this.kr_score = kr_score;
	}
	
	
	
	
	public String getSt_no() {
		return st_no;
	}



	public void setSt_no(String st_no) {
		this.st_no = st_no;
	}



	public String getSt_name() {
		return st_name;
	}



	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}



	public int getEng_score() {
		return eng_score;
	}



	public void setEng_score(int eng_score) {
		this.eng_score = eng_score;
	}



	public int getMath_score() {
		return math_score;
	}



	public void setMath_score(int math_score) {
		this.math_score = math_score;
	}



	public int getTotal_score() {
		return total_score;
	}



	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}



	public int getGrade() {
		return grade;
	}



	public void setGrade(int grade) {
		this.grade = grade;
	}



	@Override
	public int compareTo(Student st) {
		
		return this.st_no.compareTo(st.getSt_no());
	}


	@Override
	public String toString() {
		return "Student [st_no=" + st_no + ", st_name=" + st_name + ", kr_score=" + kr_score + ", eng_score="
				+ eng_score + ", math_score=" + math_score + ", total_score=" + total_score + ", grade=" + grade + "]";
	}

}
