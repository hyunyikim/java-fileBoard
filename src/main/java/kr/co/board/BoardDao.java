package kr.co.board;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class BoardDao {
	
	private String fileName = "C:\\Users\\Hyunyi\\boardData.txt" ;
	private static HashMap<Integer, BoardDto> boardMap = new HashMap<Integer, BoardDto>();
	
	public void setBoardMap() {
		
		// 처음 boardList를 로드하면 static hashmap 에도 값을 넣어준다. 
		// 값을 뿌려줄 때는 TreeMap을 이용해 key 값으로 값을 내림차순으로 정렬한 후 dto list에 담는다. 
		// 처음에 index로 진입할 때 hashmap 에 값을 입력하는 함수랑, 목록 보기로 이동할 때 보여주는 함수랑 따로 있어야 할 것 같다. 
		// --> map에 put하는 함수, list에 담는 함수 만들기 
		
        List<BoardDto> boardList = new ArrayList<BoardDto>();
        try {
            BufferedReader fr = new BufferedReader(new FileReader(fileName));
            String row = "";
            while((row = fr.readLine()) != null) {
            	
            	BoardDto dto = new BoardDto();
            	String[] rowArr = row.split("\t");
            	
            	String str = rowArr[0].toString();
            	int seq = 0;
            	if(str.length() == 2) {		// 메모장 txt 파일의 첫 번째 공백을 제거해 seq 로 받는다
            		seq = (int)str.charAt(1) - 48;
            	} else {
            		seq = Integer.parseInt(rowArr[0]);
            	}
            	dto.setSeq(seq);
            	dto.setTitle(rowArr[1]);
            	dto.setWriter(rowArr[2]);
            	dto.setRegdate(rowArr[3]);
            	dto.setContent(rowArr[4]);
            	boardMap.put(seq, dto);
            }
            fr.close();
		} catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}
	
	
	public List<BoardDto> boardList() {
		List<BoardDto> boardList = new ArrayList<BoardDto>();
		TreeMap<Integer, BoardDto> treeMap = new TreeMap<Integer, BoardDto>(Collections.reverseOrder());
		treeMap.putAll(boardMap);
		Iterator<Integer> iteratorKey = treeMap.keySet().iterator();
		while(iteratorKey.hasNext()) {
			int key = iteratorKey.next();
			boardList.add(boardMap.get(key));
		}
		return boardList;
	}
	
	
	public BoardDto boardView(int seq) {
		BoardDto dto = new BoardDto();
		dto = boardMap.get(seq);
		return dto;
	}


	public String boardWrite(BoardDto dto) {
		
		// 글 쓰고 나서 맵에 한 번 담기!!!
		TreeMap<Integer, BoardDto> treeMap = new TreeMap<Integer, BoardDto>(boardMap);
		Iterator<Integer> iteratorKey = treeMap.keySet().iterator();
		int key = 0;
		while(iteratorKey.hasNext()) {
			key = iteratorKey.next();
		}
		dto.setSeq(key + 1);
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dto.setRegdate(sdf.format(today));
		
		String txt = dto.toTxt();
		System.out.println("정제한 txt : " + txt);
        try {
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
