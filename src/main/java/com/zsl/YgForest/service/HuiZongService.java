package com.zsl.YgForest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsl.YgForest.entity.HuiZong;
import com.zsl.YgForest.mapper.HuiZongMapper;

@Service
public class HuiZongService {
	@Autowired
	private HuiZongMapper huiZongMapper;
	
	public boolean insert(HuiZong record) {
		return huiZongMapper.insert(record)>0;
	}
	public boolean del(HuiZong record) {
		
		return huiZongMapper.deleteByPrimaryKey(record.gethId())>0;
	}
	
	public List<HuiZong> select(String num){
		HuiZong r=new HuiZong();
		r.sethNum(num);
		return huiZongMapper.select(r);
	}
}
