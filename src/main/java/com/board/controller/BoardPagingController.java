package com.board.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.BoardPagingVo;
import com.board.domain.BoardVo;
import com.board.domain.Pagination;
import com.board.domain.PagingResponse;
import com.board.domain.SearchVo;
import com.board.mapper.BoardPagingMapper;
import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private BoardPagingMapper boardPagingMapper;
	
	// 게시물 목록 조회 : /Board/List?menu_id=MENU01
	@RequestMapping("/List")
	public ModelAndView list( int nowpage, BoardPagingVo boardPagingVo) {	// @RequestParam(value="nowpage")
		
		log.info( "boardPagingVo : {}", boardPagingVo );
		
		// 메뉴 목록 (페이지 상단)
		List<MenuVo> menuList = menuMapper.getMenuList();
		
		// 게시물 목록 (페이징)
			// 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null 을 담아 반환
		// count : 현재 Menu_id 의 데이터 총 자료수를 알려준다 - 페이지 번호 출력하기 위해
		// menu_id=MENU01&title=&writer=
			// title 과 writer 는 검색 기능에 필요한 자료
		int count = boardPagingMapper.count( boardPagingVo );
		PagingResponse<BoardPagingVo> response = null;
		if(count < 1) {
			response = new PagingResponse<>(Collections.emptyList(), null);
		}
		
		// SearchVo : 페이징을 위한 초기 설정값들 지정
		SearchVo searchVo = new SearchVo();
		searchVo.setPage(nowpage);
		searchVo.setPageSize(10);
		
		// Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params(in Pagination.java) 에 계산된 페이지 정보 저장
		Pagination pagination = new Pagination( count, searchVo );
		searchVo.setPagination(pagination);
		
		String menu_id  = boardPagingVo.getMenu_id();
		String title    = boardPagingVo.getTitle();
		String writer   = boardPagingVo.getWriter();
		int    offset   = searchVo.getOffset();
		int    pageSize = searchVo.getPageSize();
		
		// 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
		List<BoardPagingVo> list = boardPagingMapper.getBoardPagingList( menu_id, title, writer, offset, pageSize );
		response = new PagingResponse<>(list, pagination);
		
		System.out.println( "========response: " + response );
				
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);		// pagingmenus.jsp 가 사용
		mv.addObject("nowpage", nowpage);		// pagingmenus.jsp 가 사용, list.jsp 도 사용
		mv.addObject("menu_id", menu_id);		// list.jsp 가 사용
		mv.addObject("response", response);		// list.jsp 가 사용, data 출력을 위해 forEach-items 에 사용
		mv.addObject("searchVo", searchVo);
		mv.setViewName("boardpaging/list");
		return mv;
		
	}
	
	// 게시물 등록 : /Board/WriteForm?menu_id=${ menu_id }
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm( MenuVo menuVo ) {
		
		// 메뉴 목록 조회
		List<MenuVo> menuList = menuMapper.getMenuList();
		System.out.println( "========meniList" + menuList );
		
		// 넘어온 menu_id(?menu_id=MENU01) 처리
		String menu_id = menuVo.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);		// "" : jsp 에서 items=${} 곧 model 이름, 뒤 : List<MenuVo> menuList 의 변수 이름
		mv.addObject("menu_id", menu_id);
		mv.setViewName("board/write");
		return mv;
		
	}
	
	//  /Board/Write
	@RequestMapping("/Write")
	public ModelAndView write( BoardVo boardVo ) {
		
		// 게시글 저장 -> mapper 에 insert
		// 넘어온 값(Vo)에 담아서 Board 저장
		boardPagingMapper.insertBoard( boardVo );
		
		String menu_id = boardVo.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return mv;
		
	}
	
	// 게시글 읽기 : /Board/View?bno=1
	@RequestMapping("/View")
	public ModelAndView view( BoardVo boardVo ) {
		
		// 메뉴목록 조회 필요
		List<MenuVo> menuList = menuMapper.getMenuList();
		
		// 조회수 증가 (현재 bno 의 HIT = HIT + 1)
		boardPagingMapper.incHit( boardVo );
		
		// bno 로 조회한 게시글 정보 읽기
		BoardVo vo = boardPagingMapper.getBoard( boardVo );
		  // --Board : bno, title, content etc -> 한줄짜리 정보를 다 담고 있는 칼럼
		  // --메뉴별 bno=1 다 있음 -> 필요한 bno=1 들고오기 위해 menu_id (또는 menu_id 가 담겨있는 boardVo) 를 담아서 getBoard
		System.out.println( "========vo: " + vo );
		
		// vo.content 안의 '\n' (= enter) 을 '<br>' 로 변경
		String content = vo.getContent();
		if( content != null ) {
			content = content.replace("\n", "<br>");
			vo.setContent( content );
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList);
		mv.addObject("vo", vo);		// 여기 vo 는 넘어온 vo(= boardVo) 랑 다르다, boardMapper.getBoard( boardVo ); 임
									// view.jsp 에서 여기 vo 안에 있는 bno 를 꺼내 쓸 것
		mv.setViewName("/board/view");		// view.jsp 갈때 출력 필요, model 에 담아서 -> 윗줄 필요
		
		return mv;
		
	}
	
	// 삭제 : /Board/Delete?bno=3&menu_id=MENU01
	@RequestMapping("/Delete")
	public ModelAndView delete( BoardVo boardVo ) {		// bno 가지고 있는 애 : BoardVo
		
		// 게시글 삭제
		boardPagingMapper.deleteBoard( boardVo );	// delete 함수는 id="deleteBoard" 인 SQL 문으로 boardVo 를 가지고 가서 삭제할 것이다
		
		String menu_id = boardVo.getMenu_id();
		
		// 다시 조회
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		
		return mv;
		
	}
	
	
}
