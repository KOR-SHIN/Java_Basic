package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonSimpleReadTest {
	public static void main(String[] args) throws IOException, ParseException {
		
		// JSON File을 읽어오기위한 기반 스트림은 FileReader 
		FileReader fr = new FileReader("d:/D_Other/myJsonFile.txt");
		
		JSONParser parser = new JSONParser();

		// JSON File을 parser를 이용하여 읽어온다 
		// (Object타입으로 읽어온 후 JSONObject 타입으로 형변환)
		Object obj = parser.parse(fr);
		JSONObject jsonFile = (JSONObject) obj;
		
		String name = (String)jsonFile.get("name");
		String job = (String)jsonFile.get("job");
		long age = (long)jsonFile.get("age"); // 정수는 int가 아닌 long타입이다.
		String addr = (String)jsonFile.get("addr");
		
		System.out.println("name : " + name);
		System.out.println("job : " + job);
		System.out.println("age : " + age);
		System.out.println("addr : " + addr);
		
		JSONArray list = (JSONArray) jsonFile.get("singerList");
		Iterator<JSONObject> it = list.iterator();
		
		JSONObject singer;
		while(it.hasNext()) {
			singer = it.next();
			System.out.printf("이름 : %s, \t성별 : %s \t나이 : %d\n", 
					singer.get("name"), singer.get("gender"), singer.get("age"));
		}
		
		// JSON List에서 JSON이 들어있는 경우처럼 구조가 복잡한 경우에는
		// JSON List에 들어있는 객체를 먼저 꺼내오고
		// 꺼내온데이터를 다시 JSON으로 parsing하여 사용해야한다.
		
	}
}
