package com.zsl.YgForest.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zsl.YgForest.entity.Pi;
import com.zsl.YgForest.service.ImageService;

import priv.zsl.utils.jackson.JsonUtils;
import priv.zsl.utils.sdk.RedisUtils;

@RestController
@RequestMapping("/api/image")
public class ApiImageController {
	@Autowired
	private ImageService imageService;

	@Value("${local.upload-path}")
	private String local_path;

	
	
	@PostMapping(value = "/upload")
	public Object uploadImage(HttpServletRequest request) {
		SimpleDateFormat adf2 = new SimpleDateFormat("yyyyMMdd");
		String path = local_path;
		String datePath = "/" + adf2.format(new Date()) + "/";
		Part part;
		try {
			part = request.getPart("image");
			String fileName = part.getSubmittedFileName();
			// or-id-ip-datefilename.jpg
			// cv-id-ip-datefilename-0.63.jpg
			String str[] = fileName.split("-");
			String i_imagepath = "/piimage/id_" + str[1] + datePath;
			String redisKey = str[1] + str[3];
			
			if (fileName.contains("or")) {
				System.out.println("or - redisKey:" + redisKey);
				File f = new File(path+i_imagepath);
				if (!f.exists()) {
					System.out.println("dir no exists,the path:" + i_imagepath + ",mkdir :" + f.mkdirs());
				}
				part.write(path + i_imagepath + fileName.substring(3, fileName.length()));
				RedisUtils.set(redisKey, true);
			} else {// 处理图
				redisKey += ".jpg";
				System.out.println("cv - redisKey:" + redisKey);
				part.write(path + i_imagepath + fileName.substring(3, fileName.length()));
				imageService.insert(path,redisKey, i_imagepath, str,fileName);
			}
			return JsonUtils.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.error(e.getMessage());
		}
	}
	
	
	@PostMapping(value = "/selectByPid")
	public Object selectByPid(@RequestBody Pi record) {
		return JsonUtils.ok(imageService.selectListByPid(record.getpId()));
	}

	@PostMapping(value = "/selectPageListByPid")
	public Object selectByPid(@RequestBody JSONObject jsonData) {
		Integer pageNum = jsonData.getInteger("pageNum");
		Integer pageSize = jsonData.getInteger("pageSize");
		Integer pId = jsonData.getInteger("pId");
		return JsonUtils.ok(imageService.selectListByPid(pId,pageNum,pageSize));
	}
	
	
	
	@PostMapping(value = "/selectListDesc")
	public Object selectByPidDesc(@RequestBody JSONObject jsonData) {
		Integer pId = jsonData.getInteger("pId");
		Integer limitNum = jsonData.getInteger("limitNum");
		System.out.println(imageService.selectByPidDesc(pId,limitNum));
		return JsonUtils.ok(imageService.selectByPidDesc(pId,limitNum));
	}
}
