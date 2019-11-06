package com.zsl.YgForest.mapper;

import java.util.List;

import com.zsl.YgForest.entity.Log;
import priv.zsl.utils.mybatis.MyMapper;

public interface LogMapper extends MyMapper<Log> {
	public List<Log> mySelectAll();
}