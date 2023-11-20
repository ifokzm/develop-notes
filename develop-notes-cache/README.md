# module description
cache 模块，对应 redis

```
参见: https://blog.csdn.net/qq_41853447/article/details/113095144
```

### 一、引入依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
如果使用springboot，那么这个依赖就不用指定版本号了。


### 二、位置信息操作
* 2.1 引入redis操作模板
```
@Autowired
private RedisTemplate redisTemplate;
```

* 2.2 向redis添加单个位置
```
redisTemplate.boundGeoOps(K key)
                .add(Point var1, M var2);
```

```
key：集合名称
var1：位置信息
var2：位置名称
```
* 范例
```
// x:经度，Y:维度
Point point = new Point(106.63658, 26.653324);
// outlets 集合名称
redisTemplate.boundGeoOps("outlets")
            .add(point, "这个位置的名称");
```
批量添加位置信息


