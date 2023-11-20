package com.github.sailboat.notes.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

/**
 * 配置Druid的配置类  ( 通过yaml配置 )
 */
//@Configuration
public class DruidMonitroConfiguration {
    /**
     * 1. 对于当前这个配置仅仅是让一个Druid监控界面可以启动起来
     * 但是具体里面的监控项目还不能够直接给我们提供支持,
     * 如果你要想使用以上这些所有的监控项来监控当前的系统状态暂时都无法使用，
     * 因为这些组件都是需额外的配置
     */

    //    @Bean("druidStatViewServlet")
    public ServletRegistrationBean<StatViewServlet> getDruidStatViewServlet() {
        // druid的访问路径
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(),
                "/druid/*");
        //白名单
        // registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_ALLOW, "127.0.0.1");
        //黑名单
        // registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_DENY, "");
        //用户名
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_USERNAME, "admin");
        //密码
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_PASSWORD, "admin");
        //允许重置
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_RESET_ENABLE, "true");
        return registrationBean;
    }

    /**
     * 2. 添加Druid对web的访问控制
     */
    //    @Bean("webStatFilter")
    public WebStatFilter getWebStatFilter() {
        WebStatFilter statFilter = new WebStatFilter();
        // 对session进行监控
        statFilter.setSessionStatEnable(true);
        return statFilter;
    }

    // 配置好了一个web监控的处理
    //    @Bean
    //    @DependsOn("webStatFilter")
    public FilterRegistrationBean<WebStatFilter> getDruidStatViewServlet(WebStatFilter webStatFilter) {
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(webStatFilter);
        // 对所有的路径都进行监控配置
        registrationBean.addUrlPatterns("/*");
        // 排除路径
        registrationBean.addInitParameter(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.gif,/druid/*");
        return registrationBean;
    }

}
