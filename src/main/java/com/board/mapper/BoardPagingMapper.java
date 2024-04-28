package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardPagingVo;
import com.board.domain.BoardVo;
import com.board.menus.domain.MenuVo;

@Mapper
public interface BoardPagingMapper {
	  
	 void incHit(BoardVo boardVo);
	  
	 BoardVo getBoard(BoardVo boardVo);
	 
	//----------------------------------------------------------- 

	int count(BoardPagingVo boardPagingVo);

	List<BoardPagingVo> getBoardPagingList(String menu_id, String title, String writer, int offset, int pageSize);

	void insertBoard(BoardPagingVo boardPagingVo);

	void deleteBoard(BoardPagingVo boardPagingVo);

	
	
}
