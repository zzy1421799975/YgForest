package com.zsl.YgForest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "f_feedback")
public class FeedBack  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "f_id")
    private Integer fId;

    @Column(name = "u_id")
    private Integer uId;

    @Column(name = "f_subject")
    private String fSubject;

    @Column(name = "f_beread")
    private Integer fBeread;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "f_time")
    private Date fTime;

    @Column(name = "f_content")
    private String fContent;

    /**
     * @return f_id
     */
    public Integer getfId() {
        return fId;
    }

    /**
     * @param fId
     */
    public void setfId(Integer fId) {
        this.fId = fId;
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
     * @return f_subject
     */
    public String getfSubject() {
        return fSubject;
    }

    /**
     * @param fSubject
     */
    public void setfSubject(String fSubject) {
        this.fSubject = fSubject;
    }

    /**
     * @return f_beread
     */
    public Integer getfBeread() {
        return fBeread;
    }

    /**
     * @param fBeread
     */
    public void setfBeread(Integer fBeread) {
        this.fBeread = fBeread;
    }

    /**
     * @return f_time
     */
    public Date getfTime() {
        return fTime;
    }

    /**
     * @param fTime
     */
    public void setfTime(Date fTime) {
        this.fTime = fTime;
    }

    /**
     * @return f_content
     */
    public String getfContent() {
        return fContent;
    }

    /**
     * @param fContent
     */
    public void setfContent(String fContent) {
        this.fContent = fContent;
    }

	@Override
	public String toString() {
		return "FeedBack [fId=" + fId + ", uId=" + uId + ", fSubject=" + fSubject + ", fBeread=" + fBeread + ", fTime="
				+ fTime + ", fContent=" + fContent + "]";
	}
}