package com.board.menus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.menus.domain.MenuVo;

@Mapper
public interface MenuMapper {

	void insertMenu(MenuVo menuVo);

	
	// @Mapper : menuMapper.xml 에서 id 가 insertMenu 인 것을 찾아라
	// menuMapper.xml 에서 id="insertMenu" SQL 문을 찾아서 실행
	
	List<MenuVo> getMenuList();


	void deleteMenu(MenuVo menuVo);


	void insertMenuByName(MenuVo menuVo);


	MenuVo getMenu(String menu_id);


	void updateMenu(MenuVo menuVo);
	
}
