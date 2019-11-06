package com.zsl.YgForest.entity.extend;

import java.io.Serializable;

import com.zsl.YgForest.entity.Pi;

public class Signal implements Serializable {
	private static final long serialVersionUID = 1L;
	private Pi record;
	private Object Infrared;
	private Object Flame;
	private Object Smoke;

	public Pi getRecord() {
		return record;
	}

	public void setRecord(Pi record) {
		this.record = record;
	}

	
	public Object getInfrared() {
		return Infrared;
	}

	public void setInfrared(Object infrared) {
		Infrared = infrared;
	}

	public Object getFlame() {
		return Flame;
	}

	public void setFlame(Object flame) {
		Flame = flame;
	}

	public Object getSmoke() {
		return Smoke;
	}

	public void setSmoke(Object smoke) {
		Smoke = smoke;
	}

	public boolean judge() {
		return this.Flame == null && this.Infrared == null && this.Smoke == null;
	}
}
