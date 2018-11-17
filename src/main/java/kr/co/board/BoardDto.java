package kr.co.board;

import java.util.Date;

public class BoardDto {

	private int seq;
	private String title;
	private String writer;
	private String regdate;
	private String content;
	/*private int re_ref;
	private int re_lev;
	private int re_seq;*/
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "BoardDto [seq=" + seq + ", title=" + title + ", writer=" + writer + ", regdate=" + regdate
				+ ", content=" + content + "]";
	}
	
	public String toTxt() {
		return "\r\n" + seq + "\t" + title + "\t" + writer + "\t" + regdate + "\t" + content;
	}
	
	
}
