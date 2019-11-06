package com.zsl.YgForest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsl.YgForest.entity.VersionUpdate;
import com.zsl.YgForest.mapper.VersionUpdateMapper;

@Service
public class VersionUpdateService {

	@Autowired
	private VersionUpdateMapper versionUpdateMapper;
	
	public VersionUpdate selectNew() {
		List<VersionUpdate> list=versionUpdateMapper.selectAll();
		if(list.size()==0) {
			return null;
		}
		return list.get(list.size()-1);
	}
	
	
}
