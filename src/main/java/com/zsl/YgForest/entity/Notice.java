package com.zsl.YgForest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "f_notice")
public class Notice  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "n_id")
	private Integer nId;

	@Column(name = "u_id")
	private Integer uId;

	@Column(name = "n_subject")
	private String nSubject;

	@Column(name = "n_filepath")
	private String nFilepath;

	@Column(name = "n_beemail")
	private Integer nBeemail;

	@Column(name = "n_besms")
	private Integer nBesms;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "n_time")
	private Date nTime;

	@Column(name = "n_content")
	private String nContent;

	/**
	 * @return n_id
	 */
	public Integer getnId() {
		return nId;
	}

	/**
	 * @param nId
	 */
	public void setnId(Integer nId) {
		this.nId = nId;
	}

	/**
	 * @return a_id
	 */
	public Integer getuId() {
		return uId;
	}

	/**
	 * @param aId
	 */
	public void setuId(Integer uId) {
		this.uId = uId;
	}

	/**
	 * @return n_subject
	 */
	public String getnSubject() {
		return nSubject;
	}

	/**
	 * @param nSubject
	 */
	public void setnSubject(String nSubject) {
		this.nSubject = nSubject;
	}

	/**
	 * @return n_filepath
	 */
	public String getnFilepath() {
		return nFilepath;
	}

	/**
	 * @param nFilepath
	 */
	public void setnFilepath(String nFilepath) {
		this.nFilepath = nFilepath;
	}

	/**
	 * @return n_beemail
	 */
	public Integer getnBeemail() {
		return nBeemail;
	}

	/**
	 * @param nBeemail
	 */
	public void setnBeemail(Integer nBeemail) {
		this.nBeemail = nBeemail;
	}

	/**
	 * @return n_besms
	 */
	public Integer getnBesms() {
		return nBesms;
	}

	/**
	 * @param nBesms
	 */
	public void setnBesms(Integer nBesms) {
		this.nBesms = nBesms;
	}

	/**
	 * @return n_time
	 */
	public Date getnTime() {
		return nTime;
	}

	/**
	 * @param nTime
	 */
	public void setnTime(Date nTime) {
		this.nTime = nTime;
	}

	/**
	 * @return n_content
	 */
	public String getnContent() {
		return nContent;
	}

	/**
	 * @param nContent
	 */
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	@Override
	public String toString() {
		return "Notice [nId=" + nId + ", uId=" + uId + ", nSubject=" + nSubject + ", nFilepath=" + nFilepath
				+ ", nBeemail=" + nBeemail + ", nBesms=" + nBesms + ", nTime=" + nTime + ", nContent=" + nContent + "]";
	}
}