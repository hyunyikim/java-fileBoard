package kr.co.board;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDao dao; 
	
	@RequestMapping("/index.do")
	public String index(Model model) throws FileNotFoundException {
		System.out.println("index Controller를 탔다");
		List<BoardDto> boardList = dao.boardList();
		model.addAttribute("boardList", boardList);
		return "index";
	}
	
	@RequestMapping("/boardView.do")
	public String boardView(Model model, String seq) throws FileNotFoundException {
		
		return "boardView";
	}
	
}
