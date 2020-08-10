package ExZip;

class Point{
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	Point() {
		
	}
}

class Circle {
	//1. 중심점을 저장할 수 있는 변수 c를 선언하여라.
	double c;
	
	//2. 반지름(정수)을 저장할 수 있는 변수 r을 선언하여라
	int r;
	
	//3. 매개변수가 두 개인 생정자를 작셩하여라
	//	(단, 두 개의 매개변수를 이용하여 c와 r을 초기화 하여라)
	Circle(int radius, double center) {
		c = center;
		r = radius;
	}
	
	//4. 기본 생성자를 작성하여라.
	//	(단, 매개변수가 두 개인 생성자를 호출하여 중심점은 (100,100)으로 반지름은 200이 되도록 해라.
	Circle() {
		this(200, 100.100);
	}
}

class Triangle{
	//5. 점 여러 개를 저장할 수 있는 변수 p를 선언하여라
	int[] p;
	
	//6. 매개변수가 하나인 생성자를 이용하여 p를 초기화 하여라
	Triangle(int value) {
		p = new int[1];
		p[0] = value;
	}
	
	//7. 매개변수가 세 개인 생성자를 이용하여 p를 초기화 하여라
	Triangle(int...value) {
		p = new int[value.length];
		for(int i = 0; i<p.length; i++) {
			p[i] = value[i];
		}
	}
}
public class Ex3_11 {
	public static void main(String[] args) {
		//8. Circle의 객체를 생성하여라.
		//	(단, 매개변수가 두 개인 생성자를 이용하여라(줌심점은 200,200 반지름 50);
		Circle circle = new Circle(50, 200.200);
		
		//9. Triangle의 객체를 생성하여라.
		//	(단, 매개변수가 하나인 생성자를 이용하여라)
		Triangle triangle = new Triangle(100);		
	}
}
