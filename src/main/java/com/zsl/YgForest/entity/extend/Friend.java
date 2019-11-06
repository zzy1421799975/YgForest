package com.zsl.YgForest.entity.extend;

import java.io.Serializable;
import java.util.List;

import com.zsl.YgForest.entity.Message;
import com.zsl.YgForest.entity.User;

public class Friend implements Serializable {
	private static final long serialVersionUID = 1L;

	private User fri;
	private String lastMessage;
	private Integer noReadNum;

	public User getFri() {
		return fri;
	}

	public void setFri(User fri) {
		this.fri = fri;
	}

	public Integer getNoReadNum() {
		return noReadNum;
	}

	public void setNoReadNum(Integer noReadNum) {
		this.noReadNum = noReadNum;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

}
