package com.zsl.YgForest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zsl.YgForest.entity.Log;
import com.zsl.YgForest.mapper.LogMapper;

import priv.zsl.utils.sdk.RedisUtils;

@Service
public class LogService {
	// 个人信息
	static String msg1[][] = { { "修改个人信息", null }, { "修改头像", null }, { "修改密码", "若非本人操作，请立即修改密码，以免帐号受损！" } };
	// 设备信息
	static String msg2[][] = { { " 绑定了新设备", null }, { "修改设备信息", null }, { "删除绑定设备", null } };
	// 好友信息
	static String msg3[][] = {};

	@Autowired
	private LogMapper logMapper;

	public List<Log> selectByUid(Integer uId) {
		Log record = new Log();
		record.setuId(uId);

		String orderBy ="l_id" + " desc";
		PageHelper.startPage(999999, 20,orderBy);
		
		@SuppressWarnings("unchecked")
		List<Log> loglist = (List<Log>) RedisUtils.get("LogListByUid:" + uId);
		if (loglist == null) {
			loglist = logMapper.select(record);
			RedisUtils.set("LogListByUid" + uId, loglist);
		}
		return loglist;
	}

	public boolean insert(Integer uId, Integer lType, Integer code) {
		RedisUtils.delete("LogListByUid:" + uId);

		Log record = new Log();
		record.setuId(uId);
		record.setlType(lType);
		record.setlTime(new Date());
		if (lType == 1) {
			record.setlSubject(msg1[code][0]);
			record.setlContent(msg1[code][1]);
		} else if (lType == 2) {
			record.setlSubject(msg2[code][0]);
			record.setlContent(msg2[code][1]);
		} else {
			record.setlSubject(msg3[code][0]);
			record.setlContent(msg3[code][1]);
		}
		System.out.println("insert:" + record.toString());

		return logMapper.insertSelective(record) > 0;
	}

	public boolean insert(Integer uId, Integer lType, Integer code, String msg) {
		RedisUtils.delete("LogListByUid:" + uId);
		Log record = new Log();
		record.setuId(uId);
		record.setlType(lType);
		record.setlTime(new Date());
		if (lType == 1) {
			record.setlSubject(msg1[code][0]);
			record.setlContent(msg);
		} else if (lType == 2) {
			record.setlSubject(msg2[code][0]);
			record.setlContent(msg);
		} else {
			record.setlSubject(msg3[code][0]);
			record.setlContent(msg);
		}
		System.out.println("insert:" + record.toString());

		return logMapper.insertSelective(record) > 0;
	}

	public List<Log> mySelectAll() {
		return logMapper.mySelectAll();
	}
}
