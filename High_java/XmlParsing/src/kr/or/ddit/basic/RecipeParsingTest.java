package kr.or.ddit.basic;

import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class RecipeParsingTest {
	
	private void parsing() {

		try {
			DocumentBuilderFactory sbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = sbf.newDocumentBuilder();
			
			String url = getClass().getResource("/kr/or/ddit/basic/recipe.xml").toExternalForm();
			Document xmlDoc = builder.parse(url);
			
			Element root = xmlDoc.getDocumentElement();
			NodeList rowList = root.getElementsByTagName("row");
			
			for(int i=0; i<rowList.getLength(); i++) {
				Element temp = (Element)rowList.item(i);
				
				System.out.println("ROW_NUM : " + valueCheck(temp, "ROW_NUM"));
				System.out.println("RECIPE_ID : " + valueCheck(temp, "RECIPE_ID"));
				System.out.println("IRDNT_SN " + valueCheck(temp, "IRDNT_SN"));
				System.out.println("IRDNT_NM " + valueCheck(temp, "IRDNT_NM"));
				System.out.println("IRDNT_CPCTY : "  + valueCheck(temp, "IRDNT_CPCTY"));
				System.out.println("IRDNT_TY_CODE : "  + valueCheck(temp, "IRDNT_TY_CODE"));
				System.out.println("IRDNT_TY_NM : "  + valueCheck(temp, "IRDNT_TY_NM"));
				System.out.println("===========================================================");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String valueCheck(Element temp, String tagName) {
		if(temp.getElementsByTagName(tagName).getLength() > 0) {
			return temp.getElementsByTagName(tagName).item(0).getTextContent();
		}
		return "";
	}

	public static void main(String[] args) {
		new RecipeParsingTest().parsing();
	}

}
