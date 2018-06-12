package com.renrun.supplychain.app.entity;

import com.renrun.supplychain.base.EntityRequestBuilder;
import com.wordnik.swagger.annotations.ApiModel;

@ApiModel(value = "logconfig", description = "log配置表")
public class LogConfig extends EntityRequestBuilder {

    private String tablety; //要记录日志的对象,比如uid, 或者 bid 等
    private Integer id; //对象的 id 号
    private String ty; //日志的子类别,自行定义,比如: loginnum 可以表示登录次数
    private Integer val; //记录下和数字有关的信息,一般是次数等概念的数字
    private Integer withid; // 关联对象的 id 号,用于比较复杂的情况下可以用,如果没有默认写0
    private String des; //备注[可以自己组合 json 格式,注意不要超过8000字符]
    private String os;
    private String url; //请求url
    private String ip;
    private String msg;
    private String addr; //ip物理地址
    private String ie; //浏览器类型
    private Integer uid; //操作人uid
    private String un; //操作人姓名
    private String method; //请求方式
    private String post;//post 情况下记录 post 的数据备查


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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getTablety() {
        return tablety;
    }

    public void setTablety(String tablety) {
        this.tablety = tablety;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTy() {
        return ty;
    }

    public void setTy(String ty) {
        this.ty = ty;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public Integer getWithid() {
        return withid;
    }

    public void setWithid(Integer withid) {
        this.withid = withid;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
