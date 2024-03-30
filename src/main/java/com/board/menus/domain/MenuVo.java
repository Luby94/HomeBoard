package com.board.menus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo {

	// Fields
	private String menu_id;
	private String menu_name;
	private int menu_seq;
	
}
