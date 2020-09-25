package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T12_HorseGame {
	public static int commonRank;

	public static void main(String[] args) {

		// Horse객체가 있는 List
		List<Horse> player = new T12_HorseGame().addPlayer();
		long st_time = System.currentTimeMillis();

		// start race
		for (Horse h : player) {
			h.start();
		}

		// wait
		for (Horse h : player) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Collections.sort(player);

		System.out.println("<< RACE END >>");
		System.out.println();
		System.out.println("<< RACE RANKING >>");
		for (Horse h : player) {
			System.out.println(h.getRank() + "등 => " + h.getHorseName());
		}

		System.out.println("소요시간 : " + ((System.currentTimeMillis() - st_time) / 1000F) + "초");
	}

	private List<Horse> addPlayer() {
		List<Horse> ret = new ArrayList<Horse>();
		ret.add(new Horse("1번말"));
		ret.add(new Horse("2번말"));
		ret.add(new Horse("3번말"));
		ret.add(new Horse("4번말"));
		ret.add(new Horse("5번말"));
		ret.add(new Horse("6번말"));
		ret.add(new Horse("7번말"));
		ret.add(new Horse("8번말"));
		ret.add(new Horse("9번말"));
		ret.add(new Horse("10번말"));

		return ret;
	}

}

class Horse extends Thread implements Comparable<Horse> {
	private String horseName;
	private int rank;

	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public String getHorseName() {
		return horseName;
	}

	@Override
	public void run() {
		
		StringBuffer bf = makeLine();
		
		for(int i=0; i<bf.length(); i++) {
			try {
				Thread.sleep((int)(Math.random() * 301));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			bf.replace(i, i+1, ">");
			if(horseName.equals("10번말")) { 
				System.out.println(horseName + bf); 
			}
			else { 
				System.out.println(horseName + " " + bf); 
			}
			bf.replace(i, i+1, "-");
		}
		System.out.println();
		System.out.println(horseName + "골인지점 통과");

		this.rank = T12_HorseGame.commonRank + 1;
		T12_HorseGame.commonRank += 1;		
	}

	private StringBuffer makeLine() {
		StringBuffer bf = new StringBuffer();
		for(int i=0; i<50; i++) {
			bf.append("-");
		}
		
		return bf;
	}
	
	@Override
	public int compareTo(Horse h) {
		return this.rank - h.rank;
	}
	
	
}
