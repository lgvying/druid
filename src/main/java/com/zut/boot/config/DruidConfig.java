package com.zut.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.HashMap;


/**
 * @author 梁贵莹
 * @create 2021/7/1 下午 11:50
 */
@Configuration
@EnableSwagger2
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
    //配置swagger
    @Bean
    public Docket createRestApi() {
        // (载明包裹、发货、完工等情况的)单据，标签; 备审案件目录表; 法院积案清单; 议程;
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zut.boot.action"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("druid")  //项目名称
                        .description("SpringBoot整合druid及Swagger，详细信息......") //项目描述
                        .version("1.0")   //版本信息
                        .contact(new Contact("梁贵莹","http://www.lgvying.github.io","lgvying@gmail.com"))
                        //作者信息
                        .license("The Apache License")//许可证
                        .licenseUrl("http://www.baidu.com") //许可证url
                        .build());
    }

}
