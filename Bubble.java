package Array_01;

public class Bubble {
	public static void main(String[] args) {
		
		//과제 : 버블정렬 과정이 나오도록 출력하고 마지막에 정렬결과를 출력해라.
		int ret[] = new int[]{5, 2, 3, 1, 4};
				
		//버블정렬은 인접한 인덱스끼리 비교하는것 (앞에값과 뒤에값을 비교하여 앞에값이 크면 자리를 이동함)
		System.out.println("before sorting");
		for(int item : ret) {
			System.out.print(item + "\t");
		}
		System.out.println("");
		System.out.println("");
				
		for(int p=1; p<ret.length; p++){
			System.out.println(p + "회전");
					
			for(int i=1; i<ret.length; i++) {
						
				if(ret[i-1] > ret[i]) {
					int temp = ret[i-1];
					ret[i-1] = ret[i];
					ret[i] = temp;
				}
						
				for(int k=0; k<ret.length; k++) {
					System.out.print(ret[k] + "\t");
				}
				System.out.println();
				
			}
		}
				
		System.out.println("\nAtfer Bubble Sorting");
		
		for(int item : ret) {
		System.out.print(item + "\t");
		}
		
	}
}
