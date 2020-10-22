package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * JSON
 * - JavaScript Object Notation
 * - JSON에서 value값으로 가능한 데이터 타입
 * 	- String, number, object(JSON Object), array, boolean, null
 */
public class JsonSimpleWriterTest {
	public static void main(String[] args) throws IOException {
		// JSON 데이터 생성
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("name", "홍길동");
		jsonObj.put("job", "학생");
		jsonObj.put("age", 23); // long type으로 다뤄진다.
		jsonObj.put("addr", "대전시 중구 대흥동 대덕인재개발원");
		
		// JSONArray 데이터 생성
		JSONArray singerList = new JSONArray();
		
		JSONObject singer =  new JSONObject();
		singer.put("name", "싸이");
		singer.put("gender", "남자");
		singer.put("age", 40);
		singerList.add(singer);

		singer =  new JSONObject();
		singer.put("name", "비욘세");
		singer.put("gender", "여자");
		singer.put("age", 34);
		singerList.add(singer);
		
		singer =  new JSONObject();
		singer.put("name", "마이클잭슨");
		singer.put("gender", "남자");
		singer.put("age", 66);
		singerList.add(singer);
		
		jsonObj.put("singerList", singerList);
		
		FileWriter fw = new FileWriter("d:/D_Other/myJsonFile.txt");
		fw.write(jsonObj.toString()); 
		fw.flush();
		fw.close();

		
	}
}
