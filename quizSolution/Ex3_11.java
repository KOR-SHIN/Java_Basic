package f_oop2;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

public class DrawShape extends Frame{
	public static void main(String[] args) {
		DrawShape sample = new DrawShape();
		
		//Triangle tri = new Triangle(new Point(100,100), new Point(200, 200), new Point(300,300));
		
		
	}
	
	DrawShape() {
		
		super("기들만판림그");
		setSize(500, 500);
		setBackground(Color.magenta);
		setVisible(true);
		setResizable(false);
	}
	
	public void paint(Graphics g) {
		//1. 매개변수가 두개인 생성자를 이용하여 Circle객체를 만들어주세요.
		Point circlePoint = new Point(100,100);
		Circle circle = new Circle(circlePoint, 150);

		//2. g.drawOval()원을 그려주세요.
		g.drawOval(circle.center.x, circle.center.y, circle.r*2, circle.r*2);
		
		//3. 매개변수가 하나인 생성자를 이용하여 Triangle 객체를 만들어주세요.
		// 100,100, 200,200, 200,100
		Point triPoint1 = new Point(100, 100);
		Point triPoint2 = new Point(200, 200);
		Point triPoint3 = new Point(200, 100);
		Point[] temp = { triPoint1, triPoint2, triPoint3 };
		
		Triangle tri = new Triangle(temp);
		
		//4. g.drawLine() 3개를 이용하여 삼각형을 그려주세요.
		g.drawLine(tri.p[0].x, tri.p[0].y, tri.p[1].x, tri.p[1].y);
		g.drawLine(tri.p[1].x, tri.p[1].y, tri.p[2].x, tri.p[2].y);
		g.drawLine(tri.p[2].x, tri.p[2].y, tri.p[0].x, tri.p[0].y);
	}
	
}

/**
 * 도형을 만들기 위한 좌표를 관리하기 위한 클래스
 * @author PC-NEW11
 * @since 2020.08.24
 */

class Point{
	int x;
	int y;

	
	Point(int x, int y) {
		this.x = x; 
		this.y = y;
	}

}

class Circle {
	
	//1. 반지름(정수)를 저장할 수 있는 변수 r을 선언해주세요.
	int r;
	
	//2. 점 하나를 저장할 수 있는 변수 center를 선언해주세요.
	Point center;
	
	//3. 매개변수가 두개인 생성자를 이용하여 r과 center를 초기화해주세요.
	Circle(Point center, int r) {
		this.r = r;
		this.center = center;
	}
	
	//기본생성자를 만들어주세요.
	//단, 매개변수가 두개인 생성자를 호출하여 좌표는 100, 100 반지름은 50으로 초기화해주세요.
	Circle() {
		this(new Point(100, 100), 50);
	}		
}

class Triangle {
	//1. 점 3개를 저장할 수 있는 변수 p를 선언해주세요.
	Point[] p;
	
	//2. 매개변수가 하나있는 생성자를 만드시오.
	public Triangle(Point p[]) {
		this.p = p;
	}
	
	//3. 매개변수가 3개인 생성자를 만들어주세요.
	public Triangle(Point p1, Point p2, Point p3) {
		p = new Point[3];
		
		this.p[0] = p1;
		this.p[1] = p2;
		this.p[2] = p3;
		
	}
}