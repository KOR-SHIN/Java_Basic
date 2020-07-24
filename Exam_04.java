package z_exam;

public class Exam_04 {

	public static void main(String[] args) {

		//[5-1] 다음은 배열을 선언하거나 초기화한 것이다. 잘못된 것을 고르고 그 이유를 설명하시오.
		/*
			1. int[] arr[];
				대괄호는 데이터타입 또는 배열이름의 오른쪽 옆에 붙여야한다. (둘 다 붙이면 안된다)
				수정 : int[] arr;
			2. int[] arr = {1, 2, 3,};
				올바른 초기화
			3. int[] arr = new int[5];
				올바른 초기화
			4. int[] arr = new int[5]{1,2,3,4,5};
				배열의 선언과 초기화를 동시에 할 때는 우항에 있는 대괄호에 크기를 넣으면 안된다.
				수정 : int[] arr = new int[]{1,2,3,4,5};
			5. int[] arr[] = new int[3][];
				1번과 같은 이유로 잘못된 초기화이고, 2차원 배열을 선언하기 위해서는 좌항에서 
				데이터타입 또는 배열이름 오른쪽에 대괄호 2개를 입력한다 (둘 다 붙이면 안된다)
				수정 : int[][] arr = new int[3][]; 	
		*/
		
		//[5-2] 다음과 같은 배열에 있을 때, 물음에 답하시오.
		/*		int[][] arr = {
						{5,5,5,5,5},
						{10,10,10},
						{20,20,20,20},
						{30,30}};
				System.out.println(arr[3][1]);
			1. arr[3].length의 값은 얼마인가? 
				2차원 배열의 3행에 저장된 요소를 수량이기 때문에 2이다.
			2. arr.length의 값은 얼마인가?
				arr.length는 2차원 배열의 행의 길이를 의미하기 때문에 4이다.
			3. System.out.println(arr[4][1])의 출력 결과는 얼마인가?
				선언된 2차원 배열은 행의 길이가 4이기 때문에 행 인덱스는 0~3까지다.
				따라서 4행 1열의 값을 출력하라는 명령문은 error이다.
		*/
		
		//[5-3] 배열 arr에 담긴 모든 값을 더하는 프로그램을 작성하여라.
		int[] arr = {10, 20, 30, 40, 50};
		int sum = 0;
		
		for(int item : arr) {
			sum += item;
		}
		System.out.println("[5-3]정답");
		System.out.println("Sum = " + sum);
	
		//[5-4] 2차원 배열 arr에 담긴 모든 값의 총합과 평균을 구하는 프로그램을 완성하시오.
		int[][] arr2 = {
				{ 5, 8, 13, 5, 2 },
				{ 22, 13, 28 },
				{ 2, 18, 23, 62 }};
		int total = 0;
		float average = 0;
		float cnt = 0.0f;
		for(int i=0; i<arr2.length; i++) {
			for(int j=0; j<arr2[i].length; j++) {
				total += arr2[i][j];
				cnt++;
			}
		}
		average = (total/cnt);
		System.out.println("\n[5-4]정답");
		System.out.println("total = " + total);
		System.out.println("average = " + average);
		
		
		/*[5-5] 거스름돈을 몇 개의 동전으로 지불할 수 있는지 계산하는 문제이다. 변수 money의 금액을 동전으로 바꾸었을 때 각각 몇 개의 동전이
				필요한지 계산해서 출력하라. 
				(단, 가능한 한 적은 수의 동전으로 거슬러 주어야 한다)
		*/
		int[] coinUnit = { 500, 100, 50, 10 };
		int[] coinCount = { 0, 0, 0, 0 };
		int money = 2790;
		
		for(int i=0; i<coinUnit.length; i++) {
			coinCount[i] = (money / coinUnit[i]);
			money %= coinUnit[i];
			if(money == 0) {
				break;
			}
		}
		
		System.out.println("\n[5-5] 정답");
		System.out.println("500원 : " + coinCount[0]
				+ "\n100원 : " + coinCount[1]
				+ "\n50원 : " + coinCount[2]
				+ "\n10원 : " + coinCount[3]);
	
		//[5-6] 다음은 1과 9사이의 중복되지 않은 숫자로 이루어진 3자리 숫자를 만들어내는 프로그램을 작성하시오.
		int[] ballArr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] ball3 = new int[3];
		
		for(int i=0; i<ballArr.length; i++) {
			ballArr[i] = (int)(Math.random()*9+1);
			
			for(int j=0; j<i; j++) { //random 값 중복검사
				if(ballArr[j] == ballArr[i]) {
					i--;
				}
			}
		}
		
		for(int i=0; i<ball3.length; i++) {
			ball3[i] = ballArr[i];
		}
		
		System.out.println("\n[5-6]정답");
		for(int item : ball3) {
			System.out.println(item + "\t");
		}
		
		//[5-7] 배열 answer에 담긴 데이터를 읽고 각 숫자의 개수를 세어서 개수만큼 '*'을 찍는 프로그램을 작성하여라
		int[] answer = { 1, 4, 3, 2, 1, 2, 3, 2, 1, 4 };
		int[] counter = new int[4];
		
		
		for(int i=0; i<answer.length; i++) {
			counter[answer[i]-1] += 1;
		}
		
		System.out.println("\n[5-7] 정답");
		for(int i=0; i<counter.length; i++) {
			System.out.print((i+1) + " : " + counter[i] + "개 ");
			for(int j=0; j<counter[i]; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		
		/*[5-8] 문제 5-5에 동전의 개수를 추가한 프로그램이다. 커맨드라인으로부터 거슬러 줄 금액을 입력받아 계산한다.
		 		보유한 동전의 개수로 거스름돈을 지불할 수 없으면, '거스름돈이 부족합니다'라고 출력하고 종료한다.
		 		지불할 돈이 충분히 있ㅇ면, 거스름돈을 지불한 만큼 가진 돈에서 빼고 남은 동전의 개수를 화면에 출력한다.
		 */
		
		if(args.length != 1) {
			System.out.println("한 개의 숫자만 입력해 주세요.");
			System.exit(0);
		}
		
		System.out.println("\n[5-8] 정답");
		int cash = Integer.parseInt(args[0]);
		System.out.println("money = " + cash);
		//coinUnit[]은 5-5문제에 정의되어 있음
		int[] coin = {5, 5, 5, 5}; 
		
		
		for(int i=0; i<coinUnit.length; i++) {
			int coinNum = 0;
			
			coinNum = (cash/coinUnit[i]); //필요한 코인갯수
			if(coin[i] < coinNum) { // 필요한 코인갯수가 현재 가지고 있는 코인보다 많다면 배열안에 있는만큼 뺀다.
				coin[i] = 0;
			} else {
				coin[i] -= coinNum;
			}
			cash -=(coinUnit[i] * coinNum);
			System.out.println(coinUnit[i] + "원 : " + coinNum);
		}
		
		if(cash > 0) {
			System.out.println("거스름돈이 부족합니다.");
			System.exit(0);
		}
		
		
		System.out.println("=남은 동전의 개수 =");
		for(int i=0; i<coinUnit.length; i++) {
			System.out.println(coinUnit[i] + "원 : " + coin[i]);
		}
		
		//[5-9] 주어진 배열을 시계방향으로 90도 회전시켜서 출력하는 프로그램을 작성하시오.
		char[][] star = {
				{'*', '*', ' ', ' ', ' '},
				{'*', '*', ' ', ' ', ' '},
				{'*', '*', '*', '*', '*'},
				{'*', '*', '*', '*', '*'}};
		
		char[][] result = new char[star[0].length][star.length];
		
		System.out.println(""); //개행
		for(int i=0; i<star.length; i++) {
			for(int j=0; j<star[i].length; j++) {
				System.out.print(star[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		for(int i=0; i<star.length; i++) {
			for(int j=0; j<star[i].length; j++) { //꼭 다시풀어볼 것 (풀이방법이 비효율적임)
					if(i<2) {
						result[j][i+2] = star[i][j];
					} 
					else if (i==2) {
						result[j][i-1] = star[i][j];
					}
					else if (i==3) {
						result[j][i-3] = star[i][j];
					}
			}
		}
		System.out.println("[5-9] 정답");
		for(int i=0; i<result.length; i++) {
			for(int j=0; j<result[i].length; j++) {
				System.out.print(result[i][j]);
			}
			System.out.println();
		}
	
	}

}
