package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class T10_MapTest {

	/**
	 * <Map>
	 * key, value를 한 쌍으로 관리하는 객체
	 * key값은 중복을 허용하지 않고 순서가 없다 (Set특징)
	 * value값은 중복을 허용한다 (List의 특징)
	 * 
	 */
	
	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<>();

		//자료 추가 => put(key, value)
		map.put("name", "홍길동");
		map.put("add", "주소");
		map.put("tel", "전화번호");

		System.out.println("Map => " + map);
		
		// 자료 수정 => 데이터를 저장할 때 key값이 같으면 나중에 입력된 값을 저장한다.
		map.put("add", "대전");
		System.out.println();
		System.out.println("map.put(\"add\", \"대전\")");
		System.out.println("Map => " + map);
		
		// 자료 삭제 => remove(삭제할 key값)
		map.remove("tel");
		System.out.println();
		System.out.println("map.remove(\"tel\")");
		System.out.println("Map => " + map);
		
		// 자료 읽기 => get(key)
		System.out.println();
		System.out.println("System.out.println(\"add Value => \" + map.get(\"add\"));");
		System.out.println("add Value => " + map.get("add"));
		
		// keySet()메서드 이용
		// Map의 key값들을 Set형태로 반환한다.
		Set<String> keySet = map.keySet();
		
		System.out.println();
		System.out.println("Iterator를 이용하여 keySet 출력하기");
		
		Iterator<String> it = keySet.iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			System.out.println("key-" + key + ", value-" + map.get(key));
		}
		System.out.println("-------------------------------------------------------");
		
		// Set형의 데이터를 enhanced-for문을 사용하여 출력한다.
		System.out.println("enhanced-for문을 이용하여 출력하기");
		for(String key : keySet) {
			System.out.println("key-" + key + ", value-" + map.get(key));
		}
		System.out.println("-------------------------------------------------------");
		
		// value값을 읽어와 출력한다.
		System.out.println("values()메서드 이용하여 출력하기");
		for(String value : map.values()) {
			System.out.println("value : " + value);
		}
		System.out.println("-------------------------------------------------------");
		
		// Entry이용하기
		// Entry class는 Map안에 구현되어 있으며 key,value를 멤버변수로 가진다.
		// Map에서 Entry클래스들을 Set형식으로 저장한다.
		System.out.println("Entry 이용하여 출력하기");
		
		// Set의 Generic이 Map.Entry Type이다.
		// mapSet에 저장되는 각각의 요소들은 key-value쌍으로 이루어져있다
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		
		// 가져온 Entry객체들을 순서대로 처리하기 위해 Iterator를 사용한다.
		Iterator<Map.Entry<String, String>> iter = mapSet.iterator();
		
		while(iter.hasNext()) {
			// Iterator안에는 Entry타입의 객체가 들어있다.
			// entry변수는 Iterator안에 있는 Entry객체를 하나씩 읽어온다 (key, value의 쌍으로 이루어짐)
			Map.Entry<String, String> entry = iter.next();
			
			System.out.println("key : " + entry.getKey());
			System.out.println("value : " + entry.getValue());
			System.out.println();
		}
		
//		// Set을 Object Type으로 설정해서 while문에서 down-casting해서 사용하는 방법
//		Set mSet = map.entrySet();
//		Iterator iterator = mSet.iterator();
//		
//		while(iterator.hasNext()) {
//			Map.Entry<String, String> item = (Map.Entry<String, String>)iterator.next();
//			System.out.println("key : " + item.getKey());
//			System.out.println("value : " + item.getValue());
//			System.out.println();
//		}
		
		
	} 
}
