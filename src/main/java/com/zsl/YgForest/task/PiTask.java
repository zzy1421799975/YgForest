package com.zsl.YgForest.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zsl.YgForest.entity.Pi;
import com.zsl.YgForest.service.PiService;

@Component
public class PiTask {

	@Autowired
	PiService piService;

	//5S执行一次，搜索所有数据库设备，判断最新alive时间，与现在时间对比，差距太大则灭活
	@Scheduled(fixedRate = 5000)
	public void alive() {
		List<Pi> list = piService.selectAll();
		Date date = new Date();
		long nowtime = date.getTime();
		for (int i = 0; i < list.size(); i++) {
			if (nowtime - list.get(i).getpLivetime().getTime() > 10000 && list.get(i).getpBootstate() == 1) {
				System.out.println("灭活:" + list.get(i).getpId());
				piService.upNotAlive(list.get(i).getpId());
			}
		}
	}
}
