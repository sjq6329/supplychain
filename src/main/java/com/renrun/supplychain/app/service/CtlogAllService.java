package com.renrun.supplychain.app.service;

import com.renrun.supplychain.app.entity.*;
import com.renrun.supplychain.app.helper.SessionWrapper;
import com.renrun.supplychain.app.mapper.CtlogAllMapper;
import com.renrun.supplychain.base.ErrorCode;
import com.renrun.supplychain.base.exception.WellFormatedException;
import com.renrun.supplychain.base.pagination.Page;
import com.renrun.supplychain.base.utils.AddressUtils;
import com.renrun.supplychain.base.utils.BrowserUtils;
import com.renrun.supplychain.base.utils.JsonUtils;
import com.renrun.supplychain.base.utils.NetworkUtils;
import com.renrun.supplychain.ucenter.share.client.PermClient;
import com.renrun.supplychain.ucenter.share.client.UserClient;
import com.renrun.supplychain.ucenter.share.entity.UserBase;
import com.renrun.supplychain.ucenter.share.entity.UserPermRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjuqin on 2018/4/9.
 */
@Service
public class CtlogAllService {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CtlogAllService.class);

    @Autowired
    CtlogAllMapper ctlogAllMapper;
    @Resource
    protected HttpSession session;
    @Autowired
    PermClient permClient;
    @Resource
    protected HttpServletRequest req;
    @Autowired
    UserClient userClient;
    @Autowired
    LogConfigService logConfigService;



    public Integer insertCtlogAll(CtlogAll ctlogAll)throws WellFormatedException {
        return ctlogAllMapper.insertCtlogAll(ctlogAll);
    }

    public Page<CtlogAll> getCtlogAllPage(CtlogAllSearch ctlogAllSearch)throws WellFormatedException {
        try {
            List<CtlogAll> ctlogAllList = ctlogAllMapper.getCtlogAllList(ctlogAllSearch);
            Integer total = ctlogAllMapper.getCtlogAllCount(ctlogAllSearch);
            Page<CtlogAll> page = new Page<>();
            page.setTotal(total);
            page.setRows(ctlogAllList);
            page.setPage(ctlogAllSearch.getPager().getPage());
            page.setSize(ctlogAllSearch.getPager().getSize());
            return page;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new WellFormatedException(ErrorCode.DBServiceError, "获取联系人列表失败");
        }
    }

    public CtlogTy getCtlogTy(String ty)throws WellFormatedException {
        return ctlogAllMapper.getCtlogTy(ty);
    }

    public String getUserRoles()throws WellFormatedException {
        SessionWrapper sessionWrapper = SessionWrapper.wrap(session);
        List<UserPermRole> roles = permClient.getRolesByUid(sessionWrapper.getUid());
        StringBuffer r = new StringBuffer();
        for(UserPermRole userPermRole :roles){
            r.append(userPermRole.getRoleId()).append(",");
        }
        if(r.length()>0){
            r.setLength(r.length()-1);
        }
        return r.toString();
    }
    public String getUserRolesName()throws WellFormatedException {
        SessionWrapper sessionWrapper = SessionWrapper.wrap(session);
        List<UserPermRole> roles = permClient.getRolesByUid(sessionWrapper.getUid());
        StringBuffer r = new StringBuffer();
        for(UserPermRole userPermRole :roles){
            r.append(userPermRole.getName()).append(",");
        }
        if(r.length()>0){
            r.setLength(r.length()-1);
        }
        return r.toString();
    }

    public String getDesc(String msg)throws WellFormatedException {
        SessionWrapper sessionWrapper = SessionWrapper.wrap(session);
        LogBack logBack = new LogBack();
        String ip = NetworkUtils.ip(req);
        String url = req.getRequestURI();
        logBack.setIp(ip);
        logBack.setUrl(url);
        logBack.setMsg(msg);
        AddressUtils addressUtils = new AddressUtils();
        String addr = "";
        try {
            addr = addressUtils.getAddresses("ip="+ip, "utf-8");
            logBack.setAddr(addr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String agent = req.getHeader("User-Agent").toLowerCase();
        String ie = BrowserUtils.getBrowserName(agent);
        Integer uid = sessionWrapper.getUid();
        String un = sessionWrapper.getTruename();
        String method = req.getMethod();
        logBack.setIe(ie);
        logBack.setUid(uid);
        logBack.setUn(un);
        logBack.setMethod(method);
        if (method.equals("POST") ) {
            Map map = req.getParameterMap();
            String post = JsonUtils.encode(map);   // post 情况下记录 post 的数据备查
            if (post.length() > 7500)//如果字符超长，就截取
                post = post.substring(0, 7500);
            logBack.setPost(post);
        }
        String des = JsonUtils.encode(logBack);
        return des;
    }

    /**
     * 记录日志
     * @param ty
     * @param tabletyId
     * @param content
     * @throws WellFormatedException
     */
    public void recordlog(String ty,Integer tabletyId,String content) throws WellFormatedException {
        //添加日志
        CtlogTy ctlogTy = getCtlogTy(ty);
        CtlogAll ctlogAll = new CtlogAll();
        ctlogAll.setTablety(ctlogTy.getTablety());
        ctlogAll.setTabletyId(tabletyId);
        ctlogAll.setTy(ctlogTy.getTy());
        ctlogAll.setTyName(ctlogTy.getContent());
        ctlogAll.setRoleId(getUserRoles());
        ctlogAll.setRoleName(getUserRolesName());
        SessionWrapper sessionWrapper = SessionWrapper.wrap(session);
        ctlogAll.setRegUid(sessionWrapper.getUid());
        UserBase u = userClient.getUserBaseByUid(sessionWrapper.getUid());
        ctlogAll.setRegUidName(u.getTrueName());
        ctlogAll.setContent(content);

        String ip = NetworkUtils.ip(req);
        ctlogAll.setIp(ip);
        String url = req.getRequestURI();
        ctlogAll.setUrl(url);
        AddressUtils addressUtils = new AddressUtils();
        String addr = "";
        try {
            addr = addressUtils.getAddresses("ip="+ip, "utf-8");
            ctlogAll.setAddr(addr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String agent = req.getHeader("User-Agent").toLowerCase();
        String ie = BrowserUtils.getBrowserName(agent);
        String method = req.getMethod();
        ctlogAll.setIe(ie);
        ctlogAll.setMethod(method);
        if (method.equals("POST") ) {
            Map map = req.getParameterMap();
            String post = JsonUtils.encode(map);   // post 情况下记录 post 的数据备查
            if (post.length() > 7500)//如果字符超长，就截取
                post = post.substring(0, 7500);
            ctlogAll.setPost(post);
        }


        insertCtlogAll(ctlogAll);
        LogConfig logConfig = new LogConfig();
        logConfig.setTablety(ctlogTy.getTablety());
        logConfig.setId(tabletyId);
        logConfig.setTy(ctlogTy.getTy());
        logConfig.setVal(1);
        logConfig.setWithid(0);
        logConfig.setDes(getDesc(ctlogAll.getContent()));
        logConfigService.pclLogForNow(logConfig);
        logConfigService.pclCTFromDayForNow(logConfig);

    }
}
