# module description
service 模块，对应 MVC 的 M 概念，存放核心业务逻辑代码
+ (1)在springboot项目的配置文件中添加nacos配置信息
配置nacos地址及命名空间，如果新创建了命名空间，需要指定一下命名空间id
```yaml
nacos.config.server-addr=http://192.168.8.178:8848
#nacos.config.auto-refresh=true
nacos.config.namespace=5776702f-a25a-42f4-89d9-31114cfe160f
```

+ (2)在启动类中添加引入的nacos配置文件
通过NacosPropertySource注解，指定要引入配置文件的dataId及设置自动刷新为true。

```yaml
package com.example.demo;
 
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
 
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@NacosPropertySource(dataId = "application",autoRefreshed = true)
@NacosPropertySource(dataId = "demo1",autoRefreshed = true)
@EnableScheduling
public class DemoApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
 
}
```

+  (3)在程序中引入配置文件中的项目值
在程序中通过NacosValue引入配置文件中的项目内容，通过value属性指定项目id并设置自动刷新
```yaml
    @NacosValue(value = "${server.name}",autoRefreshed = true)
    private String name;
    @NacosValue(value = "${student.id}",autoRefreshed = true)
    private String idInfo;
    @NacosValue(value="${student.name}",autoRefreshed = true)
    private String nameInfo;
```
### Nacos配置中心-自动刷新的注意点
    ①必须在启动类中引入配置文件时，设置自动刷新为true。
    ②必须在程序引用配置文件中的项目时，设置自动刷新为true。
    ③配置文件中的自动刷新可以不进行设置。