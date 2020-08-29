package package_study;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarTest {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int year = 0;
		int month = 0;
		int date = 0;
		Calendar ct = Calendar.getInstance();
		System.out.print("현재 날짜 :");
		System.out.println(ct.get(Calendar.YEAR)+"."+(ct.get(Calendar.MONTH)+1)+"."+ct.get(Calendar.DATE));
		
		//1.연도 입력 ( 1.현재보다 이전일 때, 2현재와 같을 때, 2현재보다 미래일 때)
		//2.월 입력 (현재연도와 같으면서 현재월보다 이전일 때, 현재연도보다 미래일 때)
		//3.날짜 입력 (현재연도와 같으면서 현재월보다 이전이면서 해당 월의 최대 일수보다 작거나 같다,  해당월의 최대 일수보다 작거나 같다.)
		System.out.println("연도를 입력해 주세요");
		year = sc.nextInt(); // 연도 입력
		while(year> ct.getActualMaximum(Calendar.YEAR) || year < ct.get(Calendar.YEAR) ){ // 1. 현재연도 보다 과거일 때
			System.out.println("현재 연도보다 이전 연도를 입력할 수 없습니다.");
			System.out.println("다시 입력해주세요");
			year= sc.nextInt();
		}
		
		
		if ( year == ct.get(Calendar.YEAR)){ //현재와 같을 때
			ct.set(year, ct.get(Calendar.MONTH), ct.get(Calendar.DATE));
			
			//월입력
			System.out.println("월을 입력해 주세요 ("+(ct.get(Calendar.MONTH)+1)+"~"+"12)");
			month = sc.nextInt(); // 월 입력
			while(month > ct.getActualMaximum(Calendar.MONTH) || month < ct.get(Calendar.MONTH)){ //현재월보다 과거일 때
				System.out.println("현재 월보다 이전 월을 입력할 수 없습니다.");
				System.out.println("다시 입력해주세요");
				month = sc.nextInt();
			}
			ct.set(ct.get(Calendar.YEAR), month-1, ct.get(Calendar.DATE));
			
			//날짜입력
			System.out.println("일자를 입력해 주세요 ("+(ct.get(Calendar.DATE)+1)+"~"+(ct.getActualMaximum(Calendar.DATE))+")");
			date = sc.nextInt(); // 일 입력
			while(date > ct.getActualMaximum(Calendar.DATE) || date <= ct.get(Calendar.DATE) ){
				System.out.println("현재 일자이후의 날짜, 해당 월의 최대일자를 넘을 수 없음");
				System.out.println("다시 입력해주세요");
				date = sc.nextInt();
			}
			
		}else {//현재보다 미래일 때
			
			
			ct.set(year, 1, 1);
			
			//월입력
			System.out.println("월을 입력해 주세요 (1~12)");
			month = sc.nextInt(); //월 입력
			while(month > ct.getActualMaximum(Calendar.MONTH) || month < 0){ //현재월보다 과거일 때
				System.out.println("해당 월은 존재하지 않습니다.");
				System.out.println("다시 입력해주세요(1~12)");
				month = sc.nextInt();
			}
			
			ct.set(year, month-1, 1);
			
			//날짜입력
			System.out.println("일자를 입력해 주세요 (1~"+ct.getActualMaximum(Calendar.DATE)+")");
			date = sc.nextInt(); // 일 입력
			while(date > ct.getActualMaximum(Calendar.DATE) || date < 0 ){
				System.out.println("해당 일자가 존재하지 않습니다.");
				System.out.println("다시 입력해주세요 (1~"+ct.getActualMaximum(Calendar.DATE)+")");
				date = sc.nextInt();
			}
			
		}
		
		//캘린더 년월일 초기화
		ct.set(year, month-1, date , 0 , 0);
		System.out.print("변경 날짜 :");
		System.out.println(ct.get(Calendar.YEAR)+"."+(ct.get(Calendar.MONTH)+1)+"."+ct.get(Calendar.DATE) + "/"+ct.get(Calendar.HOUR) +":"+ ct.get(Calendar.MINUTE));
		
		
		
		
		//시간관리 ( 러닝타임+20분을 하루로 나누어 showVO 객체를 생성한다.
		int runtime = 150;
		int Totaltime = runtime + 20;
		int limited_date = ct.get(Calendar.DATE);
		
		Calendar tmptime = Calendar.getInstance();
		tmptime.set(year, month-1, date+1 , 0 , 0);
		tmptime.add(Calendar.MINUTE, -Totaltime);
		int limit_hour = tmptime.get(Calendar.HOUR_OF_DAY);
		System.out.println(limit_hour);
		
		do {
			ct.add(Calendar.MINUTE, Totaltime);
			
			System.out.println(ct.get(Calendar.YEAR)+"."+(ct.get(Calendar.MONTH)+1)+"."+ct.get(Calendar.DATE) + "/"+ct.get(Calendar.HOUR_OF_DAY) +":"+ ct.get(Calendar.MINUTE));
		} while (ct.get(Calendar.HOUR_OF_DAY)<limit_hour);

		
//		while(ct.get(Calendar.DATE)==limited_date){
//			ct.add(Calendar.MINUTE, Totaltime);
//			System.out.println(ct.get(Calendar.YEAR)+"."+(ct.get(Calendar.MONTH)+1)+"."+ct.get(Calendar.DATE) + "/"+ct.get(Calendar.HOUR_OF_DAY) +":"+ ct.get(Calendar.MINUTE));
//		}
		
		
		
		
		
		
	}
}
