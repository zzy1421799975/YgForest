package com.zsl.YgForest.entity;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "f_user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "u_id")
	private Integer uId;

	@Column(name = "u_username")
	private String uUsername;

	@Column(name = "u_password")
	private String uPassword;

	@Column(name = "u_fullname")
	private String uFullname;

	@Column(name = "u_realmname")
	private String uRealmname;

	@Column(name = "u_telephone")
	private String uTelephone;

	@Column(name = "u_email")
	private String uEmail;

	@Column(name = "u_aboutme")
	private String uAboutme;

	@Column(name = "u_money")
	private Double uMoney;
	
	@Column(name = "u_cid")
	private String uCid;
	
	@Column(name = "u_imagepath")
	private String uImagepath;

	@Column(name = "u_longitude")
	private Double uLongitude;

	@Column(name = "u_latitude")
	private Double uLatitude;

	@Column(name = "u_ipaddress")
	private String uIpaddress;

	@Column(name = "u_belogin")
	private Integer uBelogin;

	@Column(name = "u_beemail")
	private Integer uBeemail;
	
	@Column(name = "u_beadmin")
	private Integer uBeadmin;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "u_regtime")
	private Date uRegtime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "u_lastlogintime")
	private Date uLastlogintime;

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
	 * @return u_username
	 */
	public String getuUsername() {
		return uUsername;
	}

	/**
	 * @param uUsername
	 */
	public void setuUsername(String uUsername) {
		this.uUsername = uUsername;
	}

	/**
	 * @return u_password
	 */
	public String getuPassword() {
		return uPassword;
	}

	/**
	 * @param uPassword
	 */
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	/**
	 * @return u_fullname
	 */
	public String getuFullname() {
		return uFullname;
	}

	/**
	 * @param uFullname
	 */
	public void setuFullname(String uFullname) {
		this.uFullname = uFullname;
	}

	/**
	 * @return u_realmname
	 */
	public String getuRealmname() {
		return uRealmname;
	}

	/**
	 * @param uRealmname
	 */
	public void setuRealmname(String uRealmname) {
		this.uRealmname = uRealmname;
	}

	/**
	 * @return u_telephone
	 */
	public String getuTelephone() {
		return uTelephone;
	}

	/**
	 * @param uTelephone
	 */
	public void setuTelephone(String uTelephone) {
		this.uTelephone = uTelephone;
	}

	/**
	 * @return u_email
	 */
	public String getuEmail() {
		return uEmail;
	}

	/**
	 * @param uEmail
	 */
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	/**
	 * @return u_aboutme
	 */
	public String getuAboutme() {
		return uAboutme;
	}

	/**
	 * @param uAboutme
	 */
	public void setuAboutme(String uAboutme) {
		this.uAboutme = uAboutme;
	}

	/**
	 * @return u_money
	 */
	public Double getuMoney() {
		return uMoney;
	}

	/**
	 * @param uMoney
	 */
	public void setuMoney(Double uMoney) {
		this.uMoney = uMoney;
	}

	/**
	 * @return u_imagepath
	 */
	public String getuImagepath() {
		return uImagepath;
	}

	/**
	 * @param uImagepath
	 */
	public void setuImagepath(String uImagepath) {
		this.uImagepath = uImagepath;
	}

	/**
	 * @return u_longitude
	 */
	public Double getuLongitude() {
		return uLongitude;
	}

	/**
	 * @param uLongitude
	 */
	public void setuLongitude(Double uLongitude) {
		this.uLongitude = uLongitude;
	}

	/**
	 * @return u_latitude
	 */
	public Double getuLatitude() {
		return uLatitude;
	}

	/**
	 * @param uLatitude
	 */
	public void setuLatitude(Double uLatitude) {
		this.uLatitude = uLatitude;
	}

	/**
	 * @return u_ipaddress
	 */
	public String getuIpaddress() {
		return uIpaddress;
	}

	/**
	 * @param uIpaddress
	 */
	public void setuIpaddress(String uIpaddress) {
		this.uIpaddress = uIpaddress;
	}

	/**
	 * @return u_belogin
	 */
	public Integer getuBelogin() {
		return uBelogin;
	}

	/**
	 * @param uBelogin
	 */
	public void setuBelogin(Integer uBelogin) {
		this.uBelogin = uBelogin;
	}

	/**
	 * @return u_beemail
	 */
	public Integer getuBeemail() {
		return uBeemail;
	}

	/**
	 * @param uBeemail
	 */
	public void setuBeemail(Integer uBeemail) {
		this.uBeemail = uBeemail;
	}

	/**
	 * @return u_regtime
	 */
	public Date getuRegtime() {
		return uRegtime;
	}

	/**
	 * @param uRegtime
	 */
	public void setuRegtime(Date uRegtime) {
		this.uRegtime = uRegtime;
	}

	/**
	 * @return u_lastlogintime
	 */
	public Date getuLastlogintime() {
		return uLastlogintime;
	}

	/**
	 * @param uLastlogintime
	 */
	public void setuLastlogintime(Date uLastlogintime) {
		this.uLastlogintime = uLastlogintime;
	}

	public Integer getuBeadmin() {
		return uBeadmin;
	}

	public void setuBeadmin(Integer uBeadmin) {
		this.uBeadmin = uBeadmin;
	}


	public String getuCid() {
		return uCid;
	}


	public void setuCid(String uCid) {
		this.uCid = uCid;
	}


	@Override
	public String toString() {
		return "User [uId=" + uId + ", uUsername=" + uUsername + ", uPassword=" + uPassword + ", uFullname=" + uFullname
				+ ", uRealmname=" + uRealmname + ", uTelephone=" + uTelephone + ", uEmail=" + uEmail + ", uAboutme="
				+ uAboutme + ", uMoney=" + uMoney + ", uCid=" + uCid + ", uImagepath=" + uImagepath + ", uLongitude="
				+ uLongitude + ", uLatitude=" + uLatitude + ", uIpaddress=" + uIpaddress + ", uBelogin=" + uBelogin
				+ ", uBeemail=" + uBeemail + ", uBeadmin=" + uBeadmin + ", uRegtime=" + uRegtime + ", uLastlogintime="
				+ uLastlogintime + "]";
	}

	


	

}