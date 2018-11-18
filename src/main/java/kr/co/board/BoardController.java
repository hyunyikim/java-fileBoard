package kr.co.board;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDao dao; 
	
	@RequestMapping(value="/index.do")
	public String index(Model model) throws FileNotFoundException {
		dao.setBoardMap();
		List<BoardDto> boardList = dao.boardList();
		model.addAttribute("boardList", boardList);
		return "index";
	}
	
	@RequestMapping(value="/boardView.do")
	public String boardView(Model model, int seq) throws FileNotFoundException {
		BoardDto dto = dao.boardView(seq);
		model.addAttribute("dto", dao.boardView(seq));
		return "boardView";
	}

	@RequestMapping(value="/boardWrite.do", method=RequestMethod.GET)
	public String boardWriteView() {
		return "boardWrite";
	}
	
	@RequestMapping(value="/boardWrite.do", method=RequestMethod.POST)
	public String boardWrite(BoardDto dto) {
		dao.boardWrite(dto);
		dao.setBoardMap();
		return "redirect:/index.do";
	}
	
	
}
