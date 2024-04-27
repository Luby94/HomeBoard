package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;
import com.board.user.domain.UserVo;
import com.board.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class HomeController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MenuMapper menuMapper;

	//  http://localhost:9090
	@RequestMapping("/")
	public String home() {
		
		return "home";
		
	}
	
	//  /loginForm
	@RequestMapping("/loginForm")
	public ModelAndView loginForm() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users/login");
		return mv;
		
	}
	
	// login (userid=sky, passwd=1234)
	@RequestMapping("/login")
	//public ModelAndView login( @Param String userid, @Param String passwd, HttpServletRequest request ) {
	public ModelAndView login( HttpServletRequest request ) {
		
		// a tag 로 오든, form tag 로 오든 request 로 받을 수 있음
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		// DB 에서 로그인 정보 가져올 것 (db 조작 = mapper 가 함)
		// userMapper.login( userid, passwd );
		UserVo userVo = userMapper.login( userid, passwd );	// TUSER 테이블을 조회하는 userMapper 에 login 함수를 줘서, 넘어온 userid, passwd 를 userVo 에 담음
		MenuVo menuVo = menuMapper.getMenu("MENU01");
		
		String loc = "";
		
		if( userVo != null ) {	// id 와 passwd 가 일치하면
			HttpSession session = request.getSession();
			session.setAttribute("login", userVo);
			session.setAttribute("menuVo", menuVo);
			session.setMaxInactiveInterval(30*60);	// 30분 동안 유지, 30분*60초
			loc = "redirect:/";
		} else {		// 아이디 비번 틀리면 다시 로그인 화면 → loginForm
			loc = "redirect:/loginForm";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName( loc );
		return mv;
		
	}
	
	@RequestMapping("/logout")
	public String logout( HttpSession session ) {
		
		session.invalidate();
		return "redirect:/loginForm";
		
	}
	
	
}










