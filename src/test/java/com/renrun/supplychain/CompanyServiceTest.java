package com.renrun.supplychain;

import com.renrun.supplychain.app.Application;
import com.renrun.supplychain.app.service.CtlogAllService;
import com.renrun.supplychain.base.LogTypeConstant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

/**
 * Created by swk on 2017/1/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CompanyServiceTest {

    @Autowired
    private Environment environment;
    @Autowired
    private CtlogAllService ctlogAllService;


    @Before
    public void setUp() {
    }

    @Test
    public void addCompany() throws Exception {
        String content ="测试";
        ctlogAllService.recordlog(LogTypeConstant.LOGINSUNCESS,1,content);
    }

}
