package com.zsl.YgForest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsl.YgForest.entity.HuiZong;
import com.zsl.YgForest.service.HuiZongService;

@Controller
@RequestMapping(value = "/api")
public class HuiZongController {
	@Autowired
	private HuiZongService hs;

	@RequestMapping("/init")
	public String init(String num, ModelMap model, HuiZong record) {
		System.out.println(record.toString());
		HuiZong r=new HuiZong ();
		if (record.judge()) {
			hs.insert(record);
			model.addAttribute("list", hs.select(record.gethNum()));
			r=record;
		} else {
			model.addAttribute("list", hs.select(num));
		}
		 
		model.addAttribute("record",r);

		return "hzb.html";
	}

	@RequestMapping("/del")
	public String init(ModelMap model, HuiZong record) {
		hs.del(record);
		HuiZong r=new HuiZong ();
		model.addAttribute("list", hs.select(record.gethNum()));
		model.addAttribute("record",r);
		return "hzb.html";
	}
}
