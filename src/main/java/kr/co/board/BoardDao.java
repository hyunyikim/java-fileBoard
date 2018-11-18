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
	
	// 처음 boardList를 로드하면 static hashmap 에도 값을 넣어준다. 
	// 값을 뿌려줄 때는 TreeMap을 이용해 key 값으로 값을 내림차순으로 정렬한 후 dto list에 담는다. 
	
	public void setBoardMap() {
        try {
        	boardMap.clear();
            BufferedReader fr = new BufferedReader(new FileReader(fileName));
            String row = "";
            while((row = fr.readLine()) != null) {
            	
            	BoardDto dto = new BoardDto();
            	String[] rowArr = row.split("\t");
            	
            	String str = rowArr[0].toString();
            	int seq = 0;
            	
            	if(!isNumber(str)) {
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
	
	
	public boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException ne) {
			return false;
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


	public void boardWrite(BoardDto dto) {
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
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));
            fw.write(txt);
            fw.close();
		} catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}
	
	
}
