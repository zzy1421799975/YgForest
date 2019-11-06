package com.zsl.YgForest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "f_user_pi_relation")
public class UserPiRelation  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "u_id")
    private Integer uId;

    @Id
    @Column(name = "p_id")
    private Integer pId;

    @Column(name = "up_visual")
    private Integer upVisual;

    @Column(name = "up_visualfrd")
    private Integer upVisualfrd;

    @Column(name = "up_visualdata")
    private Integer upVisualdata;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "up_bindingtime")
    private Date upBindingtime;

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
     * @return up_visual
     */
    public Integer getUpVisual() {
        return upVisual;
    }

    /**
     * @param upVisual
     */
    public void setUpVisual(Integer upVisual) {
        this.upVisual = upVisual;
    }

    /**
     * @return up_visualfrd
     */
    public Integer getUpVisualfrd() {
        return upVisualfrd;
    }

    /**
     * @param upVisualfrd
     */
    public void setUpVisualfrd(Integer upVisualfrd) {
        this.upVisualfrd = upVisualfrd;
    }

    /**
     * @return up_visualdata
     */
    public Integer getUpVisualdata() {
        return upVisualdata;
    }

    /**
     * @param upVisualdata
     */
    public void setUpVisualdata(Integer upVisualdata) {
        this.upVisualdata = upVisualdata;
    }

    /**
     * @return up_bindingtime
     */
    public Date getUpBindingtime() {
        return upBindingtime;
    }

    /**
     * @param upBindingtime
     */
    public void setUpBindingtime(Date upBindingtime) {
        this.upBindingtime = upBindingtime;
    }
}