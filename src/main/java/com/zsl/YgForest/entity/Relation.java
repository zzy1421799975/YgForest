package com.zsl.YgForest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "f_relation")
public class Relation implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "r_id")
    private Integer rId;

    @Column(name = "r_sender")
    private Integer rSender;

    @Column(name = "r_receiver")
    private Integer rReceiver;

    @Column(name = "r_befriend")
    private Integer rBefriend;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "r_sendtime")
    private Date rSendtime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "r_bindingtime")
    private Date rBindingtime;

    /**
     * @return r_id
     */
    public Integer getrId() {
        return rId;
    }

    /**
     * @param rId
     */
    public void setrId(Integer rId) {
        this.rId = rId;
    }

    /**
     * @return r_sender
     */
    public Integer getrSender() {
        return rSender;
    }

    /**
     * @param rSender
     */
    public void setrSender(Integer rSender) {
        this.rSender = rSender;
    }

    /**
     * @return r_receiver
     */
    public Integer getrReceiver() {
        return rReceiver;
    }

    /**
     * @param rReceiver
     */
    public void setrReceiver(Integer rReceiver) {
        this.rReceiver = rReceiver;
    }

    /**
     * @return r_befriend
     */
    public Integer getrBefriend() {
        return rBefriend;
    }

    /**
     * @param rBefriend
     */
    public void setrBefriend(Integer rBefriend) {
        this.rBefriend = rBefriend;
    }

    /**
     * @return r_sendtime
     */
    public Date getrSendtime() {
        return rSendtime;
    }

    /**
     * @param rSendtime
     */
    public void setrSendtime(Date rSendtime) {
        this.rSendtime = rSendtime;
    }

    /**
     * @return r_bindingtime
     */
    public Date getrBindingtime() {
        return rBindingtime;
    }

    /**
     * @param rBindingtime
     */
    public void setrBindingtime(Date rBindingtime) {
        this.rBindingtime = rBindingtime;
    }
}