package com.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.domain.User;

@Component
public interface UserDao {
	@Select({"select * from user where name = #{name}"})
	public User findByName(String name);
}
