package com.zsl.YgForest.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsl.YgForest.configuration.shiro.ShiroUtils;
import com.zsl.YgForest.entity.User;
import com.zsl.YgForest.mapper.UserMapper;

import priv.zsl.utils.crypto.SimpleHashUtils;
import priv.zsl.utils.sdk.RedisUtils;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LogService logService;

	public List<User> selectAll() {
		return userMapper.selectAll();
	}

	public User selectByTelephone(String uTelephone) {

		User record = new User();
		record.setuTelephone(uTelephone);
		return userMapper.selectOne(record);
	}

	public User selectByPrimaryKey(Integer uId) {
		return userMapper.selectByPrimaryKey(uId);
	}

	public User selectOne(User record) {
		return userMapper.selectOne(record);
	}

	public boolean insertReg(String u_telephone, String u_password) {
		User record = new User();
		record.setuTelephone(u_telephone);
		if (selectOne(record) != null) {
			return false;
		}
		record.setuPassword(SimpleHashUtils.toMd5Salt(u_telephone, u_password));
		record.setuMoney(0.0);
		record.setuFullname("forest");
		record.setuAboutme("");
		record.setuImagepath("https://www.xmhhs.top/img/avatar-2.jpg");
		record.setuBelogin(1);
		record.setuBeemail(1);
		record.setuLongitude(117.5714392262);
		record.setuLatitude(26.2046853329);
		record.setuRealmname(record.getuTelephone());
		record.setuRegtime(new Date());
		record.setuBeadmin(0);
		return userMapper.insert(record) > 0;
	}

	public User insert(String u_telephone, String u_password, String uUsername) {
		User record = new User();
		record.setuTelephone(u_telephone);
		if (selectOne(record) != null) {
			return null;
		}
		record.setuPassword(SimpleHashUtils.toMd5Salt(u_telephone, u_password));
		record.setuUsername(uUsername);
		record.setuFullname("forest");
		record.setuAboutme("");
		record.setuMoney(0.0);
		record.setuImagepath("https://www.xmhhs.top/img/avatar-2.jpg");
		record.setuBelogin(1);
		record.setuBeemail(1);
		record.setuLongitude(117.5714392262);
		record.setuLatitude(26.2046853329);
		record.setuRealmname(record.getuTelephone());
		record.setuRegtime(new Date());
		record.setuBeadmin(0);
		if (userMapper.insert(record) > 0) {
			return record;
		}
		return null;
	}

	public boolean updateByPrimaryKeySelective(User record, boolean api) {
		boolean reflag = true;
		if (record == null || record.getuId() == null) {
			return false;
		}
		if (record.getuPassword() != null && record.getuPassword().length() >= 1) {
			record.setuPassword(SimpleHashUtils.toMd5Salt(record.getuTelephone(), record.getuPassword()));
			reflag = false;
		} else {
			record.setuPassword(null);
		}
		if (record.getuImagepath() != null) {
			logService.insert(record.getuId(), 1, 1);
		} else if (record.getuPassword() != null) {
			logService.insert(record.getuId(), 1, 2);
		} else {
			logService.insert(record.getuId(), 1, 0);
		}
		boolean flag = userMapper.updateByPrimaryKeySelective(record) > 0;
		if (reflag && !api) {
			ShiroUtils.relogin();
		}
		return flag;
	}

	public boolean updateLoginTime(Integer uId) {
		User record = new User();
		record.setuId(uId);
		record.setuLastlogintime(new Date());
		return userMapper.updateByPrimaryKeySelective(record) > 0;
	}

	public List<User> selectFriend(Integer uId) {
		return userMapper.selectFriend(uId);
	}

	public List<User> selectFriending(Integer uId) {
		return userMapper.selectFriending(uId);
	}

	public List<User> selectByKey(String key) {
		return userMapper.selectByKey("%" + key + "%");
	}
}
