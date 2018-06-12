package com.renrun.supplychain.app;

import com.renrun.supplychain.base.exception.ErrorHandlerForShare;
import com.renrun.supplychain.ucenter.share.config.UcenterServiceConfig;
import org.apache.catalina.filters.RequestDumperFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.util.List;

@EnableAutoConfiguration
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = "com.renrun.supplychain,com.renrun.framework")
@MapperScan("com.renrun.supplychain.app.mapper")
public class Application {
    private static Logger logger = Logger.getLogger(Application.class);

    // 记录请求信息
    @Bean
    public RequestDumperFilter dumperFilter() {
        return new RequestDumperFilter();
    }

    @Autowired
    private Environment environment;

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    @Bean
    public UcenterServiceConfig userClientConfig() {
        String addr = environment.getProperty("renrun.service.ucenter.addr");
        return new UcenterServiceConfig(addr);
    }
    @Bean
    public RestTemplate buildRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 自定义了错误处理
        restTemplate.setErrorHandler(new ErrorHandlerForShare());
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        //converter.setWriteAcceptCharset(false);
        restTemplate.getMessageConverters()
                .add(0, converter);

        return restTemplate;
    }
    /**
     * Start
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("SpringBoot Start Success");
    }

}
