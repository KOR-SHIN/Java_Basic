package Array_01;

public class Select {

	public static void main(String[] args) {

		int ret[] = new int[]{ 5, 2, 3, 1, 4 };
		
		System.out.println("---before sort---");
		for(int item : ret) {
			System.out.print(item + "\t");
		}
		System.out.println();
			
		for(int i=0; i<ret.length-1; i++) {
			System.out.println((i+1) + "회전");
			int min = ret[i]; //i번째 요소를 기준으로 지정한다
			int index = i; //정렬 후 기준요소의 인덱스를 이동시킨다.
				
			for(int j=i+1; j<ret.length; j++) {
				if(min > ret[j]) {
					index = j;
					min = ret[j];
						
					int temp = ret[i];
					ret[i] = ret[index];
					ret[index] = temp;
				}
				
				for(int k=0; k<ret.length; k++) {
					System.out.print(ret[k] + "\t");
				}
				System.out.println();
			}	
		}
	}	
}
