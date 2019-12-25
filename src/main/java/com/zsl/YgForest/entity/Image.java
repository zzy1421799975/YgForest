package com.zsl.YgForest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Table(name = "f_image")
public class Image implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "i_id")
    private Integer iId;

    @Column(name = "p_id")
    private Integer pId;

    @Column(name = "i_imagepath")
    private String iImagepath;

    @JsonSerialize(using=ToStringSerializer.class)
    @Column(name = "i_density")
    private Double iDensity;
    
    @JsonSerialize(using=ToStringSerializer.class)
    @Column(name = "i_disdensity")
    private Double disDensity;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "i_time")
    private Date iTime;

    /**
     * @return i_id
     */
    public Integer getiId() {
        return iId;
    }

    /**
     * @param iId
     */
    public void setiId(Integer iId) {
        this.iId = iId;
    }

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
     * @return i_imagepath
     */
    public String getiImagepath() {
        return iImagepath;
    }

    /**
     * @param iImagepath
     */
    public void setiImagepath(String iImagepath) {
        this.iImagepath = iImagepath;
    }

    /**
     * @return i_density
     */
    public Double getiDensity() {
        return iDensity;
    }

    /**
     * @param iDensity
     */
    public void setiDensity(Double iDensity) {
        this.iDensity = iDensity;
    }

    /**
     * @return i_time
     */
    public Date getiTime() {
        return iTime;
    }

    /**
     * @param iTime
     */
    public void setiTime(Date iTime) {
        this.iTime = iTime;
    }

    
    
	public Double getDisDensity() {
		return disDensity;
	}

	public void setDisDensity(Double disDensity) {
		this.disDensity = disDensity;
	}

	@Override
	public String toString() {
		return "Image [iId=" + iId + ", pId=" + pId + ", iImagepath=" + iImagepath + ", iDensity=" + iDensity
				+ ", iTime=" + iTime + "]";
	}

	
	
    
}