package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlAPISimpleRead {
	public static void main(String[] args) {
		new XmlAPISimpleRead().xmlParsing();
	}

	private void xmlParsing() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentbuilder = factory.newDocumentBuilder();

			InputStream is = new ByteArrayInputStream(getXmlFile().getBytes());

			Document doc = documentbuilder.parse(is);

			Element root = doc.getDocumentElement();

			NodeList nodeList = root.getElementsByTagName("row");
			printXmlFile(nodeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getXmlFile() {
		String xml = "";
		String svcKey = "Grid_20150827000000000228_1";  // 레시피 재료 정보 조회 서비스 
		
		// API를 신청하면 발급되는 번호이다
		// 공공데이터를 가져오기 위해서는 신청하고 API를 발급받아야 한다
		// 허가받지 않은 사람이 정보에 접근하는것을 방지하기 위한것이다.
		String apiKey = "9c8d7992af0ced9487074ed4628b706a26d73a911c035f02faa059884b0fc19a"; // 개인별 발급.
		String startIdx = "1";  	// 레시피 재료 시작 순번
		String endIdx = "5";		// 레시피 재료 종료 순번 (몇 번까지 있는지 모르기때문에 우선 임의의 인덱스를 설정)
		String recipeId = "1";	// 래시피가 궁금한 음식 ID 

		// 서버에서 실시간으로 정보를 읽어오기 때문에 URL을 이용한다.

		
		try {
			URL url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey 
					+ "/xml/"+ svcKey + "/"+startIdx +"/" + endIdx 
					+"?RECIPE_ID=" +  recipeId);

			URLConnection http = url.openConnection();

			// 위 주소에서 주는 데이터를 문자열로 읽기 위한 스트림 객체 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
			StringBuilder sb = new StringBuilder();

			while (true) {
				String line = br.readLine();
				if (line == null) { break; } 
				else { sb.append(line); }
			}
			xml = sb.toString();
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return xml;
	}
	
	// 빈값인지 체크하는 메서드
	private static String valueCheck(Element temp, String tagName) {
		if (temp.getElementsByTagName(tagName).getLength() > 0) {
			return temp.getElementsByTagName(tagName).item(0).getTextContent();
		}
		return "";
	}
	
	private void printXmlFile(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element temp = (Element) nodeList.item(i);

			System.out.println("ROW_NUM : " + valueCheck(temp, "ROW_NUM"));
			System.out.println("RECIPE_ID : " + valueCheck(temp, "RECIPE_ID"));
			System.out.println("COOKING_NO " + valueCheck(temp, "COOKING_NO"));
			System.out.println("COOKING_DC " + valueCheck(temp, "COOKING_DC"));
			System.out.println("===========================================================");
		}
		
	}
}
