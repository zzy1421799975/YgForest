package com.zsl.YgForest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "f_data")
public class Data  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "d_id")
    private Integer dId;

    @Column(name = "p_id")
    private Integer pId;

    @Column(name = "d_temperature")
    private Double dTemperature;

    @Column(name = "d_humidity")
    private Double dHumidity;

    @Column(name = "d_lightintensity")
    private Double dLightintensity;

    @Column(name = "d_smoke")
    private Integer dSmoke;

    @Column(name = "d_flame")
    private Integer dFlame;

    @Column(name = "d_infrared")
    private Integer dInfrared;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "d_time")
    private Date dTime;

    /**
     * @return d_id
     */
    public Integer getdId() {
        return dId;
    }

    /**
     * @param dId
     */
    public void setdId(Integer dId) {
        this.dId = dId;
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
     * @return d_temperature
     */
    public Double getdTemperature() {
        return dTemperature;
    }

    /**
     * @param dTemperature
     */
    public void setdTemperature(Double dTemperature) {
        this.dTemperature = dTemperature;
    }

    /**
     * @return d_humidity
     */
    public Double getdHumidity() {
        return dHumidity;
    }

    /**
     * @param dHumidity
     */
    public void setdHumidity(Double dHumidity) {
        this.dHumidity = dHumidity;
    }

    /**
     * @return d_lightintensity
     */
    public Double getdLightintensity() {
        return dLightintensity;
    }

    /**
     * @param dLightintensity
     */
    public void setdLightintensity(Double dLightintensity) {
        this.dLightintensity = dLightintensity;
    }

    /**
     * @return d_smoke
     */
    public Integer getdSmoke() {
        return dSmoke;
    }

    /**
     * @param dSmoke
     */
    public void setdSmoke(Integer dSmoke) {
        this.dSmoke = dSmoke;
    }

    /**
     * @return d_flame
     */
    public Integer getdFlame() {
        return dFlame;
    }

    /**
     * @param dFlame
     */
    public void setdFlame(Integer dFlame) {
        this.dFlame = dFlame;
    }

    /**
     * @return d_infrared
     */
    public Integer getdInfrared() {
        return dInfrared;
    }

    /**
     * @param dInfrared
     */
    public void setdInfrared(Integer dInfrared) {
        this.dInfrared = dInfrared;
    }

    /**
     * @return d_time
     */
    public Date getdTime() {
        return dTime;
    }

    /**
     * @param dTime
     */
    public void setdTime(Date dTime) {
        this.dTime = dTime;
    }

	@Override
	public String toString() {
		return "Data [dId=" + dId + ", pId=" + pId + ", dTemperature=" + dTemperature + ", dHumidity=" + dHumidity
				+ ", dLightintensity=" + dLightintensity + ", dSmoke=" + dSmoke + ", dFlame=" + dFlame + ", dInfrared="
				+ dInfrared + ", dTime=" + dTime + "]";
	}
    
}