package com.zsl.YgForest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "f_log")
public class Log implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "l_id")
	private Integer lId;

	@Column(name = "u_id")
	private Integer uId;

	@Column(name = "l_subject")
	private String lSubject;

	@Column(name = "l_content")
	private String lContent;

	@Column(name = "l_type")
	private Integer lType;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "l_time")
	private Date lTime;

	/**
	 * @return l_id
	 */
	public Integer getlId() {
		return lId;
	}

	/**
	 * @param lId
	 */
	public void setlId(Integer lId) {
		this.lId = lId;
	}

	/**
	 * @return u_id
	 */
	public Integer getuId() {
		return uId;
	}

	/**
	 * @param uId
	 */
	public void setuId(Integer uId) {
		this.uId = uId;
	}

	/**
	 * @return l_subject
	 */
	public String getlSubject() {
		return lSubject;
	}

	/**
	 * @param lSubject
	 */
	public void setlSubject(String lSubject) {
		this.lSubject = lSubject;
	}

	/**
	 * @return l_content
	 */
	public String getlContent() {
		return lContent;
	}

	/**
	 * @param lContent
	 */
	public void setlContent(String lContent) {
		this.lContent = lContent;
	}

	/**
	 * @return l_type
	 */
	public Integer getlType() {
		return lType;
	}

	/**
	 * @param lType
	 */
	public void setlType(Integer lType) {
		this.lType = lType;
	}

	/**
	 * @return l_time
	 */
	public Date getlTime() {
		return lTime;
	}

	/**
	 * @param lTime
	 */
	public void setlTime(Date lTime) {
		this.lTime = lTime;
	}

	@Override
	public String toString() {
		return "Log [lId=" + lId + ", uId=" + uId + ", lSubject=" + lSubject + ", lContent=" + lContent + ", lType="
				+ lType + ", lTime=" + lTime + "]";
	}
}