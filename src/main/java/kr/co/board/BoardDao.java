package kr.co.board;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BoardDao {
	
	private String fileName = "C:\\Users\\Hyunyi\\boardData.txt" ;
	private static HashMap<String, BoardDto> boardMap;
	
	public List<BoardDto> boardList() {
		
		// 처음 boardList를 로드하면 static hashmap 에도 값을 넣어준다. 
		// 값을 뿌려줄 때는 TreeMap을 이용해 key 값으로 값을 내림차순으로 정렬한 후 dto list에 담는다. 
		// 처음에 index로 진입할 때 hashmap 에 값을 입력하는 함수랑, 목록 보기로 이동할 때 보여주는 함수랑 따로 있어야 할 것 같다. 
		// --> map에 put하는 함수, list에 담는 함수 만들기 
		
        List<BoardDto> boardList = new ArrayList<BoardDto>();
        try{
            BufferedReader fr = new BufferedReader(new FileReader(fileName));
            String row = "";
            while((row = fr.readLine()) != null) {
            	
            	BoardDto dto = new BoardDto();
            	String[] rowArr = row.split("\\t");
            	dto.setSeq(rowArr[0]);
            	dto.setTitle(rowArr[1]);
            	dto.setWriter(rowArr[2]);
            	dto.setRegdate(rowArr[3]);
            	dto.setContent(rowArr[4]);
            	
            	String seq = rowArr[0];
            	boardMap.put(seq, dto);
            	
            	
            	boardList.add(dto); // 얘를 map에서 list에 담는 함수로 하나 빼기 
            }
            fr.close();
		} catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch(IOException ie) {
			ie.printStackTrace();
		}
		return boardList;
	}


	public String boardWrite() {
		
		// 1. 텍스트는 뒤가 아니라 앞에서 입력이 되어야 한다. 
		// 날짜 입력 포맷 
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String txt = "테스트입니다!!" ;
        String fileName = "C:\\Users\\Hyunyi\\test11.txt" ;
        try{
            BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));
            fw.write(txt);
            fw.flush();
            fw.close();
		} catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch(IOException ie) {
			ie.printStackTrace();
		}
		return "실행됐나";
	}
	
	
}
