package kr.or.ddit.basic;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParsingPractice {

	public static void main(String[] args) {
		new ParsingPractice().parsing();
	}
	
	public void parsing() {
		try {
			// BuilderFactory => Builder => getURL => parsing(url) => getRootElement
			// getNodeList By TagName => nodeToElement parsing => 
			
			// Builder를 만들기위한 Factory 객체
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			// Factory객체를 이용한 Builder객체 
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			// parsing하려는 xml문서의 url 가져오기
			String url = getUrl("/kr/or/ddit/basic/book.xml");

			// xml의 url을 이용하여 Dom객체 생성
			Document xmlDoc = builder.parse(url);
			
			// parsing된 xml문서의 최상위 요소 가져오기
			Element root = getRootElement(xmlDoc);
			
			// 최상위 요소의 하위 요소 접근 (TagName으로 접근)
			NodeList bookList = root.getElementsByTagName("book");
			System.out.println("bookList => " + bookList.getLength());
			
			// Element의 속성 접근
//			Node book = bookList.item(0);
//			Element bookElement = (Element)book;
//			System.out.println("isbn => " + bookElement.getAttribute("isbn"));
//			System.out.println("kind => " + bookElement.getAttribute("kind"));
			
//			// NamedNodeMap으로 속성 접근
//			NamedNodeMap nodeMap = book.getAttributes();
//			System.out.println("isbn => " + nodeMap.getNamedItem("isbn").getNodeValue());
//			System.out.println("kind => " + nodeMap.getNamedItem("kind").getNodeValue());

			for(int i=0; i<bookList.getLength(); i++) {
				Element tempNode = (Element)bookList.item(i);
				
				// Attribute
				String isbn = tempNode.getAttribute("isbn");
				String kind = tempNode.getAttribute("kind");
				
				// Context
				String title = hasTitle(tempNode);
				String author = hasAuthor(tempNode);
				String price = hasPrice(tempNode);
				
				System.out.printf("%s %5s %10s %10s %10s", isbn, kind, title, author, price);
				System.out.println();
			}
			
			
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	private String hasTitle(Element node) {
		if (node.getElementsByTagName("title").getLength() > 0) {
			return node.getElementsByTagName("title").item(0).getTextContent();
		}
		return "";
	}

	private String hasAuthor(Element node) {
		if (node.getElementsByTagName("author").getLength() > 0) {
			return node.getElementsByTagName("author").item(0).getTextContent();
		}
		return "";
	}
	
	private String hasPrice(Element node) {
		if (node.getElementsByTagName("price").getLength() > 0) {
			return node.getElementsByTagName("price").item(0).getTextContent();
		}
		return "";
	}

	private Element getRootElement(Document doc) {
		return doc.getDocumentElement();
	}
	
	private String getUrl(String url) {
		return getClass().getResource(url).toExternalForm();
	}
	
	
	
}
