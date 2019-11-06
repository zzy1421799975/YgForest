package com.zsl.YgForest.mapper;

import java.util.List;

import com.zsl.YgForest.entity.Image;

import priv.zsl.utils.mybatis.MyMapper;

public interface ImageMapper extends MyMapper<Image> {
	public List<Image> selectByPidDESC(Integer pId,Integer limit);
}