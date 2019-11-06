package com.zsl.YgForest.mapper;

import java.util.List;

import com.zsl.YgForest.entity.Data;

import priv.zsl.utils.mybatis.MyMapper;

public interface DataMapper extends MyMapper<Data> {
	public List<Data> selectByPidDESC(Integer pId,Integer limit);
}