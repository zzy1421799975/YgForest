package com.zsl.YgForest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsl.YgForest.entity.Data;
import com.zsl.YgForest.mapper.DataMapper;

@Service
public class DataService {
	@Autowired
	private DataMapper dataMapper;

	public boolean insert(Data record) {
		record.setdTime(new Date());
		
		return dataMapper.insert(record) > 0;
	} 
	public PageInfo<Data> selectByPid(Integer pId,int pageNum, int pageSize) {
		String orderBy ="d_id" + " desc";
		PageHelper.startPage(pageNum, pageSize,orderBy);
		Data record=new Data();
		record.setpId(pId);
		return new PageInfo<Data>(dataMapper.select(record));
	}
	
	public List<Data> selectByPidDesc(Integer pId,Integer limit) {
		return dataMapper.selectByPidDESC(pId,limit);
	}
}
