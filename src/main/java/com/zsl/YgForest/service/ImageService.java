package com.zsl.YgForest.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsl.YgForest.entity.Image;
import com.zsl.YgForest.mapper.ImageMapper;

import priv.zsl.utils.sdk.RedisUtils;

@Service
public class ImageService {
	@Autowired
	private ImageMapper imageMapper;

	@Async
	public void insert(String path, String redisKey, String i_imagepath, String str[], String fileName)
			throws IOException, InterruptedException {
		int k = 0;
		while (k < 20) {
			if (RedisUtils.get(redisKey) != null) {
				RedisUtils.delete(redisKey);
				insert(i_imagepath, str);
				System.out.println("insert sql redisKey:" + redisKey);
				return;
			}
			Thread.sleep(1000);
			k++;
		}
		File f = new File(path + i_imagepath + fileName.substring(3, fileName.length()));
		System.out.println("not or image,delete:" + f.delete());
	}

	public boolean insert(String path, String str[]) {
		Integer pId = Integer.valueOf(str[1]);
		Image record = new Image();
		record.setpId(pId);
		record.setiDensity(Double.valueOf(str[4].substring(0, 4)));
		record.setiTime(new Date());
		record.setiImagepath(path + str[1] + "-" + str[2] + "-" + str[3]);
		boolean flag = imageMapper.insert(record) > 0;
		RedisUtils.delete("ImageListByPid" + pId);
		return flag;
	}

	public List<Image> selectListByPid(Integer pId) {

		@SuppressWarnings("unchecked")
		List<Image> imagelist = (List<Image>) RedisUtils.get("ImageListByPid" + pId);
		if (imagelist == null) {
			Image record = new Image();
			record.setpId(pId);
			imagelist = imageMapper.select(record);
			RedisUtils.set("ImageListByPid" + pId, imagelist);
		}
		return imagelist;
	}

	public PageInfo<Image> selectListByPid(Integer pId, int pageNum, int pageSize) {
		String orderBy = "i_id" + " desc";
		PageHelper.startPage(pageNum, pageSize, orderBy);

		Image record = new Image();
		record.setpId(pId);
		return new PageInfo<Image>(imageMapper.select(record));
	}

	public List<Image> selectByPidDesc(Integer pId, Integer limit) {
		return imageMapper.selectByPidDESC(pId, limit);
	}

}
