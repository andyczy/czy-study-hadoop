package com.czy.hadoop.dao.impl;

import com.czy.hadoop.dao.UserDao;
import com.czy.hadoop.dao.mybatis.MyBatisDao;
import com.czy.hadoop.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl extends MyBatisDao implements UserDao {

	public UserDaoImpl() {
		super(User.class.getName());
		// 此时命名空间为: com.beifeng.model.User
	}

	@Override
	public User getOneUser() {
		int id = 1;
		return super.get("getOneUser", id);
	}

	@Override
	public List<User> getAllUsers() {
		return super.getList("getAllUsers", null);
	}

}
