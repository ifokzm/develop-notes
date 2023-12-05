# module description
service 模块，对应 MVC 的 M 概念，存放核心业务逻辑代码
###  去 Druid Ad（广告）
访问监控页面的时候，你可能会在页面底部（footer）看到阿里巴巴的广告

原因：引入的druid的jar包中的common.js(里面有一段js代码是给页面的footer追加广告的)

如果想去掉，有两种方式：

(1) 直接手动注释这段代码

如果是使用Maven，直接到本地仓库中，查找这个jar包

要注释的代码：
```
// this.buildFooter();
```

common.js的位置：
```
com/alibaba/druid/1.1.23/druid-1.1.23.jar!/support/http/resources/js/common.js
```
(2) 使用过滤器过滤

注册一个过滤器，过滤common.js的请求，使用正则表达式替换相关的广告内容

```
@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(DruidDataSourceAutoConfigure.class)
@ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled",
havingValue = "true", matchIfMissing = true)
public class RemoveDruidAdConfig {

    /**
    * 方法名: removeDruidAdFilterRegistrationBean
    * 方法描述 除去页面底部的广告
    * @param properties com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties
    * @return org.springframework.boot.web.servlet.FilterRegistrationBean
    */
    @Bean
    public FilterRegistrationBean removeDruidAdFilterRegistrationBean(DruidStatProperties properties) {

        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");

        final String filePath = "support/http/resources/js/common.js";

        //创建filter进行过滤
        Filter filter = new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {}

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                chain.doFilter(request, response);
                // 重置缓冲区，响应头不会被重置
                response.resetBuffer();
                // 获取common.js
                String text = Utils.readFromResource(filePath);
                // 正则替换banner, 除去底部的广告信息
                text = text.replaceAll("<a.*?banner\"></a><br/>", "");
                text = text.replaceAll("powered.*?shrek.wang</a>", "");
                response.getWriter().write(text);
            }

            @Override
            public void destroy() {}
        };

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }
}

```

两种方式都可以，建议使用的是第一种，从根源解决

### 遇到问题
SpringBoot整合mybatis-plus异常：Unsatisfied dependency expressed through field 'mapper'解决办法
报错信息截取：

Unsatisfied dependency expressed through field 'baseMapper'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean

解决办法：springboot启动类上加 @MapperScan();注解，如下

@SpringBootApplication
@MapperScan(value = "com.xxxxx.xxxx.mapper")
public class ManagesysApplication {

注：网上还有一种写法：就是在mapper接口上添加@Mapper注解，然而这种写法并不能解决扫包不全的问题。用上面的做法，指定扫描路径即可。


### ShardingSphere知识点梳理 
https://baijiahao.baidu.com/s?id=1751996369188686304&wfr=spider&for=pc   