package com.zsl.YgForest.entity;

import javax.persistence.*;

@Table(name = "f_huizong")
public class HuiZong {
	@Id
	@Column(name = "h_id")
	private Integer hId;

	@Column(name = "h_vxhao")
	private Integer hVxhao;

	@Column(name = "h_num")
	private String hNum;

	@Column(name = "h_name")
	private String hName;

	@Column(name = "h_project")
	private String hProject;

	@Column(name = "h_danwei")
	private String hDanwei;

	@Column(name = "h_jibie")
	private String hJibie;

	@Column(name = "h_time")
	private String hTime;

	@Column(name = "h_xuefen")
	private Integer hXuefen;

	/**
	 * @return h_id
	 */
	public Integer gethId() {
		return hId;
	}

	/**
	 * @param hId
	 */
	public void sethId(Integer hId) {
		this.hId = hId;
	}

	/**
	 * @return h_vxhao
	 */
	public Integer gethVxhao() {
		return hVxhao;
	}

	/**
	 * @param hVxhao
	 */
	public void sethVxhao(Integer hVxhao) {
		this.hVxhao = hVxhao;
	}

	/**
	 * @return h_num
	 */
	public String gethNum() {
		return hNum;
	}

	/**
	 * @param hNum
	 */
	public void sethNum(String hNum) {
		this.hNum = hNum;
	}

	/**
	 * @return h_name
	 */
	public String gethName() {
		return hName;
	}

	/**
	 * @param hName
	 */
	public void sethName(String hName) {
		this.hName = hName;
	}

	/**
	 * @return h_project
	 */
	public String gethProject() {
		return hProject;
	}

	/**
	 * @param hProject
	 */
	public void sethProject(String hProject) {
		this.hProject = hProject;
	}

	/**
	 * @return h_danwei
	 */
	public String gethDanwei() {
		return hDanwei;
	}

	/**
	 * @param hDanwei
	 */
	public void sethDanwei(String hDanwei) {
		this.hDanwei = hDanwei;
	}

	/**
	 * @return h_jibie
	 */
	public String gethJibie() {
		return hJibie;
	}

	/**
	 * @param hJibie
	 */
	public void sethJibie(String hJibie) {
		this.hJibie = hJibie;
	}

	/**
	 * @return h_time
	 */
	public String gethTime() {
		return hTime;
	}

	/**
	 * @param hTime
	 */
	public void sethTime(String hTime) {
		this.hTime = hTime;
	}

	/**
	 * @return h_xuefen
	 */
	public Integer gethXuefen() {
		return hXuefen;
	}

	/**
	 * @param hXuefen
	 */
	public void sethXuefen(Integer hXuefen) {
		this.hXuefen = hXuefen;
	}

	@Override
	public String toString() {
		return "HuiZong [hId=" + hId + ", hVxhao=" + hVxhao + ", hNum=" + hNum + ", hName=" + hName + ", hProject="
				+ hProject + ", hDanwei=" + hDanwei + ", hJibie=" + hJibie + ", hTime=" + hTime + ", hXuefen=" + hXuefen
				+ "]";
	}

	public boolean judge() {
		return this.hNum != null && this.hName != null && this.hVxhao != null && this.hProject != null
				&& this.hVxhao != null && this.hDanwei != null;
	}

}