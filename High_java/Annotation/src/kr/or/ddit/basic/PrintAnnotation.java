package kr.or.ddit.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.lang.model.element.Element;

/**
 *	
 * 	<Annotation>
 * 	프로그램 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨것
 *	주석처럼 프로그래밍 언어에 영향을 미치지 않으면서 다른 프로그램에게 유용한 정보를 제공한다.
 * 	어떻게 생성하고, 왜 사용되고, 어떻게 사용하는지
 * 
 * 	종류
 * 	1. 표준 어노테이션 (주로 컴파일러에게 유용한 정보를 제공하기 위한 어노테이션)
 * 	2. 메타 어노테이션 (어노테이션을 위한 어노테이션, 즉 어노테이션을 정의할때 사용됨)
 * 
 * 	어노테이션 정의하기
 * 	@interface 어노테이션 이름 {
 * 			타입요소이름(); // 반환값이 있고 매개변수는 없는 추상메서드 형태
 * 			...
 * 	}
 * 
 * 	어노테이션 요소의 규칙
 * 	1. 요소의 타입은 기본형, String, enum, annotation, class만 허용한다.
 * 	2. ( )안에 매개변수를 선언할 수 없다.
 * 	3. 예외를 선언할 수 없다.
 * 	4. 요소의 타입이 타입 매개변수 (제너릭타입문자)를 사용할 수 없다.
 */

@Target(ElementType.METHOD) // annotation이 적용가능한 대상을 지정함
@Retention(RetentionPolicy.RUNTIME) // annotation이 유지되는 기간 (CLASS가 기본값)
public @interface PrintAnnotation {

	int id = 100; // 상수 선언가능, static final int id = 100;
	String value() default "-"; // 기본값 '-'로 지정
	int count() default 20; // 기본값 20으로 지정

}










