package ExZip;

public class ScoreProgram {
	public static void main(String[] args) {
		
		//3. 변수 names를 선언하고 주변친구 6명의 이름으로 초기화 해주세요.
		String[] names = {"무진", "광진", "열호", "창만", "진욱", "대철"};
		
		
		//4. subjects 변수를 만들고 국, 영, 수, 사, 과, 오라클, 자바로 초기화 해주세요.
		String[] subjects = {"국어", "영어", "수학", "사회", "과학", "오라클", "자바"};
		
		
		//1. 6명의 7과목을 저장할 수 있는 score 배열을 생성해주세요.
		int[][] score = new int[names.length][subjects.length];
		

		//2. score 각방에 0~100사이의 임의의 정수의 값을 저장하여라.
		for(int name=0; name<score.length; name++) {
			for(int subject=0; subject<score[name].length; subject++) {
				score[name][subject] = (int)(Math.random()*101);
			}
		}
		
		
		
		//5. 사람 별 합계를 구하기 위한 공간을 확보하고, 0번에서 합계를 추가하여 출력해주세요.
		int[] totalScore = new int[names.length];
		
		for(int name=0; name<names.length; name++) {
			//사람별 합계 계산 후 저장
			for(int subject=0; subject<score[name].length; subject++) {
				totalScore[name] += score[name][subject];
			}
		}
		
		
		
		
		//6. 사람 별 평균을 구하고, 소수점 2번째 자리까지 표현한다.
		float[] scoreAvg = new float[names.length];
		
		for(int name=0; name<names.length; name++) {
			scoreAvg[name] = (int)((float)totalScore[name]/subjects.length * 100 + 0.5) / 100F;
		}
		
		
		
		//7. 과목합계를 구하고 출력하세요.
		int[] subjectTotal = new int[subjects.length];
		
		for(int sub=0; sub<subjects.length; sub++) { // 0~6
			for(int row = 0; row<names.length; row++) { //0~5
				subjectTotal[sub] += score[row][sub];
			}
		}

		
		
		//8. 과목평균을 구하고 출력하세요.
		float[] subjectAvg = new float[subjects.length];
		
		for(int index=0; index<subjectAvg.length; index++) {
			subjectAvg[index] = (int)((float)subjectTotal[index]/subjects.length * 100 + 0.5) / 100F;
		}
		
		
		
		//9. 사람 별 석차 (합계기준)
		// 사람별 합계 배열을 비교 -> 사람 이름순으로 랭크 배열에 등수를 저장
		int[] rank = new int[names.length];
		
		for(int loop=0; loop<totalScore.length; loop++) {
			int rankNumber = 6;
			int minScore = totalScore[loop];
			for(int index=0; index<totalScore.length; index++) {
				if(minScore > totalScore[index]) {
					rankNumber--;
				}
			}
			rank[loop] = rankNumber;
			
		}
		
		
		
		
		//0. 출력해주세요.
		System.out.println("\n<성적관리 프로그램>");
		for(int sub=0; sub<subjects.length; sub++) {
			//과목명 출력
			System.out.print("\t" + subjects[sub]);
		}
		System.out.print("\t합계");
		System.out.print("\t평균");
		System.out.println("\t석차");
		for(int name=0; name<score.length; name++) {
			//사람별 성적출력
			System.out.print(names[name] + " : \t");
			for(int subject=0; subject<score[name].length; subject++) {
				System.out.print(score[name][subject] + "\t");
			}
			System.out.print(totalScore[name] + "\t");
			System.out.print(scoreAvg[name] + "\t");
			System.out.println(rank[name]);
		}
		
		System.out.print("과목합계 :");
		for(int i=0; i<subjectTotal.length; i++) {
			System.out.print("\t" + subjectTotal[i]);
		}

		System.out.println();
		System.out.print("과목평균 :");
		for(int i=0; i<subjectAvg.length; i++) {
			System.out.print("\t" + subjectAvg[i]);
		}
		System.out.println();
		
		
		
		//출력결과를 정렬하시오.
		for(int commonIndex=0; commonIndex<totalScore.length; commonIndex++) {
			int minScore = totalScore[commonIndex];
			int minScoreIndex = commonIndex;
					
			for(int index = 0; index<totalScore.length; index++) {
				if(minScore > totalScore[index]) {
					minScore = totalScore[index];
					minScoreIndex = index;
					
					int tempMinScore = totalScore[index];
					totalScore[index] = totalScore[commonIndex];
					totalScore[commonIndex] = tempMinScore;
					
					float tempMinAvg = scoreAvg[commonIndex];
					scoreAvg[commonIndex] = scoreAvg[index];
					scoreAvg[index] = tempMinAvg;
					
					int tempRank = rank[index];
					rank[index] = rank[commonIndex];
					rank[commonIndex] = tempRank;
					
					String tempName = names[index];
					names[index] = names[commonIndex];
					names[commonIndex] = tempName;

					for(int col=0; col<subjects.length; col++) {
						int tempScore = score[minScoreIndex][col];
						score[minScoreIndex][col] = score[commonIndex][col];
						score[commonIndex][col] = tempScore;
					}
				}
			}
		}

		System.out.println();
		System.out.println("================================== sort 후 ========================================");
		System.out.println("<성적관리프로그램>");
		for(int sub=0; sub<subjects.length; sub++) {
			//과목명 출력
			System.out.print("\t" + subjects[sub]);
		}
		System.out.print("\t합계");
		System.out.print("\t평균");
		System.out.println("\t석차");
		
		for(int name=0; name<score.length; name++) {
			//사람별 성적출력
			System.out.print(names[name] + " : \t");
			
			for(int subject=0; subject<score[name].length; subject++) {
				System.out.print(score[name][subject] + "\t");
			}
			
			System.out.print(totalScore[name] + "\t");
			System.out.print(scoreAvg[name] + "\t");
			System.out.println(rank[name]);
		}
		
		System.out.print("과목합계 :");
		for(int i=0; i<subjectTotal.length; i++) {
			System.out.print("\t" + subjectTotal[i]);
		}
		
		System.out.println();
		System.out.print("과목평균 :");
		for(int i=0; i<subjectAvg.length; i++) {
			System.out.print("\t" + subjectAvg[i]);
		}
		System.out.println();
		
		
	}
}
