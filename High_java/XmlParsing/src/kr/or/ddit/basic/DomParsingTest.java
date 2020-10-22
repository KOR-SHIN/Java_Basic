package kr.or.ddit.basic;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOM 파싱 예제
 */
public class DomParsingTest {
	
	public void parsing() {
		try {
			// DOM Document 객체 생성하기
			// DOM에서 최상위 요소는 Node이다.
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			// XML파일 지정
			// getResource() => URL객체를 반환
			// toExternalForm() => URL을 String객체로 반환
			String url = getClass().getResource("/kr/or/ddit/basic/book.xml").toExternalForm();
			Document xmlDoc = builder.parse(url);
			
			// DOM Document 객체로부터 루트 엘리먼트 및 자식 객체 가져오기
			Element root = xmlDoc.getDocumentElement();
			System.out.println("Root Elements TagName => " + root.getTagName());
			
			// 하위 요소 접근방법1 : getElementsByTagName()
			NodeList bookNodeList = root.getElementsByTagName("book");

			Node firstBookNode = bookNodeList.item(0);
			Element firstBookElement = (Element)firstBookNode;

			// Element 속성 접근방법1 => Element객체의 getAttribute() 이용
			System.out.println("Element 객체의 속성 => " + firstBookElement.getAttribute("isbn"));
			System.out.println("Element 객체의 분류 => " + firstBookElement.getAttribute("kind"));

			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			// Element 속성 접근방법2 => Node객체의 getAttributes() 이용
			NamedNodeMap nodeMap = firstBookNode.getAttributes();

			// getNamedItem() => key=value 형태로 반환
			// getNamedImte().getNodeValue() => 해당 Node의 value만 반환
			System.out.println("Node 객체의 getAttributes() => " + nodeMap.getNamedItem("isbn").getNodeValue());
			
			// 하위 요소 접근방법2 : getChildNodes() 이용
			NodeList firstBookChildNodes = firstBookNode.getChildNodes();
			
			// getChildNodes()는 공백도 포함하기 때문에 getChildNodes()를 사용하기보다
			// getElementsByTagName()을 사용하는것이 안전하다.
			Node titleNode = firstBookChildNodes.item(1);
			Element titleElement = (Element)titleNode;
			System.out.println("titleElement.getTagName() => " + titleElement.getTagName());
			System.out.println("titleElement.getTextContent() => " + titleElement.getTextContent());
			
			// 전체 출력하기
			// 속성값 : isbn, kind
			// 엘리먼트 텍스트 값 : title, author, price
			System.out.println("----------------------------------------------");
			for(int i=0; i < bookNodeList.getLength(); i++) {
				Node bookNode = bookNodeList.item(i);
				Element element = (Element) bookNode;
				String isbn = element.getAttribute("isbn");
				String kind = element.getAttribute("kind");
				String title = element.getElementsByTagName("title").item(0).getTextContent();
				String author = element.getElementsByTagName("author").item(0).getTextContent();
				String price = element.getElementsByTagName("price").item(0).getTextContent();

				String str = String.format("%8s %10s %20s %10s %8s", isbn, kind, title, author, price);
				System.out.println(str);
			}
		} catch(Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	public static void main(String[] args) {
		DomParsingTest dom = new DomParsingTest();
		dom.parsing();
	}
	
	
	
	
	
	
	
	
	
}
