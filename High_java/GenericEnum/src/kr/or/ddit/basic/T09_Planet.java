package kr.or.ddit.basic;

import java.text.NumberFormat;
import java.util.Scanner;

enum Planet { 
	수성(2439), 금성(6052),	지구(6371),	화성(3390), 
	목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
	
	private int r;
	
	Planet(int km) {
		r = km;
	}

	public int getR() {
		return r;
	}
	
}

public class T09_Planet {

	public static void main(String[] args) {
		
		
		Planet[] p = Planet.values();
		
		NumberFormat f = NumberFormat.getInstance();
		f.setGroupingUsed(true); // false로하면 소수점만 표현함
		
		for(Planet e : p) {
			String space = "";
			if(e.name().length() < 3) { space += "  "; };
			System.out.println(space + e.name() + "의 면적 : " + f.format(4 * Math.PI * Math.pow(e.getR(), 2)) + " KM");
		}
	}
}
