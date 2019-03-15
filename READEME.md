# **基础组件web层**
> 使用方式（并未在中央仓库中存放，需要存放在私服）
```java
<dependency>
    <groupId>com.dodo.project.base.web</groupId>
    <artifactId>dodo-project-base-web</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
## **实现主要功能**
1. 封装一些常用的获取参数的方法。
2. 对fastjson进行二次封装。
3. 封装日期操作常用工具类。
4. 基于jfinal对所有response进行统一处理。

### **支持输出类型**
1. 支持html输出。 
2. 支持文件输出。
3. 支持json输出。
4. 支持jsp输出。
5. 支持freeMarker输出。
6. 支持文本数据。
