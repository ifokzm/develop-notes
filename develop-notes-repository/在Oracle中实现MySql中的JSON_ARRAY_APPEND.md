参见:[在Oracle中实现MySql中的JSON_ARRAY_APPEND](https://blog.csdn.net/zxx862309181/article/details/127357898)
### 在Oracle中实现MySql中的JSON_ARRAY_APPEND

```
<!-- 修改前xml-->
UPDATE supp_record
    <set>
      <if test="ossJson != null">
        OSS_JSON =
        case
        when OSS_JSON is null
        then JSON_ARRAY_APPEND('[]', '$', JSON_MERGE('{}', #{ossJson}))
        else JSON_ARRAY_APPEND(OSS_JSON, '$', JSON_MERGE('{}', #{ossJson})) end, 
      </if>
      <if test="dtUpdated != null">
        DT_UPDATED = #{dtUpdated} ,
      </if>
      VERSION = version+1
    </set>
```
> 特别注意：'$'指的是info字段本身，也可以指定第几项

> 特别注意：下标不能是负数，会报错，不能超过原本json数量，会被忽略


```
UPDATE supp_record
SET
        OSS_JSON =
        case
        when OSS_JSON is null
        then JSON_ARRAY_APPEND('[]', '$', JSON_MERGE('{}', ''))
        else JSON_ARRAY_APPEND(OSS_JSON, '$', JSON_MERGE('{}', '')) end, 
        DT_UPDATED = '' ,
        VERSION = version+1
```

```
<!-- 修改后xml-->
UPDATE supp_record
    <set>
      <if test="ossJson != null">
        OSS_JSON =
        case
        when OSS_JSON is null
        <!-- 修改前
        这里因为仅需要往[]追加值，所以直接使用 || 对结果进行拼接
        then JSON_ARRAY_APPEND('[]', '$', JSON_MERGE('{}', #{ossJson}))
        这里因为涉及到对已有json数据的修改，所以根据原本的意思去掉头尾的[]和{}之后直接对数据进行追加和拼接
        else JSON_ARRAY_APPEND(OSS_JSON, '$', JSON_MERGE('{}', #{ossJson})) end, 
         -->
        then '[{' || #{ossJson} ||'}]'
        else '[{'||SUBSTR(OSS_JSON, 3,LENGTH(OSS_JSON)-4) || #{ossJson} ||'}]' end,
      </if>
      <if test="dtUpdated != null">
        DT_UPDATED = #{dtUpdated} ,
      </if>
      VERSION = version+1
    </set>
```