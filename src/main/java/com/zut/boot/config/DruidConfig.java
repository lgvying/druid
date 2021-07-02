package com.zut.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;


/**
 * @author 梁贵莹
 * @create 2021/7/1 下午 11:50
 */
@Configuration
public class DruidConfig {
    //创建数据源对象
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDruidDataSource(){
        return new DruidDataSource();
    }
    //创建ServletRegistrationBean
    @Bean
    public ServletRegistrationBean getServletRegistrationBean(){
        //创建bean时 指定后台服务的url
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //创建一个map 指定账号密码
        HashMap<String,String> map=new HashMap<>();
        map.put("loginUsername","tian");
        map.put("loginPassword","123");
        //指定允许的用户
        map.put("allow","");
        //把map和bean绑定
        bean.setInitParameters(map);
        return bean;
    }

}
