# start
入口模块，引导工程启动以及基础配置

[spring boot 2.x 技术参考文档](http://static.kancloud.cn/kim_gao/spring-boot) 

> 阿里云服务器
1. 地址：121.199.33.29  
2. 登录：ssh root@121.199.33.29 -p 22


> 开发框架
 1. spring boot 2.5.8
 2. jdk 11+
 3. mysql 8.0+
 4. influxdb 2.7.6
 5. redis（未安装）
 6. netty 4.1.42.Final


> 代码库
 1. [develop-notes](https://github.com/ifokzm/develop-notes)
 2. 分支：feat/612


> 账号
 1. mysql
    ```
       用户名： wind
    ```
 2. influxdb
    ```
       管理界面：http://121.199.33.29:8086
       用户名：admin
    ```

> 项目启动方式

```
nohup java -jar develop-notes.jar > notes-log.out &
```