package com.board.user.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.user.domain.UserVo;

@Mapper
public interface UserMapper {

	List<UserVo> getUserList();

	void insertUser(UserVo userVo);

	// UserVo getUser(UserVo userVo);
	HashMap<String, Object> getUser( UserVo userVo );	// userMapper.xml 內 <resultMap id="getUserMap" type="java.util.HashMap">
											// type 이 HashMap 이기때문에 여기서도 UserVo 로 받을게 아니라 HashMap 으로 받아야함

	void updateUser(UserVo userVo);

	void deleteUser(UserVo userVo);

	UserVo login(String userid, String passwd);

}
