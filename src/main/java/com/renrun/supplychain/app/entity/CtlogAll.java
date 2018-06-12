package com.renrun.supplychain.app.entity;

import com.renrun.supplychain.base.EntityRequestBuilder;

/**
 * Created by chenjuqin on 2018/4/16.
 */
public class CtlogAll extends EntityRequestBuilder {
    private Integer id;
    private String tablety;
    private Integer tabletyId;
    private String ty;
    private String tyName;
    private String roleId;
    private String roleName;
    private Integer regUid;
    private String regUidName;
    private String content;

    private String url; //请求url
    private String ip;//请求IP地址
    private String addr; //ip物理地址
    private String ie; //浏览器类型
    private String method; //请求方式
    private String post;//post 情况下记录 post 的数据备查
    private String timeH;
    private String timeL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTablety() {
        return tablety;
    }

    public void setTablety(String tablety) {
        this.tablety = tablety;
    }

    public Integer getTabletyId() {
        return tabletyId;
    }

    public void setTabletyId(Integer tabletyId) {
        this.tabletyId = tabletyId;
    }

    public String getTy() {
        return ty;
    }

    public void setTy(String ty) {
        this.ty = ty;
    }

    public String getTyName() {
        return tyName;
    }

    public void setTyName(String tyName) {
        this.tyName = tyName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeH() {
        return timeH;
    }

    public void setTimeH(String timeH) {
        this.timeH = timeH;
    }

    public String getTimeL() {
        return timeL;
    }

    public void setTimeL(String timeL) {
        this.timeL = timeL;
    }

    public Integer getRegUid() {
        return regUid;
    }

    public void setRegUid(Integer regUid) {
        this.regUid = regUid;
    }

    public String getRegUidName() {
        return regUidName;
    }

    public void setRegUidName(String regUidName) {
        this.regUidName = regUidName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
