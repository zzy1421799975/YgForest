package com.zsl.YgForest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "f_message")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "m_id")
    private Integer mId;

    @Column(name = "m_subject")
    private String mSubject;

    @Column(name = "m_content")
    private String mContent;

    @Column(name = "m_filepath")
    private String mFilepath;

    @Column(name = "m_sender")
    private Integer mSender;

    @Column(name = "m_receiver")
    private Integer mReceiver;

    @Column(name = "m_beemail")
    private Integer mBeemail;

    @Column(name = "m_beread")
    private Integer mBeread;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "m_time")
    private Date mTime;

    @Column(name = "m_betrash")
    private Integer mBetrash;

    @Column(name = "m_Type")
    private Integer mType;
    
    /**
     * @return m_id
     */
    public Integer getmId() {
        return mId;
    }

    /**
     * @param mId
     */
    public void setmId(Integer mId) {
        this.mId = mId;
    }

    /**
     * @return m_subject
     */
    public String getmSubject() {
        return mSubject;
    }

    /**
     * @param mSubject
     */
    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    /**
     * @return m_content
     */
    public String getmContent() {
        return mContent;
    }

    /**
     * @param mContent
     */
    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    /**
     * @return m_filepath
     */
    public String getmFilepath() {
        return mFilepath;
    }

    /**
     * @param mFilepath
     */
    public void setmFilepath(String mFilepath) {
        this.mFilepath = mFilepath;
    }

    /**
     * @return m_sender
     */
    public Integer getmSender() {
        return mSender;
    }

    /**
     * @param mSender
     */
    public void setmSender(Integer mSender) {
        this.mSender = mSender;
    }

    /**
     * @return m_receiver
     */
    public Integer getmReceiver() {
        return mReceiver;
    }

    /**
     * @param mReceiver
     */
    public void setmReceiver(Integer mReceiver) {
        this.mReceiver = mReceiver;
    }

    /**
     * @return m_beemail
     */
    public Integer getmBeemail() {
        return mBeemail;
    }

    /**
     * @param mBeemail
     */
    public void setmBeemail(Integer mBeemail) {
        this.mBeemail = mBeemail;
    }

    /**
     * @return m_beread
     */
    public Integer getmBeread() {
        return mBeread;
    }

    /**
     * @param mBeread
     */
    public void setmBeread(Integer mBeread) {
        this.mBeread = mBeread;
    }

    /**
     * @return m_time
     */
    public Date getmTime() {
        return mTime;
    }

    /**
     * @param mTime
     */
    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    /**
     * @return m_betrash
     */
    public Integer getmBetrash() {
        return mBetrash;
    }

    /**
     * @param mBetrash
     */
    public void setmBetrash(Integer mBetrash) {
        this.mBetrash = mBetrash;
    }

	public Integer getmType() {
		return mType;
	}

	public void setmType(Integer mType) {
		this.mType = mType;
	}

	@Override
	public String toString() {
		return "Message [mId=" + mId + ", mSubject=" + mSubject + ", mContent=" + mContent + ", mFilepath=" + mFilepath
				+ ", mSender=" + mSender + ", mReceiver=" + mReceiver + ", mBeemail=" + mBeemail + ", mBeread="
				+ mBeread + ", mTime=" + mTime + ", mBetrash=" + mBetrash + ", mType=" + mType + "]";
	}

	
    
    
}