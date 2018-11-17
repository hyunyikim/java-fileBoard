package kr.co.board;

import java.util.HashMap;

public class BoardMap {
	
	// static 저장은 HashMap
	// list를 뿌려줄 때는 TreeMap을 이용하여 key값을 기준으로 정렬한 다음 dto 리스트에 담아서 넘겨준다. (key값의 역순으로 넘겨준다) 
	
	private static HashMap<String, BoardDto> boardMap;

	public static HashMap<String, BoardDto> getBoardList() {
		return boardMap;
	}

	public static void setBoardList(HashMap<String, BoardDto> boardList) {
		BoardMap.boardMap = boardMap;
	}
	
}
