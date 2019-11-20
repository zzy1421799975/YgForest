package com.zsl.YgForest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsl.YgForest.entity.Pi;
import com.zsl.YgForest.entity.UserPiRelation;
import com.zsl.YgForest.mapper.PiMapper;
import com.zsl.YgForest.mapper.UserPiRelationMapper;

import priv.zsl.utils.crypto.SimpleHashUtils;

@Service
public class PiService {
	@Autowired
	private PiMapper piMapper;

	@Autowired
	private LogService logService;
	@Autowired
	private UserPiRelationMapper userPiRelationMapper;

	public List<Pi> selectListByUid(Integer uId) {
		return piMapper.selectListByUid(uId);
	}

	public Pi selectByPid(Integer pId) {
		return piMapper.selectByPrimaryKey(pId);
	}

	public boolean update(Pi record, Integer uId) {
		if (record.getpPassword() != null&&record.getpPassword().length()>2) {
			record.setpPassword(SimpleHashUtils.toMd5Salt(String.valueOf(record.getpId()), record.getpPassword()));
		}
		boolean flag = piMapper.updateByPrimaryKeySelective(record) > 0;
		logService.insert(uId, 2, 1, "所修改的设备ID为" + record.getpId());
		return flag;
	}
	public boolean updateByPassword(Pi record, Integer uId) {
		if (record.getpPassword() != null&&record.getpPassword().length()>2) {
			record.setpPassword(SimpleHashUtils.toMd5Salt(String.valueOf(record.getpId()), record.getpPassword()));
		}
		System.out.println(record.toString());
		if(!record.getpPassword().equals(selectByPid(record.getpId()).getpPassword())) {
			return false;
		}
		
		boolean flag = piMapper.updateByPrimaryKeySelective(record) > 0;
		logService.insert(uId, 2, 1, "所修改的设备ID为" + record.getpId());
		return flag;
	}
	public boolean upAlive(Pi record) {
		return piMapper.updateByPrimaryKeySelective(record) > 0;
	}
	public boolean upNotAlive(Integer pId) {
		Pi record=new Pi();
		record.setpId(pId);
		record.setpBootstate(0);
		return piMapper.updateByPrimaryKeySelective(record) > 0;
	}
	public boolean changeSwitchState(Integer pId, Integer pSwitchstate) {
		Pi record = new Pi();
		record.setpId(pId);
		record.setpSwitchstate(pSwitchstate);
		record.setpStarttime(new Date());
		return piMapper.updateByPrimaryKeySelective(record) > 0;
	}

	public List<Pi> selectAll() {
		return piMapper.selectAll();
	}

	public Integer addPi(Integer uId, Integer pId, String pPassword) {
		if (uId == null || pPassword == null || pId == null) {
			return -1;
		}
		// 从数据库查得
		Pi record = selectByPid(pId);	
		if (record != null) {
			if(!record.getpPassword().equals(SimpleHashUtils.toMd5Salt(String.valueOf(pId), pPassword))) {
				System.out.println(SimpleHashUtils.toMd5Salt(String.valueOf(pId), pPassword));
				return -2;
			}
			
			
			UserPiRelation upr = new UserPiRelation();
			upr.setuId(uId);
			upr.setpId(pId);
			if (userPiRelationMapper.selectOne(upr) != null) {
				return -3;
			}
			upr.setUpBindingtime(new Date());
			upr.setUpVisual(0);
			upr.setUpVisualdata(0);
			upr.setUpVisualfrd(0);
			if (userPiRelationMapper.insert(upr) > 0) {
				logService.insert(uId, 2, 0, "添加的设备ID为" + pId);
				return 1;
			}
		}
		return -4;
	}

	public boolean remove(Integer uId, Integer pId) {
		if (pId == null) {
			return false;
		}
		logService.insert(uId, 2, 2, "删除的设备ID为" + pId);
		UserPiRelation record = new UserPiRelation();
		record.setuId(uId);
		record.setpId(pId);
		return userPiRelationMapper.delete(record) > 0;
	}
}
