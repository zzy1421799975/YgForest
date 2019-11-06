package com.zsl.YgForest.mapper;

import java.util.Date;

import com.zsl.YgForest.entity.Relation;
import priv.zsl.utils.mybatis.MyMapper;

public interface RelationMapper extends MyMapper<Relation> {
	public int agree(Integer rSender,Integer rReceiver,Date rBindingtime);
}