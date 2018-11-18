package kr.co.board;

import java.io.FileNotFoundException;
import java.io.IOException;
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
	public String boardView(Model model, int seq) {
		BoardDto dto = dao.boardView(seq);
		model.addAttribute("dto", dao.boardView(seq));
		return "boardView";
	}

	@RequestMapping(value="/boardWrite.do", method=RequestMethod.GET)
	public String boardWriteView() {
		return "boardWrite";
	}
	
	@RequestMapping(value="/boardWrite.do", method=RequestMethod.POST)
	public String boardWrite(BoardDto dto) throws FileNotFoundException {
		dao.boardWrite(dto);
		return "redirect:/index.do";
	}
	
	@RequestMapping(value="/boardUpdate.do", method=RequestMethod.GET)
	public String boardUpdateView(Model model, int seq) {
		BoardDto dto = dao.boardUpdateView(seq);
		model.addAttribute("dto", dto);
		return "boardUpdate";
	}
	
	@RequestMapping(value="/boardUpdate.do", method=RequestMethod.POST)
	public String boardUpdate(BoardDto dto) throws FileNotFoundException {
		dao.boardUpdate(dto);
		return "redirect:/index.do";
	}
	
	@RequestMapping(value="/boardDelete.do")
	public String boardDelete(int seq) throws FileNotFoundException {
		dao.boardDelete(seq);
		return "redirect:/index.do";
	}
}
