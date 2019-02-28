# my_spark_project

this is a simple demo for spark learning. 
I hope this repository can show you something you never seen before.


这是一个某电商网站spark模块的一个比较大的demo. 这个demo可以分为两部分。

一部分就是传统的java web中的内容，涉及到的知识点是jdbc, domian,dao层一些相关知识，比如说原生数据库连接池的设计（LinkedList的使用，push,pop），单例模式等知识点，这里没有用spring mvc, hibernate这些java web框架，因为我们这个只侧重spark模块。

另一部分是spark模块，其下又包括4个子模块，其分别是：
1	对用户访问session进行分析
  (1)	对这些数据按照session粒度进行聚合，就是以sessionid作为key, 主要用到的算子是mapToPair
  (2)	对不满足条件的session进行过滤掉，比如不在指定日期范围内的session,主要用到的算子是filter(return true的时候是需要保留下来的, return false是去掉的)
  (3)	统计出各个范围内的session（比如访问时长在1-3内的session占总session的比例，访问步长）占总session的比例, 这个部分主要用到了spark中提供的累加器的功能，我们自定义了一个累加器。
  (4)	统计出排名前10的商品品类。主要用到的技术是自定义一个key, 这个key里边实现自己的排序逻辑，且这个自定义的key实现了ordered和serialized序列化接口，然后sortedByKey，最后take就行。主要用到的算子有filter, reduceByKey, sortByKey,
  Take。
  (5)	对于第4步计算出来的top 10的商品品类，计算出每个品类中，对其贡献最大的10个session（即分组取topN）, 主要用到的算子是groupByKey, Join等算子
2	广告点击流量实时统计, 用spark streaming来实现
  (1)	按照黑名单进行过滤，动态生成黑名单，主要用到的算子是filter
  (2)	计算广告点击流量实时统计，主要用到的算子是updateStateByKey
  (3)	实时统计每天各个省份top3热门广告，主要实现思路是将信息注册为spark sql临时表，然后通过spark sql语句进行查询
  (4)	计算最近1小时内滑动窗口内的广告点击趋势，主要通过reduceByKeyAndWindow这个算子来实现。

3	页面单跳转化率计算模块
  这个模块是根据用户指定的页面流，比如2->3->4->5>8，计算它们之间的转化率，比如浏览量页面2的用户有100，而从2页面直接到页面3的用户只有50，那么转化率是50%。主要用到的算子是flatMapToPair。
4	各区域top3热门商品统计spark作业
  这个实现思路是将相关信息注册为spark sql临时表，然后通过spark sql语句进行查找。
