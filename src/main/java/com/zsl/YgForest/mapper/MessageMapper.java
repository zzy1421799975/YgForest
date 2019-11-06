package com.zsl.YgForest.mapper;

import java.util.List;

import com.zsl.YgForest.entity.Message;

import priv.zsl.utils.mybatis.MyMapper;

public interface MessageMapper extends MyMapper<Message> {
	List<Message> selectByUid(Integer myId,Integer uId);
}