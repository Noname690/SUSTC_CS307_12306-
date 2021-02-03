# SUSTC_CS307_12306购票系统

**张淘月 815869083@qq.com**

---------------------

#### 项目介绍

本项目为南方科技大学数据库原理课程项目，使用springboot作为后端框架，vue作为前端框架postgre数据库实现的全国火车票查询及购买系统。主要目的为练习数据库包括**连接池**、**事务处理**、**index**及**trigger**和**procedure**的使用。

- 使用proxool连接池处理并发请求
- 使用transaction进行事务处理并提高隔离级别防止幻读
- 使用数据库本身的index对查询进行加速
- 使用before trigger防止数据的错误
