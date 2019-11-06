package com.zsl.YgForest.mapper;

import java.util.List;

import com.zsl.YgForest.entity.Pi;

import priv.zsl.utils.mybatis.MyMapper;

public interface PiMapper extends MyMapper<Pi> {
	List<Pi> selectListByUid(Integer uId);
}