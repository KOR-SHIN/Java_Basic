package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02_FileTest {

	public static void main(String[] args) {
		
		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		if(f1.exists()) { // 내가 접근하려는 file이 존재하는지 확인
			System.out.println(f1.getAbsolutePath() + "은 존재합니다.");
		} else {
			System.out.println(f1.getAbsolutePath() + "은 없는 파일입니다.");
		
			try {
				if(f1.createNewFile()) { // 해당 file이 존재하지않으면 file을 생성
					System.out.println(f1.getAbsolutePath() + "파일이 생성되었습니다.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(f2.exists()) {
			System.out.println(f2.getAbsolutePath() + "은 존재합니다.");
		} else {
			System.out.println(f2.getAbsolutePath() + "은 없는 파일입니다.");
		}
		System.out.println("--------------------------------------------");
		
		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles(); // f3의 경로에 존재하는 모든 file과 dir을 File 객체로 반환
		for(File f : files) {
			System.out.print(f.getName() + " => ");
			if(f.isFile()) {
				System.out.println("파일");
			} else {
				System.out.println("디렉토리");
			}
		}
		System.out.println("--------------------------------------------");
		
		String[] strFiles = f3.list(); // f3의 경로에 존재하는 file과 dir의 이름만 String으로 반환
		for(String sfile : strFiles) {
			System.out.println(sfile);
		}
		System.out.println("--------------------------------------------");
		System.out.println();
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// 출력할 디렉토리의 정보를 갖는 File객체 생성
		File f4 = new File("d:/Z_desktopFile");
		
		displayFileList(f4);

	}

	// 지정된 디렉토리에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	private static void displayFileList(File dir) {
		System.out.println("[" + dir.getAbsolutePath() + "] Directory 내용");
		
		// 디렉토리 안의 모든 파일 목록 가져오기
		File[] files = dir.listFiles();
		
		// 하위 디렉토리 정보를 저장할 ArrayList 생성(File배열의 인덱스 저장)
		List<Integer> subDirList = new ArrayList<>();
		
		// 날짜를 출력하기 위한 형식 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for(int i=0; i<files.length; i++) {
			String attr = ""; // 파일의 속성(읽기, 쓰기, 히든, 디렉토리 구분)
			String size = ""; // 파일크기
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
				subDirList.add(i);
			} else {
				size = files[i].length() + "";
				attr += files[i].canRead() ? "R" : " "; 
				attr += files[i].canWrite() ? "W" : " "; 
				attr += files[i].isHidden() ? "H" : " "; 
			}
			
			System.out.printf("%s %5s %12s %s\n", sdf.format(new Date(files[i].lastModified())), attr
												, size , files[i].getName());
		}
		int dirCount = subDirList.size(); // 하위폴더의 개수
		int fileCount = files.length - dirCount; // 폴더안에 파일 개수
		
		System.out.println(fileCount + "개의 파일, " + dirCount + "개의 디렉토리");
		System.out.println();
		
		for(int i=0; i<subDirList.size(); i++) {
			// 하위폴더의 내용들도 출력하기 위해 현재 메서드를 재귀호출
			displayFileList(files[subDirList.get(i)]);
		}
		
	}
}
