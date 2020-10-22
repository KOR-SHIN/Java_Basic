package kr.or.ddit.basic;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 공공데이터 포털의 OPEN API 예제
 * (레시피 재료 정보를 가져오는 예제)
 */
public class JsonAPISimpleReadTest {
	private String svcKey = "Grid_20150827000000000228_1";  // 레시피 재료 정보 조회 서비스 
	private String apiKey = "9c8d7992af0ced9487074ed4628b706a26d73a911c035f02faa059884b0fc19a"; // 개인별 발급.
	private String startIdx = "1";  	// 레시피 재료 시작 순번
	private String endIdx = "5";		// 레시피 재료 종료 순번 (몇 번까지 있는지 모르기때문에 우선 임의의 인덱스를 설정)
	private String recipeId = "1";	// 래시피가 궁금한 음식 ID 
	
	public static void main(String[] args){
		new JsonAPISimpleReadTest().parsing();
	}
	
	private String getURL() {
		return "http://211.237.50.150:7080/openapi/"+ apiKey + "/json/" 
				+ svcKey + "/"+ startIdx +"/" + endIdx + "?RECIPE_ID=" + recipeId;
	}
	
	private void printJSONFile(JSONArray list) {
		for(Object item : list) {
			JSONObject temp = (JSONObject) item;
			
			System.out.println("ROW_NUM : " + temp.get("ROW_NUM"));
			System.out.println("RECIPE_ID : " + temp.get("RECIPE_ID"));
			System.out.println("COOKING_NO : " + temp.get("COOKING_NO"));
			System.out.println("COOKING_DC : " + temp.get("COOKING_DC"));
			System.out.println("===========================================================");
		}
	}
	
	private void parsing() {
		
		// 서버에서 실시간으로 정보를 읽어오기 때문에 URL을 이용한다.
		try {
			URLConnection urlConnection = new URL(getURL()).openConnection();
			
			JSONParser parser = new JSONParser();
			
			// 서버에서 가져오는 데이터를 File이 아니고 문자열 형태이다.
			// 따라서 InputSteamReader를 사용하여 바이트기반 스트림을 문자열기반으로 변경한다.
			Object obj = parser.parse((new InputStreamReader(urlConnection.getInputStream())));
			JSONObject jsonFile = (JSONObject) obj;
			JSONObject rootObj = (JSONObject)jsonFile.get(svcKey);
			
			// 전체 레시피 재료 수
			// File을 읽어오기전까지는 totalCnt를 알 수 없기때문에
			// totalCnt를 알기위해 임의의 길이만큼의 파일을 읽어온 후
			// totalCnt의 값을 endIdx로 할당한다.
			long totalCnt = (long)rootObj.get("totalCnt");
			
			// 레시피 재료 마지막 순번 설정
			endIdx = totalCnt + "";
			//=======================================================================================================================
			
			// 레시피 재료 마지막 순번을 알아내고, 다시 정보를 요청
			urlConnection = new URL(getURL()).openConnection();
			
			//=======================================================================================================================
			// parsing
			obj = parser.parse((new InputStreamReader(urlConnection.getInputStream())));
			jsonFile = (JSONObject) obj;
			rootObj = (JSONObject) jsonFile.get(svcKey);
			
			// 정상적인 데이터인지 result를 parsing한다.
			// 정상데이터라면 code는 INFO-000가 된다.
			JSONObject result = (JSONObject) rootObj.get("result");
			String code = (String) result.get("code");
			
			if(code.equals("INFO-000")) {
				// array로 되어있는 row를 가져온다.
				// row배열에는 레시피정보가 들어있다.
				JSONArray list = (JSONArray) rootObj.get("row");
				printJSONFile(list);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
