package com.czy.hadoop.dao;

import com.czy.hadoop.model.User;

import java.util.List;



/**
 * 操作user的dao接口
 */
public interface UserDao {
	User getOneUser();

	List<User> getAllUsers();
}
