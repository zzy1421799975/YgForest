package com.zsl.YgForest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsl.YgForest.entity.Image;
import com.zsl.YgForest.entity.Message;
import com.zsl.YgForest.mapper.MessageMapper;

@Service
public class MessageService {
	@Autowired
	private MessageMapper messageMapper;

	public boolean insert(Message record) {
		record.setmBeread(0);
		record.setmBetrash(0);
		record.setmTime(new Date());
		return messageMapper.insert(record) > 0;
	}

	public boolean beTrash(Integer mId) {
		Message record = new Message();
		record.setmId(mId);
		record.setmBetrash(1);
		return messageMapper.updateByPrimaryKeySelective(record) > 0;
	}

	public boolean delete(Integer mId) {
		return messageMapper.deleteByPrimaryKey(mId) > 0;
	}

	public boolean update(Message record) {
		return messageMapper.updateByPrimaryKeySelective(record) > 0;
	}

	public Message selectByPrimaryKey(Integer mId) {

		return messageMapper.selectByPrimaryKey(mId);
	}

	public Message selectOne(Message record) {
		return messageMapper.selectOne(record);
	}

	// 已发送
	public PageInfo<Message> selectMessage(Integer mSender, Integer mReceiver, Integer mBeread, Integer mBetrash,
			int pageNum, int pageSize) {
		String orderBy = "m_id" + " desc";
		if (pageSize != 0) {
			PageHelper.startPage(pageNum, pageSize, orderBy);
		}
		Message record = new Message();
		record.setmSender(mSender);
		record.setmReceiver(mReceiver);
		record.setmBeread(mBeread);
		record.setmBetrash(mBetrash);
		record.setmType(0);
		return new PageInfo<Message>(messageMapper.select(record));
	}

	// 已发送
	public List<Message> selectList(Message record) {
		return messageMapper.select(record);
	}

	// 已发送
	public List<Message> selectMessage(Integer mSender, Integer mReceiver, Integer mBeread, Integer mBetrash) {
		Message record = new Message();
		record.setmSender(mSender);
		record.setmReceiver(mReceiver);
		record.setmBeread(mBeread);
		record.setmBetrash(mBetrash);
		return messageMapper.select(record);
	}

	public PageInfo<Message> selectListDesc(Integer myId, Integer uId, int pageNum, int pageSize) {
		String orderBy = "m_id" + " desc";
		PageHelper.startPage(pageNum, pageSize);

		return new PageInfo<Message>(messageMapper.selectByUid(myId, uId));
	}

}
