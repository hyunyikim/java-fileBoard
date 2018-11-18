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
	
	BufferedWriter fw = null;
	BufferedReader fr = null;
	
	// boardMap을 초기화하고 파일 데이터를 읽어와 boardMap에 넣어주는 메서드   
	public void setBoardMap() {
        try {
        	boardMap.clear();
            fr = new BufferedReader(new FileReader(fileName));
            String row = "";
            while((row = fr.readLine()) != null) {
            	// System.out.println("txt 가 null이 아니다"); 처음에 아무 데이터도 없을 때 해결  
            	
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
	
	// 숫자 체크 메서드 
	public boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException ne) {
			return false;
		}
	}
	
	// map에 담긴 리스트를 역순으로 정렬하는 메서드 
	public List<BoardDto> boardList() {
		List<BoardDto> boardList = new ArrayList<BoardDto>();
		TreeMap<Integer, BoardDto> treeMap = new TreeMap<Integer, BoardDto>(Collections.reverseOrder());
		treeMap.putAll(boardMap);
		Iterator<Integer> mapKey = treeMap.keySet().iterator();
		while(mapKey.hasNext()) {
			int key = mapKey.next();
			boardList.add(boardMap.get(key));
		}
		return boardList;
	}
	
	// 글 상세보기 메서드 
	public BoardDto boardView(int seq) {
		BoardDto dto = new BoardDto();
		dto = boardMap.get(seq);
		return dto;
	}

	// 글쓰기 메서드 
	public void boardWrite(BoardDto dto) {
		TreeMap<Integer, BoardDto> treeMap = new TreeMap<Integer, BoardDto>(boardMap);
		Iterator<Integer> mapKey = treeMap.keySet().iterator();
		int key = 0;
		while(mapKey.hasNext()) {
			key = mapKey.next();
		}
		dto.setSeq(key + 1);
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dto.setRegdate(sdf.format(today));
		
		String txt = dto.toTxt();
        try {
            fw = new BufferedWriter(new FileWriter(fileName, true));
            fw.write(txt);
            fw.close();
		} catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}
	
	// 글 수정 view 메서드 
	public BoardDto boardUpdateView(int seq) {
		BoardDto dto = new BoardDto();
		dto = boardMap.get(seq);
		return dto;
	}
	
	// 글 수정 메서드
	public void boardUpdate(BoardDto dto) {
		boardMap.remove(dto.getSeq());
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dto.setRegdate(sdf.format(today));
		
		boardMap.put(dto.getSeq(), dto);
		mapToFileData();
	}
	
	// 글 삭제 메서드
	public void boardDelete(int seq) {
		boardMap.remove(seq);
		mapToFileData();
	}
	
	// map을 파일 데이터에 덮어쓰는 메서드
	public void mapToFileData() {
		try {
			fw = new BufferedWriter(new FileWriter(fileName));
			Iterator<Integer> mapKey = boardMap.keySet().iterator();
			String txt = "";
			while(mapKey.hasNext()) {
				txt += boardMap.get(mapKey.next()).toTxt();
			}
			fw.write(txt);
			fw.close();
		} catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
