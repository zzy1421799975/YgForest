package com.zsl.YgForest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.entity.UserPiRelation;
import com.zsl.YgForest.mapper.UserPiRelationMapper;

@Service
public class UserPiRelationService {
	@Autowired
	private UserPiRelationMapper userPiRelationMapper;
	@Autowired
	private UserService userService;
	
	public List<UserPiRelation> selectUidByPid(Integer pId){
		UserPiRelation record=new UserPiRelation();
		record.setpId(pId);
		return userPiRelationMapper.select(record);
	}
	
	public List<User> selectUserByPid(Integer pId){
		List<UserPiRelation> list=selectUidByPid(pId);
		List<User> list2=new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			list2.add(userService.selectByPrimaryKey(list.get(i).getuId()));
		}
		return list2;
	}
}
