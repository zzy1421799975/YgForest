package com.zsl.YgForest.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsl.YgForest.entity.Relation;
import com.zsl.YgForest.mapper.RelationMapper;

@Service
public class RelationService {

	@Autowired
	private RelationMapper relationMapper;

	public boolean insert(Relation record) {
		Relation r1=new Relation();
		r1.setrReceiver(record.getrSender());
		r1.setrSender(record.getrReceiver());
		if(relationMapper.selectOne(record)!=null||relationMapper.selectOne(r1)!=null) {
			return false;
		}
		record.setrSendtime(new Date());
		record.setrBefriend(0);
		return relationMapper.insert(record) > 0;
	}

	
	public boolean agree(Integer rSender, Integer rReceiver) {
		return relationMapper.agree(rSender, rReceiver, new Date()) > 0;
	}

	public boolean notAgree(Integer rSender, Integer rReceiver) {
		Relation record = new Relation();
		record.setrSender(rSender);
		record.setrReceiver(rReceiver);
		return relationMapper.delete(record) > 0;
	}
}
