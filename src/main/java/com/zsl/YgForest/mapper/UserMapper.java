package com.zsl.YgForest.mapper;

import java.util.List;

import com.zsl.YgForest.entity.User;
import priv.zsl.utils.mybatis.MyMapper;

public interface UserMapper extends MyMapper<User> {
	public List<User> selectFriend(Integer uId);

	public List<User> selectFriending(Integer uId);
	
	public List<User> selectByKey(String key);
}