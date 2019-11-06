package com.zsl.YgForest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "f_version_update")
public class VersionUpdate implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "v_id")
    private Integer vId;

    @Column(name = "v_piversion")
    private String vPiversion;

    @Column(name = "v_picontent")
    private String vPicontent;

    @Column(name = "v_pipath")
    private String vPipath;

    @Column(name = "v_appname")
    private String vAppname;

    @Column(name = "v_appversion")
    private String vAppversion;

    @Column(name = "v_appcontent")
    private String vAppcontent;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "v_time")
    private Date vTime;

    /**
     * @return v_id
     */
    public Integer getvId() {
        return vId;
    }

    /**
     * @param vId
     */
    public void setvId(Integer vId) {
        this.vId = vId;
    }

    /**
     * @return v_piversion
     */
    public String getvPiversion() {
        return vPiversion;
    }

    /**
     * @param vPiversion
     */
    public void setvPiversion(String vPiversion) {
        this.vPiversion = vPiversion;
    }

    /**
     * @return v_picontent
     */
    public String getvPicontent() {
        return vPicontent;
    }

    /**
     * @param vPicontent
     */
    public void setvPicontent(String vPicontent) {
        this.vPicontent = vPicontent;
    }

    /**
     * @return v_pipath
     */
    public String getvPipath() {
        return vPipath;
    }

    /**
     * @param vPipath
     */
    public void setvPipath(String vPipath) {
        this.vPipath = vPipath;
    }

    /**
     * @return v_appname
     */
    public String getvAppname() {
        return vAppname;
    }

    /**
     * @param vAppname
     */
    public void setvAppname(String vAppname) {
        this.vAppname = vAppname;
    }

    /**
     * @return v_appversion
     */
    public String getvAppversion() {
        return vAppversion;
    }

    /**
     * @param vAppversion
     */
    public void setvAppversion(String vAppversion) {
        this.vAppversion = vAppversion;
    }

    /**
     * @return v_appcontent
     */
    public String getvAppcontent() {
        return vAppcontent;
    }

    /**
     * @param vAppcontent
     */
    public void setvAppcontent(String vAppcontent) {
        this.vAppcontent = vAppcontent;
    }

    /**
     * @return v_time
     */
    public Date getvTime() {
        return vTime;
    }

    /**
     * @param vTime
     */
    public void setvTime(Date vTime) {
        this.vTime = vTime;
    }
}