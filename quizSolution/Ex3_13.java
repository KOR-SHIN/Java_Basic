package ExZip;

public class Ex3_13 {
	public static void main(String[] args) {
		
		TimeVO tv = new TimeVO();
		
		tv.setHour(46);
		tv.setMinute(112);
		tv.setSecond(4853);
		
		System.out.println("current Time : " + tv.getHour() + "시 " + tv.getMinute() + "분 " + tv.getSecond() + "초 입니다.");
	}
}

class TimeVO {
	private int hour;
	private int minute;
	private int second;
	
	
	public int getHour() {
		return hour;
	}
	
	public void setHour(int hour) {
		//1. 시간은 0~23시까지이다.
		//매개변수에 저장된 값이 0~23로만 저장되도록 메서드를 구현하여라.
		if(hour >= 24) {
			while(hour >= 24) {
				hour -= 24;
			}
			
			this.hour = hour;
			
		} else {
			this.hour = hour;
		}
	}
	
	public int getMinute() {
		return minute;
	}
	
	public void setMinute(int minute) {
		//2. 매개변수에 저장된 값이 0~59로만 저장되도록 메서드를 구현하여라.
		//분이 60을 넘는다면 1시간을 의미하므로 시간을 추가되도록 하여라.
		//ex. 143분 -> 2시간 23분
		
		if(minute >= 60){
			while(minute >= 60) {
				hour++;
				
				if(hour >= 24) {
					setHour(hour);
				}
				
				minute -= 60;
				
			}
			this.minute = minute;
			
		} else {
			this.minute = minute;
		}
		
		
	}
	
	public int getSecond() {
		return second;
	}
	
	public void setSecond(int second) {
		//3. 배개변수에 저장된 값이 0~59로만 저장되도록 메서드를 구현하여라.
		//초가 60을 넘는다면 1분을 의미하므로 분이 추가되도록 하여라.
		//ex. 3930초 -> 1시간 5분 30초
		
		if(second >= 60) {
			while(second >= 60) {
				minute++;
				
				if(minute >= 60) {
					setMinute(minute);
				}
				
				second -= 60;
			}
			this.second = second;
			
		} else {
			this.second = second;
		}
		
		
	}
}
