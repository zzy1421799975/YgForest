package com.zsl.YgForest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsl.YgForest.entity.FeedBack;
import com.zsl.YgForest.mapper.FeedBackMapper;

@Service
public class FeedBackService {
	@Autowired
	private FeedBackMapper feedBackMapper;

	public boolean insert(FeedBack record) {
		record.setfTime(new Date());
		record.setfBeread(0);
		System.out.println(record.toString());
		return feedBackMapper.insert(record) > 0;
	}

	public List<FeedBack> selectByUid(Integer uId) {
		FeedBack record = new FeedBack();
		record.setuId(uId);
		return feedBackMapper.select(record);
	}
	public List<FeedBack> selectAll() {
		return feedBackMapper.selectAll();
	}
	public PageInfo<FeedBack> selectAll(int pageNum, int pageSize) {
		String orderBy ="f_id" + " desc";
		PageHelper.startPage(pageNum, pageSize,orderBy);
		return new PageInfo<FeedBack>(feedBackMapper.selectAll());
	}
	public FeedBack selectByPrimaryKey(Integer key) {
		return feedBackMapper.selectByPrimaryKey(key);
	}
}
