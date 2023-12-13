> **目录**

Commons Lang3 概述

Commons lang3 常用工具类

StringUtils 工具类方法汇总

RandomUtils 工具类方法汇总

RandomStringUtils 随机字符串

ObjectUtils 对象工具类

NumberUtils 数值工具类

ArrayUtils 通用数组操作工具类

ExceptionUtils 异常工具类

Pair对象对 与 Triple 三元对象组
————————————————
版权声明：本文为CSDN博主「蚩尤后裔」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
[原文链接](https://blog.csdn.net/wangmx1993328/article/details/102488632/)


> **Commons Lang3 概述**

+ 1、标准 java 库无法提供足够的方法来操作其核心类，apache commons lang 子项目提供了这些额外的方法。特别是字符串操作方法、基本数值方法、对象反射、并发、创建和序列化以及系统属性。经理再也不用担心空指针异常。此外 lang 还还包含对 java.util.date 的基本增强，以及一系列专门用于帮助构建方法的实用程序，如 hasHcode、toString 和 equals。
+ 2、lang3.0（及其后续版本）使用的包（org.apache.commons.lang3）与以前的版本（org.apache.commons.lang）不同，允许它与以前的版本同时使用。
+ 3、Apache Commons Lang3 子项目官网地址：Lang – Home
+ 4、Apache Commons Lang3 二进制包下载地址：Lang – Download Apache Commons Lang
+ 5、Apache Commons Lang3 Maven 依赖：

```
<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
<dependency>
  <groupId>org.apache.commons</groupId>
  <artifactId>commons-lang3</artifactId>
  <version>3.9</version>
</dependency>
```
+ 6、Apache Commons Lang 3.9 API 文档：[Apache Commons Lang 3.11 API](https://commons.apache.org/proper/commons-lang/javadocs/api-release/index.html)

> **Commons lang3 常用工具类**

| 工具类 | 描述 | 示例 |
| --------| --------| --------|
| org.apache.commons.lang3.ObjectUtils | 对象工具类 | ObjectUtils.isNotEmpty(new int[]{}) = false |
| org.apache.commons.lang3.math.NumberUtils | 数值工具类 | NumberUtils.toInt(null) = 0，NumberUtils.toInt("1") = 1 |
| org.apache.commons.lang3.ArrayUtils |	数组工具类 | ArrayUtils.remove([1, 0], 1) = [1] |
| org.apache.commons.lang3.BooleanUtils	| 布尔工具类 | BooleanUtils.toInteger(true) = 1，BooleanUtils.toBoolean(1) = Boolean.TRUE |
| org.apache.commons.lang3.RandomStringUtils | 随机字符串工具类 | RandomStringUtils.randomAlphabetic(10) = "CtDdCZEldF"，RandomStringUtils.randomGraph(10) = #vdP\eCl@F |
| org.apache.commons.lang3.RandomUtils | 随机数值工具类 | RandomUtils.nextBoolean()，RandomUtils.nextInt(100,1000) |
| org.apache.commons.lang3.SystemUtils | 系统工具类 | SystemUtils.getUserHome() = "C:\Users\Think" |
| org.apache.commons.lang3.time.DateFormatUtils | 日期格式化工具类，将日期转为指定格式的字符串 | DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:dd") = "2019-11-11 11:11:11" | 
| org.apache.commons.lang3.time.DateUtils | 日期工具类，将指定格式的字符串转为日期	| DateUtils.parseDate("1993-09-08 14:30:08","yyyy-MM-dd HH:mm:dd") |
