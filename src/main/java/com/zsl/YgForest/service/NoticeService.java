package com.zsl.YgForest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsl.YgForest.entity.Notice;
import com.zsl.YgForest.mapper.NoticeMapper;

@Service
public class NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;

	public boolean insert(Notice record) {
		record.setnBesms(0);
		record.setnTime(new Date());
		return noticeMapper.insert(record)>0;
	}
	public List<Notice> selectAll() {
		return noticeMapper.selectAll();
	}
	
	public Notice selectByNid(Integer nId) {
		return noticeMapper.selectByPrimaryKey(nId);
	}
	
	public PageInfo<Notice> selectAll(int pageNum, int pageSize) {
		String orderBy ="n_id" + " desc";
		PageHelper.startPage(pageNum, pageSize,orderBy);
		return new PageInfo<Notice>(noticeMapper.selectAll());
	}
}
