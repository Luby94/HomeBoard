package com.board.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;

@Controller
@RequestMapping("/Menus")
public class MenuController {

	// MyBatis 와 연결을 위해 -> @Autowired : 자동으로 연결 -> interface
	@Autowired
	private MenuMapper menuMapper;
	
	// 메뉴 목록 조회 : /Menus/List
	@RequestMapping("/List")
	public String list( Model model ) {		// Model import : springframework
		
		// 1. 조회해서 = mapper 가서 읽어오라 (select 실행 = .xml 에서)
		// -- menuMapper.getMenuList();  ->  돌려받아야함 -> ArrayList : 개수가 확정되지 않은 배열 -> ArrayList 의 부모 : List
		// -- List : ArrayList : ArrayList 를 던져주든, vector 를 던져주든, linkedList 를 던져주든 다 처리가능
		// ∴ 조회한 결과를 ArrayList 로 돌려준다
		List<MenuVo> menuList = menuMapper.getMenuList();
		
		// 2. 조회 결과를 Model 에 넘겨준다 -> .jsp 가 쓰려는걸 담는 공간 : Model
		model.addAttribute( "menuList", menuList );		// ( "$에 들어갈 이름", 변수 )
		
		return "menus/list";
	}
	
	
	// 메뉴 입력받는 화면 : /Menus/WriteForm
	@RequestMapping("/WriteForm")
	public String writeForm() {
		return "menus/write";		//  /WEB-INF/views/  +  menus/write  +  .jsp
	}
	
	// 메뉴 저장 : /Menus/Write  ->  저장 후 list.jsp 이동
	// http://localhost:9090/Menus/Write?menu_id=menu2&menu_name=Lupy&menu_seq=2
	@RequestMapping("/Write")
	public String write( MenuVo menuVo ) {		// 넘어온 data 를 db 에 저장(mapper 가 저장)
		
		menuMapper.insertMenu( menuVo );		// menuMapper : db 에 저장하는 담당 라이브러리와의 연결을 담당하는 인터페이스
		
		return "menus/list";		// 사용자 추가 후 목록 보여주어야 함
	}
	
	// 메뉴 삭제 : /Menus/Delete?MENU_ID=menu02
	@RequestMapping("/Delete")
	@ResponseBody
	public String delete( MenuVo menuVo, Model model ) {
		
		// MENU03 을 삭제
		menuMapper.deleteMenu( menuVo );
		
		String html  =  "<script>";
		       html  += "alert('삭제되었습니다');";
		       html  += "location.href='/Menus/List';";
		       html  += "</script>";
		return html;
		
		/*
		// 다시 조회해서 model 에 담는다
		//List<MenuVo> menuList = menuMapper.getMenuList();
		//model.addAttribute( "menuList", menuList );
		
		// 이동할 파일
		//return "menus/list";
				
		// 위 주석 : 아래 한줄로 대체 가능
		return "redirect:/Menus/List";
		*/
	}
	
	// 메뉴 입력받는 화면 2 :  /Menus/WriteForm2
	@RequestMapping("/WriteForm2")
	public String writeForm2() {
		return "menus/write2";		
	}
	
	@RequestMapping("/Write2")
	public String write2( MenuVo menuVo ) {
		
		// 저장
		menuMapper.insertMenuByName( menuVo );
		
		// 조회로 이동
		return "redirect:/Menus/List";
	}
	
	// 메뉴 수정 : /Menus/UpdateForm?menu_id=${ menu.menu_id }
	@RequestMapping("/UpdateForm")
	public String updateForm( MenuVo menuVo, Model model ) {
		
		System.out.println( "menuVo: " + menuVo );
		String menu_id = menuVo.getMenu_id();		// menuVo.getMenu_id() = ${ menu.menu_id }
		
		// 수정할 data 를 menu_id 로 조회
		MenuVo menu = menuMapper.getMenu( menu_id );
		
		// 조회한 data를 model 에 담음 -> .jsp 는 model 에 담긴걸 ${} 로 빼옴
		model.addAttribute( "menu", menu );
		
		return "menus/update";
	}
	
	//  /Menus/Update
	@RequestMapping("/Update")
	public String update( MenuVo menuVo ) {
		
		// 수정
		menuMapper.updateMenu( menuVo );
		
		// 수정 후 조회
		return "redirect:/Menus/List";
	}
	
	
	
	
	
}














