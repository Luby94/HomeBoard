package com.board.user.controller;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.user.domain.UserVo;
import com.board.user.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Users")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;

	// 사용자 목록
	@RequestMapping("/List")
	public ModelAndView list() {
		
		// 목록 조회 = db 조회 = db : usermapper 한테 시킴 -> getUserList(= interface, 해당 id 의 xml 실행)
		List<UserVo> userList = userMapper.getUserList();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("userList", userList);
		mv.setViewName("users/list");
		
		// .jsp 이동
		return mv;
	}
	
	// 사용자 추가
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm() {
		
		ModelAndView mv = new ModelAndView();
		LocalDateTime today = LocalDateTime.now();
		
		String now = today.format( DateTimeFormatter.ofPattern( "yyyy-mm-dd hh:mm:ss:SSSS" ) );
		DayOfWeek wkday = today.getDayOfWeek();
		now  +=  " " + wkday;
		
		mv.addObject( "now", now );
		mv.setViewName("users/write");
				
		return mv;
		
	}
	
	@RequestMapping("/Write")
	public ModelAndView write( UserVo userVo ) {		// 넘어온 정보 -> UserVo
		
		// 저장 : mapper 에 insert user
		userMapper.insertUser( userVo );
		
		// 데이터를 가지고 이동
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return mv;
		
	}
	
	// http://localhost:9090/Users/View?userid=lupy
	@RequestMapping("/View")
	public ModelAndView view( UserVo userVo ) {
		
		// db 에서 userid=lupy 조회
		// 조회(SQL문 : SELECT) : lupy 인 id 를 들고가서 한줄을 가져옴 -> 어디에 담아서? userVo
		
		// UserVo vo = userMapper.getUser( userVo );		// userMapper 안에 있는 getUser 를 들고올건데 userVo 를 담아서 들고 올거야
		// System.out.println( "==============vo: " + vo );
		
		HashMap<String, Object> map = userMapper.getUser( userVo );		// UserMapper.java 수정
		
		log.info( "map : {}" + map );
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", map);
		
		mv.setViewName("/users/view");
		return mv;
				
	}
	
	// http://localhost:9090/Users/UpdateForm?userid=zoro
	@RequestMapping("/UpdateForm")
	public ModelAndView updateForm( UserVo userVo ) {
		
		// 아이디(userid)로 수정할 한명의 data 를 조회 -> getView
		// userMapper.getUser( userVo );  <-  getUser 에 F2 누르면 HashMap<String, Object> 앞에 나옴 -> 복붙 + map 생성
		HashMap<String, Object> map = userMapper.getUser( userVo );
		
		// 수정할 data 를 model 에 담는다
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", map);
		mv.setViewName("users/update");
		return mv;
		
	}
	
	
	// 수정
	@RequestMapping("/Update")
	public ModelAndView update( UserVo userVo ) {
		
		userMapper.updateUser( userVo );
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return mv;
		
	}
	
	// 삭제
	@RequestMapping("/Delete")
	public ModelAndView delete( UserVo userVo ) {
		
		userMapper.deleteUser( userVo );
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		
		return mv;
		
	}
	
	
}









