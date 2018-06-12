package com.renrun.supplychain.app.controller;

import com.renrun.supplychain.app.helper.AppSession;
import com.renrun.supplychain.app.helper.SessionWrapper;
import com.renrun.supplychain.app.response.Base;
import com.renrun.supplychain.app.response.ULogin;
import com.renrun.supplychain.app.service.CtlogAllService;
import com.renrun.supplychain.base.EntityRequestBuilder;
import com.renrun.supplychain.base.ErrorCode;
import com.renrun.supplychain.base.LogTypeConstant;
import com.renrun.supplychain.base.exception.WellFormatedException;
import com.renrun.supplychain.base.utils.BaseJsonController;
import com.renrun.supplychain.base.utils.StringUtils;
import com.renrun.supplychain.ucenter.share.client.CompanyClient;
import com.renrun.supplychain.ucenter.share.client.UserClient;
import com.renrun.supplychain.ucenter.share.client.UserPersonClient;
import com.renrun.supplychain.ucenter.share.entity.*;
import com.wordnik.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by swk on 2017/1/20.
 */
@RestController
@RequestMapping(value = "/wns")
@Api(value = "登录登出相关接口", description = "登录登出相关接口")
public class AuthController extends BaseJsonController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private CompanyClient companyClient;


    @Autowired
    private CtlogAllService ctlogAllService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "pass", value = "密码", required = true, dataType = "string", paramType = "form"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok")
    })
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ULogin login(HttpServletResponse response) throws WellFormatedException {
        ULogin uLogin = new ULogin();
//        UserLogin userLogin = new UserLogin();
//        userLogin.buildFromRequest(req);
//        if (StringUtils.isMobile(userLogin.getUserName())) {
//            userLogin.setMobile(userLogin.getUserName());
//        }
//
//        AuthInfo r = userClient.userLogin(userLogin);
//        if (!r.getLoginOk()) {
//            uLogin.code = ErrorCode.ServerError;
//            uLogin.message = r.getLoginMsg();
//            return uLogin;
//        }
//
//        UserBase ub = r.getUserBase();
//
//        if (!ub.getPersonType().equals("worker") || !ub.getUserCate().equals("admin")) {
//            uLogin.code = ErrorCode.ServerError;
//            uLogin.message = "无法登录";
//            return uLogin;
//        }
//
//        UserWorkerSearch search = new UserWorkerSearch();
//        search.setUserId(ub.getUid());
//        UserWorkerResult worker = userClient.getUserWorkerDetail(search);
//        if (worker == null) {
//            uLogin.code = ErrorCode.ServerError;
//            uLogin.message = "无法登录";
//            return uLogin;
//        }
//        Integer cid = worker.getCid();
//
//        UserCompany company = companyClient.getCompanyByCid(cid);
//        if (company == null) {
//            uLogin.code = ErrorCode.ServerError;
//            uLogin.message = "无法登录";
//            return uLogin;
//        }
//        String treePath = company.getTreePath();
//        String sid = session.getId();
//        r.setSid(sid);

        SessionWrapper sessionWrapper = SessionWrapper.wrap(session);
        session.setMaxInactiveInterval(6 * 3600);//设置session会话时间
//        sessionWrapper.save(
//                ub.getUid(),
//                ub.getUserName(),
//                ub.getMobile(),
//                ub.getLastLoginIp(),
//                cid,
//                treePath,
//                company.getSysCate(),
//                ub.getUserCate(),
//                ub.getPersonType(),
//                company.getCompanyName(),
//                ub.getTrueName(),
//                ub.getIsActive()
//        );
        AppSession appSession = sessionWrapper.getLoginSession();

        // 会话的cookie名默认是"SESSION"，可以看`CookieHttpSessionStrategy`，在`SmartHttpSessionStrategy`里配到
        // 定制这个名字可以看`http://docs.spring.io/spring-session/docs/current/reference/html5/guides/custom-cookie.html`
//        Cookie cookie = new Cookie("SESSION", sid);
//        response.addCookie(cookie);



        String content ="登入系统成功";
        ctlogAllService.recordlog(LogTypeConstant.LOGINSUNCESS,1,content);


        uLogin.code = ErrorCode.OK;
        uLogin.message = "登录成功";
        uLogin.data = appSession;
        return uLogin;
    }


}
