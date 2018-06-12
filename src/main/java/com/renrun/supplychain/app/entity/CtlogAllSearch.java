package com.renrun.supplychain.app.entity;

import com.renrun.supplychain.base.EntityRequestBuilder;
import com.renrun.supplychain.base.pagination.Pager;

/**
 * Created by chenjuqin on 2018/4/16.
 */
public class CtlogAllSearch extends EntityRequestBuilder {
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
    private String timeH;
    private String timeL;
    private Pager pager;
    private String startTime;
    private String endTime;
    private String timeType;
    private String search;

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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
}
