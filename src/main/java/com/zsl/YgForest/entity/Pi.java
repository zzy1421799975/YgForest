package com.zsl.YgForest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "f_pi")
public class Pi implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "p_id")
	private Integer pId;

	@Column(name = "p_name")
	private String pName;

	@Column(name = "p_remark")
	private String pRemark;

	@Column(name = "p_ipaddress")
	private String pIpaddress;

	@Column(name = "p_password")
	private String pPassword;

	@Column(name = "p_threshold")
	private Integer pThreshold;

	@Column(name = "p_delayed")
	private Integer pDelayed;

	@Column(name = "p_switchstate")
	private Integer pSwitchstate;

	@Column(name = "p_bootstate")
	private Integer pBootstate;
//经度
	@Column(name = "p_longitude")
	private Double pLongitude;
//纬度
	@Column(name = "p_latitude")
	private Double pLatitude;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "p_createtime")
	private Date pCreatetime;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "p_starttime")
	private Date pStarttime;
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "p_livetime")
	private Date pLivetime;

	/**
	 * @return p_id
	 */
	public Integer getpId() {
		return pId;
	}

	/**
	 * @param pId
	 */
	public void setpId(Integer pId) {
		this.pId = pId;
	}

	/**
	 * @return p_name
	 */
	public String getpName() {
		return pName;
	}

	/**
	 * @param pName
	 */
	public void setpName(String pName) {
		this.pName = pName;
	}

	/**
	 * @return p_remark
	 */
	public String getpRemark() {
		return pRemark;
	}

	/**
	 * @param pRemark
	 */
	public void setpRemark(String pRemark) {
		this.pRemark = pRemark;
	}

	/**
	 * @return p_ipaddress
	 */
	public String getpIpaddress() {
		return pIpaddress;
	}

	/**
	 * @param pIpaddress
	 */
	public void setpIpaddress(String pIpaddress) {
		this.pIpaddress = pIpaddress;
	}

	/**
	 * @return p_password
	 */
	public String getpPassword() {
		return pPassword;
	}

	/**
	 * @param pPassword
	 */
	public void setpPassword(String pPassword) {
		this.pPassword = pPassword;
	}

	/**
	 * @return p_threshold
	 */
	public Integer getpThreshold() {
		return pThreshold;
	}

	/**
	 * @param pThreshold
	 */
	public void setpThreshold(Integer pThreshold) {
		this.pThreshold = pThreshold;
	}

	/**
	 * @return p_delayed
	 */
	public Integer getpDelayed() {
		return pDelayed;
	}

	/**
	 * @param pDelayed
	 */
	public void setpDelayed(Integer pDelayed) {
		this.pDelayed = pDelayed;
	}

	/**
	 * @return p_switchstate
	 */
	public Integer getpSwitchstate() {
		return pSwitchstate;
	}

	/**
	 * @param pSwitchstate
	 */
	public void setpSwitchstate(Integer pSwitchstate) {
		this.pSwitchstate = pSwitchstate;
	}

	/**
	 * @return p_bootstate
	 */
	public Integer getpBootstate() {
		return pBootstate;
	}

	/**
	 * @param pBootstate
	 */
	public void setpBootstate(Integer pBootstate) {
		this.pBootstate = pBootstate;
	}

	/**
	 * @return p_longitude
	 */
	public Double getpLongitude() {
		return pLongitude;
	}

	/**
	 * @param pLongitude
	 */
	public void setpLongitude(Double pLongitude) {
		this.pLongitude = pLongitude;
	}

	/**
	 * @return p_latitude
	 */
	public Double getpLatitude() {
		return pLatitude;
	}

	/**
	 * @param pLatitude
	 */
	public void setpLatitude(Double pLatitude) {
		this.pLatitude = pLatitude;
	}

	/**
	 * @return p_createtime
	 */
	public Date getpCreatetime() {
		return pCreatetime;
	}

	/**
	 * @param pCreatetime
	 */
	public void setpCreatetime(Date pCreatetime) {
		this.pCreatetime = pCreatetime;
	}

	/**
	 * @return p_starttime
	 */
	public Date getpStarttime() {
		return pStarttime;
	}

	/**
	 * @param pStarttime
	 */
	public void setpStarttime(Date pStarttime) {
		this.pStarttime = pStarttime;
	}

	/**
	 * @return p_livetime
	 */
	public Date getpLivetime() {
		return pLivetime;
	}

	/**
	 * @param pLivetime
	 */
	public void setpLivetime(Date pLivetime) {
		this.pLivetime = pLivetime;
	}

	@Override
	public String toString() {
		return "Pi [pId=" + pId + ", pName=" + pName + ", pRemark=" + pRemark + ", pIpaddress=" + pIpaddress
				+ ", pPassword=" + pPassword + ", pThreshold=" + pThreshold + ", pDelayed=" + pDelayed
				+ ", pSwitchstate=" + pSwitchstate + ", pBootstate=" + pBootstate + ", pLongitude=" + pLongitude
				+ ", pLatitude=" + pLatitude + ", pCreatetime=" + pCreatetime + ", pStarttime=" + pStarttime
				+ ", pLivetime=" + pLivetime + "]";
	}
}