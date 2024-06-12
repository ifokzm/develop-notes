> 客户端版本种类
1. es提供
   1. **TransportClient**
      传统的客户端,基于TCP传输协议与Elasticsearch通信。(已经被弃用,不推荐使用)
      适用于Elasticsearch 5.x及以前的版本
      因为Elasticsearch 6.x及以上版本已不再支持TCP Transport协议,TransportClient无法连接Elasticsearch集群。
   2. **RestHighLevelClient**
      是一个高级的REST客户端,主要用于与Elasticsearch集群通信。
      基于Java Low Level REST Client构建,提供更高级的API,隐藏底层细节。
      主要用于Kibana和Logstash等工具。
      RestHighLevelClient适用于Elasticsearch 6.2.0及以上版本。
      
2. spring提供
   1. **ES存储库操作：简单的CRUD**
      特点
      当你需要进行简单的CRUD操作（创建、读取、更新、删除文档）时，使用ES存储库操作是一个不错的选择。
      这是因为Spring Data Elasticsearch会自动生成存储库接口的实现，从而使你的代码更简洁、易于理解。
   2. **ElasticsearchTemplate**
      基于ElasticsearchRestTemplate封装,提供更高级的API,如各种CRUD操作。
      也需要依赖Spring Framework。
   3. **ElasticsearchRestTemplate**
      是一个Spring的RestTemplate的扩展,用于与Elasticsearch RESTful API交互。
      基于Spring的RestTemplate,提供Elasticsearch的自动化JSON序列化和反序列化。
      需要依赖Spring Framework。
   4. **ElasticsearchOperations**：接口实现
      使用选择
      1. 如果需要更多spring集成相关的功能,则使用ElasticsearchTemplate。
      2. 如果需要直接访问Elasticsearch RESTful接口,则使用ElasticsearchRestTemplate。
      3. 性能和功能上,ElasticsearchTemplate略胜一筹。
      
3. ElasticsearchTemplate和ElasticsearchRestTemplate主要的区别在于
   1. 底层实现:
   ElasticsearchTemplate 底层使用 Elasticsearch Java API 实现。
   ElasticsearchRestTemplate 底层使用 Elasticsearch Rest API 实现。
   2. 使用范围:
   ElasticsearchTemplate 只能在内部使用,不支持跨节点操作。
   ElasticsearchRestTemplate 支持跨节点操作。
   3. 功能:
   ElasticsearchTemplate 功能更全面,支持多种操作,如索引、搜索、删除等。
   ElasticsearchRestTemplate 主要用于搜索。
   4. 性能: 
   ElasticsearchTemplate 性能更高,不需要经过 HTTP 请求。
   ElasticsearchRestTemplate 需要通过 HTTP 请求,性能相对较低。
   5. 复杂度:
   ElasticsearchTemplate 复杂度较高,需要深入理解 Elasticsearch Java API。
   ElasticsearchRestTemplate 复杂度较低,只需要理解 Elasticsearch 的 REST API。
总的来说:
    如果只在单个节点上操作 Elasticsearch,优先选择 ElasticsearchTemplate。
    如果需要跨节点操作,或者为了简单起见,可以选择 ElasticsearchRestTemplate。