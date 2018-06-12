package com.renrun.supplychain.app.helper;

import javax.servlet.http.HttpSession;

/**
 * Session包装类
 * Created by swk on 2017/2/17.
 */
public class SessionWrapper {

    private HttpSession session;

    private SessionWrapper(HttpSession session) {
        this.session = session;
    }

    public static SessionWrapper wrap(HttpSession session) {
        return new SessionWrapper(session);
    }

    public void save(
            Integer uid, String userName, String mobile, String lastLoginIp, Integer cid,
            String treePath, String sysCate, String userCate, String personType, String companyName, String truename,Integer isActive) {
        session.setAttribute("sid", session.getId());
        session.setAttribute("uid", uid);
        session.setAttribute("userName", userName);
        session.setAttribute("mobile", mobile);
        session.setAttribute("lastLoginIp", lastLoginIp);
        session.setAttribute("cid", cid);
        session.setAttribute("treePath",treePath);
        session.setAttribute("sysCate", sysCate);
        session.setAttribute("userCate", userCate);
        session.setAttribute("personType", personType);
        session.setAttribute("companyName", companyName);
        session.setAttribute("truename", truename);
        session.setAttribute("isActive",isActive);
    }

    public void save(AppSession appSession) {
        session.setAttribute("sid", appSession.getSid());
        session.setAttribute("uid", appSession.getUid());
        session.setAttribute("userName", appSession.getUserName());
        session.setAttribute("mobile", appSession.getMobile());
        session.setAttribute("lastLoginIp", appSession.getLastLoginIp());
        session.setAttribute("treePath",appSession.getTreePath());
        session.setAttribute("cid", appSession.getCid());
        session.setAttribute("sysCate", appSession.getSysCate());
        session.setAttribute("userCate", appSession.getUserCate());
        session.setAttribute("personType", appSession.getPersonType());
        session.setAttribute("companyName", appSession.getCompanyName());
        session.setAttribute("truename", appSession.getTruename());
        session.setAttribute("isActive",appSession.getIsActive());
    }

    /**
     * 检查用户是否前台登录
     * @return
     */
    public boolean checkFrontLogin() {
        String sid = getSid();
        Integer uid = getUid();
        String userCate = getUserCate();

        if(userCate == null || !userCate.equals("user")) {
            return false;
        }

        if (sid == null || sid.isEmpty() || uid == null || uid == 0) {
            return false;
        }

        return true;
    }

    /**
     * 检查后台用户是否登录
     * @return
     */
    public boolean checkLogin() {
        String sid = getSid();
        Integer uid = getUid();
        String userCate = getUserCate();

        if(userCate == null || !userCate.equals("admin")) {
            return false;
        }

        if (sid == null || sid.isEmpty() || uid == null || uid == 0) {
            return false;
        }

        return true;
    }

    /**
     * 获取所有的会话数据
     * @return
     */
    public AppSession getLoginSession() {
        AppSession appSession = new AppSession();
        appSession.setSid(getSid());
        appSession.setUid(getUid());
        appSession.setUserName(getUserName());
        appSession.setMobile(getMobile());
        appSession.setLastLoginIp(getLastLoginIp());
        appSession.setPersonType(getPersonType());
        appSession.setCid(getCid());
        appSession.setTreePath(getTreePath());
        appSession.setSysCate(getSysCate());
        appSession.setCompanyName(getCompanyName());
        appSession.setTruename(getTruename());
        appSession.setIsActive(getIsActive());
        return appSession;
    }

    /**
     * 清除会话数据
     */
    public void clear() {
        session.removeAttribute("sid");
        session.removeAttribute("uid");
        session.removeAttribute("userName");
        session.removeAttribute("mobile");
        session.removeAttribute("lastLoginIp");
        session.removeAttribute("cid");
        session.removeAttribute("treePath");
        session.removeAttribute("sysCate");
        session.removeAttribute("personType");
        session.removeAttribute("companyName");
        session.removeAttribute("truename");
        session.removeAttribute("isActive");
    }

    /**
     * 获取会话ID
     * @return
     */
    public String getSid() {
        return (String)session.getAttribute("sid");
    }

    /**
     * 获取用户ID
     * @return
     */
    public Integer getUid() {
        return (Integer)session.getAttribute("uid");
    }

    /**
     * 获取用户名
     * @return
     */
    public String getUserName() {
        return (String)session.getAttribute("userName");
    }

    /**
     * 获取手机
     * @return
     */
    public String getMobile() {
        return (String)session.getAttribute("mobile");
    }

    /**
     * 获取最后登录IP
     * @return
     */
    public String getLastLoginIp() {
        return (String)session.getAttribute("lastLoginIp");
    }

    /**
     * 获取公司ID
     * @return
     */
    public Integer getCid() {
        return (Integer)session.getAttribute("cid");
    }

    public String getTreePath() {
        return (String)session.getAttribute("treePath");
    }

    /**
     * 获取业务系统
     * @return
     */
    public String getSysCate() {
        return (String)session.getAttribute("sysCate");
    }

    /**
     * 获取用户类型
     * @return
     */
    public String getPersonType() {
        return (String)session.getAttribute("personType");
    }

    /**
     * 获取用户类型
     * @return
     */
    public String getUserCate() {
        return (String)session.getAttribute("userCate");
    }

    /**
     * 获取公司名称
     * @return
     */
    public String getCompanyName() {
        return (String)session.getAttribute("companyName");
    }

    /**
     * 获取真实姓名
     * @return
     */
    public String getTruename() {
        return (String)session.getAttribute("truename");
    }
    /**
     * 获取激活状态
     * @return
     */
    public Integer getIsActive() {
        return (Integer) session.getAttribute("isActive");
    }
}
