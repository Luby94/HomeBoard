package com.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVo {

	private int bno;
	private String menu_id;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	private int hit;
	
}
