package com.board.user.controller;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.user.domain.UserVo;
import com.board.user.mapper.UserMapper;

@Controller
@RequestMapping("/Users")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;

	// 사용자 목록
	@RequestMapping("/List")
	public ModelAndView list() {
		
		// 목록 조회
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
	
}
