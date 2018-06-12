package com.renrun.supplychain.app.helper;

/**
 * Created by swk on 2017/2/17.
 */
public class AppSession {
    private String sid;
    private Integer uid;
    private String userName;
    private String mobile;
    private String lastLoginIp;
    private Integer cid;
    private String treePath;
    private String sysCate;
    private String personType;
    private String userCate;
    private String companyName;
    private String truename;
    private Integer isActive;
    //lx.xu 2017-06-08
    private Integer isSfz;

    public Integer getIsSfz() {
        return isSfz;
    }

    public void setIsSfz(Integer isSfz) {
        this.isSfz = isSfz;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public String getSysCate() {
        return sysCate;
    }

    public void setSysCate(String sysCate) {
        this.sysCate = sysCate;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getUserCate() {
        return userCate;
    }

    public void setUserCate(String userCate) {
        this.userCate = userCate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
